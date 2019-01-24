package com.thorton.grant.uspto.prototypewebapp.model.entities.USPTO.user;


import com.thorton.grant.uspto.prototypewebapp.model.entities.USPTO.tradeMark.application.participants.Owner;
import com.thorton.grant.uspto.prototypewebapp.model.entities.USPTO.tradeMark.application.types.BaseTrademarkApplication;
import com.thorton.grant.uspto.prototypewebapp.model.entities.USPTO.tradeMark.application.participants.Lawyer;
import com.thorton.grant.uspto.prototypewebapp.model.entities.USPTO.tradeMark.assets.TradeMark;
import com.thorton.grant.uspto.prototypewebapp.model.entities.security.UserCredentials;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "PTOUsers")
public class PTOUser extends UserPersonalData {

    public PTOUser() {
        myTrademarks = new HashSet<>();
        myApplications = new HashSet<>();
        myLawyers = new HashSet<>();
        myOwners = new HashSet<>();
        myManagedContacts = new HashSet<>();
    }

    /////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////

    @OneToMany(cascade = CascadeType.ALL)
    private Set<TradeMark> myTrademarks;


    @OneToMany( cascade = CascadeType.ALL)
    private Set<BaseTrademarkApplication> myApplications;
    /////////////////////////////////////////////////////////
    // owning object i.e  ptoUser owns trademark applications
    /////////////////////////////////////////////////////////


    @OneToMany(cascade = CascadeType.ALL)
    private Set<Lawyer> myLawyers;

    @OneToMany(cascade = CascadeType.ALL)
    private Set<Owner> myOwners;


    @OneToMany(cascade = CascadeType.ALL)
    private Set<ManagedContact> myManagedContacts;






    @Column(name =  "profile_complete" )
    private boolean profileComplete = false;

    @OneToOne(cascade = CascadeType.ALL)
    private UserCredentials userCredentials ;


    private boolean useTwoFactorAuthentication = false;
    private String twoFactorAuthType = "email";






    /////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////
    private String continuationURL = null;

    public String getContinuationURL() {
        return continuationURL;
    }

    public void setContinuationURL(String continuationURL) {
        this.continuationURL = continuationURL;
    }

    public BaseTrademarkApplication addApplication(BaseTrademarkApplication newApplication){

            myApplications.add(newApplication);
        return  newApplication;
    }


    public Lawyer addLawyer(Lawyer newLawyer){

           myLawyers.add(newLawyer);
        return newLawyer;
    }

    public void removeLawyer(Lawyer lawyer){
        myLawyers.remove(lawyer);

    }

    public Lawyer findLawyerContactByEmail(String email){
        Lawyer lawyer = null;
        for(Iterator<Lawyer> iter = myLawyers.iterator(); iter.hasNext(); ) {
            //this.availableLawyers.add(new Lawyer( iter.next() ));

            Lawyer current = iter.next();

            if(current.getEmail().equals(email)){
                lawyer = current;
            }
        }
       return lawyer;
    }

    public  Owner findOwnerContactByEmail(String email){
        Owner owner = null;
        for(Iterator<Owner> iter = myOwners.iterator(); iter.hasNext(); ) {
            //this.availableLawyers.add(new Lawyer( iter.next() ));

            Owner current = iter.next();

            if(current.getEmail().equals(email)){
                owner = current;
            }
        }
        return owner;
    }


    public Owner addOwner(Owner newOwner){
        this.myOwners.add(newOwner);

        return newOwner;

    }
    public void removeOwner(Owner owner){
        this.myOwners.remove(owner);
    }


    public Set<Owner> getMyOwners() {
        return myOwners;
    }

    public void setMyOwners(Set<Owner> myOwners) {
        this.myOwners = myOwners;
    }

    public TradeMark addTradeMark(TradeMark newTrademark){

          myTrademarks.add(newTrademark);
        return  newTrademark;
    }

    public Set<ManagedContact> getMyManagedContacts() {
        return myManagedContacts;
    }

    public void setMyManagedContacts(Set<ManagedContact> myManagedContacts) {
        this.myManagedContacts = myManagedContacts;

    }

