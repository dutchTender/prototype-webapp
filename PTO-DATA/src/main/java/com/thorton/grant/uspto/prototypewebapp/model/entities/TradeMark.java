package com.thorton.grant.uspto.prototypewebapp.model.entities;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.sql.Blob;


@Entity
@Table(name = "trade_marks")
public class TradeMark extends BaseEntity {


    private String PTOtradeMarkID;
    private String description;

    private Blob trademarkImage;

    @ManyToOne
    private PTOUser trademarkOwner;

    public String getPTOtradeMarkID() {
        return PTOtradeMarkID;
    }

    public void setPTOtradeMarkID(String PTOtradeMarkID) {
        this.PTOtradeMarkID = PTOtradeMarkID;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Blob getTrademarkImage() {
        return trademarkImage;
    }

    public void setTrademarkImage(Blob trademarkImage) {
        this.trademarkImage = trademarkImage;
    }

    public PTOUser getTrademarkOwner() {
        return trademarkOwner;
    }

    public void setTrademarkOwner(PTOUser trademarkOwner) {
        this.trademarkOwner = trademarkOwner;
    }


}
