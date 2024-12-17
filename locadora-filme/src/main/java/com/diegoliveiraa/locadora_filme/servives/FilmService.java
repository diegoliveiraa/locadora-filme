package com.diegoliveiraa.locadora_filme.servives;

import com.diegoliveiraa.locadora_filme.dtos.FilmDTO;
import com.diegoliveiraa.locadora_filme.entitys.Film;
import com.diegoliveiraa.locadora_filme.repositories.FilmRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FilmService {

    @Autowired
    private FilmRepository filmRepository;

    public Film createFilm(FilmDTO data) {

        Film newFilm = new Film(data);

        this.filmRepository.save(newFilm);

        return newFilm;
    }

    public List<Film> getAllFilm() {
        return this.filmRepository.findAll();
    }

    public Film updateFilm(FilmDTO data) {
        Film updateFilm = this.findById(data.id());

        updateFilm.setTitle(data.title());
        updateFilm.setGender(data.gender());
        updateFilm.setReleaseDate(data.releaseDate());
        updateFilm.setFilmType(data.filmType());
        updateFilm.setQuantity(data.quantity());
        updateFilm.setLocationPrice(data.locationPrice());

        this.filmRepository.save(updateFilm);

        return updateFilm;
    }

    public void deleteFilm(String id) {
        Film deleteFilm = this.findById(id);

        this.filmRepository.delete(deleteFilm);
    }

    public Film findByTitle(String title) {
        return this.filmRepository.findByTitle(title)
                .orElseThrow(() -> new RuntimeException("Film not found!"));
    }

    public Film findById(String id) {
        return this.filmRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Film not found"));
    }

    public void saveFilm(Film data) {
        this.filmRepository.save(data);
    }
}
