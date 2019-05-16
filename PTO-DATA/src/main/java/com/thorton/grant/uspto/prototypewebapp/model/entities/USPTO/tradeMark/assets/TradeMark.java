package com.thorton.grant.uspto.prototypewebapp.model.entities.USPTO.tradeMark.assets;

import com.thorton.grant.uspto.prototypewebapp.model.entities.USPTO.tradeMark.application.types.BaseTrademarkApplication;
import com.thorton.grant.uspto.prototypewebapp.model.entities.USPTO.user.PTOUser;
import com.thorton.grant.uspto.prototypewebapp.model.entities.base.BaseEntity;
import org.springframework.lang.Nullable;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.Objects;


@Entity
@Table(name = "trade_marks")
public class TradeMark extends BaseEntity {


    public TradeMark() {

        disclaimerDeclarationList =  new ArrayList<>();
    }

    private String PTOtradeMarkID;
    private String description;


    private String TradeMarkBaseType; // TradeMark or serviceMark,
    private String TradeMarkOptions; // this is uncommon


    private String trademarkDesignType;  // ex: design with text

    private String trademarkImagePath;
    private String trademarkBWImagePath;

    private String baseStoragePath;




    private String trademarkStandardCharacterText;
    private boolean standardCharacterMark;




    private String markLiteral;
    private String markDescription;


    // color claim fields
    @Nullable
    private boolean markColorClaim;



    private String markColors;
    private String markColorDescription;



    private boolean markColorClaimBW = false; // black and white or color
    private boolean acceptBWmark;
    private String markBWDescription;

    private boolean colorClaimSet;
    private boolean acceptBWmarkSet;






    // translation
    private boolean foreignLanguageTranslationWording;
    private boolean translationSet;

    private String  foreignLanguageType_translation;
    private String foreignLanguageTranslationOriginalText;
    private String foreignLanguageTranslationUSText;




    // transliteration
    private boolean foreignLanguateTransliterationWording;
    private boolean tranlierationSet;

    private String  foreignLanguageType_transliteration;
    private String foreignLanguateTransliterationOriginalText;
    private String foreignLanguateTransliterationUSText;

    // name / portrait / Signature
    private boolean containNamePortaitSignature;
    private boolean namePortraitSet;

    private boolean consentFileUploaded = false;

    private String trademarkConsentFilePath; // pdf or .doc

    private String trademarkConsentFileName;
    private String trademarkConsentDownLoadPath;




    private boolean isName;
    private String nameFirstName;
    private String nameMiddleName;
    private String nameLastName;

    private boolean isPortrait;
    private String portraitFirstName;
    private String portraitMiddleName;
    private String portraitLastName;

    private boolean isSignature;
    private String signatureFirstName;
    private String signatureMiddleName;
    private String signatureLastName;

    private boolean isNPSLivingPerson;
    private boolean NPSLivingPersonSet;













    // disclaimer fields

    private boolean actvieDisclaimer;
    private boolean disclaimerSet;

    private String disclaimerDeclaration;

    private ArrayList<String> disclaimerDeclarationList;

    // this needs to be an array list ...see how we implemented
    // this for attorney docket number




    // prior registration fields



    private boolean priorRegistratoin;
    private boolean priorRegistratoinSet;

    private String priorRegistrationNumber;


    // meaning and significance fields

    private boolean markWordingHasSignifigance;
    private boolean meaningSet;

    private String  markWordingSignifiganceText;

    private String markWordingIndustryText;





    @OneToOne
    private BaseTrademarkApplication initialFilingInfo;

    private boolean typeSet = false;

    @ManyToOne
    private PTOUser trademarkOwner;


    public String getPTOtradeMarkID() {
        return PTOtradeMarkID;
    }

