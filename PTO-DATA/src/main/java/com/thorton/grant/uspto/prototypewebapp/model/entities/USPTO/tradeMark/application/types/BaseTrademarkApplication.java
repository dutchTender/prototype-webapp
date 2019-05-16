package com.thorton.grant.uspto.prototypewebapp.model.entities.USPTO.tradeMark.application.types;


import com.thorton.grant.uspto.prototypewebapp.model.entities.USPTO.tradeMark.application.actions.OfficeActions;
import com.thorton.grant.uspto.prototypewebapp.model.entities.USPTO.tradeMark.application.actions.Petition;
import com.thorton.grant.uspto.prototypewebapp.model.entities.USPTO.tradeMark.application.participants.Lawyer;
import com.thorton.grant.uspto.prototypewebapp.model.entities.USPTO.tradeMark.application.participants.Owner;
import com.thorton.grant.uspto.prototypewebapp.model.entities.USPTO.tradeMark.assets.GSClassCategory;
import com.thorton.grant.uspto.prototypewebapp.model.entities.USPTO.tradeMark.assets.GoodAndService;
import com.thorton.grant.uspto.prototypewebapp.model.entities.USPTO.tradeMark.assets.TradeMark;
import com.thorton.grant.uspto.prototypewebapp.model.entities.USPTO.user.PTOUser;
import com.thorton.grant.uspto.prototypewebapp.model.entities.base.BaseEntity;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.util.*;


@Entity
public class BaseTrademarkApplication  {

    // need to have an owner

    // could have an lawyer as well

    public BaseTrademarkApplication() {

        availableLawyers = new HashSet<>();

        owners = new HashSet<>();
        officeActions = new HashSet<>();


        goodAndServices = new HashSet<>();
        sectionStatus = new ArrayList<>();
        sectionStatus.add("NA"); //#1
        sectionStatus.add("NA"); //#2
        sectionStatus.add("NA"); //#3
        sectionStatus.add("NA"); //#4
        sectionStatus.add("NA"); //#5
        sectionStatus.add("NA"); //#6

    }

    ////////////////////////////////////////////////////////
    // stage 1 save point flags
    ////////////////////////////////////////////////////////
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String trademarkName;
    private String applicationInternalID;
    private boolean isAttorneySet = false;




    private boolean isAttorneyFiling;
    private boolean isForeignEnityFiling;
    private boolean isEntityTypeSet;

    private String currentStage;
    private String lastViewModel;

    private String ownerEmail;

    public String getOwnerEmail() {
        return ownerEmail;
    }


    ////////////////////////////////////////////////////////
    // modeling
    ////////////////////////////////////////////////////////
    //  parent entity
    ////////////////////////////////////////////////////////
    @ManyToOne
    private PTOUser ptoUser;

    ////////////////////////////////////////////////////////
    // sub ordinate objects
    ////////////////////////////////////////////////////////
    // set these details in stage
    // lawyer is the subordinate object here

    /////////////////////////////////////////////////////////////////////
    // stage 1
    /////////////////////////////////////////////////////////////////////

    @OneToOne(cascade = CascadeType.ALL)
    @Nullable
    private Lawyer primaryLawyer;

    @OneToOne(cascade = CascadeType.ALL)
    @Nullable
    private Owner primaryOwner;


    @OneToMany(cascade = CascadeType.ALL)
    @Nullable
    private Set<Lawyer> availableLawyers;


    // can be a lawyer or owner ???

    @OneToMany(cascade = CascadeType.ALL)
    @Nullable
    private Set<Owner> owners; // default owner   PTO user



    @OneToMany(fetch = FetchType.EAGER ,cascade = CascadeType.ALL)
    @Nullable
    private Set<OfficeActions> officeActions; // default owner   PTO user

    @OneToMany(fetch = FetchType.EAGER ,cascade =  CascadeType.ALL)
    @Nullable
    private Set<Petition>  petitions;
    /////////////////////////////////////////////////////////////////////
    // stage 2
    /////////////////////////////////////////////////////////////////////
    @OneToOne(cascade = CascadeType.ALL)
    @Nullable
    private TradeMark tradeMark;

    private boolean tradeMarkUploaded;











    private String ownerType;
    private String ownerSubType;
    private String attorneyCollapseID;
    private boolean searchExistingGSdatabase = false;


    ////////////////////////////////////////////////////////
    // one class of Good and service // old code
    // leave it here for now until the new code works
    ////////////////////////////////////////////////////////
    @OneToMany(cascade = CascadeType.ALL)
    @Nullable
    private Set<GoodAndService> goodAndServices; // default owner   PTO user
    ////////////////////////////////////////////////////////
    // new data structure to store all of the goods and services
    // key: classNumber
    // object HashSet of GoodAndService
    ////////////////////////////////////////////////////////


    ////////////////////////////////////////////////////////
    // an list of ids that corresponds to the TreeMap of GS hash set sorted by TreeMap key (classNumber)/ and also sorted
    // within each GoodAndServices set
    ////////////////////////////////////////////////////////
    private ArrayList<String> GoodsAndServicesIDListView;



    private ArrayList<GSClassCategory> goodsAndServicesClasses;


    private boolean markInUseForAllGS;
    private boolean markAllgsSet;


    private boolean markHasForeignRegistration;
    private boolean markFappSet;


    //additional info fields
    private boolean provideMiscInfo;
    private boolean provideMiscInfoFlagSet;





    private String miscInformation;
    private String miscInfoImagePath;
    private String miscInfoImageName;
    private boolean miscInfoImageTypeWord;

    public boolean  miscInfoImageUploaded;



    // concurrent use fields
    private boolean concurrentUse;
    private boolean concurrentUseSet;

    private boolean concurrentTypeCourtDecree;
    private boolean concurrentTypeCourtDecreeSet;


    private boolean concurrentTypePriorDecision;
    private boolean concurrentTypePriorDecisionSet;

    private boolean concurrentTypeWrittenConsent;
    private boolean concurrentTypeWrittenConsentSet;


    private boolean concurrentTypeEarlierFirstUse;
    private boolean concurrentTypeEarlierFirstUseSet;



    private boolean concurrentTypeSet;



    private String concurrentUseEvidenceFilePath;

    private String concurrentUseEvidenceFileName;

    private boolean concurrentEvidentFileTypeWord;
    private boolean concurrentUseEvidenceFileUploaded;



    private String concurentEvidenceDescription;




    private String GeoAreaMarkInCommerce;

    private String modeOfUse;

    private String ttabProceedingNumber;

    private String concurrentUserRegistrationNumber;

    private boolean concurrentUserNoRegistrationClaim;
    private boolean concurrentUserNoRegistrationClaimSet;


    private String concurrentUserName;

