package com.thorton.grant.uspto.prototypewebapp.services.USPTO.tradeMark.application.actions;

import com.thorton.grant.uspto.prototypewebapp.interfaces.USPTO.tradeMark.application.actions.OfficeActionsService;
import com.thorton.grant.uspto.prototypewebapp.model.entities.USPTO.tradeMark.application.actions.OfficeActions;
import com.thorton.grant.uspto.prototypewebapp.repositories.jpa.USPTO.tradeMark.application.actions.OfficeActionsRepository;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;


@Service
public class OfficeActions_JPA_Service implements OfficeActionsService {

    private  final OfficeActionsRepository officeActionsRepository;


    public OfficeActions_JPA_Service(OfficeActionsRepository officeActionsRepository) {
        this.officeActionsRepository = officeActionsRepository;
    }

    @Override
    public Set<OfficeActions> findAll() {
        Set<OfficeActions>  officeActions = new HashSet<>();

        officeActionsRepository.findAll().forEach(officeActions::add);
        return  officeActions;
    }

    @Override
    public Optional<OfficeActions> findById(Long id) {
        return officeActionsRepository.findById(id);
    }

    @Override
    public OfficeActions save(OfficeActions object) {
        return officeActionsRepository.save(object);
    }

    @Override
    public void delete(OfficeActions object) {
    officeActionsRepository.delete(object);
    }

    @Override
    public void deleteById(Long id) {
    officeActionsRepository.deleteById(id);
    }
}
