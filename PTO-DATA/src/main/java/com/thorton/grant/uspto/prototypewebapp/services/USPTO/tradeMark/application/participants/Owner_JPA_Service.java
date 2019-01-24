package com.thorton.grant.uspto.prototypewebapp.services.USPTO.tradeMark.application.participants;

import com.thorton.grant.uspto.prototypewebapp.interfaces.USPTO.tradeMark.application.participants.OwnerService;
import com.thorton.grant.uspto.prototypewebapp.model.entities.USPTO.tradeMark.application.participants.Owner;
import com.thorton.grant.uspto.prototypewebapp.repositories.jpa.USPTO.tradeMark.application.participants.OwnerRepository;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;


@Service
public class Owner_JPA_Service implements OwnerService {


    private  final  OwnerRepository ownerRepository;

    public Owner_JPA_Service(OwnerRepository ownerRepository) {
        this.ownerRepository = ownerRepository;
    }

    @Override
    public Owner findByEmail(String email) {
        return ownerRepository.findByEmail(email);

    }

    @Override
    public Set<Owner> findAll() {
        Set<Owner>  owners = new HashSet<>();

        ownerRepository.findAll().forEach(owners::add);
        return  owners;
    }

    @Override
    public Optional<Owner> findById(Long id) {
        return ownerRepository.findById(id);
    }

    @Override
    public Owner save(Owner object) {
        return ownerRepository.save(object);
    }

    @Override
    public void delete(Owner object) {
        ownerRepository.delete(object);

    }

    @Override
    public void deleteById(Long id) {
        ownerRepository.deleteById(id);

    }
}
