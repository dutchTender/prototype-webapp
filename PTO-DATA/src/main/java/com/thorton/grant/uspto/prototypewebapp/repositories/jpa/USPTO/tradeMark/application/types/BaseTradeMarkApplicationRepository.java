package com.thorton.grant.uspto.prototypewebapp.repositories.jpa.USPTO.tradeMark.application.types;

import com.thorton.grant.uspto.prototypewebapp.model.entities.USPTO.tradeMark.application.types.BaseTrademarkApplication;
import org.springframework.data.repository.CrudRepository;

public interface BaseTradeMarkApplicationRepository extends CrudRepository<BaseTrademarkApplication, Long> {

    BaseTrademarkApplication findByOwnerEmail(String email);


    BaseTrademarkApplication findByApplicationInternalID(String id);
}
