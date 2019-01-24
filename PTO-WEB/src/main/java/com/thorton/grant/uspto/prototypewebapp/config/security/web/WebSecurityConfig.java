package com.thorton.grant.uspto.prototypewebapp.config.security.web;

import com.thorton.grant.uspto.prototypewebapp.config.security.authProviders.USPTOCustomAuthenticationProvider;
import com.thorton.grant.uspto.prototypewebapp.service.authentication.userDetails.USPTOUserDetailsService;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import org.springframework.context.annotation.Configuration;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.sql.DataSource;
@Configuration
@EnableWebSecurity
public class WebSecurityConfig  extends WebSecurityConfigurerAdapter {

    @Value("${spring.queries.users-query}")
    private String userQuery;

    @Value("${spring.queries.roles-query}")
    private String roleQuery;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;


    // the data source will be auto injected by spring
    private final DataSource dataSource;

    public WebSecurityConfig(BCryptPasswordEncoder bCryptPasswordEncoder, DataSource dataSource, USPTOCustomAuthenticationProvider usptoCustomAuthenticationProvider) {
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.dataSource = dataSource;
        this.usptoCustomAuthenticationProvider = usptoCustomAuthenticationProvider;
    }

    @Autowired
    private USPTOUserDetailsService usptoUserDetailsService;


    private final USPTOCustomAuthenticationProvider usptoCustomAuthenticationProvider;


    @Override
    @Autowired
    protected void configure(AuthenticationManagerBuilder auth)
            throws Exception {

                /////////////////////////////////////////////////////////////
                // custom authentication provider
                /////////////////////////////////////////////////////////////
                // this provider right now lets every one in ..
                //auth.authenticationProvider(usptoCustomAuthenticationProvider);
                /////////////////////////////////////////////////////////////
                //final DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
                //daoAuthenticationProvider.setUserDetailsService(usptoUserDetailsService);

                // uses both DAO and custom provider
                //auth.authenticationProvider(usptoCustomAuthenticationProvider).authenticationProvider(daoAuthenticationProvider);
                /////////////////////////////////////////////////////////////
                // DAO authentication provider
                /////////////////////////////////////////////////////////////
                auth.userDetailsService(usptoUserDetailsService);
                /////////////////////////////////////////////////////////////

                /////////////////////////////////////////////////////////////
                // JDBC custom query authentication
                /////////////////////////////////////////////////////////////
                 /*
                jdbcAuthentication()
                .usersByUsernameQuery(userQuery)
                .authoritiesByUsernameQuery(roleQuery)
                .dataSource(dataSource)
                .passwordEncoder(bCryptPasswordEncoder);
                */

                 // not sure if we need a authenticatoin manger, as the default can take a list of custom identity providers

    }






    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .headers().frameOptions().sameOrigin().and()

                .authorizeRequests()
                .antMatchers( "/resources/**","/h2-console/**", "/console/**","/webjars/**","/registration/**","/","/home","/newUser","/aboutUs/**","/contact/**","/public/**","/owner/**","/registrationConfirm/**","/CompleteRegistration/**","/REST/**").permitAll()
                //.antMatchers("/vets**","/owners**").hasRole("vet")
                //.antMatchers("/owners","owner","/pet").hasRole("owner")
                //.antMatchers("/owner**","/vet**","/pet**").hasRole("admin")
                .anyRequest().authenticated()
                .and()
                .anonymous()

                .and()

                .formLogin()
                .loginPage("/login").successForwardUrl("/2FactorAuth").failureForwardUrl("/passwordFailure")
                .permitAll()
                .and()
                .logout()
                .permitAll()
                .and()
                .logout()
                .logoutUrl("/logout")
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID")
                .logoutSuccessUrl("/logoutConfirm")
                .permitAll();



        // supports h2-console access
        http.csrf().disable();
        http.headers().frameOptions().disable();
    }


    // in memory user and roles
    // we will replace this with a database version
    // also we will do one with oAuth (web Identity management service)



    @Override
    public void configure(WebSecurity web) throws Exception {
        web
                .ignoring()
                .antMatchers("/resources/**", "/static/**", "/css/**", "/js/**", "/images/**","/documents/**");
    }



}
