package com.diegoliveiraa.locadora_filme.entitys;

import com.diegoliveiraa.locadora_filme.dtos.UserDTO;
import com.diegoliveiraa.locadora_filme.infra.BaseEntity;
import com.diegoliveiraa.locadora_filme.infra.UserType;
import jakarta.persistence.*;

import java.util.Date;

@Entity(name = "users")
@Table(name = "users")
public class User extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private String username;
    private String password;
    private String firstname;
    private String lastname;

    @Column(unique = true, length = 11)
    private String document;

    private String phone;

    @Enumerated(EnumType.STRING)
    private UserType userType;

    private String cep;
    private String address;
    private String number;
    private String complement;
    private String city;
    private Date birthday;

    public User(String id, String username, String password, String firstname, String lastname, String document, String phone, UserType userType, String cep, String address, String number, String complement, String city, Date birthday) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.firstname = firstname;
        this.lastname = lastname;
        this.document = document;
        this.phone = phone;
        this.userType = userType;
        this.cep = cep;
        this.address = address;
        this.number = number;
        this.complement = complement;
        this.city = city;
        this.birthday = birthday;
    }

    public User() {
    }

    public User(UserDTO data) {
        super();
        this.username = data.username();
        this.password = data.password();
        this.firstname = data.firstname();
        this.lastname = data.lastname();
        this.document = data.document();
        this.phone = data.phone();
        this.userType = data.userType();
        this.cep = data.cep();
        this.address = data.address();
        this.number = data.number();
        this.complement = data.complement();
        this.city = data.city();
        this.birthday = data.birthday();

    }

    public String getId() {
        return this.id;
    }

    public String getUsername() {
        return this.username;
    }

    public String getPassword() {
        return this.password;
    }

    public String getFirstname() {
        return this.firstname;
    }

    public String getLastname() {
        return this.lastname;
    }

    public String getDocument() {
        return this.document;
    }

    public String getPhone() {
        return this.phone;
    }

    public UserType getUserType() {
        return this.userType;
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

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public void setDocument(String document) {
        this.document = document;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
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
        if (!(o instanceof User)) return false;
        final User other = (User) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$id = this.getId();
        final Object other$id = other.getId();
        if (this$id == null ? other$id != null : !this$id.equals(other$id)) return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof User;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $id = this.getId();
        result = result * PRIME + ($id == null ? 43 : $id.hashCode());
        return result;
    }
}
