package com.thorton.grant.uspto.prototypewebapp.services.security;

import com.thorton.grant.uspto.prototypewebapp.interfaces.Secruity.AuthenticationTokenService;
import com.thorton.grant.uspto.prototypewebapp.model.entities.security.AuthenticationToken;
import com.thorton.grant.uspto.prototypewebapp.repositories.jpa.security.AuthenticationTokenRepository;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;



@Service
public class AuthenticationToken_JPA_Service  implements AuthenticationTokenService {

    private final AuthenticationTokenRepository authenticationTokenRepository;


    public AuthenticationToken_JPA_Service(AuthenticationTokenRepository authenticationTokenRepository) {
        this.authenticationTokenRepository = authenticationTokenRepository;
    }

    @Override
    public AuthenticationToken findByToken(String token) {
        return authenticationTokenRepository.findByToken(token);
    }

    @Override
    public Set<AuthenticationToken> findAll() {
        Set<AuthenticationToken> authenticationTokens = new HashSet<>();
        authenticationTokenRepository.findAll().forEach(authenticationTokens::add);
        return authenticationTokens;
    }

    @Override
    public Optional<AuthenticationToken> findById(Long id) {
        return authenticationTokenRepository.findById(id);
    }

    @Override
    public AuthenticationToken save(AuthenticationToken object) {
        return authenticationTokenRepository.save(object);
    }

    @Override
    public void delete(AuthenticationToken object) {
        authenticationTokenRepository.delete(object);

    }

    @Override
    public void deleteById(Long id) {

        authenticationTokenRepository.deleteById(id);

    }
}
