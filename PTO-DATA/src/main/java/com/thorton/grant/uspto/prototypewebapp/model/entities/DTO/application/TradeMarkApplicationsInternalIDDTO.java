package com.thorton.grant.uspto.prototypewebapp.model.entities.DTO.application;

import java.util.ArrayList;

public class TradeMarkApplicationsInternalIDDTO {


    private ArrayList<String> myApplicationIDs;

    private ArrayList<String> myApplicationOwners;

    private ArrayList<String> myApplicationStatues;

    private ArrayList<String> myApplicationTMname;

    public ArrayList<String> getMyApplicationTMname() {
        return myApplicationTMname;
    }

    public void setMyApplicationTMname(ArrayList<String> myApplicationTMname) {
        this.myApplicationTMname = myApplicationTMname;
    }

    public ArrayList<String> getMyApplicationOwners() {
        return myApplicationOwners;
    }

    public void setMyApplicationOwners(ArrayList<String> myApplicationOwners) {
        this.myApplicationOwners = myApplicationOwners;
    }

    public ArrayList<String> getMyApplicationStatues() {
        return myApplicationStatues;
    }

    public void setMyApplicationStatues(ArrayList<String> myApplicationStatues) {
        this.myApplicationStatues = myApplicationStatues;
    }

    public ArrayList<String> getMyApplicationIDs() {
        return myApplicationIDs;
    }

    public void setMyApplicationIDs(ArrayList<String> myApplicationIDs) {
        this.myApplicationIDs = myApplicationIDs;
    }
}
