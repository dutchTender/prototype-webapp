package com.thorton.grant.uspto.prototypewebapp.model.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Objects;


@Entity
@Table(name = "user_personal_data")
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



        @Column(name="email")
        private String email;


        // add image object for user profile picture

        @OneToOne(cascade = CascadeType.ALL)
        private UserCredentials userCredentials ;

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

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public UserCredentials getUserCredentials() {
        return userCredentials;
    }

    public void setUserCredentials(UserCredentials userCredentials) {
        this.userCredentials = userCredentials;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

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
