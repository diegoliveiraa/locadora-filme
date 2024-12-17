package com.diegoliveiraa.locadora_filme.entitys;

import com.diegoliveiraa.locadora_filme.infra.BaseEntity;
import com.diegoliveiraa.locadora_filme.infra.FilmType;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "films")
@Table(name = "films")
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

    public Film(String id, String title, String gender, LocalDate releaseDate, FilmType filmType, Long quantity, BigDecimal locationPrice, List<LocationFilm> locationFilms) {
        this.id = id;
        this.title = title;
        this.gender = gender;
        this.releaseDate = releaseDate;
        this.filmType = filmType;
        this.quantity = quantity;
        this.locationPrice = locationPrice;
        this.locationFilms = locationFilms;
    }

    public Film() {
    }

    public String getId() {
        return this.id;
    }

    public String getTitle() {
        return this.title;
    }

    public String getGender() {
        return this.gender;
    }

    public LocalDate getReleaseDate() {
        return this.releaseDate;
    }

    public FilmType getFilmType() {
        return this.filmType;
    }

    public Long getQuantity() {
        return this.quantity;
    }

    public BigDecimal getLocationPrice() {
        return this.locationPrice;
    }

    public List<LocationFilm> getLocationFilms() {
        return this.locationFilms;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    public void setFilmType(FilmType filmType) {
        this.filmType = filmType;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

    public void setLocationPrice(BigDecimal locationPrice) {
        this.locationPrice = locationPrice;
    }

    public void setLocationFilms(List<LocationFilm> locationFilms) {
        this.locationFilms = locationFilms;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof Film)) return false;
        final Film other = (Film) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$id = this.getId();
        final Object other$id = other.getId();
        if (this$id == null ? other$id != null : !this$id.equals(other$id)) return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof Film;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $id = this.getId();
        result = result * PRIME + ($id == null ? 43 : $id.hashCode());
        return result;
    }
}
