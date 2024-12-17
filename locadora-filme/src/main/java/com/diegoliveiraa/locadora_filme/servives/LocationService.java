package com.diegoliveiraa.locadora_filme.servives;

import com.diegoliveiraa.locadora_filme.dtos.LocationDTO;
import com.diegoliveiraa.locadora_filme.dtos.LocationFilmDTO;
import com.diegoliveiraa.locadora_filme.entitys.*;
import com.diegoliveiraa.locadora_filme.repositories.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public Location createLocation(LocationDTO data) throws Exception {

        User user = this.userService.findById(data.user());
        Renter renter = this.renterService.findById(data.renter());
        this.updateSelectedFilm(data.film());

        Location newLocation = new Location();
        newLocation.setUser(user);
        newLocation.setRenter(renter);

        List<LocationFilm> locationFilms = data.film().stream()
                .map(locationFilmDTO -> {
                    Film film = null;
                    try {
                        film = filmService.findById(locationFilmDTO.film());
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                    LocationFilm locationFilm = new LocationFilm();
                    locationFilm.setLocation(newLocation);
                    locationFilm.setFilm(film);
                    locationFilm.setQuantityLocated(locationFilmDTO.quantityLocated());

                    return locationFilm;
                })
                .toList();

        newLocation.setLocationFilms(locationFilms);

        this.locationRepository.save(newLocation);

        return newLocation;
    }

    public void updateSelectedFilm(List<LocationFilmDTO> dataFilm) throws Exception {

        for (LocationFilmDTO filmData : dataFilm) {
            Film film = this.filmService.findById(filmData.film());
            if (film.getQuantity() < filmData.quantityLocated()) {
                throw new IllegalAccessException("Insuficient quantity for film" + film.getTitle());
            }

            film.setQuantity(film.getQuantity() - filmData.quantityLocated());

            this.filmService.saveFilm(film);

        }
    }

    public List<Location> getAllLocation() {
        return this.locationRepository.findAll();
    }

    public Location findLocationById(String id) throws Exception {
        return this.locationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Location not found!"));
    }

    public Location devolutionLocation(LocationDTO data) throws Exception {

        Location updateState = this.findLocationById(data.id());
        this.updateDevolutionFilm(data.film());

        updateState.setPaymentType(data.paymentType());
        updateState.setPaymentStatus(data.paymentStatus());
        updateState.setDevolutionStatus(data.devolutionStatus());
        updateState.setTotalPayment(data.totalPayment());

        this.locationRepository.save(updateState);

        return updateState;
    }

    public void updateDevolutionFilm(List<LocationFilmDTO> dataFilm) throws Exception {
        for (LocationFilmDTO filmData : dataFilm) {
            Film film = this.filmService.findById(filmData.film());

            film.setQuantity(film.getQuantity() + filmData.quantityLocated());

            this.filmService.saveFilm(film);
        }
    }


}
