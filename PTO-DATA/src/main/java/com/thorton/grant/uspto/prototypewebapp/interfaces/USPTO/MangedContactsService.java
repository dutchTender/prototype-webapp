package com.thorton.grant.uspto.prototypewebapp.interfaces.USPTO;

import com.thorton.grant.uspto.prototypewebapp.interfaces.base.CrudService;
import com.thorton.grant.uspto.prototypewebapp.model.entities.USPTO.user.ManagedContact;

public interface MangedContactsService extends CrudService<ManagedContact, Long> {

          ManagedContact findContactByEmail(String email);
          ManagedContact findConctactByDisplayName(String name);
}