    private String concurrentUserCountry;

    private String concurrentUserAddress1;
    private String concurrentUserCity;

    private String concurrentUserState;
    private String concurrentUserZipcode;

    private String concurrentUserGoodsAndService;

    private String GeoAreaConcurrentUser;
    private String modeOfuseConcurrentUser;

    private String timePeriodConcurrentUser;




    // 2f claim fields

    private  boolean claimDistinctiveness;

    private boolean inheritantlyDistinctive;
    private boolean inheritantlyDistinctiveSet;

    private boolean inheritantlyWhole;
    private boolean inheritantlyPart;
    private boolean wholePartSet;

    private String inPartClaimDescription;

    private boolean distinctClaimBasedEvidence;
    private String distinctiveEvidenceFilePath;

    private String distinctiveEvidenceFileName;

    private boolean distinctClaimBasedPriorReg;
    private String distinctClaimBasedPriorRegNumber;


    private boolean distinctClaimBasedFiveYOU;










    // type of register fields
    private boolean principalRegister = true;

    private boolean supplementalRegister = false;

    private boolean registerTypeSet;



    // use in another form fields

    private boolean useInAnotherForm;
    private boolean useInAnotherFormCurrent;
    private boolean useInAnotherFormCurrentSet;

    private boolean userInAnotherFormWhole;
    private boolean useInAnotherFormPart;
    private boolean userInAnotherFormWholePartSet;

    private String userInAnotherFormMarkpart;

    private Date  useInAnotherFormFirstUseDate;
    private Date useInAnotherFormFirstCommerceDate;













    // bread crumb fields
    // 6 values; each value can be "active", "NA", "done"
    private ArrayList<String> sectionStatus;


    // receipt fields

    private String recieptFilePath;
    private String filingStatus = "Draft";
    private boolean standardTextMark;

    private String applicationSignature;
    private Date applicationDateSigned;
    private String applicationDateSignedDisplay;

    private String appSignatoryName;
    private  String appSignatoryPosition;
    private String appSignatoryPhone;

    private String signatureType;
    private boolean signDirect;

    // declaration fields

    private boolean declarationAll;

    private boolean declarationApplicantIsOwner;  // check box 1
    private boolean declarationApplicantIsOwnerSet;

    private boolean declarationMarkInUse;   // check box 2
    private boolean declarationMarkInUseSet;

    private boolean declarationSpecimen; // check box 3
    private boolean declarationSpecimenSet;

    private boolean declarationConcurrentUser; // check box 4
    private boolean declarationConcurrentUserSet;

    private boolean declarationNoOtherHasRight; // check box 5
    private boolean declarationNoOtherHasRightSet;



    private boolean declarationEvidenceSupport;  // check box 6
    private boolean declarationEvidenceSupportSet;


    private boolean declarationWarningFalseStatement; // check box 7
    private boolean declarationWarningFalseStatementSet;


    // fee variables

    private Integer baseFee = 275;


    // TEAS fields
    private boolean validateTEASFields = false;



    /////////////////////////////////////////////////////////////////////////////////////////
    // initial black out period date
    // initial black out period interval

    // user can file petitions to ammend at this time frame
    // no office actions can be issued at this time
    // status : "Black Out"



    /////////////////////////////////////////////////////////////////////////////////////////
    // first office action window start date
    // first office action wiondow interval

    // this is a time when an office action can be initated by the system or our lawyers
    // we will automatically issue an abandoned status based on our validation class
    // which will return abandoned every instance for now. we can update the logic of this module
    // later when that becomes clear

    // status "Office Action 1"


    /////////////////////////////////////////////////////////////////////////////////////////
    // we will not response to any office action (As that module is not built)
    // we will simply allow the office action to expire and allow user to file a petition to revive
    /////////////////////////////////////////////////////////////////////////////////////////




    //////////////////////////////////////////////////////////////////////////
    // date fields and flags to support petitions and amendments workflow
    // with time expiration
    // filing application statuses should be well defined
    //////////////////////////////////////////////////////////////////////////

    private Date applicationFilingDate;



    private boolean claimDidNotRecieveOfficeAction; // this can only be claimed once. once it is set to true, you can not make that claim any more  // this is actually a filed that needs to be on the filing object

    ///////////////////////////////////////////////////////////////

    public boolean isClaimDidNotRecieveOfficeAction() {
        return claimDidNotRecieveOfficeAction;
    }

    public void setClaimDidNotRecieveOfficeAction(boolean claimDidNotRecieveOfficeAction) {
        this.claimDidNotRecieveOfficeAction = claimDidNotRecieveOfficeAction;
    }


    public Date getApplicationFilingDate() {
        return applicationFilingDate;
    }

    public void setApplicationFilingDate(Date applicationFilingDate) {
        this.applicationFilingDate = applicationFilingDate;
    }

    public boolean isAttorneyPoolEmpty() {


        boolean status = false;

        if (availableLawyers.size()== 0) {
            status = true;
        }

        return  status;
    }

    public String getOwnerType() {
        return ownerType;
    }

    public void setOwnerType(String ownerType) {
        this.ownerType = ownerType;
    }

    public String getOwnerSubType() {
        return ownerSubType;
    }

    public void setOwnerSubType(String ownerSubType) {
        this.ownerSubType = ownerSubType;
    }

    public PTOUser getPtoUser() {
        return ptoUser;
    }

    public void setPtoUser(PTOUser ptoUser) {
        this.ptoUser = ptoUser;
    }

    public Lawyer getPrimaryLawyer() {
        return primaryLawyer;
    }

    public void setPrimaryLawyer(@Nullable Lawyer primaryLawyer) {
        this.primaryLawyer = primaryLawyer;
    }


    public Set<Lawyer> getAvailableLawyers() {
        return availableLawyers;
    }



    public Set<Lawyer> getAvailableLawyersExcludePrimary() {

      Set<Lawyer> lawyerPool = new HashSet<>();
        Lawyer lawyer = null;
        for(Iterator<Lawyer> iter = availableLawyers.iterator(); iter.hasNext(); ) {
            Lawyer current = iter.next();

            if(current.isPrimary() == false){
                lawyerPool.add(current);
            }
        }


        return lawyerPool;
    }

    public void setAvailableLawyers(@Nullable Set<Lawyer> availableLawyers) {

        this.availableLawyers = availableLawyers;

    }

    public void addAvailableLawyer(Lawyer lawyer){
        availableLawyers.add(lawyer);

    }
    public void removeAvailableLawyer(Lawyer lawyer){
        availableLawyers.remove(lawyer);

    }
    public Lawyer findContactByEmail(String email){
        Lawyer lawyer = null;
        for(Iterator<Lawyer> iter = availableLawyers.iterator(); iter.hasNext(); ) {
             Lawyer current = iter.next();

            if(current.getEmail().equals(email)){
                lawyer = current;
            }
        }
        return lawyer;
    }