    public ManagedContact addManagedContact (ManagedContact newContact){
        myManagedContacts.add(newContact);
        return newContact;
    }
    public void removeManagedContact(ManagedContact contact){
        myManagedContacts.remove(contact);

    }
    public ManagedContact findManagedContactByEmail(String email){

        ManagedContact contact = null;
        for(Iterator<ManagedContact> iter = myManagedContacts.iterator(); iter.hasNext(); ) {
            //this.availableLawyers.add(new Lawyer( iter.next() ));

            ManagedContact current = iter.next();

            if(current.getEmail().equals(email)){
                contact = current;
            }
        }
        return contact;

    }

    public ManagedContact findManagedContactByDisplayName(String name){

        ManagedContact contact = null;
        for(Iterator<ManagedContact> iter = myManagedContacts.iterator(); iter.hasNext(); ) {
            //this.availableLawyers.add(new Lawyer( iter.next() ));

            ManagedContact current = iter.next();

            if(current.getDisplayName().equals(name)){
                contact = current;
            }
        }
        return contact;

    }


    public Set<ManagedContact> getAttorneyManagedContact(){
        Set<ManagedContact> attorneyContacts = new HashSet<>();

        for(Iterator<ManagedContact> iter = myManagedContacts.iterator(); iter.hasNext(); ) {
            //this.availableLawyers.add(new Lawyer( iter.next() ));

            ManagedContact current = iter.next();
            if(current.getContactType() == "attorney"){
               attorneyContacts.add(current);
            }

        }


        return attorneyContacts;
    }

    public Set<ManagedContact> getOwnerManagedContact(){
        Set<ManagedContact> ownerContacts = new HashSet<>();

        for(Iterator<ManagedContact> iter = myManagedContacts.iterator(); iter.hasNext(); ) {
            //this.availableLawyers.add(new Lawyer( iter.next() ));

            ManagedContact current = iter.next();
            if(current.getContactType() == "owner"){
                ownerContacts.add(current);
            }

        }


        return ownerContacts;
    }






    ///////////////////////////////////////////////////////////


    public Set<TradeMark> getMyTrademarks() {
        return myTrademarks;
    }

    public void setMyTrademarks(Set<TradeMark> myTrademarks) {
        this.myTrademarks = myTrademarks;
    }

    public Set<BaseTrademarkApplication> getMyApplications() {
        return myApplications;
    }

    public void setMyApplications(Set<BaseTrademarkApplication> myApplications) {
        this.myApplications = myApplications;
    }

    public Set<Lawyer> getMyLawyers() {
        return myLawyers;
    }

    public void setMyLawyers(Set<Lawyer> myLawyers) {
        this.myLawyers = myLawyers;
    }

    public boolean isProfileComplete() {
        return profileComplete;
    }

    public void setProfileComplete(boolean profileComplete) {
        this.profileComplete = profileComplete;
    }

    public UserCredentials getUserCredentials() {
        return userCredentials;
    }

    public void setUserCredentials(UserCredentials userCredentials) {
        this.userCredentials = userCredentials;
    }

    public boolean isUseTwoFactorAuthentication() {
        return useTwoFactorAuthentication;
    }

    public void setUseTwoFactorAuthentication(boolean useTwoFactorAuthentication) {
        this.useTwoFactorAuthentication = useTwoFactorAuthentication;
    }

    public String getTwoFactorAuthType() {
        return twoFactorAuthType;
    }

    public void setTwoFactorAuthType(String twoFactorAuthType) {
        this.twoFactorAuthType = twoFactorAuthType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        PTOUser ptoUser = (PTOUser) o;
        return Objects.equals(userCredentials, ptoUser.userCredentials);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), userCredentials);
    }

    @Override
    public String toString() {
        return "PTOUser{" +
                "myTrademarks=" + myTrademarks +
                ", myApplications=" + myApplications +
                ", myLawyers=" + myLawyers +
                ", profileComplete=" + profileComplete +
                ", userCredentials=" + userCredentials +
                '}';
    }
}
