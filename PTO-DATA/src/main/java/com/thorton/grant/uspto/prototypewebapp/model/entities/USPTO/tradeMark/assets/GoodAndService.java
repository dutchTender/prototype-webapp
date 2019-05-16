package com.thorton.grant.uspto.prototypewebapp.model.entities.USPTO.tradeMark.assets;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;
import java.util.Objects;

@Entity
public class GoodAndService {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private String classNumber;

    private String classDescription;

    private String internalID;
    ////////////////////////////////////////////////////////
    //filing basis fields
    ////////////////////////////////////////////////////////
    private boolean markInUse;
    private boolean markInUseSet;

    private Date firstGSDate;
    private boolean firstGSDateSet;

    private Date firstCommerceDate;
    private boolean firstCommerceDateSet;


    private String classSpecimenImgPath;
    private String classSpecimenImgName;

    private String classSpecimenDescr;

    private boolean provideSample;

    private String sampleImagePath;
    private String sampleImageName;

    private String rootStoragePath;




    private boolean sampleUploaded;

    private String sampleDescription;

    public String getpfaID(){
        return internalID+"pfa";
    }

    public String getpfrID(){
        return internalID+"pfr";
    }

    public String getpnaID(){
        return internalID+"pna";
    }


    public String getInUseRadioIDYes(){ return  internalID+"inUseRadioYES";}

    public String getInUseRadioIDNo(){ return  internalID+"inUseRadioNO";}



    public String getFAfilingDateAlertMessageID(){ return  internalID+"faAlertMessage"; }
    public String getFAfilingDateAlertButtonID() {return  internalID+"faAlertButton";}
    public String getFAfilingDateAlertDivID(){return  internalID+"faAlertDIV";}



    public String getFRregistrationDateAlertMessageID(){ return  internalID+"frAlertMessage"; }
    public String getFRregistrationAlertButtonID() {return  internalID+"frAlertButton";}
    public String getFRregistrationAlertDivID(){return  internalID+"frAlertDIV";}




    public String getFirstDatesAlertMessageID(){ return  internalID+"firstDatesAlertMessage"; }
    public String getFirstDatesAlertButtonID() {return  internalID+"firstDatesAlertButton";}
    public String getFirstDAtesAlertDivID(){return  internalID+"firstDatesAlertDIV";}




    public String getFirstGSDateID () { return  internalID+"firstGSDateID"; }

    public String getFirstMarkDateID(){ return  internalID+"firstMarkDateID"; }

    ////////////////////////////////////////////////////////
    // foreign application fields
    ////////////////////////////////////////////////////////


    // foreign radio checkboxes

    private boolean pendingFA;


    private boolean foreignRegistration;


    private boolean foreignAR_NA;




    // foreign application fields
    private String faCountry;
    private String faRegistrationNumber;
    private Date faFilingDate;




    // foreign registration fields
    private String frCountry;
    private String frRegistartionNumber;
    private Date   frRegistrationDate;
    private Date  frExpirationDate;
    private Date  frRenewlDate;
    private String frCertImagePath;

    private String frCertImageName;



    // class level fields that propogates up

    public boolean atLeastOneGoodInCommerceClassFlag;
    public boolean  atLeastOneGoodInCommerceClassFlagSet;

    public boolean  provideSpecimenForAllGS;

    public boolean  provideSpecimenForAllGSSet;

    public boolean pendingFAAllGS;

    public boolean forenginRegistrationAllGS;

    public boolean NA_AllGS;


    public String faCountryCC;

    public String faAppNumberCC;

    public Date  faFilingDateCC;


    public String frCountryCC;
    public String frRegistrationNumberCC;

    public Date frRegistrationDateCC;
    public Date frExpireDateCC;

    public Date frRenewalDateCC;


    public String frCertImagePathCC;
    public String frCertImageNameCC;


    public boolean frCertUploadedCC;


    public Date firstGSDateCC;
    public Date firstMarkDateCC;



















    public boolean isAtLeastOneGoodInCommerceClassFlag() {
        return atLeastOneGoodInCommerceClassFlag;
    }

    public void setAtLeastOneGoodInCommerceClassFlag(boolean atLeastOneGoodInCommerceClassFlag) {
        this.atLeastOneGoodInCommerceClassFlag = atLeastOneGoodInCommerceClassFlag;
    }

    public boolean isProvideSpecimenForAllGS() {
        return provideSpecimenForAllGS;
    }

    public void setProvideSpecimenForAllGS(boolean provideSpecimenForAllGS) {
        this.provideSpecimenForAllGS = provideSpecimenForAllGS;
    }

    public String getClassNumber() {
        return classNumber;
    }

    public void setClassNumber(String classNumber) {
        this.classNumber = classNumber;
    }

