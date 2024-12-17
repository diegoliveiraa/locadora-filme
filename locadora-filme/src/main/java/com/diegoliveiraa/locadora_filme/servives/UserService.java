package com.diegoliveiraa.locadora_filme.servives;

import com.diegoliveiraa.locadora_filme.dtos.UserDTO;
import com.diegoliveiraa.locadora_filme.entitys.User;
import com.diegoliveiraa.locadora_filme.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User createUser(UserDTO data) {

        User newUser = new User();

        newUser.setUsername(data.username());
        newUser.setPassword(data.password());
        newUser.setFirstname(data.firstname());
        newUser.setLastname(data.lastname());
        newUser.setDocument(data.document());
        newUser.setPhone(data.phone());
        newUser.setUserType(data.userType());
        newUser.setCep(data.cep());
        newUser.setAddress(data.address());
        newUser.setNumber(data.number());
        newUser.setComplement(data.complement());
        newUser.setCity(data.city());
        newUser.setBirthday(data.birthday());

        this.userRepository.save(newUser);

        return newUser;
    }

    public List<User> getAllUsers() {
        return this.userRepository.findAll();
    }

    public User updateUser(UserDTO data) {

        User updateUser = this.findByDocument(data.document());

        updateUser.setUsername(data.username());
        updateUser.setPassword(data.password());
        updateUser.setFirstname(data.firstname());
        updateUser.setLastname(data.lastname());
        updateUser.setDocument(data.document());
        updateUser.setPhone(data.phone());
        updateUser.setUserType(data.userType());
        updateUser.setCep(data.cep());
        updateUser.setAddress(data.address());
        updateUser.setNumber(data.number());
        updateUser.setComplement(data.complement());
        updateUser.setCity(data.city());
        updateUser.setBirthday(data.birthday());

        this.userRepository.save(updateUser);

        return updateUser;
    }

    public User deleteUser(UserDTO data) {

        User deleteUser = this.findByDocument(data.document());

        this.userRepository.delete(deleteUser);

        return deleteUser;
    }

    public User findByDocument(String document) {
        return this.userRepository.findByDocument(document)
                .orElseThrow(() -> new RuntimeException("User not found!"));
    }
}
