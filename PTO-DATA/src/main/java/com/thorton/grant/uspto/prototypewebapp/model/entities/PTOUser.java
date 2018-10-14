package com.thorton.grant.uspto.prototypewebapp.model.entities;


import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Set;

@Entity
@Table(name = "PTOUser")
public class PTOUser extends  UserPersonalData{

    @OneToMany
    private Set<Patent> userPatents;

    @OneToMany
    private Set<TradeMark> userTrademarks;




}
