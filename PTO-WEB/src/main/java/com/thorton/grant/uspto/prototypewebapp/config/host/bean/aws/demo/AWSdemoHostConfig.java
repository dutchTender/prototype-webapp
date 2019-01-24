package com.thorton.grant.uspto.prototypewebapp.config.host.bean.aws.demo;

import com.thorton.grant.uspto.prototypewebapp.config.host.bean.endPoint.HostBean;
import com.thorton.grant.uspto.prototypewebapp.config.host.bean.endPoint.ServerHostConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

@Profile("AWSdemo")
@Configuration
@PropertySource("classpath:server-host-AWS-prod1.properties")
public class AWSdemoHostConfig implements ServerHostConfig {




    @Value("${uspto.host}")
    private String host;

    @Value("${uspto.port}")
    private String port;


    @Override
    public String getServerHost() {
        return host;
    }

    @Override
    public String getServerPort() {
        return port;

    }

    @Override
    public String getServerBaseURL() {
        return host+":"+port;
    }


    @Bean
    public HostBean hostBean(){
        HostBean hostBean = new HostBean();

        hostBean.setHost(host);
        hostBean.setPort(port);
        System.out.println("!!!!!!!!!!!!!!!HostBean DI for AWS DEMO Host!!!!!!!!!!!!!!!!!");
        return  hostBean;
    }

    @Bean
    public static PropertySourcesPlaceholderConfigurer properties (){

        PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer = new PropertySourcesPlaceholderConfigurer();

        return  propertySourcesPlaceholderConfigurer;

    }


}
