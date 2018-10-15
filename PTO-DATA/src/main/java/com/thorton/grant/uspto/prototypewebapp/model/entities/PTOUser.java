package com.thorton.grant.uspto.prototypewebapp.model.entities;


import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Set;

@Entity
@Table(name = "PTOUsers")
public class PTOUser extends  UserPersonalData{

    @OneToMany(mappedBy = "patentOwner")
    private Set<Patent> userPatents;

    @OneToMany(mappedBy = "trademarkOwner")
    private Set<TradeMark> userTrademarks;


    public Set<Patent> getUserPatents() {
        return userPatents;
    }

    public void setUserPatents(Set<Patent> userPatents) {
        this.userPatents = userPatents;
    }

    public Set<TradeMark> getUserTrademarks() {
        return userTrademarks;
    }

    public void setUserTrademarks(Set<TradeMark> userTrademarks) {
        this.userTrademarks = userTrademarks;
    }
}
