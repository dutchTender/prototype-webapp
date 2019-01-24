package com.thorton.grant.uspto.prototypewebapp.model.entities.DTO.application;

import java.util.ArrayList;

public class ContactsDisplayDTO {


    private ArrayList<String> contactNames;

    private ArrayList<String> contactEmails;

    private ArrayList<String> contactFirms;

    private ArrayList<String> contactEntitySubType;

    public ArrayList<String> getContactEntitySubType() {
        return contactEntitySubType;
    }

    public void setContactEntitySubType(ArrayList<String> contactEntitySubType) {
        this.contactEntitySubType = contactEntitySubType;
    }

    public ArrayList<String> getContactNames() {
        return contactNames;
    }

    public void setContactNames(ArrayList<String> contactNames) {
        this.contactNames = contactNames;
    }

    public ArrayList<String> getContactEmails() {
        return contactEmails;
    }

    public void setContactEmails(ArrayList<String> contactEmails) {
        this.contactEmails = contactEmails;
    }

    public ArrayList<String> getContactFirms() {
        return contactFirms;
    }

    public void setContactFirms(ArrayList<String> contactFirms) {
        this.contactFirms = contactFirms;
    }
}
