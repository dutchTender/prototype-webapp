package com.thorton.grant.uspto.prototypewebapp.services.USPTO.tradeMark.asset;

import com.thorton.grant.uspto.prototypewebapp.interfaces.USPTO.tradeMark.asset.GoodsAndServicesService;
import com.thorton.grant.uspto.prototypewebapp.model.entities.USPTO.tradeMark.assets.GoodAndService;
import com.thorton.grant.uspto.prototypewebapp.repositories.jpa.USPTO.tradeMark.asset.GoodsAndServicesRepository;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
@Service
public class GoodsAndServices_JPA_Service implements GoodsAndServicesService {

    private final GoodsAndServicesRepository goodsAndServicesRepository;

    public GoodsAndServices_JPA_Service(GoodsAndServicesRepository goodsAndServicesRepository) {
        this.goodsAndServicesRepository = goodsAndServicesRepository;
    }

    @Override
    public Set<GoodAndService> findAll() {
        Set<GoodAndService> goodAndServices = new HashSet<>();

        goodsAndServicesRepository.findAll().forEach(goodAndServices::add);
        return goodAndServices;
    }

    @Override
    public Optional<GoodAndService> findById(Long id) {
        return goodsAndServicesRepository.findById(id);
    }

    @Override
    public GoodAndService save(GoodAndService object) {
        return goodsAndServicesRepository.save(object);
    }

    @Override
    public void delete(GoodAndService object) {

        goodsAndServicesRepository.delete(object);

    }

    @Override
    public void deleteById(Long id) {
        goodsAndServicesRepository.deleteById(id);
    }
}
