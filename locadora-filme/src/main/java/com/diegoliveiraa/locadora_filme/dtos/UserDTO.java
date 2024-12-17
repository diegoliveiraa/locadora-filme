package com.diegoliveiraa.locadora_filme.dtos;

import com.diegoliveiraa.locadora_filme.infra.UserType;

import java.util.Date;

public record UserDTO(String username, String password, String firstname, String lastname, String document,
                      String phone, UserType userType, String cep, String address, String number,
                      String complement, String city, Date birthday) {
}
