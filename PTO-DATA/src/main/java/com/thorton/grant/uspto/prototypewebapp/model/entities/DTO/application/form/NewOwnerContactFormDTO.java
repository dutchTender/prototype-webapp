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

    private String solpFirstName;
    private String solpLAstName;
    private String solpMiddleName;



    //////////////////////////////////////////////
    // owner - Partnership
    //////////////////////////////////////////////
    private List<partnerDTO>  partnerDTOs;

    public void addPartner(partnerDTO partner){
        this.partnerDTOs.add(partner);
    }


    private String partnerType2;

    private String partner_first_name2;
    private String partner_last_name2;
    private String partner_middle_name2;
    private String partner_suffix2;
    private String partner_citizen2;




    private String partner_name2;
    private String partner_state_org2;
    private String partner_alt_name2;
    private String partner_alt_type2;

    private String partnerType3;
    private String partner_first_name3;
    private String partner_last_name3;
    private String partner_middle_name3;
    private String partner_suffix3;
    private String partner_citizen3;

    private String partner_name3;
    private String partner_state_org3;
    private String partner_alt_name3;
    private String partner_alt_type3;





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

    public String getSolpFirstName() {
        return solpFirstName;
    }

    public void setSolpFirstName(String solpFirstName) {
        this.solpFirstName = solpFirstName;
    }

    public String getSolpLAstName() {
        return solpLAstName;
    }

    public void setSolpLAstName(String solpLAstName) {
        this.solpLAstName = solpLAstName;
    }

    public String getSolpMiddleName() {
        return solpMiddleName;
    }

    public void setSolpMiddleName(String solpMiddleName) {
        this.solpMiddleName = solpMiddleName;
    }

    public List<partnerDTO> getPartnerDTOs() {
        return partnerDTOs;
    }

    public void setPartnerDTOs(List<partnerDTO> partnerDTOs) {
        this.partnerDTOs = partnerDTOs;
    }

    public String getPartner_first_name2() {
        return partner_first_name2;
    }

    public void setPartner_first_name2(String partner_first_name2) {
        this.partner_first_name2 = partner_first_name2;
    }

    public String getPartner_last_name2() {
        return partner_last_name2;
    }

    public void setPartner_last_name2(String partner_last_name2) {
        this.partner_last_name2 = partner_last_name2;
    }

    public String getPartner_middle_name2() {
        return partner_middle_name2;
    }

    public void setPartner_middle_name2(String partner_middle_name2) {
        this.partner_middle_name2 = partner_middle_name2;
    }

    public String getPartner_suffix2() {
        return partner_suffix2;
    }

    public void setPartner_suffix2(String partner_suffix2) {
        this.partner_suffix2 = partner_suffix2;
    }

    public String getPartner_citizen2() {
        return partner_citizen2;
    }

    public void setPartner_citizen2(String partner_citizen2) {
        this.partner_citizen2 = partner_citizen2;
    }

    public String getPartner_name2() {
        return partner_name2;
    }

    public void setPartner_name2(String partner_name2) {
        this.partner_name2 = partner_name2;
    }

    public String getPartner_state_org2() {
        return partner_state_org2;
    }

    public void setPartner_state_org2(String partner_state_org2) {
        this.partner_state_org2 = partner_state_org2;
    }

    public String getPartner_alt_name2() {
        return partner_alt_name2;
    }

    public void setPartner_alt_name2(String partner_alt_name2) {
        this.partner_alt_name2 = partner_alt_name2;
    }

    public String getPartner_alt_type2() {
        return partner_alt_type2;
    }

    public void setPartner_alt_type2(String partner_alt_type2) {
        this.partner_alt_type2 = partner_alt_type2;
    }

    public String getPartner_first_name3() {
        return partner_first_name3;
    }

    public void setPartner_first_name3(String partner_first_name3) {
        this.partner_first_name3 = partner_first_name3;
    }

    public String getPartner_last_name3() {
        return partner_last_name3;
    }

    public void setPartner_last_name3(String partner_last_name3) {
        this.partner_last_name3 = partner_last_name3;
    }

    public String getPartner_middle_name3() {
        return partner_middle_name3;
    }

    public void setPartner_middle_name3(String partner_middle_name3) {
        this.partner_middle_name3 = partner_middle_name3;
    }

    public String getPartner_suffix3() {
        return partner_suffix3;
    }

    public void setPartner_suffix3(String partner_suffix3) {
        this.partner_suffix3 = partner_suffix3;
    }

    public String getPartner_citizen3() {
        return partner_citizen3;
    }

    public void setPartner_citizen3(String partner_citizen3) {
        this.partner_citizen3 = partner_citizen3;
    }

    public String getPartner_name3() {
        return partner_name3;
    }

    public void setPartner_name3(String partner_name3) {
        this.partner_name3 = partner_name3;
    }

    public String getPartner_state_org3() {
        return partner_state_org3;
    }

    public void setPartner_state_org3(String partner_state_org3) {
        this.partner_state_org3 = partner_state_org3;
    }

    public String getPartner_alt_name3() {
        return partner_alt_name3;
    }

    public void setPartner_alt_name3(String partner_alt_name3) {
        this.partner_alt_name3 = partner_alt_name3;
    }

    public String getPartner_alt_type3() {
        return partner_alt_type3;
    }

    public void setPartner_alt_type3(String partner_alt_type3) {
        this.partner_alt_type3 = partner_alt_type3;
    }

    public String getPartnerType2() {
        return partnerType2;
    }

    public void setPartnerType2(String partnerType2) {
        this.partnerType2 = partnerType2;
    }

    public String getPartnerType3() {
        return partnerType3;
    }

    public void setPartnerType3(String partnerType3) {
        this.partnerType3 = partnerType3;
    }
}
