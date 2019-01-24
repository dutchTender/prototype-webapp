package com.thorton.grant.uspto.prototypewebapp.services.USPTO.tradeMark.application.types;

import com.thorton.grant.uspto.prototypewebapp.interfaces.USPTO.tradeMark.application.types.BaseTradeMarkApplicationService;
import com.thorton.grant.uspto.prototypewebapp.model.entities.USPTO.tradeMark.application.types.BaseTrademarkApplication;
import com.thorton.grant.uspto.prototypewebapp.repositories.jpa.USPTO.tradeMark.application.types.BaseTradeMarkApplicationRepository;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;


@Service
public class BaseTradeMarkApplication_JPA_Service implements BaseTradeMarkApplicationService {


    private final BaseTradeMarkApplicationRepository baseTradeMarkApplicationRepository;

    public BaseTradeMarkApplication_JPA_Service(BaseTradeMarkApplicationRepository baseTradeMarkApplicationRepository) {
        this.baseTradeMarkApplicationRepository = baseTradeMarkApplicationRepository;
    }

    @Override
    public BaseTrademarkApplication findByEmail(String email) {
        return baseTradeMarkApplicationRepository.findByOwnerEmail(email);
    }

    @Override
    public Set<BaseTrademarkApplication> findAll() {
        Set<BaseTrademarkApplication>  baseTrademarkApplications = new HashSet<>();

        baseTradeMarkApplicationRepository.findAll().forEach(baseTrademarkApplications::add);
        return  baseTrademarkApplications;
    }

    @Override
    public Optional<BaseTrademarkApplication> findById(Long id) {
        return baseTradeMarkApplicationRepository.findById(id);
    }

    @Override
    public BaseTrademarkApplication save(BaseTrademarkApplication object) {
        return baseTradeMarkApplicationRepository.save(object);
    }

    @Override
    public void delete(BaseTrademarkApplication object) {
        baseTradeMarkApplicationRepository.delete(object);

    }

    @Override
    public void deleteById(Long id) {
        baseTradeMarkApplicationRepository.deleteById(id);

    }

    @Override
    public BaseTrademarkApplication findByInternalID(String id) {
        return baseTradeMarkApplicationRepository.findByApplicationInternalID(id);
    }
}
