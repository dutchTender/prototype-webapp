package com.thorton.grant.uspto.prototypewebapp.services.USPTO;

import com.thorton.grant.uspto.prototypewebapp.interfaces.USPTO.MangedContactsService;
import com.thorton.grant.uspto.prototypewebapp.model.entities.USPTO.user.ManagedContact;
import com.thorton.grant.uspto.prototypewebapp.repositories.jpa.USPTO.ManagedContactsRepository;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;


@Service
public class ManagedContacts_JPA_Service implements MangedContactsService {


    private final ManagedContactsRepository managedContactsRepository;

    public ManagedContacts_JPA_Service(ManagedContactsRepository managedContactsRepository) {
        this.managedContactsRepository = managedContactsRepository;
    }


    @Override
    public ManagedContact findContactByEmail(String email) {
        return managedContactsRepository.findByEmail(email);
    }

    @Override
    public ManagedContact findConctactByDisplayName(String name) {
        return managedContactsRepository.findByDisplayName(name);
    }

    @Override
    public Set<ManagedContact> findAll() {
        Set<ManagedContact> managedContacts = new HashSet<>();

          managedContactsRepository.findAll().forEach(managedContacts::add);

          return managedContacts;

    }

    @Override
    public Optional<ManagedContact> findById(Long id) {
        return managedContactsRepository.findById(id);
    }

    @Override
    public ManagedContact save(ManagedContact object) {
        return managedContactsRepository.save(object);
    }

    @Override
    public void delete(ManagedContact object) {
        managedContactsRepository.delete(object);

    }

    @Override
    public void deleteById(Long id) {
        managedContactsRepository.deleteById(id);
    }


}
