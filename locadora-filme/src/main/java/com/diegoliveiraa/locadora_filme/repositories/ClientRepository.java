package com.diegoliveiraa.locadora_filme.repositories;

import com.diegoliveiraa.locadora_filme.entitys.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, String> {
}
