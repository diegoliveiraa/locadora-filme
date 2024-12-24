package com.diegoliveiraa.locadora_filme.repositories;

import com.diegoliveiraa.locadora_filme.entitys.Film;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FilmRepository extends JpaRepository<Film, String> {
    Optional<Film> findByTitleOrGender(String title, String gender);

    Optional<Film> findById(String id);
}
