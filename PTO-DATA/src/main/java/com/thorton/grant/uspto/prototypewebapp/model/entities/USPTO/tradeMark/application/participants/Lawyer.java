package com.thorton.grant.uspto.prototypewebapp.model.entities.USPTO.tradeMark.application.participants;


import com.thorton.grant.uspto.prototypewebapp.model.entities.USPTO.tradeMark.application.types.BaseTrademarkApplication;
import com.thorton.grant.uspto.prototypewebapp.model.entities.USPTO.user.PTOUser;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import java.util.*;

@Entity
public class Lawyer extends Contact {


    public Lawyer() {
        docketNumberList = new ArrayList<>();
    }

    // object copy constructor
    public Lawyer(Lawyer lawyer) {
        this.setEmail(lawyer.getEmail());
        this.setFirstName(lawyer.getFirstName());
        this.setLastName(lawyer.getLastName());
        this.setLawFirmName(lawyer.getLawFirmName());
        this.setBarLicense(lawyer.getBarLicense());
        this.setBarJurisdiction(lawyer.getBarJurisdiction());
        this.setClient(lawyer.getClient());
        this.setPrimary(lawyer.isPrimary());
    }

    private boolean isPrimary;
    private boolean validBarAssociation; // affiliation

    private String lawFirmName;
    private String barLicense;

    private String docketNumber;
    // support multiple docket numbers
    private ArrayList<String> docketNumberList;


    private String AffiliationStatus;
    private boolean AffiliationStatusSet;
    private boolean affliationUS;




    private boolean usCertifyCheck;




    private String barJurisdiction;
    private String MembershipNumber;
    private Date BarAdmissionDate;
    private String BarCertificateImageKey; // its the name of the file

    private String BarCertificateImageFileName; // its the name of the file

    private boolean BarCertificateImageUploaded; // its the name of the file


    // flags if a bar certificate upload was a pdf file
    private boolean barCertifcatePDF;







    private String CanadianAgentName;
    private boolean isApplicantCA;




    private String address1;
    private String address2;
    private String address3;

    private String collapseID;





    @OneToOne
    private BaseTrademarkApplication primaryCase;

    @ManyToOne
    private BaseTrademarkApplication poolMember;


    @ManyToOne
    private PTOUser client;

    public PTOUser getClient() {
        return client;
    }

    public void setClient(PTOUser client) {
        this.client = client;
    }

    public boolean isPrimary() {
        return isPrimary;
    }

    public void setPrimary(boolean primary) {
        isPrimary = primary;
    }

    public boolean isValidBarAssociation() {
        return validBarAssociation;
    }

    public void setValidBarAssociation(boolean validBarAssociation) {
        this.validBarAssociation = validBarAssociation;
    }

    public String getLawFirmName() {
        return lawFirmName;
    }

    public void setLawFirmName(String lawFirmName) {
        this.lawFirmName = lawFirmName;
    }

    public String getBarLicense() {
        return barLicense;
    }

    public void setBarLicense(String barLicense) {
        this.barLicense = barLicense;
    }

    public String getBarJurisdiction() {
        return barJurisdiction;
    }

    public void setBarJurisdiction(String barJurisdiction) {
        this.barJurisdiction = barJurisdiction;
    }

    public BaseTrademarkApplication getPrimaryCase() {
        return primaryCase;
    }

    public void setPrimaryCase(BaseTrademarkApplication primaryCase) {
        this.primaryCase = primaryCase;
    }

    public BaseTrademarkApplication getPoolMember() {
        return poolMember;
    }

    public void setPoolMember(BaseTrademarkApplication poolMember) {
        this.poolMember = poolMember;
    }

    public String getDocketNumber() {
        return docketNumber;
    }

