package com.thorton.grant.uspto.prototypewebapp.interfaces;


import com.thorton.grant.uspto.prototypewebapp.model.entities.PTOUser;

public interface PTOUserService extends CrudService<PTOUser, Long> {

    public PTOUser findByEmail(String email);




}
