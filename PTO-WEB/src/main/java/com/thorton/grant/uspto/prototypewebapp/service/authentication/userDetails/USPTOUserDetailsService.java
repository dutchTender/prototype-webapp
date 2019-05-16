package com.thorton.grant.uspto.prototypewebapp.service.authentication.userDetails;


import com.thorton.grant.uspto.prototypewebapp.interfaces.Secruity.UserCredentialsService;
import com.thorton.grant.uspto.prototypewebapp.model.entities.security.UserCredentials;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class USPTOUserDetailsService implements UserDetailsService {

    @Autowired
    private UserCredentialsService userCredentialsService;

    ///////////////////////////////////////////////////////////////////////////////
    // this method is what spring security calls to evaluate use credentials
    ///////////////////////////////////////////////////////////////////////////////
    public UserDetails loadUserByUsername(String email)
            throws UsernameNotFoundException {

        System.out.println("11111111111111111111111111111111111111111111111111");
        System.out.println(email);
        System.out.println("11111111111111111111111111111111111111111111111111");
        System.out.println(email.toLowerCase());



        UserCredentials userCredentials = userCredentialsService.findByEmail(email.toLowerCase());
        if (userCredentials == null) {
            throw new UsernameNotFoundException(
                    "No user found with username: "+ email);
        }
        boolean enabled = true;
        if(userCredentials.getActive() == 0){
            enabled = false;
        }
        boolean accountNonExpired = true;
        boolean credentialsNonExpired = true;
        boolean accountNonLocked = true;


        return  new org.springframework.security.core.userdetails.User
                (userCredentials.getEmail(),
                        userCredentials.getPassword(), enabled, accountNonExpired,
                        credentialsNonExpired, accountNonLocked,
                        getAuthorities(userCredentials.getUserRolesStrings()));
    }

    private static List<GrantedAuthority> getAuthorities (List<String> roles) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        for (String role : roles) {
            authorities.add(new SimpleGrantedAuthority(role));
        }
        return authorities;
    }
}