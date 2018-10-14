package com.thorton.grant.uspto.prototypewebapp.services;

import com.thorton.grant.uspto.prototypewebapp.interfaces.ApplicantService;
import com.thorton.grant.uspto.prototypewebapp.model.entities.Applicant;
import com.thorton.grant.uspto.prototypewebapp.repositories.jpa.ApplicantRepository;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;


@Service
public class Applicant_JPA_Service implements ApplicantService {

    private final ApplicantRepository applicantRepository;


    public Applicant_JPA_Service(ApplicantRepository applicantRepository) {
        this.applicantRepository = applicantRepository;
    }

    @Override
    public Set<Applicant> findAll() {

        Set<Applicant> applicants = new HashSet<>();

        applicantRepository.findAll().forEach(applicants::add);

        return applicants;


    }

    @Override
    public Optional<Applicant> findById(Long id) {
        return applicantRepository.findById(id);
    }

    @Override
    public Applicant save(Applicant object) {
        return applicantRepository.save(object);
    }

    @Override
    public void delete(Applicant object) {
                applicantRepository.delete(object);
    }

    @Override
    public void deleteById(Long id) {
                applicantRepository.deleteById(id);
    }



}
