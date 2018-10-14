package com.thorton.grant.uspto.prototypewebapp.model.entities;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.sql.Blob;


@Entity
@Table(name = "trademarks")
public class TradeMark extends BaseEntity {


    private String PTOtradeMarkID;
    private String description;

    private Blob trademarkImage;

    @OneToOne(mappedBy = "userTrademarks")
    private PTOUser tradeMarkOwner;

}
