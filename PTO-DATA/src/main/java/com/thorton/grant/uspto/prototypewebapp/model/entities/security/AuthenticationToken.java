package com.thorton.grant.uspto.prototypewebapp.model.entities.security;



import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.Objects;


@Entity
public class AuthenticationToken {


    public AuthenticationToken() {
    }

    public AuthenticationToken(String token, UserCredentials userCredentials) {
        this.token = token;
        this.userCredentials = userCredentials;
    }



    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String token;
    private static final int EXPIRATION = 30; // token expires in 30 minutes

    // account associated with this token
    @OneToOne(targetEntity = UserCredentials.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name="user_credentials_id")
    private UserCredentials userCredentials;

    ///////////////////////////////////////////////////////////////////////////////
    ///////////////////////////////////////////////////////////////////////////////

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

    public UserCredentials getUserCredentials() {
        return userCredentials;
    }

    public void setUserCredentials(UserCredentials userCredentials) {
        this.userCredentials = userCredentials;
    }

    public Date getExpiredTime(){
        return calculateExpDate(30);
    }
    private Date calculateExpDate(int expTimeInMinutes){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Timestamp(calendar.getTime().getTime()));
        calendar.add(Calendar.MINUTE, expTimeInMinutes);
        return new Date(calendar.getTime().getTime());

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AuthenticationToken that = (AuthenticationToken) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(token, that.token) &&
                Objects.equals(userCredentials, that.userCredentials);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, token, userCredentials);
    }
}
