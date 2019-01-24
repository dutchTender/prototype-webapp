package com.thorton.grant.uspto.prototypewebapp.model.entities.DTO.application.form;


import java.util.ArrayList;
import java.util.List;

public class NewOwnerContactFormDTO {


    public NewOwnerContactFormDTO() {
        this.partnerDTOs =  new ArrayList<>();
    }

    private String firstName;
private String middleName;
private String lastName;
private String suffix;


private String ownerType;

private String ownerCitizenShip;

private String OwnerAddressCountry;
private String ownerAddress1;

private String ownerAddress2;

private String ownerAddress3;

private String ownerCity;

private String ownerState;

private String ownerZipcode;

private String ownerEmail;

private String ownerWebSite;

private String ownerPhone;




//////////////////////////////////////////////
// owner sole proprietorship
//////////////////////////////////////////////

private String ownerName;

private String ownerAdditionalName; // this is basically a enum

private String ownerOrganizationState;



//////////////////////////////////////////////
// owner - Partnership
//////////////////////////////////////////////
private List<partnerDTO>  partnerDTOs;

public void addPartner(partnerDTO partner){
    this.partnerDTOs.add(partner);
}





//////////////////////////////////////////////
// application id tied to the owner,
// i.e this owner is assigned
//////////////////////////////////////////////
private String appInternalID;



    public String getAppInternalID() {
        return appInternalID;
    }

    public void setAppInternalID(String appInternalID) {
        this.appInternalID = appInternalID;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getSuffix() {
        return suffix;
    }

    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }

    public String getOwnerType() {
        return ownerType;
    }

    public void setOwnerType(String ownerType) {
        this.ownerType = ownerType;
    }

    public String getOwnerCitizenShip() {
        return ownerCitizenShip;
    }

    public void setOwnerCitizenShip(String ownerCitizenShip) {
        this.ownerCitizenShip = ownerCitizenShip;
    }

    public String getOwnerAddressCountry() {
        return OwnerAddressCountry;
    }

    public void setOwnerAddressCountry(String ownerAddressCountry) {
        OwnerAddressCountry = ownerAddressCountry;
    }

    public String getOwnerAddress1() {
        return ownerAddress1;
    }

    public void setOwnerAddress1(String ownerAddress1) {
        this.ownerAddress1 = ownerAddress1;
    }

    public String getOwnerAddress2() {
        return ownerAddress2;
    }

    public void setOwnerAddress2(String ownerAddress2) {
        this.ownerAddress2 = ownerAddress2;
    }

    public String getOwnerAddress3() {
        return ownerAddress3;
    }

    public void setOwnerAddress3(String ownerAddress3) {
        this.ownerAddress3 = ownerAddress3;
    }

    public String getOwnerCity() {
        return ownerCity;
    }

    public void setOwnerCity(String ownerCity) {
        this.ownerCity = ownerCity;
    }

    public String getOwnerState() {
        return ownerState;
    }

    public void setOwnerState(String ownerState) {
        this.ownerState = ownerState;
    }

    public String getOwnerZipcode() {
        return ownerZipcode;
    }

    public void setOwnerZipcode(String ownerZipcode) {
        this.ownerZipcode = ownerZipcode;
    }

    public String getOwnerEmail() {
        return ownerEmail;
    }

    public void setOwnerEmail(String ownerEmail) {
        this.ownerEmail = ownerEmail;
    }

    public String getOwnerWebSite() {
        return ownerWebSite;
    }

    public void setOwnerWebSite(String ownerWebSite) {
        this.ownerWebSite = ownerWebSite;
    }

    public String getOwnerPhone() {
        return ownerPhone;
    }

    public void setOwnerPhone(String ownerPhone) {
        this.ownerPhone = ownerPhone;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public String getOwnerAdditionalName() {
        return ownerAdditionalName;
    }

    public void setOwnerAdditionalName(String ownerAdditionalName) {
        this.ownerAdditionalName = ownerAdditionalName;
    }

    public String getOwnerOrganizationState() {
        return ownerOrganizationState;
    }

    public void setOwnerOrganizationState(String ownerOrganizationState) {
        this.ownerOrganizationState = ownerOrganizationState;
    }

    public List<partnerDTO> getPartnerDTOs() {
        return partnerDTOs;
    }

    public void setPartnerDTOs(List<partnerDTO> partnerDTOs) {
        this.partnerDTOs = partnerDTOs;
    }
}
