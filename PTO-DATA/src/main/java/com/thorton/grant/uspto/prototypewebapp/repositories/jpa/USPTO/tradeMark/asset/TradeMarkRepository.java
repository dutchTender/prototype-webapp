package com.thorton.grant.uspto.prototypewebapp.repositories.jpa.USPTO.tradeMark.asset;

import com.thorton.grant.uspto.prototypewebapp.model.entities.USPTO.tradeMark.assets.TradeMark;
import org.springframework.data.repository.CrudRepository;

public interface TradeMarkRepository extends CrudRepository<TradeMark, Long> {
}
