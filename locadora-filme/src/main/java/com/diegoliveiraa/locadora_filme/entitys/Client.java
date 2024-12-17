package com.diegoliveiraa.locadora_filme.entitys;

import com.diegoliveiraa.locadora_filme.infra.BaseEntity;
import com.diegoliveiraa.locadora_filme.infra.ClientStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.*;

import java.util.Date;

@Entity(name = "clients")
@Table(name = "clients")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
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
}