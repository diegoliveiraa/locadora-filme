package com.diegoliveiraa.locadora_filme.repositories;

import com.diegoliveiraa.locadora_filme.entitys.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {
}
