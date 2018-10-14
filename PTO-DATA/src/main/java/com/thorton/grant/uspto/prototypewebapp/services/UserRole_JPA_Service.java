package com.thorton.grant.uspto.prototypewebapp.services;

import com.thorton.grant.uspto.prototypewebapp.interfaces.UserRoleService;
import com.thorton.grant.uspto.prototypewebapp.model.entities.UserRole;
import com.thorton.grant.uspto.prototypewebapp.repositories.jpa.UserRoleRepository;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;


@Service
public class UserRole_JPA_Service implements UserRoleService {


    public UserRole_JPA_Service(UserRoleRepository userRoleRepository) {
        this.userRoleRepository = userRoleRepository;
    }

    private final UserRoleRepository userRoleRepository;



    @Override
    public Set<UserRole> findAll() {

        Set<UserRole> userRoles = new HashSet<>();
        userRoleRepository.findAll().forEach(userRoles::add);
        return userRoles;
    }

    @Override
    public Optional<UserRole> findById(Long id) {
        return userRoleRepository.findById(id);
    }

    @Override
    public UserRole save(UserRole object) {
        return userRoleRepository.save(object);
    }

    @Override
    public void delete(UserRole object) {
            userRoleRepository.delete(object);
    }

    @Override
    public void deleteById(Long id) {
        userRoleRepository.deleteById(id);
    }
}
