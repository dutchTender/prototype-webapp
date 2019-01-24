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
    private Date firstCommerceDate;

    private String classSpecimenImgPath;
    private String classSpecimenDescr;

    private boolean provideSample;

    private String sampleImagePath;

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
    private Date  frExpirationDate;
    private Date  frRenewlDate;

    private String frCertImagePath;









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
            if(firstCommerceDate != null || firstGSDate != null){
                identification = identification+"SECTION 1(a), ";
            }
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GoodAndService that = (GoodAndService) o;
        return id.equals(that.id) &&
                classNumber.equals(that.classNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, classNumber);
    }

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
}
