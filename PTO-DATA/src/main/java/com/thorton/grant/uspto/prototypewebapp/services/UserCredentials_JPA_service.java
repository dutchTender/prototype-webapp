package com.thorton.grant.uspto.prototypewebapp.services;

import com.thorton.grant.uspto.prototypewebapp.interfaces.UserCredentialsService;
import com.thorton.grant.uspto.prototypewebapp.model.entities.UserCredentials;
import com.thorton.grant.uspto.prototypewebapp.repositories.jpa.UserCredentialsRepository;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class UserCredentials_JPA_service implements UserCredentialsService {



    public UserCredentials_JPA_service(UserCredentialsRepository userCredentialsRepository) {
        this.userCredentialsRepository = userCredentialsRepository;
    }

    private final UserCredentialsRepository userCredentialsRepository;


    @Override
    public Set<UserCredentials> findAll() {
        Set<UserCredentials> userCredentials = new HashSet<>();

        userCredentialsRepository.findAll().forEach(userCredentials::add);
        return userCredentials;
    }

    @Override
    public Optional<UserCredentials> findById(Long id) {
        return  userCredentialsRepository.findById(id);
    }

    @Override
    public UserCredentials save(UserCredentials object) {
        return userCredentialsRepository.save(object);
    }

    @Override
    public void delete(UserCredentials object) {
            userCredentialsRepository.delete(object);
    }

    @Override
    public void deleteById(Long id) {
            userCredentialsRepository.deleteById(id);
    }
}
