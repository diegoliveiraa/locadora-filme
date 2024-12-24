package com.diegoliveiraa.locadora_filme.controllers;

import com.diegoliveiraa.locadora_filme.dtos.FilmDTO;
import com.diegoliveiraa.locadora_filme.entitys.Film;
import com.diegoliveiraa.locadora_filme.servives.FilmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/films")
public class FilmCotroller {
    @Autowired
    private FilmService filmService;

    @PostMapping
    public ResponseEntity<Film> createFilm(@RequestBody FilmDTO data) {

        Film newFilm = this.filmService.createFilm(data);
        return new ResponseEntity<>(newFilm, HttpStatus.CREATED);
    }

    @PostMapping("/update-film")
    public ResponseEntity<Film> updateFilm(@RequestBody FilmDTO data) {

        Film updateFilm = this.filmService.updateFilm(data);
        return new ResponseEntity<>(updateFilm, HttpStatus.OK);
    }

    @RequestMapping
    public ResponseEntity<List<Film>> searchAllFilm() {

        List<Film> allFilm = this.filmService.getAllFilm();
        return new ResponseEntity<>(allFilm, HttpStatus.OK);
    }

    @RequestMapping("/search-film")
    public ResponseEntity<Film> searchFilm(@RequestBody String title, @RequestBody String gender) {

        Film searchFilm = this.filmService.findByTitleOrGender(title, gender);
        return new ResponseEntity<>(searchFilm, HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<Film> deleteFilm(@RequestBody String id) {
        Film deleteFilm = this.filmService.deleteFilm(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
