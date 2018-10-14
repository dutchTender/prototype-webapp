package com.thorton.grant.uspto.prototypewebapp.services;

import com.thorton.grant.uspto.prototypewebapp.interfaces.RolePrivilegeService;
import com.thorton.grant.uspto.prototypewebapp.model.entities.RolePrivilege;
import com.thorton.grant.uspto.prototypewebapp.repositories.jpa.RolePrivilegeRepository;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class RolePrivileges_JPA_Service implements RolePrivilegeService {


    public RolePrivileges_JPA_Service(RolePrivilegeRepository rolePrivilegeRepository) {
        this.rolePrivilegeRepository = rolePrivilegeRepository;
    }

    private final RolePrivilegeRepository rolePrivilegeRepository;


    @Override
    public Set<RolePrivilege> findAll() {

        Set<RolePrivilege> rolePrivileges = new HashSet<>();
        rolePrivilegeRepository.findAll().forEach(rolePrivileges::add);
        return rolePrivileges;
    }

    @Override
    public Optional<RolePrivilege> findById(Long id) {
        return rolePrivilegeRepository.findById(id);
    }

    @Override
    public RolePrivilege save(RolePrivilege object) {
        return rolePrivilegeRepository.save(object);
    }

    @Override
    public void delete(RolePrivilege object) {
        rolePrivilegeRepository.delete(object);

    }

    @Override
    public void deleteById(Long id) {
        rolePrivilegeRepository.deleteById(id);
    }
}
