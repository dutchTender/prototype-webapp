package com.thorton.grant.uspto.prototypewebapp.model.entities.security;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.Objects;


@Entity
public class VerificationToken {

    private static final int EXPIRATION = 60*24;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    public VerificationToken() {
    }

    public VerificationToken(String token, UserCredentials newCredential) {
        this.token = token;
        this.newCredential = newCredential;
    }

    private String token;

    public static int getEXPIRATION() {
        return EXPIRATION;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public UserCredentials getNewCredential() {
        return newCredential;
    }

    public void setNewCredential(UserCredentials newCredential) {
        this.newCredential = newCredential;
    }

    // account associated with this token
    @OneToOne(targetEntity = UserCredentials.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name="user_credentials_id")
    private UserCredentials newCredential;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VerificationToken that = (VerificationToken) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(token, that.token) &&
                Objects.equals(newCredential, that.newCredential);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, token, newCredential);
    }

    @Override
    public String toString() {
        return "VerificationToken{" +
                "id=" + id +
                ", token='" + token + '\'' +
                ", newCredential=" + newCredential +
                '}';
    }
// calculate expiration date


    public Date getExpiredTime(){
        return calculateExpDate(24*60);
    }
    private Date calculateExpDate(int expTimeInMinutes){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Timestamp(calendar.getTime().getTime()));
        calendar.add(Calendar.MINUTE, expTimeInMinutes);
        return new Date(calendar.getTime().getTime());

    }

}
