package com.diegoliveiraa.locadora_filme.dtos;

import com.diegoliveiraa.locadora_filme.infra.DevolutionStatus;
import com.diegoliveiraa.locadora_filme.infra.PaymentStatus;
import com.diegoliveiraa.locadora_filme.infra.PaymentType;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public record LocationDTO(String id, String user, String renter
        , List<LocationFilmDTO> film
        , PaymentType paymentType, PaymentStatus paymentStatus, DevolutionStatus devolutionStatus
        , BigDecimal totalPayment, Date dateDevolution) {
}

