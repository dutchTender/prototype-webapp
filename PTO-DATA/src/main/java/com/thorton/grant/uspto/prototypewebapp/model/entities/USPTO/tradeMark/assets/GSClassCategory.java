package com.thorton.grant.uspto.prototypewebapp.model.entities.USPTO.tradeMark.assets;


import org.springframework.lang.Nullable;


import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;


public class GSClassCategory {


    public GSClassCategory() {

        goodAndServices = new ArrayList<>();
    }

    private ArrayList<GoodAndService> goodAndServices;
    private  Integer classCategoryNumber;

    private String classCategoryName;

    private String classCategoryImagePath;

    private String classCategoryImageName;

    private String classCategoryDescr;





    // first gs date class level
    private Date firstGSDateCC;

    // first mark date  level

    private Date firstMarkDateCC;




    // foreign radio checkboxes

    private boolean pendingFACC;


    private boolean foreignRegistrationCC;


    private boolean foreignAR_NACC;




    // foreign application fields
    private String faCountryCC;
    private String faRegistrationNumberCC;
    private Date faFilingDateCC;




    // foreign registration fields
    private String frCountryCC;
    private String frRegistartionNumberCC;
    private Date   frRegistrationDateCC;
    private Date  frExpirationDateCC;
    private Date  frRenewlDateCC;
    private String frCertImagePathCC;

    private String frCertImageNameCC;
    private boolean frCertCCuploaded;


    // id functions

    public String getpfaCCID(){
        return classCategoryNumber+"pfa";
    }

    public String getpfrCCID(){
        return classCategoryNumber+"pfr";
    }

    public String getpnaCCID(){
        return classCategoryNumber+"pna";
    }



    public String getFAfilingDateAlertMessageID(){ return  classCategoryNumber+"faAlertMessage"; }
    public String getFAfilingDateAlertButtonID() {return  classCategoryNumber+"faAlertButton";}
    public String getFAfilingDateAlertDivID(){return  classCategoryNumber+"faAlertDIV";}




    public String getFRregistrationDateAlertMessageID(){ return  classCategoryNumber+"frCCregistrationDAteAlertMessage"; }
    public String getFRregistrationDateAlertButtonID() {return  classCategoryNumber+"frCCregistrationDateAlertButton";}
    public String getFRregistrationDateAlertDivID(){return  classCategoryNumber+"frCCregistratoinDateAlertDIV";}






    public String getFirstDatesAlertMessageID(){ return  classCategoryNumber+"firstDatesAlertMessage"; }
    public String getFirstDatesAlertButtonID() {return  classCategoryNumber+"firstDatesAlertButton";}
    public String getFirstDAtesAlertDivID(){return  classCategoryNumber+"firstDatesAlertDIV";}

    public String getFirstGSDateID () { return  classCategoryNumber+"firstGSDateID"; }

    public String getFirstMarkDateID(){ return  classCategoryNumber+"firstMarkDateID"; }

    ////////////////////////////////////////////////////////
    // get id functions for goods and services
    ////////////////////////////////////////////////////////
    public String getCCImpageFormID(){


        return getClassCategoryNumber()+"imageForm";
    }

    public String getCCImageID(){
        return getClassCategoryNumber()+"classSpecImg";
    }
    public String getCCFileInputID(){
        return getClassCategoryNumber()+"classFileInputID";
    }

    public String getCCSpecDescID(){
        return getClassCategoryNumber()+"classSpecimenDescID";
    }


    public String getCCUploadSpinnerID(){

        return getClassCategoryNumber()+"classUploadSpinnerID";
    }

    ////////////////////////////////////////////////////////
    public String getCCFRCertImageFormID(){

        return getClassCategoryNumber()+"FRcertForm";
    }
    public String getCCFRCertImageID(){
        return getClassCategoryNumber()+"FRCertImg";
    }

    public String getCCFRCertFileID(){

        return getClassCategoryNumber()+"FRCertFile";
    }

    public String getCCFRCertUploadSpinnerID(){
        return  getClassCategoryNumber()+"FRCertSpinner";
    }

    public String getCCspecDownloadLinkID(){

        return  getClassCategoryNumber()+"gsSpecDownloadID";
    }


