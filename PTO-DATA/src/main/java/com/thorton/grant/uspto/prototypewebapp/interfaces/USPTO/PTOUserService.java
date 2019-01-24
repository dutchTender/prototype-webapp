package com.thorton.grant.uspto.prototypewebapp.interfaces.USPTO;


import com.thorton.grant.uspto.prototypewebapp.interfaces.base.CrudService;
import com.thorton.grant.uspto.prototypewebapp.model.entities.USPTO.tradeMark.application.participants.Lawyer;
import com.thorton.grant.uspto.prototypewebapp.model.entities.USPTO.tradeMark.application.types.BaseTrademarkApplication;
import com.thorton.grant.uspto.prototypewebapp.model.entities.USPTO.tradeMark.assets.TradeMark;
import com.thorton.grant.uspto.prototypewebapp.model.entities.USPTO.user.PTOUser;

public interface PTOUserService extends CrudService<PTOUser, Long> {

     PTOUser findByEmail(String email);










}
