package com.thorton.grant.uspto.prototypewebapp.services.security;

import com.thorton.grant.uspto.prototypewebapp.interfaces.registration.VerificationTokenService;
import com.thorton.grant.uspto.prototypewebapp.model.entities.security.VerificationToken;
import com.thorton.grant.uspto.prototypewebapp.repositories.jpa.security.VerificationTokenRepository;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;


@Service
public class VerificationToken_JPA_Service implements VerificationTokenService {




    private final VerificationTokenRepository verificationTokenRepository;

    public VerificationToken_JPA_Service(VerificationTokenRepository verificationTokenRepository) {
        this.verificationTokenRepository = verificationTokenRepository;
    }

    @Override
    public Set<VerificationToken> findAll() {
        Set<VerificationToken> verificationTokens = new HashSet<>();
        verificationTokenRepository.findAll().forEach(verificationTokens::add);
        return verificationTokens;
    }

    @Override
    public Optional<VerificationToken> findById(Long id) {
        return verificationTokenRepository.findById(id);
    }

    @Override
    public VerificationToken save(VerificationToken object) {
        return verificationTokenRepository.save(object);
    }

    @Override
    public void delete(VerificationToken object) {
                verificationTokenRepository.delete(object);
    }

    @Override
    public void deleteById(Long id) {
                verificationTokenRepository.deleteById(id);
    }

    @Override
    public VerificationToken findByVerificationToken(String verificationToken) {
        return verificationTokenRepository.findByToken(verificationToken);
    }
}
