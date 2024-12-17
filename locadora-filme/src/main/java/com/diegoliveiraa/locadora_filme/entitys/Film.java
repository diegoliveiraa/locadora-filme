package com.diegoliveiraa.locadora_filme.entitys;

import com.diegoliveiraa.locadora_filme.infra.BaseEntity;
import com.diegoliveiraa.locadora_filme.infra.FilmType;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "films")
@Table(name = "films")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Film extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String title;
    private String gender;
    private LocalDate releaseDate;
    @Enumerated(EnumType.STRING)
    private FilmType filmType;
    private Long quantity;
    private BigDecimal locationPrice;

    @OneToMany(mappedBy = "films", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<LocationFilm> locationFilms = new ArrayList<>();
}