    public void setDocketNumber(String docketNumber) {
        this.docketNumber = docketNumber;
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


    public String getAffiliationStatus() {
        return AffiliationStatus;
    }

    public void setAffiliationStatus(String affiliationStatus) {
        AffiliationStatus = affiliationStatus;
    }

    public String getMembershipNumber() {
        return MembershipNumber;
    }

    public void setMembershipNumber(String membershipNumber) {
        MembershipNumber = membershipNumber;
    }

    public Date getBarAdmissionDate() {
        return BarAdmissionDate;
    }

    public void setBarAdmissionDate(Date barAdmissionDate) {
        BarAdmissionDate = barAdmissionDate;
    }

    public String getBarCertificateImageKey() {
        return BarCertificateImageKey;
    }

    public void setBarCertificateImageKey(String barCertificateImageKey) {
        BarCertificateImageKey = barCertificateImageKey;
    }


    public String getCanadianAgentName() {
        return CanadianAgentName;
    }

    public void setCanadianAgentName(String canadianAgentName) {
        CanadianAgentName = canadianAgentName;
    }

    public boolean isApplicantCA() {
        return isApplicantCA;
    }

    public void setApplicantCA(boolean applicantCA) {
        isApplicantCA = applicantCA;
    }

    public String getCollapseID() {
        return collapseID;
    }

    public void setCollapseID(String collapseID) {
        this.collapseID = collapseID;
    }

    public boolean isAffiliationStatusSet() {
        return AffiliationStatusSet;
    }

    public void setAffiliationStatusSet(boolean affiliationStatusSet) {
        AffiliationStatusSet = affiliationStatusSet;
    }

    public boolean isAffliationUS() {
        return affliationUS;
    }

    public void setAffliationUS(boolean affliationUS) {
        this.affliationUS = affliationUS;
    }

    public boolean isBarCertificateImageUploaded() {
        return BarCertificateImageUploaded;
    }

    public void setBarCertificateImageUploaded(boolean barCertificateImageUploaded) {
        BarCertificateImageUploaded = barCertificateImageUploaded;
    }

    public String getBarCertificateImageFileName() {
        return BarCertificateImageFileName;
    }

    public void setBarCertificateImageFileName(String barCertificateImageFileName) {
        BarCertificateImageFileName = barCertificateImageFileName;
    }


    public String getBarAdmissionDateDisplay() {
        if( getBarAdmissionDate() != null) {
            return getBarAdmissionDate().toString().substring(0, 10);
        }
        else {
            return "";
        }
    }

    public ArrayList<String> getDocketNumberList() {
        return docketNumberList;
    }

    public void setDocketNumberList(ArrayList<String> docketNumberList) {
        this.docketNumberList = docketNumberList;
    }

    public void addDocketNumber(String dNumber){

        docketNumberList.add(dNumber);
    }

    public void removeDocketNumber(String dNumber){
        docketNumberList.remove(dNumber);
    }

    public boolean isBarCertifcatePDF() {
        return barCertifcatePDF;
    }

    public void setBarCertifcatePDF(boolean barCertifcatePDF) {
        this.barCertifcatePDF = barCertifcatePDF;
    }

    public boolean isUsCertifyCheck() {
        return usCertifyCheck;
    }

    public void setUsCertifyCheck(boolean usCertifyCheck) {
        this.usCertifyCheck = usCertifyCheck;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Lawyer lawyer = (Lawyer) o;
        return Objects.equals(lawFirmName, lawyer.lawFirmName) &&
                Objects.equals(barLicense, lawyer.barLicense) &&
                Objects.equals(docketNumber, lawyer.docketNumber) &&
                Objects.equals(primaryCase, lawyer.primaryCase) &&
                Objects.equals(poolMember, lawyer.poolMember) &&
                Objects.equals(client, lawyer.client);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), lawFirmName, barLicense, docketNumber, primaryCase, poolMember, client);
    }

    @Override
    public String toString() {
        return "Lawyer{" +
                "isPrimary=" + isPrimary +
                ", validBarAssociation=" + validBarAssociation +
                ", lawFirmName='" + lawFirmName + '\'' +
                ", barLicense='" + barLicense + '\'' +
                ", docketNumber='" + docketNumber + '\'' +
                ", barJurisdiction='" + barJurisdiction + '\'' +
                ", primaryCase=" + primaryCase +
                ", poolMember=" + poolMember +
                ", client=" + client +
                '}';
    }
}