    public String getClassDescription() {
        return classDescription;
    }

    public void setClassDescription(String classDescription) {
        this.classDescription = classDescription;
    }

    public String getSampleImagePath() {
        return sampleImagePath;
    }

    public void setSampleImagePath(String sampleImagePath) {
        this.sampleImagePath = sampleImagePath;
    }

    public String getSampleDescription() {
        return sampleDescription;
    }

    public void setSampleDescription(String sampleDescription) {
        this.sampleDescription = sampleDescription;
    }

    public String getInternalID() {
        return internalID;
    }

    public void setInternalID(String internalID) {
        this.internalID = internalID;
    }


    public boolean isMarkInUse() {
        return markInUse;
    }

    public void setMarkInUse(boolean markInUse) {
        this.markInUse = markInUse;
    }

    public Date getFirstGSDate() {

        return firstGSDate;

    }

    public Date getFrRegistrationDate() {
        return frRegistrationDate;
    }

    public void setFrRegistrationDate(Date frRegistrationDate) {
        this.frRegistrationDate = frRegistrationDate;
    }

    public String  getFRregistrationDateDisplay(){
        if(frRegistrationDate != null) {
            return frRegistrationDate.toString().substring(0, 10);
        }
        else {
            return "";
        }
    }

    public String getFirstGSDateDisplay() {
        if(firstGSDate != null) {
            return firstGSDate.toString().substring(0, 10);
        }
        else {
            return "";
        }
    }

    public void setFirstGSDate(Date firstGSDate) {
        this.firstGSDate = firstGSDate;
    }

    public Date getFirstCommerceDate() {
        return firstCommerceDate;
    }

    public String getFirstCommerceDateDisplay() {
        if(firstCommerceDate!= null) {
            return firstCommerceDate.toString().substring(0, 10);
        }
        else {
            return "";
        }
    }

    public String getFAFilingDateDisplay() {
        if(faFilingDate!= null) {
            return faFilingDate.toString().substring(0, 10);
        }
        else {
            return "";
        }
    }


    public String getFRexpirationDateDisplay() {
        if(frExpirationDate!= null) {
            return frExpirationDate.toString().substring(0, 10);
        }
        else {
            return "";
        }
    }

    public String getFRrenewalDateDisplay() {
        if(frRenewlDate!= null) {
            return frRenewlDate.toString().substring(0, 10);
        }
        else {
            return "";
        }
    }


    public String getIdentification(){

        String identification = "";
        if(markInUse == true){

                identification = identification+"SECTION 1(a), ";

            if(pendingFA == true){
                identification = identification+"SECTION 44(d), ";
            }
            if(foreignRegistration == true){
                identification = identification+"SECTION 44(e), ";
            }
        }
        else{

            identification = identification+"SECTION 1(b), ";
        }

        if(identification != ""){
            identification = identification.substring(0, identification.length()-2);
        }


        return identification;
    }

    public void setFirstCommerceDate(Date firstCommerceDate) {
        this.firstCommerceDate = firstCommerceDate;
    }




    public boolean isMarkInUseSet() {
        return markInUseSet;
    }

    public void setMarkInUseSet(boolean markInUseSet) {
        this.markInUseSet = markInUseSet;
    }


    public boolean isProvideSample() {
        return provideSample;
    }

    public void setProvideSample(boolean provideSample) {
        this.provideSample = provideSample;
    }

    public String getClassSpecimenImgPath() {
        return classSpecimenImgPath;
    }

    public void setClassSpecimenImgPath(String classSpecimenImgPath) {
        this.classSpecimenImgPath = classSpecimenImgPath;
    }


    ////////////////////////////////////////////////////////
    // get id functions for goods and services
    ////////////////////////////////////////////////////////
    public String getGSImpageFormID(){


        return getInternalID()+"imageForm";
    }

    public String getGSImageID(){
        return getInternalID()+"classSpecImg";
    }
    public String getGSFileInputID(){
        return getInternalID()+"classFileInputID";
    }

    public String getGSSpecDescID(){
        return getInternalID()+"classSpecimenDescID";
    }


    public String getGSUploadSpinnerID(){

        return getInternalID()+"classUploadSpinnerID";
    }

    ////////////////////////////////////////////////////////
    public String getFRCertImageFormID(){

        return getInternalID()+"FRcertForm";
    }
    public String getFRCertImageID(){
        return getInternalID()+"FRCertImg";
    }

    public String getFRCertFileID(){

        return getInternalID()+"FRCertFile";
    }

    public String getFRCertUploadSpinnerID(){
        return  getInternalID()+"FRCertSpinner";
    }

    public String getGSspecDownloadLinkID(){

        return  getInternalID()+"gsSpecDownloadID";
    }


