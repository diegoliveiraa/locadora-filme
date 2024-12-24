package com.diegoliveiraa.locadora_filme.entitys;

import com.diegoliveiraa.locadora_filme.infra.BaseEntity;
import com.diegoliveiraa.locadora_filme.infra.DevolutionStatus;
import com.diegoliveiraa.locadora_filme.infra.PaymentStatus;
import com.diegoliveiraa.locadora_filme.infra.PaymentType;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "locations")
@Table(name = "locations")
public class Location extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User userId;

    @ManyToOne
    @JoinColumn(name = "renter_id", nullable = false)
    private Renter renterId;

    @OneToMany(mappedBy = "location", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnoreProperties("location")
    private List<LocationFilm> locationFilms = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    private PaymentType paymentType;

    @Enumerated(EnumType.STRING)
    private PaymentStatus paymentStatus;

    @Enumerated(EnumType.STRING)
    private DevolutionStatus devolutionStatus;

    private BigDecimal totalPayment;

    private LocalDateTime dateDevolution;

    public Location(String id, User userId, Renter renterId, List<LocationFilm> locationFilms, PaymentType paymentType, PaymentStatus paymentStatus, DevolutionStatus devolutionStatus, BigDecimal totalPayment, LocalDateTime dateDevolution) {
        this.id = id;
        this.userId = userId;
        this.renterId = renterId;
        this.locationFilms = locationFilms;
        this.paymentType = paymentType;
        this.paymentStatus = paymentStatus;
        this.devolutionStatus = devolutionStatus;
        this.totalPayment = totalPayment;
        this.dateDevolution = dateDevolution;
    }

    public Location() {
    }

    public String getId() {
        return this.id;
    }

    public User getUserId() {
        return this.userId;
    }

    public Renter getRenterId() {
        return this.renterId;
    }

    public List<LocationFilm> getLocationFilms() {
        return this.locationFilms;
    }

    public PaymentType getPaymentType() {
        return this.paymentType;
    }

    public PaymentStatus getPaymentStatus() {
        return this.paymentStatus;
    }

    public BigDecimal getTotalPayment() {
        return this.totalPayment;
    }

    public LocalDateTime getDateDevolution() {
        return this.dateDevolution;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setUserId(User userId) {
        this.userId = userId;
    }

    public void setRenterId(Renter renterId) {
        this.renterId = renterId;
    }

    @JsonIgnoreProperties("location")
    public void setLocationFilms(List<LocationFilm> locationFilms) {
        this.locationFilms = locationFilms;
    }

    public void setPaymentType(PaymentType paymentType) {
        this.paymentType = paymentType;
    }

    public void setPaymentStatus(PaymentStatus paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public DevolutionStatus getDevolutionStatus() {
        return devolutionStatus;
    }

    public void setDevolutionStatus(DevolutionStatus devolutionStatus) {
        this.devolutionStatus = devolutionStatus;
    }

    public void setTotalPayment(BigDecimal totalPayment) {
        this.totalPayment = totalPayment;
    }

    public void setDateDevolution(LocalDateTime dateDevolution) {
        this.dateDevolution = dateDevolution;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof Location)) return false;
        final Location other = (Location) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$id = this.getId();
        final Object other$id = other.getId();
        if (this$id == null ? other$id != null : !this$id.equals(other$id)) return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof Location;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $id = this.getId();
        result = result * PRIME + ($id == null ? 43 : $id.hashCode());
        return result;
    }
}
