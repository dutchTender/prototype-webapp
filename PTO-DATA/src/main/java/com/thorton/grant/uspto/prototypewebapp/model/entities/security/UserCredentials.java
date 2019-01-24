package com.thorton.grant.uspto.prototypewebapp.model.entities.security;


import com.thorton.grant.uspto.prototypewebapp.model.entities.USPTO.user.PTOUser;
import com.thorton.grant.uspto.prototypewebapp.model.entities.USPTO.user.UserPersonalData;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name="user_credentials")

public class UserCredentials  {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="user_credentials_id")
    private Long id;

    @Column(name = "username") // firstInitialLastName +userCredId
    //@NotEmpty(message = "*Please provide your name")
    // store email value as username
    private String username;

    @Column(name = "password")
    @Length(min = 5, message = "*Your password must have at least 5 characters")

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

    @Column(name="completed_profile")
    private boolean completedProfile;

    @OneToOne(mappedBy = "userCredentials")
    private PTOUser userPersonalData;

    //@OneToOne(mappedBy = "")
    //private VerificationToken securityToken;


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

    public List<String> getUserRolesStrings() {
        List<String>  role_list = new ArrayList<>();
        userRoles.forEach(userRole -> role_list.add(userRole.getRoleName()));
        return role_list;
    }

    public void setUserRoles(Set<UserRole> userRoles) {
        this.userRoles = userRoles;
    }

    public int getActive() {
        return active;
    }

    public void setActive(int active) {
        this.active = active;
    }

    public PTOUser getUserPersonalData() {
        return userPersonalData;
    }

    public void setUserPersonalData(PTOUser userPersonalData) {
        this.userPersonalData = userPersonalData;
    }

    public boolean isCompletedProfile() {
        return completedProfile;
    }

    public void setCompletedProfile(boolean completedProfile) {
        this.completedProfile = completedProfile;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserCredentials that = (UserCredentials) o;
        return Objects.equals(username, that.username) &&
                Objects.equals(password, that.password) &&
                Objects.equals(email, that.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, password, email);
    }

    @Override
    public String toString() {
        return "UserCredentials{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", passwordConfirm='" + passwordConfirm + '\'' +
                ", email='" + email + '\'' +
                ", userRoles=" + userRoles +
                ", active=" + active +
                ", completedProfile=" + completedProfile +
                ", userPersonalData=" + userPersonalData +
                '}';
    }
}

