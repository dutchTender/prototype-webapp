package com.thorton.grant.uspto.prototypewebapp.model.entities.USPTO.tradeMark.application.participants;

import com.thorton.grant.uspto.prototypewebapp.model.entities.USPTO.tradeMark.application.types.BaseTrademarkApplication;
import com.thorton.grant.uspto.prototypewebapp.model.entities.USPTO.user.PTOUser;

import javax.persistence.*;
import java.util.*;

@Entity
public class Owner extends Contact{

    public Owner() {

       governingEntities = new ArrayList<>();


    }

    @ManyToOne
    private BaseTrademarkApplication trademarkApplication;


    @ManyToOne
    private PTOUser client;


    @OneToMany(cascade = CascadeType.ALL)
    private List<GoverningEntity> governingEntities;

    private boolean governingEntitiesTypeOwner;

    private String governingEntitiesDisplayName;


    private boolean personTypeOwner;



    private String ownerType;

    private String ownerEnityType;

    private boolean foreignEntityOwner;





    private String ownersubType;

    private String webSiteURL;

    private String CitizenShip;

    private String  address1;

    private String address2;

    private String address3;

    ///////////////////////////////////////////
    // sole proprietorship
    ///////////////////////////////////////////
    private String ownerName;

    private String ownerAdditionalName;

    private String ownerOrganizationState;

    private String ownerSolpFirstName;

    private String ownerSolpLastName;

    private String ownerSolpMiddleName;


    private String ownerDisplayname;


    boolean alternameSet;

    @OneToOne
    private BaseTrademarkApplication primaryApplication;




    public BaseTrademarkApplication getTrademarkApplication() {
        return trademarkApplication;
    }

    public void setTrademarkApplication(BaseTrademarkApplication trademarkApplication) {
        this.trademarkApplication = trademarkApplication;
    }

    public String getOwnerType() {
        return ownerType;
    }

    public void setOwnerType(String ownerType) {
        this.ownerType = ownerType;
    }

    public String getOwnersubType() {
        return ownersubType;
    }

    public void setOwnersubType(String ownersubType) {
        this.ownersubType = ownersubType;
    }
    public PTOUser getClient() {
        return client;
    }

    public void setClient(PTOUser client) {
        this.client = client;
    }

    public String getWebSiteURL() {
        return webSiteURL;
    }

    public void setWebSiteURL(String webSiteURL) {
        this.webSiteURL = webSiteURL;
    }

    public String getOwnerEnityType() {
        return ownerEnityType;
    }

    public void setOwnerEnityType(String ownerEnityType) {
        this.ownerEnityType = ownerEnityType;
    }

    public String getCitizenShip() {
        return CitizenShip;
    }

    public void setCitizenShip(String citizenShip) {
        CitizenShip = citizenShip;
    }

    public String getAddress1() {
        return address1;
    }

    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    public String getAddress2() {
        return address2;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    public String getAddress3() {
        return address3;
    }

    public void setAddress3(String address3) {
        this.address3 = address3;
    }


    public List<GoverningEntity> getGoverningEntities() {
        return governingEntities;
    }

    public void setGoverningEntities(List<GoverningEntity> governingEntities) {
        this.governingEntities = governingEntities;
    }

    public GoverningEntity addGoverningEnity(GoverningEntity governingEntity){
        this.governingEntities.add(governingEntity);

        return governingEntity;

    }

   public void deleteGoverningEntity(GoverningEntity governingEntity){
        this.governingEntities.remove(governingEntity);
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

    public String getOwnerDisplayname() {

        if(getFirstName() == null || getLastName() == null){
            return getOwnerName();
        }
        else {
            return getFirstName() + " " + getLastName();
        }

    }

    public void setOwnerDisplayname(String ownerDisplayname) {
        this.ownerDisplayname = ownerDisplayname;
    }

    public String getOwnerEntityCountryOfOrigin(){
       // this value is either united states, or bahamas

        if(ownerEnityType.equals("US")){
           return "United States Of America";
        }
        else {
            return "Bahamas";
        }


    }

    public BaseTrademarkApplication getPrimaryApplication() {
        return primaryApplication;
    }

    public void setPrimaryApplication(BaseTrademarkApplication primaryApplication) {
        this.primaryApplication = primaryApplication;
    }

    public String getOwnerSolpFirstName() {
        return ownerSolpFirstName;
    }

    public void setOwnerSolpFirstName(String ownerSolpFirstName) {
        this.ownerSolpFirstName = ownerSolpFirstName;
    }

    public String getOwnerSolpLastName() {
        return ownerSolpLastName;
    }

    public void setOwnerSolpLastName(String ownerSolpLastName) {
        this.ownerSolpLastName = ownerSolpLastName;
    }

    public String getOwnerSolpMiddleName() {
        return ownerSolpMiddleName;
    }

    public void setOwnerSolpMiddleName(String ownerSolpMiddleName) {
        this.ownerSolpMiddleName = ownerSolpMiddleName;
    }

    public boolean isGoverningEntitiesTypeOwner() {
        return governingEntitiesTypeOwner;
    }

    public void setGoverningEntitiesTypeOwner(boolean governingEntitiesTypeOwner) {
        this.governingEntitiesTypeOwner = governingEntitiesTypeOwner;
    }

    public String getGoverningEntitiesDisplayName() {
        return governingEntitiesDisplayName;
    }

    public void setGoverningEntitiesDisplayName(String governingEntitiesDisplayName) {
        this.governingEntitiesDisplayName = governingEntitiesDisplayName;
    }

    public boolean isForeignEntityOwner() {
        return foreignEntityOwner;
    }

    public String getLegalLanguaguePartner(){

        String partnerList ="";
        for(int a =0; a < governingEntities.size(); a++){
            if(governingEntities.get(a).isPersonEntity() == true){
                partnerList = partnerList + governingEntities.get(a).getFirstName()+" "+governingEntities.get(a).getLastName()+", "+governingEntities.get(a).getGoverningEntityType()+", Citizen of "+governingEntities.get(a).getEntityCitizenship();

            }
            else {
                partnerList = partnerList + governingEntities.get(a).getEntityName()+", "+governingEntities.get(a).getGoverningEntityType()+", state legally organized : "+governingEntities.get(a).getOrganizationState();
            }
            if(a != governingEntities.size() -1){
                partnerList = partnerList + ", ";
            }

        }
        return  ownerDisplayname+", a partnership organized under the laws of "+ownerOrganizationState+", composed of "+partnerList+".";
    }

    public boolean isPersonTypeOwner() {
        return personTypeOwner;
    }

    public void setPersonTypeOwner(boolean personTypeOwner) {
        this.personTypeOwner = personTypeOwner;
    }

    public void setForeignEntityOwner(boolean foreignEntityOwner) {
        this.foreignEntityOwner = foreignEntityOwner;
    }

    public boolean isAlternameSet() {
        return alternameSet;
    }

    public void setAlternameSet(boolean alternameSet) {
        this.alternameSet = alternameSet;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Owner owner = (Owner) o;
        return Objects.equals(trademarkApplication, owner.trademarkApplication) &&
                Objects.equals(client, owner.client) &&
                Objects.equals(ownerType, owner.ownerType) &&
                Objects.equals(ownersubType, owner.ownersubType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), trademarkApplication, client, ownerType, ownersubType);
    }

    @Override
    public String toString() {
        return "Owner{" +
                "trademarkApplication=" + trademarkApplication +
                ", client=" + client +
                ", ownerType='" + ownerType + '\'' +
                ", ownersubType='" + ownersubType + '\'' +
                '}';
    }
}
