package com.thorton.grant.uspto.prototypewebapp.model.entities.USPTO.tradeMark.application.participants;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
@Entity
public class GoverningEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String entityName;
    private String entityAlternateNameType;

    private String entityAlternateName;


    private String firstName;

    private String lastName;
    private String middleName;

    private String displayName;

    private String collapseID;

    private boolean primaryGoverningEntity;


    private String governingEntityType;





    private String suffix;

    private String entityCitizenship;

    private String organizationCountry;

    private String organizationState;

    ////////////////////////////////////////////////////////////////
    // denotes if the governing entity is a body or a person
    ////////////////////////////////////////////////////////////////
    private boolean personEntity;


    public String getEntityName() {
        return entityName;
    }

    public void setEntityName(String entityName) {
        this.entityName = entityName;
    }

    public String getEntityAlternateNameType() {
        return entityAlternateNameType;
    }

    public void setEntityAlternateNameType(String entityAlternateNameType) {
        this.entityAlternateNameType = entityAlternateNameType;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getCollapseID() {
        return collapseID;
    }

    public void setCollapseID(String collapseID) {
        this.collapseID = collapseID;
    }

    public String getSuffix() {
        return suffix;
    }

    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }

    public String getEntityCitizenship() {
        return entityCitizenship;
    }

    public void setEntityCitizenship(String entityCitizenship) {
        this.entityCitizenship = entityCitizenship;
    }

    public String getOrganizationCountry() {
        return organizationCountry;
    }

    public void setOrganizationCountry(String organizationCountry) {
        this.organizationCountry = organizationCountry;
    }

    public String getOrganizationState() {
        return organizationState;
    }

    public void setOrganizationState(String organizationState) {
        this.organizationState = organizationState;
    }

    public boolean isPersonEntity() {
        return personEntity;
    }

    public void setPersonEntity(boolean personEntity) {
        this.personEntity = personEntity;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public boolean isPrimaryGoverningEntity() {
        return primaryGoverningEntity;
    }

    public void setPrimaryGoverningEntity(boolean primaryGoverningEntity) {
        this.primaryGoverningEntity = primaryGoverningEntity;
    }


    public String getEntityAlternateName() {
        return entityAlternateName;
    }

    public void setEntityAlternateName(String entityAlternateName) {
        this.entityAlternateName = entityAlternateName;
    }

    public String getGoverningEntityType() {
        return governingEntityType;
    }

    public void setGoverningEntityType(String governingEntityType) {
        this.governingEntityType = governingEntityType;
    }
}
