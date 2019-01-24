package com.thorton.grant.uspto.prototypewebapp.interfaces.USPTO.tradeMark.application.participants;

import com.thorton.grant.uspto.prototypewebapp.interfaces.base.CrudService;
import com.thorton.grant.uspto.prototypewebapp.model.entities.USPTO.tradeMark.application.participants.Lawyer;

public interface LawyerService extends CrudService<Lawyer, Long> {

    Lawyer findByEmail(String email);
}

