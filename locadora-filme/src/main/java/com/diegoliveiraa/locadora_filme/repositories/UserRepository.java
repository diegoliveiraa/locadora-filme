package com.diegoliveiraa.locadora_filme.repositories;

import com.diegoliveiraa.locadora_filme.entitys.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, String> {

    Optional<User> findByDocument(String document);
}
