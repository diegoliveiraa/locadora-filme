package com.diegoliveiraa.locadora_filme.entitys;

import com.diegoliveiraa.locadora_filme.infra.BaseEntity;
import com.diegoliveiraa.locadora_filme.infra.PaymentStatus;
import com.diegoliveiraa.locadora_filme.infra.PaymentType;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity(name = "locations")
@Table(name = "locations")
public class Location extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "client_id", nullable = false)
    private Client client;

    @OneToMany(mappedBy = "location", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnoreProperties("location")
    private List<LocationFilm> locationFilms = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    private PaymentType paymentType;

    @Enumerated(EnumType.STRING)
    private PaymentStatus paymentStatus;

    private BigDecimal totalPayment;

    private Date dateDevolution;

    public Location(String id, User user, Client client, List<LocationFilm> locationFilms, PaymentType paymentType, PaymentStatus paymentStatus, BigDecimal totalPayment, Date dateDevolution) {
        this.id = id;
        this.user = user;
        this.client = client;
        this.locationFilms = locationFilms;
        this.paymentType = paymentType;
        this.paymentStatus = paymentStatus;
        this.totalPayment = totalPayment;
        this.dateDevolution = dateDevolution;
    }

    public Location() {
    }

    public String getId() {
        return this.id;
    }

    public User getUser() {
        return this.user;
    }

    public Client getClient() {
        return this.client;
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

    public Date getDateDevolution() {
        return this.dateDevolution;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setClient(Client client) {
        this.client = client;
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

    public void setTotalPayment(BigDecimal totalPayment) {
        this.totalPayment = totalPayment;
    }

    public void setDateDevolution(Date dateDevolution) {
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
