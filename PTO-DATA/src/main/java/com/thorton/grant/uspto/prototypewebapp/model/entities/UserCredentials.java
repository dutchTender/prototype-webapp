package com.thorton.grant.uspto.prototypewebapp.model.entities;


import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import java.util.Objects;
import java.util.Set;

@Entity
@Table(name="user_credentials")

public class UserCredentials  {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="user_credentials_id")
    private Long id;

    @Column(name = "username")
    //@NotEmpty(message = "*Please provide your name")
    // store email value as username
    private String username;

    @Column(name = "password")
    @Length(min = 5, message = "*Your password must have at least 5 characters")
    @NotEmpty(message = "*Please provide your password")
    private String password;
    private String passwordConfirm;

    @Email(message = "*Please provide a valid Email")
    @NotEmpty(message = "*Please provide an email")
    private String email;


    @ManyToMany
    @JoinTable(name = "credentials_role", joinColumns = @JoinColumn(name = "user_credentials_id"), inverseJoinColumns = @JoinColumn(name="user_role_id"))
    private Set<UserRole> userRoles;

    @Column(name="active")
    private int active;

    @OneToOne(mappedBy = "userCredentials")
    private UserPersonalData userPersonalData;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPasswordConfirm() {
        return passwordConfirm;
    }

    public void setPasswordConfirm(String passwordConfirm) {
        this.passwordConfirm = passwordConfirm;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<UserRole> getUserRoles() {
        return userRoles;
    }

    public void setUserRoles(Set<UserRole> user_roles) {
        this.userRoles = user_roles;
    }

    public int getActive() {
        return active;
    }

    public void setActive(int active) {
        this.active = active;
    }

    public UserPersonalData getUserPersonalData() {
        return userPersonalData;
    }

    public void setUserPersonalData(UserPersonalData userPersonalData) {
        this.userPersonalData = userPersonalData;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserCredentials that = (UserCredentials) o;
        return Objects.equals(email, that.email);
    }



    @Override
    public int hashCode() {
        return Objects.hash(email);
    }

    @Override
    public String toString() {
        return "UserCredentials{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", passwordConfirm='" + passwordConfirm + '\'' +
                ", email='" + email + '\'' +
                ", user_roles=" + userRoles +
                ", active=" + active +
                ", userPersonalData=" + userPersonalData +
                '}';
    }
}

