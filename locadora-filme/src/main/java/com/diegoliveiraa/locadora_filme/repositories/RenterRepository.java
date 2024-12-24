package com.diegoliveiraa.locadora_filme.repositories;

import com.diegoliveiraa.locadora_filme.entitys.Renter;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RenterRepository extends JpaRepository<Renter, String> {
    Optional<Renter> findByDocumentOrName(String document, String name);

    Optional<Renter> findById(String id);
}
