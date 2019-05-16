package com.thorton.grant.uspto.prototypewebapp;

import com.thorton.grant.uspto.prototypewebapp.service.storage.properties.StorageProperties;
import org.apache.catalina.Context;
import org.apache.catalina.connector.Connector;
import org.apache.tomcat.util.descriptor.web.SecurityCollection;
import org.apache.tomcat.util.descriptor.web.SecurityConstraint;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.ArrayList;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.thorton.grant.uspto")
@EntityScan(basePackages = "com.thorton.grant.uspto")
@ComponentScan(basePackages = "com.thorton.grant.uspto")
@EnableConfigurationProperties(StorageProperties.class)
public class PrototypeWebappApplication {

    public static void main(String[] args) {
        SpringApplication.run(PrototypeWebappApplication.class, args);
    }


   @Bean
    public ServletWebServerFactory tomcatRedirectToHTTPS() {
        TomcatServletWebServerFactory tomcat = new TomcatServletWebServerFactory() {
            @Override
            protected void postProcessContext(Context context) {
                SecurityConstraint securityConstraint = new SecurityConstraint();
                securityConstraint.setUserConstraint("CONFIDENTIAL");
                SecurityCollection collection = new SecurityCollection();
                collection.addPattern("/*");
                securityConstraint.addCollection(collection);
                context.addConstraint(securityConstraint);
            }
        };

        ArrayList<Connector> redirectConnectors = new ArrayList<>();
        redirectConnectors.add(redirectConnector80());
       // redirectConnectors.add(redirectConnector443());
        Connector [] customConnectors = redirectConnectors.toArray(new Connector[]{});

        tomcat.addAdditionalTomcatConnectors(customConnectors);
        return tomcat;
    }

    @Value("${server.port}")
    private int serverPortHttps;


    private Connector redirectConnector80() {
        Connector connector = new Connector("org.apache.coyote.http11.Http11NioProtocol");
        connector.setScheme("http");
        connector.setPort(80);
        connector.setSecure(false);
        connector.setRedirectPort(serverPortHttps);
        return connector;
    }

    private Connector redirectConnector443() {
        Connector connector = new Connector("org.apache.coyote.http11.Http11NioProtocol");
        connector.setScheme("https");
        connector.setPort(443);
        connector.setSecure(false);
        connector.setRedirectPort(serverPortHttps);
        return connector;
    }

}
