package com.thorton.grant.uspto.prototypewebapp.services.USPTO.tradeMark.application.participants;

import com.thorton.grant.uspto.prototypewebapp.interfaces.USPTO.tradeMark.application.participants.LawyerService;
import com.thorton.grant.uspto.prototypewebapp.model.entities.USPTO.tradeMark.application.participants.Lawyer;
import com.thorton.grant.uspto.prototypewebapp.repositories.jpa.USPTO.tradeMark.application.participants.LawyerRepository;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;


@Service
public class Lawyer_JPA_Service implements LawyerService {


    private final LawyerRepository lawyerRepository;

    public Lawyer_JPA_Service(LawyerRepository lawyerRepository) {
        this.lawyerRepository = lawyerRepository;
    }

    @Override
    public Set<Lawyer> findAll() {
        Set<Lawyer>  lawyers = new HashSet<>();

        lawyerRepository.findAll().forEach(lawyers::add);
        return  lawyers;
    }

    @Override
    public Optional<Lawyer> findById(Long id) {
        return  lawyerRepository.findById(id);
    }

    @Override
    public Lawyer save(Lawyer object) {
        return lawyerRepository.save(object);
    }

    @Override
    public void delete(Lawyer object) {

          lawyerRepository.delete(object);

    }

    @Override
    public void deleteById(Long id) {
        lawyerRepository.deleteById(id);
    }

    @Override
    public Lawyer findByEmail(String email) {
        return lawyerRepository.findByEmail(email);
    }


}
