package com.thorton.grant.uspto.prototypewebapp.model.entities.USPTO.tradeMark.application.types;


import com.thorton.grant.uspto.prototypewebapp.model.entities.USPTO.tradeMark.application.actions.OfficeActions;
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
        actions = new HashSet<>();
        owners = new HashSet<>();


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




    /////////////////////////////////////////////////////////////////////
    // stage 2
    /////////////////////////////////////////////////////////////////////
    @OneToOne(cascade = CascadeType.ALL)
    @Nullable
    private TradeMark tradeMark;

    private boolean tradeMarkUploaded;


    @OneToMany(cascade =  CascadeType.ALL)
    @Nullable
    private Set<OfficeActions> actions;
    ////////////////////////////////////////////////////////


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
    private String miscInformation;
    private String miscInfoImagePath;





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

    private boolean declarationApplicantIsOwner;  // check box 1
    private boolean declarationMarkInUse;   // check box 2
    private boolean declarationMarkInUseSpecimen; // check box 3
    private boolean declarationConcurrentUser; // check box 4
    private boolean declarationEvidenceSupport;  // check box 5
    private boolean declarationWarningFalseStatement; // check box 6



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

    public Lawyer findAttorneyContactByDisplayName(String name){
        Lawyer lawyer = null;
        for(Iterator<Lawyer> iter = availableLawyers.iterator(); iter.hasNext(); ) {
            Lawyer current = iter.next();

            if((current.getFirstName()+" "+current.getLastName()).equals(name)){
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


    public Set<OfficeActions> getActions() {
        return actions;
    }

    public void setActions(@Nullable Set<OfficeActions> actions) {
        this.actions = actions;
    }



    public Long getId() {
        return id;
    }

    public void copyAvailableLawyers(Set<Lawyer> availableLawyers){

        for(Iterator<Lawyer> iter = availableLawyers.iterator(); iter.hasNext(); ) {
           this.availableLawyers.add(new Lawyer( iter.next() ));
        }

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
                    gsClassCategory.setClassCategoryDescr(current.getClassSpecimenDescr());
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
            return"("+ (getUniqueClassNumberforGS().size()-1)+")$"+((getUniqueClassNumberforGS().size()-1)*275);
        }
        else {
            return "(0)$0";
        }



    }

    public String getBasicFeeCalculationString(){


            return "$275";




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
        return getUniqueClassNumberforGS().size()+"";
    }

    public String getTotalFeeString(){
        String val = "";
                if(getUniqueClassNumberforGS().size() > 0){
                  val=  (275+Integer.valueOf(getUniqueClassNumberforGS().size()-1)*275)+"";
                }
                else{
                    val = "275";

                }


        return "Fee $"+val;
    }
    public String getTotalFeeAmount(){
        String val = "";
        if(getUniqueClassNumberforGS().size() > 0){
            val=  (275+Integer.valueOf(getUniqueClassNumberforGS().size()-1)*275)+"";
        }
        else{
            val = "275";

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

    public boolean isDeclarationMarkInUseSpecimen() {
        return declarationMarkInUseSpecimen;
    }

    public void setDeclarationMarkInUseSpecimen(boolean declarationMarkInUseSpecimen) {
        this.declarationMarkInUseSpecimen = declarationMarkInUseSpecimen;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BaseTrademarkApplication that = (BaseTrademarkApplication) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(trademarkName, that.trademarkName) &&
                Objects.equals(applicationInternalID, that.applicationInternalID) &&
                Objects.equals(ownerEmail, that.ownerEmail) &&
                Objects.equals(availableLawyers, that.availableLawyers) &&
                Objects.equals(ownerType, that.ownerType) &&
                Objects.equals(ownerSubType, that.ownerSubType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, trademarkName, applicationInternalID, ownerEmail, availableLawyers, ownerType, ownerSubType);
    }

    @Override
    public String toString() {
        return "BaseTrademarkApplication{" +
                "id=" + id +
                ", trademarkName='" + trademarkName + '\'' +
                ", applicationInternalID='" + applicationInternalID + '\'' +
                ", isAttorneySet=" + isAttorneySet +
                ", isAttorneyFiling=" + isAttorneyFiling +
                ", isForeignEnityFiling=" + isForeignEnityFiling +
                ", currentStage='" + currentStage + '\'' +
                ", lastViewModel='" + lastViewModel + '\'' +
                ", ownerEmail='" + ownerEmail + '\'' +
                ", ptoUser=" + ptoUser +
                ", primaryLawyer=" + primaryLawyer +
                ", availableLawyers=" + availableLawyers +
                ", owners=" + owners +
                ", tradeMark=" + tradeMark +
                ", actions=" + actions +
                ", ownerType='" + ownerType + '\'' +
                ", ownerSubType='" + ownerSubType + '\'' +
                '}';
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
