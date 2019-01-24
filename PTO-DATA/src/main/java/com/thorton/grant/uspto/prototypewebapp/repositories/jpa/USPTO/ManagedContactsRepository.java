package com.thorton.grant.uspto.prototypewebapp.repositories.jpa.USPTO;

import com.thorton.grant.uspto.prototypewebapp.model.entities.USPTO.user.ManagedContact;
import org.springframework.data.repository.CrudRepository;

public interface ManagedContactsRepository extends CrudRepository<ManagedContact, Long> {

    ManagedContact findByEmail(String email);
    ManagedContact findByDisplayName(String displayName);

}
