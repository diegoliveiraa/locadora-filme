package com.diegoliveiraa.locadora_filme.entitys;

import com.diegoliveiraa.locadora_filme.infra.BaseEntity;
import com.diegoliveiraa.locadora_filme.infra.UserType;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity(name = "users")
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
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
}
