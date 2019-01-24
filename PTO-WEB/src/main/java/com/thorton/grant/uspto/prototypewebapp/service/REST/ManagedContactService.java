package com.thorton.grant.uspto.prototypewebapp.service.REST;

import com.thorton.grant.uspto.prototypewebapp.config.host.bean.endPoint.HostBean;
import com.thorton.grant.uspto.prototypewebapp.factories.ServiceBeanFactory;
import com.thorton.grant.uspto.prototypewebapp.model.entities.USPTO.user.ManagedContact;
import com.thorton.grant.uspto.prototypewebapp.model.entities.USPTO.user.PTOUser;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
@RestController
@Service
public class ManagedContactService extends  BaseRESTapiService {


    @CrossOrigin(origins = {"http://localhost:80","http://efile-reimagined.com"})
    @RequestMapping(method = GET, value="/REST/apiGateway/user/managedContact/get/{email}")
    @ResponseBody
    ResponseEntity<String> getManagedContact(@PathVariable String email ){

        PTOUser ptoUser = getCurrentPTOuser();

        if(ptoUser != null){

            ManagedContact contact = ptoUser.findManagedContactByEmail(email);

            if(contact != null){

               // build custom response entity and return it
               // {{field_name:field_value}, {..}}


                String first_name="{firstName:"+contact.getFirstName() +"},";
                String middle_name="{middleName:"+contact.getMidlleName()+"},";
                String last_name="{lastName:"+contact.getLastName()+"},";
                String suffix="{suffix:"+contact.getSuffix()+"},";

                String country="{country:"+contact.getCountry()+"},";
                String address="{address:"+contact.getAddress()+"},";
                String city="{city:"+contact.getCity()+"},";
                String state="{state:"+contact.getState()+"},";
                String zipcode="{zipcode:"+contact.getZipcode()+"},";
                String email2="{email:"+contact.getEmail()+"},";
                String phone="{phone:"+contact.getPrimaryPhonenumber()+"}";


                String message = "{"+first_name+middle_name+last_name+suffix+country+address+city+state+zipcode+email2+phone+ "}";
                return  super.buildResponseEnity("200", message);




            }


        }


                // we need a different response entity
        return  super.buildResponseEnity("404", "in valid session");

    }

    @Override
    public ServiceBeanFactory getServiceBeanFactory() {
        return super.getServiceBeanFactory();
    }

    @Override
    public HostBean getHostBean() {
        return super.getHostBean();
    }

    public ManagedContactService(ServiceBeanFactory serviceBeanFactory, HostBean hostBean) {
        super(serviceBeanFactory, hostBean);
    }

    @Override
    boolean verifyValidUserSession(String contact_email) {
        return super.verifyValidUserSession(contact_email);
    }

    @Override
    ResponseEntity<String> buildResponseEnity(String status_code, String response_main) {
        return super.buildResponseEnity(status_code, response_main);
    }

    @Override
    PTOUser getCurrentPTOuser() {
        return super.getCurrentPTOuser();
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    @Override
    public String toString() {
        return super.toString();
    }



}
