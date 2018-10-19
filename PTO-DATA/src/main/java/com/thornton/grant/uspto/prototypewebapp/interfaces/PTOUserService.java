package com.thornton.grant.uspto.prototypewebapp.interfaces;


import com.thornton.grant.uspto.prototypewebapp.model.entities.PTOUser;

public interface PTOUserService extends CrudService<PTOUser, Long> {

    public PTOUser findByEmail(String email);




}
