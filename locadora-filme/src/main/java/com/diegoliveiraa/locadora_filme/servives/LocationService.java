package com.diegoliveiraa.locadora_filme.servives;

import com.diegoliveiraa.locadora_filme.dtos.LocationDTO;
import com.diegoliveiraa.locadora_filme.dtos.LocationFilmDTO;
import com.diegoliveiraa.locadora_filme.entitys.*;
import com.diegoliveiraa.locadora_filme.infra.DevolutionStatus;
import com.diegoliveiraa.locadora_filme.infra.FilmStatus;
import com.diegoliveiraa.locadora_filme.repositories.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

@Service
public class LocationService {

    @Autowired
    private LocationRepository locationRepository;

    @Autowired
    private RenterService renterService;

    @Autowired
    private FilmService filmService;

    @Autowired
    private UserService userService;

    @Value("${location.max-films-per-renterId}")
    private int maxFilmsPerRenter;

    @Value("${location.default-penalty}")
    private BigDecimal penaltyTax;

    public Location createLocation(LocationDTO data) throws Exception {

        User user = this.userService.findById(data.userId());

        this.renterService.checkRenterStatus(data.renterId());
        this.validateMaxFilmsPerRenter(data);

        Renter renter = this.renterService.findById(data.renterId());

        this.updateSelectedFilm(data.film());

        Location newLocation = new Location();
        newLocation.setUserId(user);
        newLocation.setRenterId(renter);

        List<LocationFilm> locationFilms = data.film().stream()
                .map(locationFilmDTO -> {
                    Film film = null;
                    try {
                        film = filmService.findById(locationFilmDTO.filmId());
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                    LocationFilm locationFilm = new LocationFilm();
                    locationFilm.setLocationId(newLocation);
                    locationFilm.setFilmId(film);
                    locationFilm.setDevolutionStatus(locationFilmDTO.devolutionStatus());
                    locationFilm.setQuantityLocated(locationFilmDTO.quantityLocated());

                    return locationFilm;
                })
                .toList();

        newLocation.setLocationFilms(locationFilms);
        newLocation.setPaymentType(data.paymentType());
        newLocation.setPaymentStatus(data.paymentStatus());
        newLocation.setDevolutionStatus(data.devolutionStatus());
        newLocation.setTotalPayment(data.totalPayment());
        newLocation.setDateDevolution(data.dateDevolution());

        this.locationRepository.save(newLocation);

        return newLocation;
    }

    public void validateMaxFilmsPerRenter(LocationDTO data) {
        Location existingLocation = this.findLocationByRenter(data.renterId());

        if (existingLocation.getLocationFilms().size() >= maxFilmsPerRenter) {
            throw new IllegalArgumentException(
                    "The renterId '" + data.renterId() + " reached the maximum limit of "
                            + maxFilmsPerRenter + " leased films."
            );
        }
    }

    //checa se o filme esta alugado, senao configura como alugado.
    public void updateSelectedFilm(List<LocationFilmDTO> dataFilm) throws Exception {
        for (LocationFilmDTO filmData : dataFilm) {
            Film film = this.filmService.findById(filmData.filmId());

            if (film.getFilmStatus() == FilmStatus.LEASED) {
                throw new IllegalArgumentException("Film" + film.getTitle() + "' is leased.");
            } else {

                film.setFilmStatus(FilmStatus.LEASED);
                this.filmService.saveFilm(film);
            }
        }
    }

    public List<Location> getAllLocation() {
        return this.locationRepository.findAll();
    }

    //busca de filmes alugados e atrasados
    public List<Film> findOverdueLocations(LocationDTO data) {
        LocalDateTime currentDate = LocalDateTime.now();
        List<Location> overdueLocations = this.locationRepository
                .findOverdueLocations(data.devolutionStatus().name(), currentDate);
        List<Film> overdueFilms = new ArrayList<>();

        for (Location location : overdueLocations) {
            for (LocationFilm locationFilm : location.getLocationFilms()) {
                overdueFilms.add(locationFilm.getFilmId());
            }
        }

        return overdueFilms;
    }

    //busca da locacao especifica por id
    public Location findLocationById(String id) throws Exception {
        return this.locationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Location not found!"));
    }

    //Busca do locador
    public Location findLocationByRenter(String renter) {
        return this.locationRepository.findLocationByRenter(renter)
                .orElseThrow(() -> new RuntimeException("Renter not found!"));
    }

    // Devolução do filme
    public Location devolutionLocation(LocationDTO data) throws Exception {
        User user = this.userService.findById(data.userId());

        this.validateMaxDateDevolution(data);

        Renter renter = this.renterService.findById(data.renterId());
        Location updateState = this.findLocationById(data.id());

        // Atualiza o status dos filmes e dos LocationFilms
        for (LocationFilmDTO locationFilmDTO : data.film()) {
            this.updateFilm(locationFilmDTO);
            this.updateLocationFilm(updateState, locationFilmDTO);
        }

        // Verifica se todos os filmes associados foram devolvidos
        boolean allFilmsReturned = updateState.getLocationFilms().stream()
                .allMatch(film -> film.getDevolutionStatus() == DevolutionStatus.CLOSE);

        // Atualiza o status geral da locação
        if (allFilmsReturned) {
            updateState.setDevolutionStatus(DevolutionStatus.CLOSE);
        } else {
            updateState.setDevolutionStatus(DevolutionStatus.OPEN);
        }

        updateState.setPaymentType(data.paymentType());
        updateState.setPaymentStatus(data.paymentStatus());
        updateState.setTotalPayment(data.totalPayment());

        // Salva as atualizações no banco de dados
        this.locationRepository.save(updateState);

        return updateState;
    }

    //Verificacao de multa
    public void validateMaxDateDevolution(LocationDTO data) {
        Location location = this.findLocationByRenter(data.renterId());

        if (LocalDateTime.now().isAfter(location.getDateDevolution())) {

            long lateDays = ChronoUnit.DAYS.between(location.getDateDevolution(), LocalDateTime.now());

            BigDecimal currentTotal = location.getTotalPayment();
            BigDecimal penalty = currentTotal.multiply(penaltyTax).multiply(BigDecimal.valueOf(lateDays));
            BigDecimal updatedTotal = currentTotal.add(penalty);

            location.setTotalPayment(updatedTotal);

            throw new IllegalArgumentException(
                    "The renter'" + data.renterId() + "' has exceeded the maximum return date. "
                            + "A penalty of " + penaltyTax.multiply(BigDecimal.valueOf(100)) + "% has been applied."
            );
        }
    }

    //Atualizar o status do Film
    public void updateFilm(LocationFilmDTO dataFilm) throws Exception {

        Film film = this.filmService.findById(dataFilm.filmId());
        film.setFilmStatus(dataFilm.filmStatus());
        this.filmService.saveFilm(film);
    }

    //Atualizar o status do LocationFilm
    private void updateLocationFilm(Location updateState, LocationFilmDTO locationFilmDTO) {
        for (LocationFilm locationFilm : updateState.getLocationFilms()) {
            if (locationFilm.getFilmId().getId().equals(locationFilmDTO.filmId())) {
                locationFilm.setDevolutionStatus(locationFilmDTO.devolutionStatus());
            }
        }
    }

}
