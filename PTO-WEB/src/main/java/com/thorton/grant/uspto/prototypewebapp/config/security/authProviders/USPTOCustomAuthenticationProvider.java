package com.thorton.grant.uspto.prototypewebapp.config.security.authProviders;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class USPTOCustomAuthenticationProvider implements AuthenticationProvider {





    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        final String userEmail = authentication.getName();
        final String password = authentication.getCredentials().toString();
        System.out.println("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
        System.out.println("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
        System.out.println("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
        System.out.println("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");

        if(!supportsAuthentication(authentication)){
            return null;
        }

        if(doAuthenticationAgainstThirdPartySystem()){
            return  new UsernamePasswordAuthenticationToken(userEmail, password, new ArrayList<>());
        }
        else {
            throw new BadCredentialsException("Authentication against third party system failed.");
        }
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }




    private boolean doAuthenticationAgainstThirdPartySystem(){
        // AWS Cognito hand shake logic here

        return true;
    }

    private boolean supportsAuthentication(Authentication authentication){
        // check if this is cognito ..or some other provider
        return true;
    }
}
