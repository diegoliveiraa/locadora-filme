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

        User newUser = new User(data);

        this.userRepository.save(newUser);

        return newUser;
    }

    public List<User> getAllUsers() {
        return this.userRepository.findAll();
    }

    public User updateUser(UserDTO data) {

        User updateUser = this.findByDocumentOrName(data.document(), data.firstname());

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

    public User deleteUser(String id) {

        User deleteUser = this.findById(id);

        this.userRepository.delete(deleteUser);
        return deleteUser;
    }

    public User findById(String id) {
        return this.userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found!"));
    }

    public User findByDocumentOrName(String document, String name) {
        return this.userRepository.findByDocumentOrName(document, name)
                .orElseThrow(() -> new RuntimeException("User not found!"));
    }
}