package com.diegoliveiraa.locadora_filme.dtos;

import com.diegoliveiraa.locadora_filme.infra.FilmType;

import java.math.BigDecimal;
import java.time.LocalDate;

public record FilmDTO(String id, String title, String gender, LocalDate releaseDate
        , FilmType filmType, Long quantity, BigDecimal locationPrice) {
}