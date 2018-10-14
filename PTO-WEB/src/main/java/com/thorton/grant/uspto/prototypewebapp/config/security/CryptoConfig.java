package com.thorton.grant.uspto.prototypewebapp.config.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


@Configuration
public class CryptoConfig {



        @Bean
        public BCryptPasswordEncoder passwordEncoder(){

            BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

            return  bCryptPasswordEncoder;
        }

}