    public String getFRcertDownloadID(){

        return  getInternalID()+"frCertDownloadID";
    }
    ////////////////////////////////////////////////////////







    public boolean isSampleUploaded() {
        return sampleUploaded;
    }

    public void setSampleUploaded(boolean sampleUploaded) {
        this.sampleUploaded = sampleUploaded;
    }

    public String getClassSpecimenDescr() {
        return classSpecimenDescr;
    }

    public void setClassSpecimenDescr(String classSpecimenDescr) {
        this.classSpecimenDescr = classSpecimenDescr;
    }


    public boolean isPendingFA() {
        return pendingFA;
    }

    public void setPendingFA(boolean pendingFA) {
        this.pendingFA = pendingFA;
    }


    public boolean isForeignRegistration() {
        return foreignRegistration;
    }

    public void setForeignRegistration(boolean foreignRegistration) {
        this.foreignRegistration = foreignRegistration;
    }


    public boolean isForeignAR_NA() {
        return foreignAR_NA;
    }

    public void setForeignAR_NA(boolean foreignAR_NA) {
        this.foreignAR_NA = foreignAR_NA;
    }



    public String getFaCountry() {
        return faCountry;
    }

    public void setFaCountry(String faCountry) {
        this.faCountry = faCountry;
    }

    public String getFaRegistrationNumber() {
        return faRegistrationNumber;
    }

    public void setFaRegistrationNumber(String faRegistrationNumber) {
        this.faRegistrationNumber = faRegistrationNumber;
    }

    public Date getFaFilingDate() {
        return faFilingDate;
    }

    public void setFaFilingDate(Date faFilingDate) {
        this.faFilingDate = faFilingDate;
    }

    public String getFrCountry() {
        return frCountry;
    }

    public void setFrCountry(String frCountry) {
        this.frCountry = frCountry;
    }

    public String getFrRegistartionNumber() {
        return frRegistartionNumber;
    }

    public void setFrRegistartionNumber(String frRegistartionNumber) {
        this.frRegistartionNumber = frRegistartionNumber;
    }

    public Date getFrExpirationDate() {
        return frExpirationDate;
    }

    public void setFrExpirationDate(Date frExpirationDate) {
        this.frExpirationDate = frExpirationDate;
    }

    public Date getFrRenewlDate() {
        return frRenewlDate;
    }

    public void setFrRenewlDate(Date frRenewlDate) {
        this.frRenewlDate = frRenewlDate;
    }

    public String getFrCertImagePath() {
        return frCertImagePath;
    }

    public void setFrCertImagePath(String frCertImagePath) {
        this.frCertImagePath = frCertImagePath;
    }


    public String getClassNumberDisplay(){


        return "Class "+classNumber;
    }


    public boolean isGSSpecimenSet(){

        if(getSampleImagePath() == null){
            return false;
        }
        else {
            return true;
        }
    }

    public boolean isFRcertUploaded(){
        if(frCertImagePath == null || frCertImagePath.equals("")){
            return false;
        }
        else {
            return true;
        }
    }

    public boolean isFirstGSDateSet() {
        return firstGSDateSet;
    }

    public void setFirstGSDateSet(boolean firstGSDateSet) {
        this.firstGSDateSet = firstGSDateSet;
    }

    public boolean isFirstCommerceDateSet() {
        return firstCommerceDateSet;
    }

    public void setFirstCommerceDateSet(boolean firstCommerceDateSet) {
        this.firstCommerceDateSet = firstCommerceDateSet;
    }

    public boolean isAtLeastOneGoodInCommerceClassFlagSet() {
        return atLeastOneGoodInCommerceClassFlagSet;
    }

    public void setAtLeastOneGoodInCommerceClassFlagSet(boolean atLeastOneGoodInCommerceClassFlagSet) {
        this.atLeastOneGoodInCommerceClassFlagSet = atLeastOneGoodInCommerceClassFlagSet;
    }

    public boolean isProvideSpecimenForAllGSSet() {
        return provideSpecimenForAllGSSet;
    }

    public void setProvideSpecimenForAllGSSet(boolean provideSpecimenForAllGSSet) {
        this.provideSpecimenForAllGSSet = provideSpecimenForAllGSSet;
    }

    public String getRootStoragePath() {
        return rootStoragePath;
    }

    public void setRootStoragePath(String rootStoragePath) {
        this.rootStoragePath = rootStoragePath;
    }




    public String getSampleImagePhysicalPath(){


        String path = getSampleImagePath();


        path = path.replace("/files", this.rootStoragePath);

        return path;
    }

    public boolean is1B(){

        boolean retVal = false;

        if(this.getIdentification().contains("1(b)")){
            retVal = true;
        }
        return  retVal;
    }

    public String getClassSpecimenImgName() {
        return classSpecimenImgName;
    }

