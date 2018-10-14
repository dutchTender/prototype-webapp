package com.thorton.grant.uspto.prototypewebapp.model.entities;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import java.util.Objects;
import java.util.Set;

@Entity
@Table(name="user_credentials")
@Getter
@Setter
public class UserCredentials  {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="user_credentials_id")
    private Long id;

    @Column(name = "username")
    //@NotEmpty(message = "*Please provide your name")
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
    private Set<UserRole> user_roles;

    @Column(name="active")
    private int active;

    @OneToOne
    private UserPersonalData userPersonalData;

    private String firstName;
    private String lastName;


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
                ", user_roles=" + user_roles +
                ", active=" + active +
                ", userPersonalData=" + userPersonalData +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }
}

