package com.diegoliveiraa.locadora_filme.entitys;

import com.diegoliveiraa.locadora_filme.infra.BaseEntity;
import com.diegoliveiraa.locadora_filme.infra.DevolutionStatus;
import jakarta.persistence.*;

@Entity(name = "locationFilm")
@Table(name = "locationFilms")
public class LocationFilm extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @ManyToOne
    @JoinColumn(name = "film_id", nullable = false)
    private Film filmId;

    @ManyToOne
    @JoinColumn(name = "location_id", nullable = false)
    private Location locationId;

    @Enumerated(EnumType.STRING)
    private DevolutionStatus devolutionStatus;

    @Column(name = "quantity_located", nullable = false)
    private Long quantityLocated;

    public LocationFilm(String id, Film filmId, Location locationId, DevolutionStatus devolutionStatus, Long quantityLocated) {
        this.id = id;
        this.filmId = filmId;
        this.locationId = locationId;
        this.quantityLocated = quantityLocated;
    }

    public LocationFilm() {
    }

    public String getId() {
        return this.id;
    }

    public Film getFilmId() {
        return this.filmId;
    }

    public Location getLocationId() {
        return this.locationId;
    }

    public DevolutionStatus getDevolutionStatus() {
        return this.devolutionStatus;
    }

    public Long getQuantityLocated() {
        return this.quantityLocated;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setFilmId(Film filmId) {
        this.filmId = filmId;
    }

    public void setLocationId(Location locationId) {
        this.locationId = locationId;
    }

    public void setDevolutionStatus(DevolutionStatus devolutionStatus) {
        this.devolutionStatus = devolutionStatus;
    }

    public void setQuantityLocated(Long quantityLocated) {
        this.quantityLocated = quantityLocated;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof LocationFilm)) return false;
        final LocationFilm other = (LocationFilm) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$id = this.getId();
        final Object other$id = other.getId();
        if (this$id == null ? other$id != null : !this$id.equals(other$id)) return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof LocationFilm;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $id = this.getId();
        result = result * PRIME + ($id == null ? 43 : $id.hashCode());
        return result;
    }
}
