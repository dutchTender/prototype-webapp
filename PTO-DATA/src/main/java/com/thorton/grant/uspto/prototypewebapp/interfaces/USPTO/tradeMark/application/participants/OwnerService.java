package com.thorton.grant.uspto.prototypewebapp.interfaces.USPTO.tradeMark.application.participants;

import com.thorton.grant.uspto.prototypewebapp.interfaces.base.CrudService;
import com.thorton.grant.uspto.prototypewebapp.model.entities.USPTO.tradeMark.application.participants.Owner;

public interface OwnerService extends CrudService<Owner, Long> {

    Owner findByEmail(String email);
}
