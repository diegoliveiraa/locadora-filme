package com.diegoliveiraa.locadora_filme.entitys;

import com.diegoliveiraa.locadora_filme.infra.BaseEntity;
import com.diegoliveiraa.locadora_filme.infra.ClientStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

import java.util.Date;

@Entity(name = "clients")
@Table(name = "clients")
public class Client extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String firstname;
    private String lastname;
    @Column(unique = true, length = 11)
    @NotBlank
    @Pattern(regexp = "\\d{11}", message = "Document must have exactly 11 digits")
    private String document;
    private String phone;
    @Enumerated(EnumType.STRING)
    private ClientStatus clientStatus;
    private String cep;
    private String address;
    private String number;
    private String complement;
    private String city;
    private Date birthday;

    public Client(String id, String firstname, String lastname, @NotBlank @Pattern(regexp = "\\d{11}", message = "Document must have exactly 11 digits") String document, String phone, ClientStatus clientStatus, String cep, String address, String number, String complement, String city, Date birthday) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.document = document;
        this.phone = phone;
        this.clientStatus = clientStatus;
        this.cep = cep;
        this.address = address;
        this.number = number;
        this.complement = complement;
        this.city = city;
        this.birthday = birthday;
    }

    public Client() {
    }

    public String getId() {
        return this.id;
    }

    public String getFirstname() {
        return this.firstname;
    }

    public String getLastname() {
        return this.lastname;
    }

    public @NotBlank @Pattern(regexp = "\\d{11}", message = "Document must have exactly 11 digits") String getDocument() {
        return this.document;
    }

    public String getPhone() {
        return this.phone;
    }

    public ClientStatus getClientStatus() {
        return this.clientStatus;
    }

    public String getCep() {
        return this.cep;
    }

    public String getAddress() {
        return this.address;
    }

    public String getNumber() {
        return this.number;
    }

    public String getComplement() {
        return this.complement;
    }

    public String getCity() {
        return this.city;
    }

    public Date getBirthday() {
        return this.birthday;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public void setDocument(@NotBlank @Pattern(regexp = "\\d{11}", message = "Document must have exactly 11 digits") String document) {
        this.document = document;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setClientStatus(ClientStatus clientStatus) {
        this.clientStatus = clientStatus;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public void setComplement(String complement) {
        this.complement = complement;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof Client)) return false;
        final Client other = (Client) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$id = this.getId();
        final Object other$id = other.getId();
        if (this$id == null ? other$id != null : !this$id.equals(other$id)) return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof Client;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $id = this.getId();
        result = result * PRIME + ($id == null ? 43 : $id.hashCode());
        return result;
    }
}