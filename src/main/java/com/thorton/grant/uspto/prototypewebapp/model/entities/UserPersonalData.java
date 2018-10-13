package com.thorton.grant.uspto.prototypewebapp.model.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import java.util.Objects;


@Entity
@Getter
@Setter
public class UserPersonalData extends BaseEntity {


        @Column(name = "first_name")

        private String firstName;

        @Column(name = "last_name")

        private String lastName;

        @Column(name = "address")
        private String address;

        @Column(name="city")
        private String city;

        @Column(name="telephone")
        private String telephone;

        // add image object for user profile picture

        @OneToOne(cascade = CascadeType.ALL)
        private UserCredentials userCredentials ;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserPersonalData userData = (UserPersonalData) o;
        return Objects.equals(firstName, userData.firstName) &&
                Objects.equals(lastName, userData.lastName) &&
                Objects.equals(address, userData.address) &&
                Objects.equals(city, userData.city) &&
                Objects.equals(telephone, userData.telephone) &&
                Objects.equals(userCredentials, userData.userCredentials);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, address, city, telephone, userCredentials);
    }

    @Override
    public String toString() {
        return "UserPersonalData{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", address='" + address + '\'' +
                ", city='" + city + '\'' +
                ", telephone='" + telephone + '\'' +
                ", userCredentials=" + userCredentials +
                '}';
    }
}