    public String getCCFRcertDownloadID(){

        return  getClassCategoryNumber()+"frCertDownloadID";
    }



    //



    public boolean atLeastOneGoodInCommerce;

    public boolean atLeastOneGoodInCommerceSet;

    public boolean provideSpecimenForAll;

    public boolean provideSpecimenForAllset;




    @Nullable
    public ArrayList<GoodAndService> getGoodAndServices() {

        return goodAndServices;
    }

    public void setGoodAndServices(@Nullable ArrayList<GoodAndService> goodAndServices) {
        this.goodAndServices = goodAndServices;
    }

    public Integer getClassCategoryNumber() {
        return classCategoryNumber;
    }

    public String getClassNumberDisplay(){


        return "Class "+classCategoryNumber;
    }

    public void setClassCategoryNumber(Integer classCategoryNumber) {
        this.classCategoryNumber = classCategoryNumber;
    }

    public String getClassCategoryName() {
        return classCategoryName;
    }

    public void setClassCategoryName(String classCategoryName) {
        this.classCategoryName = classCategoryName;
    }

    public GoodAndService findGSbyInternalID(String internalID){
        GoodAndService goodAndService = null;
        for(Iterator<GoodAndService> iter = goodAndServices.iterator(); iter.hasNext(); ) {
            GoodAndService current = iter.next();

            if(current.getInternalID().equals(internalID)){
                goodAndService = current;
            }
        }
        return goodAndService;
    }

    public void addGoodAndService(GoodAndService goodAndService){
        goodAndServices.add(goodAndService);
    }
    public void removeGoodAndService(GoodAndService goodAndService){
        goodAndServices.remove(goodAndService);
    }

    public String getClassCategoryImagePath() {
        return classCategoryImagePath;
    }

    public void setClassCategoryImagePath(String classCategoryImagePath) {
        this.classCategoryImagePath = classCategoryImagePath;
    }


    public String getClassCategporyImpageFormID(){


        return getClassCategoryNumber()+"imageForm";
    }


    public String getClassCategoryTopLevelName(){

        return getClassCategoryNumber()+"topLevel";
    }


    public String getClassCategoryInnterLevelName(){

        return getClassCategoryNumber()+"innerLevel";
    }


    public String getClassImageID(){
        return getClassCategoryNumber()+"classSpecImg";
    }
    public String getClassFileInputID(){
        return getClassCategoryNumber()+"classFileInputID";
    }

    public String getClassSpecDescID(){
        return getClassCategoryNumber()+"classSpecimenDescID";
    }


    public String getClassUploadSpinnerID(){

        return getClassCategoryNumber()+"classUploadSpinnerID";
    }

    public String getClassSpecDownloadLinkID(){
        return getClassCategoryNumber()+"classSpecDownloadID";

    }







    public boolean isClassSpecConsentUploaded(){

        boolean retVal = false;
        if(classCategoryImagePath != null){
            retVal =true;
        }
        return retVal;
    }
    public String getClassCategoryDescr() {
        return classCategoryDescr;
    }

    public void setClassCategoryDescr(String classCategoryDescr) {
        this.classCategoryDescr = classCategoryDescr;
    }


    public boolean isCategorySpecimenSet(){

        if(classCategoryImagePath == null){
            return false;
        }
        else {
            return true;
        }

    }


    public boolean isAtLeastOneGoodInCommerce() {
        return atLeastOneGoodInCommerce;
    }

    public void setAtLeastOneGoodInCommerce(boolean atLeastOneGoodInCommerce) {
        this.atLeastOneGoodInCommerce = atLeastOneGoodInCommerce;
    }

    public boolean isAtLeastOneGoodInCommerceSet() {
        return atLeastOneGoodInCommerceSet;
    }

    public void setAtLeastOneGoodInCommerceSet(boolean atLeastOneGoodInCommerceSet) {
        this.atLeastOneGoodInCommerceSet = atLeastOneGoodInCommerceSet;
    }

    public boolean isProvideSpecimenForAll() {
        return provideSpecimenForAll;
    }

    public void setProvideSpecimenForAll(boolean provideSpecimenForAll) {
        this.provideSpecimenForAll = provideSpecimenForAll;
    }

