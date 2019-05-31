package com.thorton.grant.uspto.prototypewebapp.service.storage.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("storage")
public class StorageProperties {

    /**
     * Folder location for storing files
     */

   //private String location = "/home/zhangl/images/";

    // load this value from property file
    //

   private String location = "C:\\images\\attorney";

   //private String location = "/Users/stanikmas_lynn/Documents/GrantThornton/images/";

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }





}
