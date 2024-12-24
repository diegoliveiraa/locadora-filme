package com.diegoliveiraa.locadora_filme.dtos;

import com.diegoliveiraa.locadora_filme.entitys.Location;
import com.diegoliveiraa.locadora_filme.infra.DevolutionStatus;
import com.diegoliveiraa.locadora_filme.infra.FilmStatus;

public record LocationFilmDTO(String filmId, Location locationId, DevolutionStatus devolutionStatus, FilmStatus filmStatus, Long quantityLocated) {
}