    public boolean isProvideSpecimenForAllset() {
        return provideSpecimenForAllset;
    }

    public void setProvideSpecimenForAllset(boolean provideSpecimenForAllset) {
        this.provideSpecimenForAllset = provideSpecimenForAllset;
    }

    public String getClassCategoryImageName() {
        return classCategoryImageName;
    }

    public void setClassCategoryImageName(String classCategoryImageName) {
        this.classCategoryImageName = classCategoryImageName;
    }

    public boolean isPendingFACC() {
        return pendingFACC;
    }

    public void setPendingFACC(boolean pendingFACC) {
        this.pendingFACC = pendingFACC;
    }

    public boolean isForeignRegistrationCC() {
        return foreignRegistrationCC;
    }

    public void setForeignRegistrationCC(boolean foreignRegistrationCC) {
        this.foreignRegistrationCC = foreignRegistrationCC;
    }

    public boolean isForeignAR_NACC() {
        return foreignAR_NACC;
    }

    public void setForeignAR_NACC(boolean foreignAR_NACC) {
        this.foreignAR_NACC = foreignAR_NACC;
    }

    public String getFaCountryCC() {
        return faCountryCC;
    }

    public void setFaCountryCC(String faCountryCC) {
        this.faCountryCC = faCountryCC;
    }

    public String getFaRegistrationNumberCC() {
        return faRegistrationNumberCC;
    }

    public void setFaRegistrationNumberCC(String faRegistrationNumberCC) {
        this.faRegistrationNumberCC = faRegistrationNumberCC;
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

    public String getFrRegistartionNumberCC() {
        return frRegistartionNumberCC;
    }

    public void setFrRegistartionNumberCC(String frRegistartionNumberCC) {
        this.frRegistartionNumberCC = frRegistartionNumberCC;
    }

    public Date getFrRegistrationDateCC() {
        return frRegistrationDateCC;
    }

    public void setFrRegistrationDateCC(Date frRegistrationDateCC) {
        this.frRegistrationDateCC = frRegistrationDateCC;
    }

    public Date getFrExpirationDateCC() {
        return frExpirationDateCC;
    }

    public void setFrExpirationDateCC(Date frExpirationDateCC) {
        this.frExpirationDateCC = frExpirationDateCC;
    }

    public Date getFrRenewlDateCC() {
        return frRenewlDateCC;
    }

    public void setFrRenewlDateCC(Date frRenewlDateCC) {
        this.frRenewlDateCC = frRenewlDateCC;
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

    public boolean isFrCertCCuploaded() {
        return frCertCCuploaded;
    }

    public void setFrCertCCuploaded(boolean frCertCCuploaded) {
        this.frCertCCuploaded = frCertCCuploaded;
    }

    public String getFaFilingDateCCDisplay(){
        if(faFilingDateCC != null) {
            return faFilingDateCC.toString().substring(0, 10);
        }
        else {
            return "";
        }
    }


    public String getFrREgistrationDateCCDispaly(){

        if(frRegistrationDateCC != null) {
            return frRegistrationDateCC.toString().substring(0, 10);
        }
        else {
            return "";
        }
    }


    public String getFrExpireationDateCCDisplay (){

        if(frExpirationDateCC != null) {
            return frExpirationDateCC.toString().substring(0, 10);
        }
        else {
            return "";
        }
    }


    public String getFRrenewalDateCCDisplay(){
        if(frRenewlDateCC != null) {
            return frRenewlDateCC.toString().substring(0, 10);
        }
        else {
            return "";
        }

    }


    public String getFirstGSDateCCDisplay() {
        if(firstGSDateCC != null) {
            return firstGSDateCC.toString().substring(0, 10);
        }
        else {
            return "";
        }
    }

    public String getFirstCommerceDateCCDisplay() {
        if(firstMarkDateCC!= null) {
            return firstMarkDateCC.toString().substring(0, 10);
        }
        else {
            return "";
        }
    }


    public boolean onlyOneGS(){
        boolean retValue = false;

        if(goodAndServices.size() == 1){
            retValue = true;
        }


        return retValue;

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
}