    public void setClassSpecimenImgName(String classSpecimenImgName) {
        this.classSpecimenImgName = classSpecimenImgName;
    }

    public String getSampleImageName() {
        return sampleImageName;
    }

    public void setSampleImageName(String sampleImageName) {
        this.sampleImageName = sampleImageName;
    }

    public String getFrCertImageName() {
        return frCertImageName;
    }

    public void setFrCertImageName(String frCertImageName) {
        this.frCertImageName = frCertImageName;
    }

    public String getFaCountryCC() {
        return faCountryCC;
    }

    public void setFaCountryCC(String faCountryCC) {
        this.faCountryCC = faCountryCC;
    }

    public String getFaAppNumberCC() {
        return faAppNumberCC;
    }

    public void setFaAppNumberCC(String faAppNumberCC) {
        this.faAppNumberCC = faAppNumberCC;
    }

    public Date getFaFilingDateCC() {
        return faFilingDateCC;
    }

    public void setFaFilingDateCC(Date faFilingDateCC) {
        this.faFilingDateCC = faFilingDateCC;
    }

    public String getFrCountryCC() {
        return frCountryCC;
    }

    public void setFrCountryCC(String frCountryCC) {
        this.frCountryCC = frCountryCC;
    }

    public String getFrRegistrationNumberCC() {
        return frRegistrationNumberCC;
    }

    public void setFrRegistrationNumberCC(String frRegistrationNumberCC) {
        this.frRegistrationNumberCC = frRegistrationNumberCC;
    }

    public Date getFrRegistrationDateCC() {
        return frRegistrationDateCC;
    }

    public void setFrRegistrationDateCC(Date frRegistrationDateCC) {
        this.frRegistrationDateCC = frRegistrationDateCC;
    }

    public Date getFrExpireDateCC() {
        return frExpireDateCC;
    }

    public void setFrExpireDateCC(Date frExpireDateCC) {
        this.frExpireDateCC = frExpireDateCC;
    }

    public Date getFrRenewalDateCC() {
        return frRenewalDateCC;
    }

    public void setFrRenewalDateCC(Date frRenewalDateCC) {
        this.frRenewalDateCC = frRenewalDateCC;
    }

    public String getFrCertImagePathCC() {
        return frCertImagePathCC;
    }

    public void setFrCertImagePathCC(String frCertImagePathCC) {
        this.frCertImagePathCC = frCertImagePathCC;
    }

    public String getFrCertImageNameCC() {
        return frCertImageNameCC;
    }

    public void setFrCertImageNameCC(String frCertImageNameCC) {
        this.frCertImageNameCC = frCertImageNameCC;
    }

    public boolean isFrCertUploadedCC() {
        return frCertUploadedCC;
    }

    public void setFrCertUploadedCC(boolean frCertUploadedCC) {
        this.frCertUploadedCC = frCertUploadedCC;
    }

    public Date getFirstGSDateCC() {
        return firstGSDateCC;
    }

    public void setFirstGSDateCC(Date firstGSDateCC) {
        this.firstGSDateCC = firstGSDateCC;
    }

    public Date getFirstMarkDateCC() {
        return firstMarkDateCC;
    }

    public void setFirstMarkDateCC(Date firstMarkDateCC) {
        this.firstMarkDateCC = firstMarkDateCC;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GoodAndService that = (GoodAndService) o;
        return id.equals(that.id) &&
                classNumber.equals(that.classNumber);
    }
    /////////////////////////////////////////////////////////////////////////////////////////////////////
    @Override
    public int hashCode() {
        return Objects.hash(id, classNumber);
    }
    /////////////////////////////////////////////////////////////////////////////////////////////////////

    @Override
    public String toString() {
        return "GoodAndService{" +
                "id=" + id +
                ", classNumber='" + classNumber + '\'' +
                ", classDescription='" + classDescription + '\'' +
                ", sampleImagePath='" + sampleImagePath + '\'' +
                ", sampleDescription='" + sampleDescription + '\'' +
                '}';
    }
    ///////////////////////////////////////////////////////////////////////////////////////////////////////


    public boolean isPendingFAAllGS() {
        return pendingFAAllGS;
    }

    public void setPendingFAAllGS(boolean pendingFAAllGS) {
        this.pendingFAAllGS = pendingFAAllGS;
    }

    public boolean isForenginRegistrationAllGS() {
        return forenginRegistrationAllGS;
    }

    public void setForenginRegistrationAllGS(boolean forenginRegistrationAllGS) {
        this.forenginRegistrationAllGS = forenginRegistrationAllGS;
    }

    public boolean isNA_AllGS() {
        return NA_AllGS;
    }

    public void setNA_AllGS(boolean NA_AllGS) {
        this.NA_AllGS = NA_AllGS;
    }
}
