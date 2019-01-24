package com.thorton.grant.uspto.prototypewebapp.services.USPTO;

import com.thorton.grant.uspto.prototypewebapp.interfaces.USPTO.PTOUserService;
import com.thorton.grant.uspto.prototypewebapp.model.entities.USPTO.tradeMark.application.participants.Lawyer;
import com.thorton.grant.uspto.prototypewebapp.model.entities.USPTO.tradeMark.application.types.BaseTrademarkApplication;
import com.thorton.grant.uspto.prototypewebapp.model.entities.USPTO.tradeMark.assets.TradeMark;
import com.thorton.grant.uspto.prototypewebapp.model.entities.USPTO.user.PTOUser;
import com.thorton.grant.uspto.prototypewebapp.repositories.jpa.USPTO.PTOUserRepository;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;


@Service
public class PTOUser_JPA_Service implements PTOUserService {

    private final PTOUserRepository PTOUserRepository;


    public PTOUser_JPA_Service(PTOUserRepository PTOUserRepository) {
        this.PTOUserRepository = PTOUserRepository;
    }

    @Override
    public Set<PTOUser> findAll() {

        Set<PTOUser> PTOUsers = new HashSet<>();

        PTOUserRepository.findAll().forEach(PTOUsers::add);

        return PTOUsers;


    }

    @Override
    public Optional<PTOUser> findById(Long id) {
        return PTOUserRepository.findById(id);
    }

    @Override
    public PTOUser save(PTOUser object) {
        return PTOUserRepository.save(object);
    }

    @Override
    public void delete(PTOUser object) {
                PTOUserRepository.delete(object);
    }

    @Override
    public void deleteById(Long id) {
                PTOUserRepository.deleteById(id);
    }


    public PTOUser findByEmail(String email){
       PTOUser ptoUser =  PTOUserRepository.findByEmail(email);
       return ptoUser;
    }


}