    @Nullable
    public Set<Owner> getOwners() {
        return owners;
    }

    public void setOwners(@Nullable Set<Owner> owners) {
        this.owners = owners;
    }

    public Owner addOwner(Owner newOwner){

        this.owners.add(newOwner);
        return newOwner;

    }

    public void removeOwner(Owner owner){
        this.owners.remove(owner);
    }

    public Owner findOwnerByEmail(String email){
        Owner owner = null;
        for(Iterator<Owner> iter = owners.iterator(); iter.hasNext(); ) {
            Owner current = iter.next();

            if(current.getEmail().equals(email)){
                owner = current;
            }
        }
        return owner;
    }

    public Owner findOwnerByDisplayName(String name){
        Owner owner = null;
        for(Iterator<Owner> iter = owners.iterator(); iter.hasNext(); ) {
            Owner current = iter.next();

            if(current.getOwnerDisplayname().equals(name)){
                owner = current;
            }
        }
        return owner;
    }


     public boolean isOwnerSet(){

        boolean ownerSet = false;

        if(owners.size() > 0){
            ownerSet = true;
        }
        return ownerSet;
     }


    @Nullable
    public Set<OfficeActions> getOfficeActions() {
        return officeActions;
    }

    public void setOfficeActions(@Nullable Set<OfficeActions> officeActions) {
        this.officeActions = officeActions;
    }


    public OfficeActions addOfficeAction( OfficeActions officeAction){
        this.officeActions.add(officeAction);

        return officeAction;
    }


    public Petition addPetition( Petition petition){
        this.petitions.add(petition);

        return petition;
    }



    public OfficeActions findOfficeActionById(String id){
        OfficeActions action = null;
        for(Iterator<OfficeActions> iter = officeActions.iterator(); iter.hasNext(); ) {
            OfficeActions current = iter.next();

            if(current.getInternalID().equals(id)){
                action = current;
            }
        }
        return action;
    }


    public Petition findPetitionById(String id){
        Petition petition = null;
        for(Iterator<Petition> iter = petitions.iterator(); iter.hasNext(); ) {
            Petition current = iter.next();

            if(current.getInternalID().equals(id)){
                petition = current;
            }
        }
        return petition;
    }

    public boolean isAttorneyFiling() {
        return isAttorneyFiling;
    }

    public void setAttorneyFiling(boolean attorneyFiling) {
        isAttorneyFiling = attorneyFiling;
    }

    public boolean isForeignEnityFiling() {
        return isForeignEnityFiling;
    }

    public void setForeignEnityFiling(boolean foreignEnityFiling) {
        isForeignEnityFiling = foreignEnityFiling;
    }


    public TradeMark getTradeMark() {
        return tradeMark;
    }

    public void setTradeMark(@Nullable TradeMark tradeMark) {
        this.tradeMark = tradeMark;
    }




    public Long getId() {
        return id;
    }


    public String getTrademarkName() {
        return trademarkName;
    }

    public void setTrademarkName(String trademarkName) {
        this.trademarkName = trademarkName;
    }

    public String getApplicationInternalID() {
        return applicationInternalID;
    }

    public void setApplicationInternalID(String applicationInternalID) {
        this.applicationInternalID = applicationInternalID;
    }

    public boolean isAttorneySet() {
        return isAttorneySet;
    }

    public void setAttorneySet(boolean attorneySet) {
        isAttorneySet = attorneySet;
    }


    public String getCurrentStage() {
        return currentStage;
    }

    public void setCurrentStage(String currentStage) {
        this.currentStage = currentStage;
    }

    public String getLastViewModel() {
        return lastViewModel;
    }

    public void setLastViewModel(String lastViewModel) {
        this.lastViewModel = lastViewModel;
    }

    public void setOwnerEmail(String ownerEmail) {
        this.ownerEmail = ownerEmail;
    }

    public String getAttorneyCollapseID() {
        return attorneyCollapseID;
    }

    public void setAttorneyCollapseID(String attorneyCollapseID) {
        this.attorneyCollapseID = attorneyCollapseID;
    }


    // goods and services
    @Nullable
    public ArrayList<GoodAndService> getGoodAndServicesList() {


        Set<Integer> uniqeClassNumber = getUniqueClassNumberforGS();

        ArrayList<GoodAndService> returnGoodsServicesList = new ArrayList<>();

        // for each unique key, after its sorted
        // sort uniqeIDs
        List<Integer> sortedUniqeClassNumbers = new ArrayList<>(uniqeClassNumber);
        Collections.sort(sortedUniqeClassNumbers);

        System.out.println("found "+sortedUniqeClassNumbers.size()+" unique class numbers.");

        for(int a=0; a < sortedUniqeClassNumbers.size(); a++){
            System.out.println("processing class number : "+sortedUniqeClassNumbers.get(a));
            ArrayList<GoodAndService> sortedCategory = new ArrayList<>();

            for(Iterator<GoodAndService> iter = goodAndServices.iterator(); iter.hasNext(); ) {
                GoodAndService current = iter.next();

                if(sortedUniqeClassNumbers.get(a) == Integer.valueOf(current.getClassNumber())){
                    System.out.println("adding class with description : "+current.getClassDescription() );
                    sortedCategory.add(current);
                }
            }
            // sort sorted category and add it to return value

            Collections.sort(sortedCategory, new CustomComparator());
            System.out.println("sorted GS size is "+sortedCategory.size());

             for(int b =0; b < sortedCategory.size(); b++){
                 returnGoodsServicesList.add(sortedCategory.get(b));
             }

             System.out.println("return array list sie is "+returnGoodsServicesList.size());

        }
        // go through the main gs list and find GS that matches this key

        // for each id unique id,
        //   create an arrayList
             // for each GS that matches this unique Category
             // add to the array list

        // // end for loop
        // sort array list
        // and add to return hash set




        return returnGoodsServicesList;
    }



    @Nullable
    public Set<GoodAndService> getGoodAndServices() {
        return goodAndServices;
    }

    public void setGoodAndServices(@Nullable Set<GoodAndService> goodAndServices) {
        this.goodAndServices = goodAndServices;
    }

    public void addGoodAndService(GoodAndService goodAndService){
        goodAndServices.add(goodAndService);
    }
    public void removeGoodAndService(GoodAndService goodAndService){
        goodAndServices.remove(goodAndService);
    }

    public boolean isSearchExistingGSdatabase() {
        return searchExistingGSdatabase;
    }

    public void setSearchExistingGSdatabase(boolean searchExistingGSdatabase) {
        this.searchExistingGSdatabase = searchExistingGSdatabase;
    }