    public void setPTOtradeMarkID(String PTOtradeMarkID) {
        this.PTOtradeMarkID = PTOtradeMarkID;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTrademarkImagePath() {


        if(isMarkColorClaim()){
            return trademarkImagePath;
        }
        else {
            if(trademarkDesignType.equals("Standard Character")){
                return  "";
            }
            else {
                if(isMarkColorClaimBW()) {
                    return trademarkBWImagePath;
                }
                else {
                    return trademarkImagePath;
                }
            }
        }

    }

    public void setTrademarkImagePath(String trademarkImagePath) {
        this.trademarkImagePath = trademarkImagePath;
    }

    public BaseTrademarkApplication getInitialFilingInfo() {
        return initialFilingInfo;
    }

    public void setInitialFilingInfo(BaseTrademarkApplication initialFilingInfo) {
        this.initialFilingInfo = initialFilingInfo;
    }

    public PTOUser getTrademarkOwner() {
        return trademarkOwner;
    }

    public void setTrademarkOwner(PTOUser trademarkOwner) {
        this.trademarkOwner = trademarkOwner;
    }

    public String getTrademarkDesignType() {
        return trademarkDesignType;
    }

    public void setTrademarkDesignType(String trademarkDesignType) {
        this.trademarkDesignType = trademarkDesignType;
    }

    public boolean isTypeSet() {
        return typeSet;
    }

    public void setTypeSet(boolean typeSet) {
        this.typeSet = typeSet;
    }

    public String getTrademarkBWImagePath() {
        return trademarkBWImagePath;
    }

    public void setTrademarkBWImagePath(String trademarkBWImagePath) {
        this.trademarkBWImagePath = trademarkBWImagePath;
    }

    public String getMarkLiteral() {
        return markLiteral;
    }

    public void setMarkLiteral(String markLiteral) {
        this.markLiteral = markLiteral;
    }

    public String getMarkDescription() {

       return markDescription;

    }

    public void setMarkDescription(String markDescription) {
        this.markDescription = markDescription;
    }

    public boolean isMarkColorClaim() {
        return markColorClaim;
    }

    public void setMarkColorClaim(boolean markColorClaim) {
        this.markColorClaim = markColorClaim;
    }

    public boolean isForeignLanguageTranslationWording() {
        return foreignLanguageTranslationWording;
    }

    public void setForeignLanguageTranslationWording(boolean foreignLanguageTranslationWording) {
        this.foreignLanguageTranslationWording = foreignLanguageTranslationWording;
    }

    public String getForeignLanguageTranslationOriginalText() {
        return foreignLanguageTranslationOriginalText;
    }

    public void setForeignLanguageTranslationOriginalText(String foreignLanguageTranslationOriginalText) {
        this.foreignLanguageTranslationOriginalText = foreignLanguageTranslationOriginalText;
    }

    public String getForeignLanguageTranslationUSText() {
        return foreignLanguageTranslationUSText;
    }

    public void setForeignLanguageTranslationUSText(String foreignLanguageTranslationUSText) {
        this.foreignLanguageTranslationUSText = foreignLanguageTranslationUSText;
    }

    public boolean isForeignLanguateTransliterationWording() {
        return foreignLanguateTransliterationWording;
    }

    public void setForeignLanguateTransliterationWording(boolean foreignLanguateTransliterationWording) {
        this.foreignLanguateTransliterationWording = foreignLanguateTransliterationWording;
    }

    public String getForeignLanguateTransliterationOriginalText() {
        return foreignLanguateTransliterationOriginalText;
    }

    public void setForeignLanguateTransliterationOriginalText(String foreignLanguateTransliterationOriginalText) {
        this.foreignLanguateTransliterationOriginalText = foreignLanguateTransliterationOriginalText;
    }

    public String getForeignLanguateTransliterationUSText() {
        return foreignLanguateTransliterationUSText;
    }

    public void setForeignLanguateTransliterationUSText(String foreignLanguateTransliterationUSText) {
        this.foreignLanguateTransliterationUSText = foreignLanguateTransliterationUSText;
    }

    public boolean isContainNamePortaitSignature() {
        return containNamePortaitSignature;
    }

    public void setContainNamePortaitSignature(boolean containNamePortaitSignature) {
        this.containNamePortaitSignature = containNamePortaitSignature;
    }

    public String getTradeMarkBaseType() {
        return TradeMarkBaseType;
    }

    public void setTradeMarkBaseType(String tradeMarkBaseType) {
        TradeMarkBaseType = tradeMarkBaseType;
    }

    public String getTradeMarkOptions() {
        return TradeMarkOptions;
    }

    public void setTradeMarkOptions(String tradeMarkOptions) {
        TradeMarkOptions = tradeMarkOptions;
    }

    public boolean isMarkColorClaimBW() {
        return markColorClaimBW;
    }

    public void setMarkColorClaimBW(boolean markColorClaimBW) {
        this.markColorClaimBW = markColorClaimBW;
    }

    public String getMarkColors() {
        return markColors;
    }

    public void setMarkColors(String markColors) {
        this.markColors = markColors;
    }

    public String getMarkColorDescription() {
        return markColorDescription;
    }

    public void setMarkColorDescription(String markColorDescription) {
        this.markColorDescription = markColorDescription;
    }

    public boolean isAcceptBWmark() {
        return acceptBWmark;
    }

    public void setAcceptBWmark(boolean acceptBWmark) {
        this.acceptBWmark = acceptBWmark;
    }

    public String getMarkBWDescription() {
        return markBWDescription;
    }

    public void setMarkBWDescription(String markBWDescription) {
        this.markBWDescription = markBWDescription;
    }

    public String getForeignLanguageType_translation() {
        return foreignLanguageType_translation;
    }

    public void setForeignLanguageType_translation(String foreignLanguageType_translation) {
        this.foreignLanguageType_translation = foreignLanguageType_translation;
    }

    public String getForeignLanguageType_transliteration() {
        return foreignLanguageType_transliteration;
    }

    public void setForeignLanguageType_transliteration(String foreignLanguageType_transliteration) {
        this.foreignLanguageType_transliteration = foreignLanguageType_transliteration;
    }


    public boolean isActvieDisclaimer() {
        return actvieDisclaimer;
    }

    public void setActvieDisclaimer(boolean actvieDisclaimer) {
        this.actvieDisclaimer = actvieDisclaimer;
    }

    public String getDisclaimerDeclaration() {
        return disclaimerDeclaration;
    }

    public void setDisclaimerDeclaration(String disclaimerDeclaration) {
        this.disclaimerDeclaration = disclaimerDeclaration;
    }

    public boolean isPriorRegistratoin() {
        return priorRegistratoin;
    }

    public void setPriorRegistratoin(boolean priorRegistratoin) {
        this.priorRegistratoin = priorRegistratoin;
    }

    public String getPriorRegistrationNumber() {
        return priorRegistrationNumber;
    }

    public void setPriorRegistrationNumber(String priorRegistrationNumber) {
        this.priorRegistrationNumber = priorRegistrationNumber;
    }

    public boolean isMarkWordingHasSignifigance() {
        return markWordingHasSignifigance;
    }

    public void setMarkWordingHasSignifigance(boolean markWordingHasSignifigance) {
        this.markWordingHasSignifigance = markWordingHasSignifigance;
    }

    public String getMarkWordingSignifiganceText() {
        return markWordingSignifiganceText;
    }

    public void setMarkWordingSignifiganceText(String markWordingSignifiganceText) {
        this.markWordingSignifiganceText = markWordingSignifiganceText;
    }

    public String getTrademarkConsentFilePath() {
        return trademarkConsentFilePath;
    }

    public void setTrademarkConsentFilePath(String trademarkConsentFilePath) {
        this.trademarkConsentFilePath = trademarkConsentFilePath;
    }

    public boolean isConsentFileUploaded() {
        return consentFileUploaded;
    }

    public void setConsentFileUploaded(boolean consentFileUploaded) {
        this.consentFileUploaded = consentFileUploaded;
    }

    public boolean isName() {
        return isName;
    }

    public void setName(boolean name) {
        isName = name;
    }

    public String getNameFirstName() {
        return nameFirstName;
    }

    public void setNameFirstName(String nameFirstName) {
        this.nameFirstName = nameFirstName;
    }

    public String getNameMiddleName() {
        return nameMiddleName;
    }

    public void setNameMiddleName(String nameMiddleName) {
        this.nameMiddleName = nameMiddleName;
    }

    public String getNameLastName() {
        return nameLastName;
    }

    public void setNameLastName(String nameLastName) {
        this.nameLastName = nameLastName;
    }

    public boolean isPortrait() {
        return isPortrait;
    }

    public void setPortrait(boolean portrait) {
        isPortrait = portrait;
    }

    public String getPortraitFirstName() {
        return portraitFirstName;
    }

    public void setPortraitFirstName(String portraitFirstName) {
        this.portraitFirstName = portraitFirstName;
    }

    public String getPortraitMiddleName() {
        return portraitMiddleName;
    }

    public void setPortraitMiddleName(String portraitMiddleName) {
        this.portraitMiddleName = portraitMiddleName;
    }

    public String getPortraitLastName() {
        return portraitLastName;
    }

    public void setPortraitLastName(String portraitLastName) {
        this.portraitLastName = portraitLastName;
    }

    public boolean isSignature() {
        return isSignature;
    }

    public void setSignature(boolean signature) {
        isSignature = signature;
    }

    public String getSignatureFirstName() {
        return signatureFirstName;
    }

    public void setSignatureFirstName(String signatureFirstName) {
        this.signatureFirstName = signatureFirstName;
    }

    public String getSignatureMiddleName() {
        return signatureMiddleName;
    }

    public void setSignatureMiddleName(String signatureMiddleName) {
        this.signatureMiddleName = signatureMiddleName;
    }

    public String getSignatureLastName() {
        return signatureLastName;
    }

    public void setSignatureLastName(String signatureLastName) {
        this.signatureLastName = signatureLastName;
    }

    public boolean isNPSLivingPerson() {
        return isNPSLivingPerson;
    }

    public void setNPSLivingPerson(boolean NPSLivingPerson) {
        isNPSLivingPerson = NPSLivingPerson;
    }


    public String getTradeMarkPageTitle(){

        return "Trademark Details - "+trademarkDesignType;
    }

    public String getTrademarkStandardCharacterText() {
        return trademarkStandardCharacterText;
    }

    public void setTrademarkStandardCharacterText(String trademarkStandardCharacterText) {
        this.trademarkStandardCharacterText = trademarkStandardCharacterText;
    }

    public String getMarkWordingIndustryText() {
        return markWordingIndustryText;
    }

    public void setMarkWordingIndustryText(String markWordingIndustryText) {
        this.markWordingIndustryText = markWordingIndustryText;
    }

    public boolean isColorClaimSet() {
        return colorClaimSet;
    }

    public void setColorClaimSet(boolean colorClaimSet) {
        this.colorClaimSet = colorClaimSet;
    }

    public boolean isTranslationSet() {
        return translationSet;
    }

    public void setTranslationSet(boolean translationSet) {
        this.translationSet = translationSet;
    }

    public boolean isTranlierationSet() {
        return tranlierationSet;
    }

    public void setTranlierationSet(boolean tranlierationSet) {
        this.tranlierationSet = tranlierationSet;
    }

    public boolean isNamePortraitSet() {
        return namePortraitSet;
    }

    public void setNamePortraitSet(boolean namePortraitSet) {
        this.namePortraitSet = namePortraitSet;
    }

    public boolean isDisclaimerSet() {
        return disclaimerSet;
    }

    public void setDisclaimerSet(boolean disclaimerSet) {
        this.disclaimerSet = disclaimerSet;
    }

    public boolean isPriorRegistratoinSet() {
        return priorRegistratoinSet;
    }

    public void setPriorRegistratoinSet(boolean priorRegistratoinSet) {
        this.priorRegistratoinSet = priorRegistratoinSet;
    }

    public boolean isMeaningSet() {
        return meaningSet;
    }

    public void setMeaningSet(boolean meaningSet) {
        this.meaningSet = meaningSet;
    }

    //////////////////////////////////////////////////////////////////////////////
    // check color claim, then return the apporporiate image path, color or b&w
    //////////////////////////////////////////////////////////////////////////////
    public String getMarkImageDisplayPath(){

        if(markColorClaim == true){
            return getTrademarkImagePath();
        }
        else{
            if(markColorClaimBW == true){
                return getTrademarkBWImagePath();
            }
            else {
                // in case nothing is set
                return getTrademarkImagePath();
            }
        }

    }

    public String getBaseStoragePath() {
        return baseStoragePath;
    }

    public void setBaseStoragePath(String baseStoragePath) {
        this.baseStoragePath = baseStoragePath;
    }




    public boolean isStandardCharacterMark() {
        return standardCharacterMark;
    }

    public void setStandardCharacterMark(boolean standardCharacterMark) {
        this.standardCharacterMark = standardCharacterMark;
    }

    public boolean isAcceptBWmarkSet() {
        return acceptBWmarkSet;
    }

    public void setAcceptBWmarkSet(boolean acceptBWmarkSet) {
        this.acceptBWmarkSet = acceptBWmarkSet;
    }


    public String getMarkImagePhysicalPath(){


        String path = getMarkImageDisplayPath();


        path = path.replace("/files", this.baseStoragePath);

        return path;
    }

    public String getTrademarkConsentFileName() {
        return trademarkConsentFileName;
    }

    public void setTrademarkConsentFileName(String trademarkConsentFileName) {
        this.trademarkConsentFileName = trademarkConsentFileName;
    }

    public String getTrademarkConsentDownLoadPath() {
        return trademarkConsentDownLoadPath;
    }

    public void setTrademarkConsentDownLoadPath(String trademarkConsentDownLoadPath) {
        this.trademarkConsentDownLoadPath = trademarkConsentDownLoadPath;
    }

    public boolean isNPSLivingPersonSet() {
        return NPSLivingPersonSet;
    }

    public void setNPSLivingPersonSet(boolean NPSLivingPersonSet) {
        this.NPSLivingPersonSet = NPSLivingPersonSet;
    }

    public ArrayList<String> getDisclaimerDeclarationList() {
        return disclaimerDeclarationList;
    }

    public void setDisclaimerDeclarationList(ArrayList<String> disclaimerDeclarationList) {
        this.disclaimerDeclarationList = disclaimerDeclarationList;
    }

    public void addDisclaimerDeclaration( String disclaimerDeclaration){
        disclaimerDeclarationList.add(disclaimerDeclaration);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TradeMark tradeMark = (TradeMark) o;
        return Objects.equals(PTOtradeMarkID, tradeMark.PTOtradeMarkID) &&
                Objects.equals(description, tradeMark.description) &&
                Objects.equals(trademarkDesignType, tradeMark.trademarkDesignType) &&
                Objects.equals(trademarkImagePath, tradeMark.trademarkImagePath);
    }

    @Override
    public int hashCode() {
        return Objects.hash(PTOtradeMarkID, description, trademarkDesignType, trademarkImagePath);
    }

    @Override
    public String toString() {
        return "TradeMark{" +
                "PTOtradeMarkID='" + PTOtradeMarkID + '\'' +
                ", description='" + description + '\'' +
                ", trademarkDesignType='" + trademarkDesignType + '\'' +
                ", trademarkImagePath='" + trademarkImagePath + '\'' +
                ", initialFilingInfo=" + initialFilingInfo +
                ", trademarkOwner=" + trademarkOwner +
                '}';
    }
}
