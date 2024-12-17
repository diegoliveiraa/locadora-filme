package com.diegoliveiraa.locadora_filme.dtos;

import com.diegoliveiraa.locadora_filme.infra.RenterStatus;

import java.util.Date;

public record RenterDTO(String firstname, String lastname, String document, String phone
        , RenterStatus renterStatus, String cep, String address, String number
        , String complement, String city, Date birthday) {
}