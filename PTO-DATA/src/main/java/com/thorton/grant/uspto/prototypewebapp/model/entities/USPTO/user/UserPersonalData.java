package com.thorton.grant.uspto.prototypewebapp.model.entities.USPTO.user;

import com.thorton.grant.uspto.prototypewebapp.model.entities.USPTO.tradeMark.application.actions.OfficeActions;
import com.thorton.grant.uspto.prototypewebapp.model.entities.base.BaseEntity;
import com.thorton.grant.uspto.prototypewebapp.model.entities.security.UserCredentials;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.util.*;


@Entity
@Table(name = "user_personal_data")
public class UserPersonalData extends BaseEntity {

    public UserPersonalData() {
        phoneNumbers = new ArrayList<>();
    }

    @Column(name = "first_name")

        private String firstName;

        @Column(name = "last_name")

        private String lastName;

        @Column(name = "midlle_name")

        private String midlleName;

        @Column(name = "title")
        private String title;

        @Column(name = "suffix")
        private String suffix;


        @Column(name = "address")
        private String address;

        @Column(name="city")
        private String city;

        @Column(name="state")
        private String  state;

        @Column(name="zipcode")
        private String  zipcode;

        @Column(name="country")
        private String  country;

        @Column(name = "primary_Phonenumber")
        private String primaryPhonenumber;





        @OneToMany(cascade =  CascadeType.ALL)
        @Nullable
        private List<PhoneNumber> phoneNumbers;



        @Column(name="email")
        private String email;

        @Column(name="alternate_email")
        private String alertnateEmail;


        public String getFirstName() {
            return firstName;
        }

        public void setFirstName(String firstName) {
            this.firstName = firstName;
        }

        public String getLastName() {
            return lastName;
        }

        public void setLastName(String lastName) {
            this.lastName = lastName;
        }

        public String getMidlleName() {
            return midlleName;
        }

        public void setMidlleName(String midlleName) {
            this.midlleName = midlleName;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getSuffix() {
            return suffix;
        }

        public void setSuffix(String suffix) {
            this.suffix = suffix;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getState() {
            return state;
        }

        public void setState(String state) {
            this.state = state;
        }

        public String getZipcode() {
            return zipcode;
        }

        public void setZipcode(String zipcode) {
            this.zipcode = zipcode;
        }

        public String getCountry() {
            return country;
        }

        public void setCountry(String country) {
            this.country = country;
        }

        public String getPrimaryPhonenumber() {
            return primaryPhonenumber;
        }

        public void setPrimaryPhonenumber(String primaryPhonenumber) {
            this.primaryPhonenumber = primaryPhonenumber;
        }

    @Nullable
    public List<PhoneNumber> getPhoneNumbers() {
        return phoneNumbers;
    }

    public void setPhoneNumbers(@Nullable List<PhoneNumber> phoneNumbers) {
        this.phoneNumbers = phoneNumbers;
    }


    public void addPhoneNumber( PhoneNumber phoneNumber){
            phoneNumbers.add(phoneNumber);
    }
    public void removePhoneNumber(PhoneNumber phoneNumber){
            phoneNumbers.remove(phoneNumber);
    }

    public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getAlertnateEmail() {
            return alertnateEmail;
        }

        public void setAlertnateEmail(String alertnateEmail) {
            this.alertnateEmail = alertnateEmail;
        }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserPersonalData that = (UserPersonalData) o;
        return Objects.equals(firstName, that.firstName) &&
                Objects.equals(lastName, that.lastName) &&
                Objects.equals(midlleName, that.midlleName) &&
                Objects.equals(title, that.title) &&
                Objects.equals(suffix, that.suffix) &&
                Objects.equals(address, that.address) &&
                Objects.equals(city, that.city) &&
                Objects.equals(state, that.state) &&
                Objects.equals(zipcode, that.zipcode) &&
                Objects.equals(country, that.country) &&
                Objects.equals(primaryPhonenumber, that.primaryPhonenumber) &&

                Objects.equals(email, that.email) &&
                Objects.equals(alertnateEmail, that.alertnateEmail);
    }


}
