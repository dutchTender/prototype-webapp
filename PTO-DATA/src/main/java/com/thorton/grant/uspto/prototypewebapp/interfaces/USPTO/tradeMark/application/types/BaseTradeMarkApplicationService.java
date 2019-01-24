package com.thorton.grant.uspto.prototypewebapp.interfaces.USPTO.tradeMark.application.types;

import com.thorton.grant.uspto.prototypewebapp.interfaces.base.CrudService;
import com.thorton.grant.uspto.prototypewebapp.model.entities.USPTO.tradeMark.application.types.BaseTrademarkApplication;

public interface BaseTradeMarkApplicationService extends CrudService<BaseTrademarkApplication, Long> {

    BaseTrademarkApplication findByEmail(String email);

    BaseTrademarkApplication findByInternalID(String id);

}
