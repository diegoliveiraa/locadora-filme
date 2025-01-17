package com.diegoliveiraa.locadora_filme.dtos;

import com.diegoliveiraa.locadora_filme.infra.FilmStatus;
import com.diegoliveiraa.locadora_filme.infra.FilmType;

import java.math.BigDecimal;
import java.time.LocalDate;

public record FilmDTO(String id, String serialNumber, String title, String gender, LocalDate releaseDate
        , FilmType filmType, FilmStatus filmStatus, BigDecimal locationPrice) {
}