    public GoodAndService findGSbyDescription(String description){
        GoodAndService goodAndService = null;
        for(Iterator<GoodAndService> iter = goodAndServices.iterator(); iter.hasNext(); ) {
            GoodAndService current = iter.next();

            if(current.getClassDescription().equals(description)){
                goodAndService = current;
            }
        }
        return goodAndService;
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

    public ArrayList<String> getGoodsAndServicesIDListView() {
        return GoodsAndServicesIDListView;
    }

    public void setGoodsAndServicesIDListView(ArrayList<String> goodsAndServicesIDListView) {
        GoodsAndServicesIDListView = goodsAndServicesIDListView;
    }


    @Nullable
    public ArrayList<GSClassCategory> getGoodsAndServicesClasses() {
        return goodsAndServicesClasses;
    }

    // goods and services category view
    @Nullable
    public ArrayList<GSClassCategory> getGoodAndServicesCategories() {


        ArrayList<GSClassCategory> returnArrayList = new ArrayList<>();

        Set<Integer> uniqeClassNumber = getUniqueClassNumberforGS();

        // for each unique number create a GSClass category object


            // loop through and add GS classes with matching class numbers to the category object
            // call sort on category object's GS array list ..so that every thing added is sorted
            // add category object to the return arraylist


       // call sort on return arraylist


        List<Integer> sortedUniqeClassNumbers = new ArrayList<>(uniqeClassNumber);
        Collections.sort(sortedUniqeClassNumbers);

        for(int a=0; a < sortedUniqeClassNumbers.size(); a++){

            GSClassCategory gsClassCategory = new GSClassCategory();
            gsClassCategory.setClassCategoryNumber(sortedUniqeClassNumbers.get(a));
            for(Iterator<GoodAndService> iter = goodAndServices.iterator(); iter.hasNext(); ) {
                GoodAndService current = iter.next();

                if(gsClassCategory.getClassCategoryNumber() == Integer.valueOf(current.getClassNumber())){
                    gsClassCategory.addGoodAndService(current);
                    gsClassCategory.setClassCategoryImagePath(current.getClassSpecimenImgPath());
                    gsClassCategory.setClassCategoryImageName(current.getSampleImageName());
                    gsClassCategory.setClassCategoryDescr(current.getClassSpecimenDescr());
                    gsClassCategory.setAtLeastOneGoodInCommerce(current.isAtLeastOneGoodInCommerceClassFlag());
                    gsClassCategory.setAtLeastOneGoodInCommerceSet(current.isAtLeastOneGoodInCommerceClassFlagSet());
                    gsClassCategory.setProvideSpecimenForAll(current.isProvideSpecimenForAllGS());
                    gsClassCategory.setProvideSpecimenForAllset(current.isProvideSpecimenForAllGSSet());


                    // new class level options and fields
                    gsClassCategory.setPendingFACC(current.isPendingFAAllGS());
                    gsClassCategory.setForeignRegistrationCC(current.isForeignRegistration());
                    gsClassCategory.setForeignAR_NACC(current.isNA_AllGS());
                    gsClassCategory.setFaCountryCC(current.getFaCountryCC());
                    gsClassCategory.setFaRegistrationNumberCC(current.getFaAppNumberCC());
                    gsClassCategory.setFaFilingDateCC(current.getFaFilingDateCC());
                    gsClassCategory.setFrCountryCC(current.getFrCountryCC());
                    gsClassCategory.setFrRegistartionNumberCC(current.getFrRegistrationNumberCC());
                    gsClassCategory.setFrRegistrationDateCC(current.getFrRegistrationDateCC());
                    gsClassCategory.setFrExpirationDateCC(current.getFrExpireDateCC());
                    gsClassCategory.setFrRenewlDateCC(current.getFrRenewalDateCC());
                    gsClassCategory.setFrCertImagePathCC(current.getFrCertImagePathCC());
                    gsClassCategory.setFrCertImageNameCC(current.getFrCertImageNameCC());
                    gsClassCategory.setFrCertCCuploaded(current.isFrCertUploadedCC());


                    gsClassCategory.setFirstGSDateCC(current.getFirstGSDateCC());
                    gsClassCategory.setFirstMarkDateCC(current.getFirstMarkDateCC());





                }
            }
            Collections.sort(gsClassCategory.getGoodAndServices(), new CustomComparator());
            returnArrayList.add(gsClassCategory);

        }

        Collections.sort(returnArrayList, new CustomComparatorGSClassCategory());
        return  returnArrayList;

    }




    public void setGoodsAndServicesClasses(@Nullable ArrayList<GSClassCategory> goodsAndServicesClasses) {
        this.goodsAndServicesClasses = goodsAndServicesClasses;
    }


    public void addGSClass(GSClassCategory classCategory){
        this.goodsAndServicesClasses.add(classCategory);
    }
    public void removeGSClass(GSClassCategory classCategory){
        this.goodsAndServicesClasses.remove(classCategory);
    }

    public GSClassCategory findGSClassByClassNumber( Integer classNumber){
        GSClassCategory gsClassCategory = null;
        for(int a =0; a < this.goodsAndServicesClasses.size(); a++){
            if(goodsAndServicesClasses.get(a).getClassCategoryNumber() == classNumber){
                gsClassCategory = goodsAndServicesClasses.get(a);
            }

        }
        return  gsClassCategory;
    }

    public boolean isMarkInUseForAllGS() {
        return markInUseForAllGS;
    }

    public void setMarkInUseForAllGS(boolean markInUseForAllGS) {
        this.markInUseForAllGS = markInUseForAllGS;
    }

    public boolean isMarkHasForeignRegistration() {
        return markHasForeignRegistration;
    }

    public void setMarkHasForeignRegistration(boolean markHasForeignRegistration) {
        this.markHasForeignRegistration = markHasForeignRegistration;
    }

    public boolean isMarkFappSet() {
        return markFappSet;
    }

    public void setMarkFappSet(boolean markFappSet) {
        this.markFappSet = markFappSet;
    }

    public boolean isMarkAllgsSet() {
        return markAllgsSet;
    }

    public void setMarkAllgsSet(boolean markAllgsSet) {
        this.markAllgsSet = markAllgsSet;
    }

    public boolean isEntityTypeSet() {
        return isEntityTypeSet;
    }

    public void setEntityTypeSet(boolean entityTypeSet) {
        isEntityTypeSet = entityTypeSet;
    }

    @Nullable
    public Owner getPrimaryOwner() {
        return primaryOwner;
    }

    public void setPrimaryOwner(@Nullable Owner primaryOwner) {
        this.primaryOwner = primaryOwner;
    }

    public String getMiscInformation() {
        return miscInformation;
    }

    public void setMiscInformation(String miscInformation) {
        this.miscInformation = miscInformation;
    }

    public String getMiscInfoImagePath() {
        return miscInfoImagePath;
    }

    public void setMiscInfoImagePath(String miscInfoImagePath) {
        this.miscInfoImagePath = miscInfoImagePath;
    }

    public String getExtraFeeCalculationString(){

        if(getUniqueClassNumberforGS().size() > 0){
            return"("+ (getUniqueClassNumberforGS().size()-1)+")$"+((getUniqueClassNumberforGS().size()-1)*baseFee);
        }
        else {
            return "(0)$0";
        }



    }

    public String getBasicFeeCalculationString(){


            return "$"+String.valueOf(baseFee);




    }

    public String getNumberOfExtraClasses(){

        if( getUniqueClassNumberforGS().size() > 0) {
            return getUniqueClassNumberforGS().size() - 1 + "";
        }
        else{
            return "0";
        }
    }


    public String getTotalNumberOfclasses(){

        if(getUniqueClassNumberforGS().size() == 0){
            return "1";
        }
        return getUniqueClassNumberforGS().size()+"";
    }

    public String getTotalFeeString(){
        String val = "";
                if(getUniqueClassNumberforGS().size() > 0){
                  val=  (baseFee+Integer.valueOf(getUniqueClassNumberforGS().size()-1)*baseFee)+"";
                }
                else{
                    val = String.valueOf(baseFee);

                }


        return "Fee $"+val;
    }
    public String getTotalFeeAmount(){
        String val = "";
        if(getUniqueClassNumberforGS().size() > 0){
            val=  (baseFee+Integer.valueOf(getUniqueClassNumberforGS().size()-1)*baseFee)+"";
        }
        else{
            val = String.valueOf(baseFee);

        }


        return "$"+val+".00";
    }

    public ArrayList<String> getSectionStatus() {
        return sectionStatus;
    }

    public void setSectionStatus(ArrayList<String> sectionStatus) {
        this.sectionStatus = sectionStatus;
    }

    public String getRecieptFilePath() {
        return recieptFilePath;
    }

    public void setRecieptFilePath(String recieptFilePath) {
        this.recieptFilePath = recieptFilePath;
    }

    public String getFilingStatus() {
        return filingStatus;
    }

    public void setFilingStatus(String filingStatus) {
        this.filingStatus = filingStatus;
    }

    public boolean isTradeMarkUploaded() {
        return tradeMarkUploaded;
    }

    public void setTradeMarkUploaded(boolean tradeMarkUploaded) {
        this.tradeMarkUploaded = tradeMarkUploaded;
    }

    public boolean isStandardTextMark() {
        return standardTextMark;
    }

    public void setStandardTextMark(boolean standardTextMark) {
        this.standardTextMark = standardTextMark;
    }

    public String getApplicationSignature() {
        return applicationSignature;
    }

    public void setApplicationSignature(String applicationSignature) {
        this.applicationSignature = applicationSignature;
    }

    public Date getApplicationDateSigned() {
        return applicationDateSigned;
    }

    public void setApplicationDateSigned(Date applicationDateSigned) {
        this.applicationDateSigned = applicationDateSigned;
    }

    public String getApplicationDateSignedDisplay() {
        return applicationDateSignedDisplay;
    }

    public void setApplicationDateSignedDisplay(String applicationDateSignedDisplay) {
        this.applicationDateSignedDisplay = applicationDateSignedDisplay;
    }

    public String getAppSignatoryName() {
        return appSignatoryName;
    }

    public void setAppSignatoryName(String appSignatoryName) {
        this.appSignatoryName = appSignatoryName;
    }

    public String getAppSignatoryPosition() {
        return appSignatoryPosition;
    }

    public void setAppSignatoryPosition(String appSignatoryPosition) {
        this.appSignatoryPosition = appSignatoryPosition;
    }

    public String getAppSignatoryPhone() {
        return appSignatoryPhone;
    }

    public void setAppSignatoryPhone(String appSignatoryPhone) {
        this.appSignatoryPhone = appSignatoryPhone;
    }

    public String getSignatureType() {
        return signatureType;
    }

    public void setSignatureType(String signatureType) {
        this.signatureType = signatureType;
    }

    public boolean isSignDirect() {
        return signDirect;
    }

    public void setSignDirect(boolean signDirect) {
        this.signDirect = signDirect;
    }

    public boolean isDeclarationApplicantIsOwner() {
        return declarationApplicantIsOwner;
    }

    public void setDeclarationApplicantIsOwner(boolean declarationApplicantIsOwner) {
        this.declarationApplicantIsOwner = declarationApplicantIsOwner;
    }

    public boolean isDeclarationMarkInUse() {
        return declarationMarkInUse;
    }

    public void setDeclarationMarkInUse(boolean declarationMarkInUse) {
        this.declarationMarkInUse = declarationMarkInUse;
    }

    public boolean isDeclarationConcurrentUser() {
        return declarationConcurrentUser;
    }

    public void setDeclarationConcurrentUser(boolean declarationConcurrentUser) {
        this.declarationConcurrentUser = declarationConcurrentUser;
    }

    public boolean isDeclarationEvidenceSupport() {
        return declarationEvidenceSupport;
    }

    public void setDeclarationEvidenceSupport(boolean declarationEvidenceSupport) {
        this.declarationEvidenceSupport = declarationEvidenceSupport;
    }

    public boolean isDeclarationWarningFalseStatement() {
        return declarationWarningFalseStatement;
    }

    public void setDeclarationWarningFalseStatement(boolean declarationWarningFalseStatement) {
        this.declarationWarningFalseStatement = declarationWarningFalseStatement;
    }

    public Integer getBaseFee() {
        return baseFee;
    }

    public void setBaseFee(Integer baseFee) {
        this.baseFee = baseFee;
    }

    public String getConcurrentUseEvidenceFilePath() {
        return concurrentUseEvidenceFilePath;
    }

    public void setConcurrentUseEvidenceFilePath(String concurrentUseEvidenceFilePath) {
        this.concurrentUseEvidenceFilePath = concurrentUseEvidenceFilePath;
    }

    public boolean isConcurrentUse() {
        return concurrentUse;
    }

    public void setConcurrentUse(boolean concurrentUse) {
        this.concurrentUse = concurrentUse;
    }

    public boolean isConcurrentTypeCourtDecree() {
        return concurrentTypeCourtDecree;
    }

    public void setConcurrentTypeCourtDecree(boolean concurrentTypeCourtDecree) {
        this.concurrentTypeCourtDecree = concurrentTypeCourtDecree;
    }

    public boolean isConcurrentTypePriorDecision() {
        return concurrentTypePriorDecision;
    }

    public void setConcurrentTypePriorDecision(boolean concurrentTypePriorDecision) {
        this.concurrentTypePriorDecision = concurrentTypePriorDecision;
    }

    public boolean isConcurrentTypeWrittenConsent() {
        return concurrentTypeWrittenConsent;
    }

    public void setConcurrentTypeWrittenConsent(boolean concurrentTypeWrittenConsent) {
        this.concurrentTypeWrittenConsent = concurrentTypeWrittenConsent;
    }

    public boolean isConcurrentTypeEarlierFirstUse() {
        return concurrentTypeEarlierFirstUse;
    }

    public void setConcurrentTypeEarlierFirstUse(boolean concurrentTypeEarlierFirstUse) {
        this.concurrentTypeEarlierFirstUse = concurrentTypeEarlierFirstUse;
    }

    public boolean isConcurrentTypeSet() {
        return concurrentTypeSet;
    }

    public void setConcurrentTypeSet(boolean concurrentTypeSet) {
        this.concurrentTypeSet = concurrentTypeSet;
    }

    public boolean isValidateTEASFields() {
        return validateTEASFields;
    }

    public void setValidateTEASFields(boolean validateTEASFields) {
        this.validateTEASFields = validateTEASFields;
    }

    public boolean isDeclarationApplicantIsOwnerSet() {
        return declarationApplicantIsOwnerSet;
    }

    public void setDeclarationApplicantIsOwnerSet(boolean declarationApplicantIsOwnerSet) {
        this.declarationApplicantIsOwnerSet = declarationApplicantIsOwnerSet;
    }

    public boolean isDeclarationMarkInUseSet() {
        return declarationMarkInUseSet;
    }

    public void setDeclarationMarkInUseSet(boolean declarationMarkInUseSet) {
        this.declarationMarkInUseSet = declarationMarkInUseSet;
    }

    public boolean isDeclarationSpecimen() {
        return declarationSpecimen;
    }

    public void setDeclarationSpecimen(boolean declarationSpecimen) {
        this.declarationSpecimen = declarationSpecimen;
    }

    public boolean isDeclarationSpecimenSet() {
        return declarationSpecimenSet;
    }

    public void setDeclarationSpecimenSet(boolean declarationSpecimenSet) {
        this.declarationSpecimenSet = declarationSpecimenSet;
    }

    public boolean isDeclarationConcurrentUserSet() {
        return declarationConcurrentUserSet;
    }

    public void setDeclarationConcurrentUserSet(boolean declarationConcurrentUserSet) {
        this.declarationConcurrentUserSet = declarationConcurrentUserSet;
    }

    public boolean isDeclarationEvidenceSupportSet() {
        return declarationEvidenceSupportSet;
    }

    public void setDeclarationEvidenceSupportSet(boolean declarationEvidenceSupportSet) {
        this.declarationEvidenceSupportSet = declarationEvidenceSupportSet;
    }

    public boolean isDeclarationWarningFalseStatementSet() {
        return declarationWarningFalseStatementSet;
    }

    public void setDeclarationWarningFalseStatementSet(boolean declarationWarningFalseStatementSet) {
        this.declarationWarningFalseStatementSet = declarationWarningFalseStatementSet;
    }

    public boolean isProvideMiscInfo() {
        return provideMiscInfo;
    }

    public void setProvideMiscInfo(boolean provideMiscInfo) {
        this.provideMiscInfo = provideMiscInfo;
    }

    public boolean isProvideMiscInfoFlagSet() {
        return provideMiscInfoFlagSet;
    }

    public void setProvideMiscInfoFlagSet(boolean provideMiscInfoFlagSet) {
        this.provideMiscInfoFlagSet = provideMiscInfoFlagSet;
    }

    public boolean isConcurrentUseSet() {
        return concurrentUseSet;
    }

    public void setConcurrentUseSet(boolean concurrentUseSet) {
        this.concurrentUseSet = concurrentUseSet;
    }

    public boolean isConcurrentTypeCourtDecreeSet() {
        return concurrentTypeCourtDecreeSet;
    }

    public void setConcurrentTypeCourtDecreeSet(boolean concurrentTypeCourtDecreeSet) {
        this.concurrentTypeCourtDecreeSet = concurrentTypeCourtDecreeSet;
    }

    public boolean isConcurrentTypePriorDecisionSet() {
        return concurrentTypePriorDecisionSet;
    }

    public void setConcurrentTypePriorDecisionSet(boolean concurrentTypePriorDecisionSet) {
        this.concurrentTypePriorDecisionSet = concurrentTypePriorDecisionSet;
    }

    public boolean isConcurrentTypeWrittenConsentSet() {
        return concurrentTypeWrittenConsentSet;
    }

    public void setConcurrentTypeWrittenConsentSet(boolean concurrentTypeWrittenConsentSet) {
        this.concurrentTypeWrittenConsentSet = concurrentTypeWrittenConsentSet;
    }

    public boolean isConcurrentTypeEarlierFirstUseSet() {
        return concurrentTypeEarlierFirstUseSet;
    }

    public void setConcurrentTypeEarlierFirstUseSet(boolean concurrentTypeEarlierFirstUseSet) {
        this.concurrentTypeEarlierFirstUseSet = concurrentTypeEarlierFirstUseSet;
    }

    public String getConcurentEvidenceDescription() {
        return concurentEvidenceDescription;
    }

    public void setConcurentEvidenceDescription(String concurentEvidenceDescription) {
        this.concurentEvidenceDescription = concurentEvidenceDescription;
    }

    public String getGeoAreaMarkInCommerce() {
        return GeoAreaMarkInCommerce;
    }

    public void setGeoAreaMarkInCommerce(String geoAreaMarkInCommerce) {
        GeoAreaMarkInCommerce = geoAreaMarkInCommerce;
    }

    public String getModeOfUse() {
        return modeOfUse;
    }

    public void setModeOfUse(String modeOfUse) {
        this.modeOfUse = modeOfUse;
    }

    public String getTtabProceedingNumber() {
        return ttabProceedingNumber;
    }

    public void setTtabProceedingNumber(String ttabProceedingNumber) {
        this.ttabProceedingNumber = ttabProceedingNumber;
    }

    public String getConcurrentUserRegistrationNumber() {
        return concurrentUserRegistrationNumber;
    }

    public void setConcurrentUserRegistrationNumber(String concurrentUserRegistrationNumber) {
        this.concurrentUserRegistrationNumber = concurrentUserRegistrationNumber;
    }

    public String getConcurrentUserName() {
        return concurrentUserName;
    }

    public void setConcurrentUserName(String concurrentUserName) {
        this.concurrentUserName = concurrentUserName;
    }

    public String getConcurrentUserCountry() {
        return concurrentUserCountry;
    }

    public void setConcurrentUserCountry(String concurrentUserCountry) {
        this.concurrentUserCountry = concurrentUserCountry;
    }

    public String getConcurrentUserAddress1() {
        return concurrentUserAddress1;
    }

    public void setConcurrentUserAddress1(String concurrentUserAddress1) {
        this.concurrentUserAddress1 = concurrentUserAddress1;
    }

    public String getConcurrentUserCity() {
        return concurrentUserCity;
    }

    public void setConcurrentUserCity(String concurrentUserCity) {
        this.concurrentUserCity = concurrentUserCity;
    }

    public String getConcurrentUserState() {
        return concurrentUserState;
    }

    public void setConcurrentUserState(String concurrentUserState) {
        this.concurrentUserState = concurrentUserState;
    }

    public String getConcurrentUserZipcode() {
        return concurrentUserZipcode;
    }

    public void setConcurrentUserZipcode(String concurrentUserZipcode) {
        this.concurrentUserZipcode = concurrentUserZipcode;
    }

    public String getConcurrentUserGoodsAndService() {
        return concurrentUserGoodsAndService;
    }

    public void setConcurrentUserGoodsAndService(String concurrentUserGoodsAndService) {
        this.concurrentUserGoodsAndService = concurrentUserGoodsAndService;
    }

    public String getGeoAreaConcurrentUser() {
        return GeoAreaConcurrentUser;
    }

    public void setGeoAreaConcurrentUser(String geoAreaConcurrentUser) {
        GeoAreaConcurrentUser = geoAreaConcurrentUser;
    }

    public String getModeOfuseConcurrentUser() {
        return modeOfuseConcurrentUser;
    }

    public void setModeOfuseConcurrentUser(String modeOfuseConcurrentUser) {
        this.modeOfuseConcurrentUser = modeOfuseConcurrentUser;
    }

    public String getTimePeriodConcurrentUser() {
        return timePeriodConcurrentUser;
    }

    public void setTimePeriodConcurrentUser(String timePeriodConcurrentUser) {
        this.timePeriodConcurrentUser = timePeriodConcurrentUser;
    }

    public boolean isDeclarationNoOtherHasRight() {
        return declarationNoOtherHasRight;
    }

    public void setDeclarationNoOtherHasRight(boolean declarationNoOtherHasRight) {
        this.declarationNoOtherHasRight = declarationNoOtherHasRight;
    }

    public boolean isDeclarationNoOtherHasRightSet() {
        return declarationNoOtherHasRightSet;
    }

    public void setDeclarationNoOtherHasRightSet(boolean declarationNoOtherHasRightSet) {
        this.declarationNoOtherHasRightSet = declarationNoOtherHasRightSet;
    }

    public boolean isPrincipalRegister() {
        return principalRegister;
    }

    public void setPrincipalRegister(boolean principalRegister) {
        this.principalRegister = principalRegister;
    }

    public boolean isSupplementalRegister() {
        return supplementalRegister;
    }

    public boolean isRegisterTypeSet() {
        return registerTypeSet;
    }

    public void setRegisterTypeSet(boolean registerTypeSet) {
        this.registerTypeSet = registerTypeSet;
    }

    public void setSupplementalRegister(boolean supplementalRegister) {
        this.supplementalRegister = supplementalRegister;
    }

    public String getDistinctiveEvidenceFilePath() {
        return distinctiveEvidenceFilePath;
    }

    public void setDistinctiveEvidenceFilePath(String distinctiveEvidenceFilePath) {
        this.distinctiveEvidenceFilePath = distinctiveEvidenceFilePath;
    }

    public boolean isClaimDistinctiveness() {
        return claimDistinctiveness;
    }

    public void setClaimDistinctiveness(boolean claimDistinctiveness) {
        this.claimDistinctiveness = claimDistinctiveness;
    }

    public boolean isInheritantlyDistinctive() {
        return inheritantlyDistinctive;
    }

    public void setInheritantlyDistinctive(boolean inheritantlyDistinctive) {
        this.inheritantlyDistinctive = inheritantlyDistinctive;
    }

    public boolean isInheritantlyDistinctiveSet() {
        return inheritantlyDistinctiveSet;
    }

    public void setInheritantlyDistinctiveSet(boolean inheritantlyDistinctiveSet) {
        this.inheritantlyDistinctiveSet = inheritantlyDistinctiveSet;
    }

    public boolean isInheritantlyWhole() {
        return inheritantlyWhole;
    }

    public void setInheritantlyWhole(boolean inheritantlyWhole) {
        this.inheritantlyWhole = inheritantlyWhole;
    }

    public boolean isInheritantlyPart() {
        return inheritantlyPart;
    }

    public void setInheritantlyPart(boolean inheritantlyPart) {
        this.inheritantlyPart = inheritantlyPart;
    }

    public boolean isWholePartSet() {
        return wholePartSet;
    }

    public void setWholePartSet(boolean wholePartSet) {
        this.wholePartSet = wholePartSet;
    }

    public String getInPartClaimDescription() {
        return inPartClaimDescription;
    }

    public void setInPartClaimDescription(String inPartClaimDescription) {
        this.inPartClaimDescription = inPartClaimDescription;
    }

    public boolean isDistinctClaimBasedEvidence() {
        return distinctClaimBasedEvidence;
    }

    public void setDistinctClaimBasedEvidence(boolean distinctClaimBasedEvidence) {
        this.distinctClaimBasedEvidence = distinctClaimBasedEvidence;
    }

    public boolean isDistinctClaimBasedPriorReg() {
        return distinctClaimBasedPriorReg;
    }

    public void setDistinctClaimBasedPriorReg(boolean distinctClaimBasedPriorReg) {
        this.distinctClaimBasedPriorReg = distinctClaimBasedPriorReg;
    }

    public String getDistinctClaimBasedPriorRegNumber() {
        return distinctClaimBasedPriorRegNumber;
    }

    public void setDistinctClaimBasedPriorRegNumber(String distinctClaimBasedPriorRegNumber) {
        this.distinctClaimBasedPriorRegNumber = distinctClaimBasedPriorRegNumber;
    }

    public boolean isDistinctClaimBasedFiveYOU() {
        return distinctClaimBasedFiveYOU;
    }

    public void setDistinctClaimBasedFiveYOU(boolean distinctClaimBasedFiveYOU) {
        this.distinctClaimBasedFiveYOU = distinctClaimBasedFiveYOU;
    }

    public boolean isConcurrentEvidentFileTypeWord() {
        return concurrentEvidentFileTypeWord;
    }

    public void setConcurrentEvidentFileTypeWord(boolean concurrentEvidentFileTypeWord) {
        this.concurrentEvidentFileTypeWord = concurrentEvidentFileTypeWord;
    }

    public String getConcurrentUseEvidenceFileName() {
        return concurrentUseEvidenceFileName;
    }

    public void setConcurrentUseEvidenceFileName(String concurrentUseEvidenceFileName) {
        this.concurrentUseEvidenceFileName = concurrentUseEvidenceFileName;
    }

    public String getDistinctiveEvidenceFileName() {
        return distinctiveEvidenceFileName;
    }

    public void setDistinctiveEvidenceFileName(String distinctiveEvidenceFileName) {
        this.distinctiveEvidenceFileName = distinctiveEvidenceFileName;
    }

    public boolean isMiscInfoImageTypeWord() {
        return miscInfoImageTypeWord;
    }

    public void setMiscInfoImageTypeWord(boolean miscInfoImageTypeWord) {
        this.miscInfoImageTypeWord = miscInfoImageTypeWord;
    }

    public String getMiscInfoImageName() {
        return miscInfoImageName;
    }

    public void setMiscInfoImageName(String miscInfoImageName) {
        this.miscInfoImageName = miscInfoImageName;
    }

    public boolean isMiscInfoImageUploaded() {
        return miscInfoImageUploaded;
    }

    public void setMiscInfoImageUploaded(boolean miscInfoImageUploaded) {
        this.miscInfoImageUploaded = miscInfoImageUploaded;
    }

    public boolean isConcurrentUserNoRegistrationClaim() {
        return concurrentUserNoRegistrationClaim;
    }

    public void setConcurrentUserNoRegistrationClaim(boolean concurrentUserNoRegistrationClaim) {
        this.concurrentUserNoRegistrationClaim = concurrentUserNoRegistrationClaim;
    }

    public boolean isConcurrentUserNoRegistrationClaimSet() {
        return concurrentUserNoRegistrationClaimSet;
    }

    public void setConcurrentUserNoRegistrationClaimSet(boolean concurrentUserNoRegistrationClaimSet) {
        this.concurrentUserNoRegistrationClaimSet = concurrentUserNoRegistrationClaimSet;
    }

    public boolean isConcurrentUseEvidenceFileUploaded() {
        return concurrentUseEvidenceFileUploaded;
    }

    public void setConcurrentUseEvidenceFileUploaded(boolean concurrentUseEvidenceFileUploaded) {
        this.concurrentUseEvidenceFileUploaded = concurrentUseEvidenceFileUploaded;
    }

    public boolean isUseInAnotherForm() {
        return useInAnotherForm;
    }

    public void setUseInAnotherForm(boolean useInAnotherForm) {
        this.useInAnotherForm = useInAnotherForm;
    }

    public boolean isUseInAnotherFormCurrent() {
        return useInAnotherFormCurrent;
    }

    public void setUseInAnotherFormCurrent(boolean useInAnotherFormCurrent) {
        this.useInAnotherFormCurrent = useInAnotherFormCurrent;
    }

    public boolean isUseInAnotherFormCurrentSet() {
        return useInAnotherFormCurrentSet;
    }

    public void setUseInAnotherFormCurrentSet(boolean useInAnotherFormCurrentSet) {
        this.useInAnotherFormCurrentSet = useInAnotherFormCurrentSet;
    }

    public boolean isUserInAnotherFormWhole() {
        return userInAnotherFormWhole;
    }

    public void setUserInAnotherFormWhole(boolean userInAnotherFormWhole) {
        this.userInAnotherFormWhole = userInAnotherFormWhole;
    }

    public boolean isUseInAnotherFormPart() {
        return useInAnotherFormPart;
    }

    public void setUseInAnotherFormPart(boolean useInAnotherFormPart) {
        this.useInAnotherFormPart = useInAnotherFormPart;
    }

    public boolean isUserInAnotherFormWholePartSet() {
        return userInAnotherFormWholePartSet;
    }

    public void setUserInAnotherFormWholePartSet(boolean userInAnotherFormWholePartSet) {
        this.userInAnotherFormWholePartSet = userInAnotherFormWholePartSet;
    }

    public String getUserInAnotherFormMarkpart() {
        return userInAnotherFormMarkpart;
    }

    public void setUserInAnotherFormMarkpart(String userInAnotherFormMarkpart) {
        this.userInAnotherFormMarkpart = userInAnotherFormMarkpart;
    }

    public Date getUseInAnotherFormFirstUseDate() {
        return useInAnotherFormFirstUseDate;
    }

    public void setUseInAnotherFormFirstUseDate(Date useInAnotherFormFirstUseDate) {
        this.useInAnotherFormFirstUseDate = useInAnotherFormFirstUseDate;
    }

    public Date getUseInAnotherFormFirstCommerceDate() {
        return useInAnotherFormFirstCommerceDate;
    }

    public void setUseInAnotherFormFirstCommerceDate(Date useInAnotherFormFirstCommerceDate) {
        this.useInAnotherFormFirstCommerceDate = useInAnotherFormFirstCommerceDate;
    }

    public String  getUseInAnotherFormFirstUseDateDisplay(){
        if(useInAnotherFormFirstUseDate != null) {
            return useInAnotherFormFirstUseDate.toString().substring(0, 10);
        }
        else {
            return "";
        }
    }

    public String  getUseInAnotherFormFirstCommerceDateDisplay(){
        if(useInAnotherFormFirstCommerceDate != null) {
            return useInAnotherFormFirstCommerceDate.toString().substring(0, 10);
        }
        else {
            return "";
        }
    }

    public boolean isDeclarationAll() {
        return declarationAll;
    }

    public void setDeclarationAll(boolean declarationAll) {
        this.declarationAll = declarationAll;
    }

    @Nullable
    public Set<Petition> getPetitions() {
        return petitions;
    }

    public void setPetitions(@Nullable Set<Petition> petitions) {
        this.petitions = petitions;
    }


    public Set<Integer> getUniqueClassNumberforGS(){

       Set<Integer> uniqeIDS = new HashSet<>();

        for(Iterator<GoodAndService> iter = goodAndServices.iterator(); iter.hasNext(); ) {
            GoodAndService current = iter.next();


               uniqeIDS.add(Integer.valueOf(current.getClassNumber()));

        }


        return uniqeIDS;

    }

    public class CustomComparator implements Comparator<GoodAndService> {
        @Override
        public int compare(GoodAndService o1, GoodAndService o2) {

            return o1.getClassDescription().compareTo(o2.getClassDescription());
        }
    }


    public class CustomComparatorGSClassCategory implements Comparator<GSClassCategory> {
        @Override
        public int compare(GSClassCategory o1, GSClassCategory o2) {

            return o1.getClassCategoryNumber().compareTo(o2.getClassCategoryNumber());
        }
    }
}
