package com.thorton.grant.uspto.prototypewebapp.model.entities.USPTO.user;



import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class ManagedContact extends UserPersonalData {

@ManyToOne
private PTOUser contactOwner;
private String contactType;
private String displayName;


    ///////////////////////////////////////////////////////////////////////////////
    // owner specific fields
    ///////////////////////////////////////////////////////////////////////////////
    private String ownerEntityName;
    private String ownerEntityType;  // i.e additional name field "Trading as" or " Doing business as"
    private String ownerStateOfFormation; // includes incorporation or organization
    private String ownerCountryOfCitizenship;

    ///////////////////////////////////////////////////////////////////////////////
    // attorney specific fields
    ///////////////////////////////////////////////////////////////////////////////
    private String lawFirmName;
    private String docketNumber;




    ///////////////////////////////////////////////////////////////////////////////
    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public PTOUser getContactOwner() {
        return contactOwner;
    }

    public void setContactOwner(PTOUser contactOwner) {
        this.contactOwner = contactOwner;
    }

    public String getContactType() {
        return contactType;
    }

    public void setContactType(String contactType) {
        this.contactType = contactType;
    }

    public String getOwnerEntityName() {
        return ownerEntityName;
    }

    public void setOwnerEntityName(String ownerEntityName) {
        this.ownerEntityName = ownerEntityName;
    }

    public String getOwnerEntityType() {
        return ownerEntityType;
    }

    public void setOwnerEntityType(String ownerEntityType) {
        this.ownerEntityType = ownerEntityType;
    }

    public String getOwnerStateOfFormation() {
        return ownerStateOfFormation;
    }

    public void setOwnerStateOfFormation(String ownerStateOfFormation) {
        this.ownerStateOfFormation = ownerStateOfFormation;
    }

    public String getOwnerCountryOfCitizenship() {
        return ownerCountryOfCitizenship;
    }

    public void setOwnerCountryOfCitizenship(String ownerCountryOfCitizenship) {
        this.ownerCountryOfCitizenship = ownerCountryOfCitizenship;
    }


    public String getDocketNumber() {
        return docketNumber;
    }

    public void setDocketNumber(String docketNumber) {
        this.docketNumber = docketNumber;
    }

    public String getLawFirmName() {
        return lawFirmName;
    }

    public void setLawFirmName(String lawFirmName) {
        this.lawFirmName = lawFirmName;
    }
}
