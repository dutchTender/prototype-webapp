package com.thorton.grant.uspto.prototypewebapp.model.entities.USPTO.tradeMark.application.participants;


import com.thorton.grant.uspto.prototypewebapp.model.entities.USPTO.user.UserPersonalData;

import javax.persistence.Entity;

import java.util.Date;
import java.util.Objects;



@Entity
public class Contact  extends UserPersonalData {

    private String companyName;
    private Date   lastContactDate;
    private String preferredContactMethod;

    ////////////////////////////////////////////////////////////////////////////////////////
    // tradeMarkApplication is the owning object, Contact is the subordinate object here
    ////////////////////////////////////////////////////////////////////////////////////////



    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public Date getLastContactDate() {
        return lastContactDate;
    }

    public void setLastContactDate(Date lastContactDate) {
        this.lastContactDate = lastContactDate;
    }

    public String getPreferredContactMethod() {
        return preferredContactMethod;
    }

    public void setPreferredContactMethod(String preferredContactMethod) {
        this.preferredContactMethod = preferredContactMethod;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Contact contact = (Contact) o;
        return Objects.equals(companyName, contact.companyName) &&
                Objects.equals(lastContactDate, contact.lastContactDate) &&
                Objects.equals(preferredContactMethod, contact.preferredContactMethod);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), companyName, lastContactDate, preferredContactMethod);
    }

    @Override
    public String toString() {
        return "Contact{" +
                "companyName='" + companyName + '\'' +
                ", lastContactDate=" + lastContactDate +
                ", preferredContactMethod='" + preferredContactMethod + '\'' +
                '}';
    }
}
