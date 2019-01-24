package com.thorton.grant.uspto.prototypewebapp.model.entities.USPTO.tradeMark.assets;


import org.springframework.lang.Nullable;


import java.util.ArrayList;
import java.util.Iterator;


public class GSClassCategory {


    public GSClassCategory() {

        goodAndServices = new ArrayList<>();
    }

    private ArrayList<GoodAndService> goodAndServices;
    private  Integer classCategoryNumber;

    private String classCategoryName;

    private String classCategoryImagePath;

    private String classCategoryDescr;


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

    public String getClassCategoryDescr() {
        return classCategoryDescr;
    }

    public void setClassCategoryDescr(String classCategoryDescr) {
        this.classCategoryDescr = classCategoryDescr;
    }
}
