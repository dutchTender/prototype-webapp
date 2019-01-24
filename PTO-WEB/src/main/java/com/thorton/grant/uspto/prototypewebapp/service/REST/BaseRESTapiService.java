package com.thorton.grant.uspto.prototypewebapp.service.REST;

import com.thorton.grant.uspto.prototypewebapp.config.host.bean.endPoint.HostBean;
import com.thorton.grant.uspto.prototypewebapp.factories.ServiceBeanFactory;
import com.thorton.grant.uspto.prototypewebapp.interfaces.USPTO.PTOUserService;
import com.thorton.grant.uspto.prototypewebapp.model.entities.USPTO.user.PTOUser;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.ArrayList;

public class BaseRESTapiService {




    private  final ServiceBeanFactory serviceBeanFactory;

    private final HostBean hostBean;

    public ServiceBeanFactory getServiceBeanFactory() {
        return serviceBeanFactory;
    }

    public HostBean getHostBean() {
        return hostBean;
    }

    public BaseRESTapiService(ServiceBeanFactory serviceBeanFactory, HostBean hostBean) {
        this.serviceBeanFactory = serviceBeanFactory;
        this.hostBean = hostBean;
    }

    ////////////////////////////////////////////////
    // verify user session is valid
    ////////////////////////////////////////////////
    boolean verifyValidUserSession(String contact_email){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        PTOUserService ptoUserService = serviceBeanFactory.getPTOUserService();
        PTOUser ptoUser = ptoUserService.findByEmail(email);// ?? we may not need to save this
        if(ptoUser == null || contact_email == ""){

            return false;

        }
        else {
            return true;
        }

    }
    ////////////////////////////////////////////////

    ////////////////////////////////////////////////
    // build response enity for REST API
    ////////////////////////////////////////////////
    ResponseEntity<String> buildResponseEnity(String status_code, String response_main){

        //String statusCode = "404";
        String statusCode = status_code;
        //String responseMsg = "Contact with email address :"+contact_email+ "has not been set as Primary Attorney.";
        String responseMsg = response_main;
        responseMsg = "{status:" + statusCode +" } { msg:"+responseMsg+" }";
        HttpHeaders responseHeader = new HttpHeaders ();
        responseHeader.setAccessControlAllowOrigin(hostBean.getHost()+hostBean.getPort());
        ArrayList<String> headersAllowed = new ArrayList<String>();
        headersAllowed.add("Access-Control-Allow-Origin");
        responseHeader.setAccessControlAllowHeaders(headersAllowed);
        ArrayList<String> methAllowed = new ArrayList<String>();

        return ResponseEntity.ok().headers(responseHeader).body(responseMsg) ;



    }
    ////////////////////////////////////////////////

    PTOUser getCurrentPTOuser(){

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        PTOUserService ptoUserService = getServiceBeanFactory().getPTOUserService();
        PTOUser ptoUser = ptoUserService.findByEmail(email);

        return ptoUser;
    }

}
