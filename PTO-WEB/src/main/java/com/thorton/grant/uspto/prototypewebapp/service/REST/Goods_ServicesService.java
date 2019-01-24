package com.thorton.grant.uspto.prototypewebapp.service.REST;

import com.thorton.grant.uspto.prototypewebapp.config.host.bean.endPoint.HostBean;
import com.thorton.grant.uspto.prototypewebapp.factories.ServiceBeanFactory;
import com.thorton.grant.uspto.prototypewebapp.interfaces.USPTO.tradeMark.application.types.BaseTradeMarkApplicationService;
import com.thorton.grant.uspto.prototypewebapp.interfaces.USPTO.tradeMark.asset.GoodsAndServicesService;
import com.thorton.grant.uspto.prototypewebapp.model.entities.USPTO.tradeMark.application.types.BaseTrademarkApplication;
import com.thorton.grant.uspto.prototypewebapp.model.entities.USPTO.tradeMark.assets.GoodAndService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Locale;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@RestController
@Service
public class Goods_ServicesService  extends BaseRESTapiService{

    public Goods_ServicesService(ServiceBeanFactory serviceBeanFactory, HostBean hostBean) {
        super(serviceBeanFactory, hostBean);
    }



    @CrossOrigin(origins = {"https://localhost","https://efile-reimagined.com"})
    @RequestMapping(method = GET, value="/REST/apiGateway/GS/add/{classNumber}/{classDescription}/{gsID}/{appInternalID}")
    @ResponseBody
    ResponseEntity<String> updateApplictionGoodsServcis(@PathVariable String classNumber , @PathVariable String classDescription,  @PathVariable String gsID, @PathVariable String appInternalID){

        String appFieldReadable = "";
        BaseTradeMarkApplicationService baseTradeMarkApplicationService = getServiceBeanFactory().getBaseTradeMarkApplicationService();
        BaseTrademarkApplication baseTrademarkApplication = baseTradeMarkApplicationService.findByInternalID(appInternalID);

        if(baseTrademarkApplication.findGSbyInternalID(gsID) != null){

            return buildResponseEnity("444", "Good and Service Already added to the Application");

        }


        System.out.println("adding class number : "+classNumber);
        System.out.println("adding GS ID : "+gsID);

        // create the good and service
        GoodAndService goodAndService = new GoodAndService();
        goodAndService.setClassNumber(classNumber);
        goodAndService.setClassDescription(classDescription);
        goodAndService.setInternalID(gsID);
        baseTrademarkApplication.addGoodAndService(goodAndService);

        //baseTrademarkApplication.getGoodsAndSevicesMap().get(classNumber).add(goodAndService);

        appFieldReadable = "Good and Service";
        System.out.println("add GS called 33333333333333333333333333333333333");
        baseTradeMarkApplicationService.save(baseTrademarkApplication);
        String responseMsg = "{{server-message:"+appFieldReadable+" has been saved}";

        // new return message structure

        //  return buildResponseEnity("200", "{image-url:" +filePath+"}");
        // {server-msg:xxxxx},{fee-display-html:xxxx},{total-class-html:xxxxx},{total-extra-class-html:xxxx},{extra-class-fee-info-html:xxxxxx},{extra-class-fee-calc-html},{basic-fee-calc-html:xxxxx},{fee-total-html}
        responseMsg+=",{fee-display-html:"+baseTrademarkApplication.getTotalFeeString()+"}"+",{total-class-html:"+baseTrademarkApplication.getTotalNumberOfclasses()+"}"+",{total-extra-class-html:"+baseTrademarkApplication.getNumberOfExtraClasses()+"}"+",{basic-fee-calc-html:"+baseTrademarkApplication.getBasicFeeCalculationString()+"}"+",{extra-class-fee-calc-html:"+baseTrademarkApplication.getExtraFeeCalculationString()+"}"+",{total-fee-html:"+baseTrademarkApplication.getTotalFeeString()+  "}}";
        // but responsemsg




        return buildResponseEnity("200", responseMsg);
    }



    @CrossOrigin(origins = {"https://localhost","https://efile-reimagined.com"})
    @RequestMapping(method = GET, value="/REST/apiGateway/GS/remove/{classNumber}/{classDescription}/{gsID}/{appInternalID}")
    @ResponseBody
    ResponseEntity<String> updateApplictionGoodsServcisRemove(@PathVariable String classNumber , @PathVariable String classDescription, @PathVariable String gsID, @PathVariable String appInternalID){

        String appFieldReadable = "Good and Service";
        BaseTradeMarkApplicationService baseTradeMarkApplicationService = getServiceBeanFactory().getBaseTradeMarkApplicationService();
        BaseTrademarkApplication baseTrademarkApplication = baseTradeMarkApplicationService.findByInternalID(appInternalID);


        if(baseTrademarkApplication.findGSbyInternalID(gsID) == null){

            return buildResponseEnity("444", "Good and Service is not part of the Application");

        }

        System.out.println("remove GS called 2222222222222222222222222222222");
        GoodAndService deleteThisGS = baseTrademarkApplication.findGSbyInternalID(gsID);
       // baseTrademarkApplication.getGoodsAndSevicesMap().get(classNumber).remove(deleteThisGS);
        baseTrademarkApplication.removeGoodAndService(deleteThisGS);
        baseTradeMarkApplicationService.save(baseTrademarkApplication);



        GoodsAndServicesService goodsAndServicesService = getServiceBeanFactory().getGoodsAndServicesService();
        goodsAndServicesService.delete(deleteThisGS);

        String responseMsg = "{{server-message:"+appFieldReadable+" has been saved}";

        // new return message structure

        //  return buildResponseEnity("200", "{image-url:" +filePath+"}");
        // {server-msg:xxxxx},{fee-display-html:xxxx},{total-class-html:xxxxx},{total-extra-class-html:xxxx},{extra-class-fee-info-html:xxxxxx},{extra-class-fee-calc-html},{basic-fee-calc-html:xxxxx},{fee-total-html}
        responseMsg+=",{fee-display-html:"+baseTrademarkApplication.getTotalFeeString()+"}"+",{total-class-html:"+baseTrademarkApplication.getTotalNumberOfclasses()+"}"+",{total-extra-class-html:"+baseTrademarkApplication.getNumberOfExtraClasses()+"}"+",{basic-fee-calc-html:"+baseTrademarkApplication.getBasicFeeCalculationString()+"}"+",{extra-class-fee-calc-html:"+baseTrademarkApplication.getExtraFeeCalculationString()+"}}";
        //return ResponseEntity.ok().headers(responseHeader).body(responseMsg) ;
        return buildResponseEnity("200", responseMsg);
    }




    @CrossOrigin(origins = {"https://localhost","https://efile-reimagined.com"})
    @RequestMapping(method = GET, value="/REST/apiGateway/GS/update/{gsField}/{gsValue}/{appInternalID}")
    @ResponseBody
    ResponseEntity<String> updateGoods_ServicesSelectOptions(@PathVariable String gsField , @PathVariable String gsValue, @PathVariable String appInternalID){

        String appFieldReadable = "";
        BaseTradeMarkApplicationService baseTradeMarkApplicationService = getServiceBeanFactory().getBaseTradeMarkApplicationService();
        BaseTrademarkApplication baseTrademarkApplication = baseTradeMarkApplicationService.findByInternalID(appInternalID);

        if(gsField.equals("GS-select-Option")){
            // ptoUser.setState(param); // sets state code
            if(gsValue.equals("search")) {
                baseTrademarkApplication.setSearchExistingGSdatabase(true);
            }
            else {
                baseTrademarkApplication.setSearchExistingGSdatabase(false);

            }
            appFieldReadable = "Goods And Services search option";

        }
        if(gsField.equals("GS-mark-inUse")){
            // ptoUser.setState(param); // sets state code
            if(gsValue.equals("yes")) {
                if(baseTrademarkApplication.isMarkInUseForAllGS() == true){
                    return buildResponseEnity("444", "");
                }
                baseTrademarkApplication.setMarkInUseForAllGS(true);
                baseTrademarkApplication.setMarkAllgsSet(true);
                // we need to loop though all gs and set its in use to true
                for(Iterator<GoodAndService> iter = baseTrademarkApplication.getGoodAndServices().iterator(); iter.hasNext(); ) {
                    GoodAndService current = iter.next();
                    current.setMarkInUse(true);
                    current.setMarkInUseSet(true);
                }
            }
            else {
                if(baseTrademarkApplication.isMarkAllgsSet() == false){
                    baseTrademarkApplication.setMarkInUseForAllGS(false);
                    baseTrademarkApplication.setMarkAllgsSet(true);
                }
                else {
                    if(baseTrademarkApplication.isMarkInUseForAllGS() == false){
                        return buildResponseEnity("444", "");
                    }
                    baseTrademarkApplication.setMarkInUseForAllGS(false);
                }
                for(Iterator<GoodAndService> iter = baseTrademarkApplication.getGoodAndServices().iterator(); iter.hasNext(); ) {
                    GoodAndService current = iter.next();
                    current.setMarkInUse(false);
                    current.setMarkInUseSet(true);
                }
                // we need to loop though all gs and set its in use to false

            }
            appFieldReadable = "Filing Basis mark in use option";

        }
        if(gsField.equals("GS-mark-fapp")){
            // ptoUser.setState(param); // sets state code
            if(gsValue.equals("yes")) {

                if(baseTrademarkApplication.isMarkHasForeignRegistration() == true){
                    return buildResponseEnity("444", "");
                }
                baseTrademarkApplication.setMarkHasForeignRegistration(true);
                baseTrademarkApplication.setMarkFappSet(true);
            }
            else {

                if(baseTrademarkApplication.isMarkFappSet() == false) {
                    baseTrademarkApplication.setMarkHasForeignRegistration(false);
                    baseTrademarkApplication.setMarkFappSet(true);
                }
                else {
                    if(baseTrademarkApplication.isMarkHasForeignRegistration()== false){
                        return buildResponseEnity("444", "");
                    }
                    baseTrademarkApplication.setMarkHasForeignRegistration(false);
                }

            }
            appFieldReadable = "Filing Basis foreign registration/application option";

        }

        baseTradeMarkApplicationService.save(baseTrademarkApplication);
        String responseMsg = appFieldReadable+" has been saved.";

        //return ResponseEntity.ok().headers(responseHeader).body(responseMsg) ;
        return buildResponseEnity("200", responseMsg);
    }




    @CrossOrigin(origins = {"https://localhost","https://efile-reimagined.com"})
    @RequestMapping(method = GET, value="/REST/apiGateway/GS/fb/update/{fbField}/{fbValue}/{gsID}/{appInternalID}")
    @ResponseBody
    ResponseEntity<String> updateFilingBasisForGoodsServcices(@PathVariable String fbField , @PathVariable String fbValue,  @PathVariable String gsID, @PathVariable String appInternalID){

        String appFieldReadable = "";
        BaseTradeMarkApplicationService baseTradeMarkApplicationService = getServiceBeanFactory().getBaseTradeMarkApplicationService();
        BaseTrademarkApplication baseTrademarkApplication = baseTradeMarkApplicationService.findByInternalID(appInternalID);




        if(fbField.equals("fb-mark-in-use")){
            // ptoUser.setState(param); // sets state code

            if(fbValue.equals("yes")){
                baseTrademarkApplication.findGSbyInternalID(gsID).setMarkInUse(true);
                baseTrademarkApplication.findGSbyInternalID(gsID).setMarkInUseSet(true);

            }
            if(fbValue.equals("no")){
                baseTrademarkApplication.findGSbyInternalID(gsID).setMarkInUse(false);
                baseTrademarkApplication.findGSbyInternalID(gsID).setMarkInUseSet(true);

            }

            appFieldReadable = "Filing Basis Mark In Use";

        }


        if(fbField.equals("fb-gs-date")){
            // ptoUser.setState(param); // sets state code


            try {
                DateFormat format = new SimpleDateFormat("yyyy-mm-dd", Locale.ENGLISH);
                Date date = format.parse(fbValue);
                baseTrademarkApplication.findGSbyInternalID(gsID).setFirstGSDate(date);

            }
            catch(Exception ex){
                return buildResponseEnity("420", "ERROR: Could not save Date, invalid Date format");

            }
            appFieldReadable = "Filing Basis First Good and Services Date";

        }


        if(fbField.equals("fb-mark-date")){
            // ptoUser.setState(param); // sets state code


            try {
                DateFormat format = new SimpleDateFormat("yyyy-mm-dd", Locale.ENGLISH);
                Date date = format.parse(fbValue);
                baseTrademarkApplication.findGSbyInternalID(gsID).setFirstCommerceDate(date);

            }
            catch(Exception ex){
                return buildResponseEnity("420", "ERROR: Could not save Date, invalid Date format");

            }
            appFieldReadable = "Filing Basis First Commerce Date";

        }
        if(fbField.equals("fb-sample-desc")){
            // ptoUser.setState(param); // sets state code

            baseTrademarkApplication.findGSbyInternalID(gsID).setSampleDescription(fbValue);

            appFieldReadable = "Filing Basis Specimen Description";

        }

        if(fbField.equals("fb-provide-spec")){
            // ptoUser.setState(param); // sets state code

            if(fbValue.equals("yes")){
                baseTrademarkApplication.findGSbyInternalID(gsID).setProvideSample(true);

            }
            if(fbValue.equals("no")){
                baseTrademarkApplication.findGSbyInternalID(gsID).setProvideSample(false);


            }

            appFieldReadable = "Filing Basis Provide Specimen";

        }


        if(fbField.equals("gs-pfa-option")){
            // ptoUser.setState(param); // sets state code

            if(fbValue.equals("yes")){
                baseTrademarkApplication.findGSbyInternalID(gsID).setPendingFA(true);



            }
            if(fbValue.equals("no")){
                baseTrademarkApplication.findGSbyInternalID(gsID).setPendingFA(false);



            }

            appFieldReadable = "Filing Basis Pending Foreign Application Option";

        }

        if(fbField.equals("gs-pfr-option")){
            // ptoUser.setState(param); // sets state code

            if(fbValue.equals("yes")){
                baseTrademarkApplication.findGSbyInternalID(gsID).setForeignRegistration(true);



            }
            if(fbValue.equals("no")){
                baseTrademarkApplication.findGSbyInternalID(gsID).setForeignRegistration(false);



            }

            appFieldReadable = "Filing Basis Foreign Registration Option";

        }

        if(fbField.equals("gs-pna-option")){
            // ptoUser.setState(param); // sets state code

            if(fbValue.equals("yes")){
                baseTrademarkApplication.findGSbyInternalID(gsID).setForeignAR_NA(true);
                baseTrademarkApplication.findGSbyInternalID(gsID).setForeignRegistration(false);
                baseTrademarkApplication.findGSbyInternalID(gsID).setPendingFA(false);



            }
            if(fbValue.equals("no")){
                baseTrademarkApplication.findGSbyInternalID(gsID).setForeignAR_NA(false);



            }

            appFieldReadable = "Filing Basis Foreign Not Applicable Option";

        }


        if(fbField.equals("gs-pfa-country")){
            // ptoUser.setState(param); // sets state code

                baseTrademarkApplication.findGSbyInternalID(gsID).setFaCountry(fbValue);

            appFieldReadable = "Filing Basis Pending Foreign Application Country";

        }


        if(fbField.equals("gs-pfa-app-Number")){
            // ptoUser.setState(param); // sets state code

            baseTrademarkApplication.findGSbyInternalID(gsID).setFaRegistrationNumber(fbValue);

            appFieldReadable = "Filing Basis Pending Foreign Application Number";

        }


        if(fbField.equals("gs-pfa-filing-date")){
            // ptoUser.setState(param); // sets state code

           // baseTrademarkApplication.findGSbyInternalID(gsID).setFaRegistrationNumber(fbValue);

            try {
                DateFormat format = new SimpleDateFormat("yyyy-mm-dd", Locale.ENGLISH);
                Date date = format.parse(fbValue);
                baseTrademarkApplication.findGSbyInternalID(gsID).setFaFilingDate(date);

            }
            catch(Exception ex){
                return buildResponseEnity("420", "ERROR: Could not save Date, invalid Date format");

            }
            appFieldReadable = "Filing Basis  Pending Foreign Application Filing Date";

        }

        if(fbField.equals("gs-pfr-country")){
            // ptoUser.setState(param); // sets state code

            baseTrademarkApplication.findGSbyInternalID(gsID).setFrCountry(fbValue);

            appFieldReadable = "Filing Basis Foreign Registration Country";

        }

        if(fbField.equals("gs-pfr-reg-Number")){
            // ptoUser.setState(param); // sets state code

            baseTrademarkApplication.findGSbyInternalID(gsID).setFrRegistartionNumber(fbValue);

            appFieldReadable = "Filing Basis Foreign Registration Number";

        }

        if(fbField.equals("gs-pfr-exp-date")){
            // ptoUser.setState(param); // sets state code

            // baseTrademarkApplication.findGSbyInternalID(gsID).setFaRegistrationNumber(fbValue);

            try {
                DateFormat format = new SimpleDateFormat("yyyy-mm-dd", Locale.ENGLISH);
                Date date = format.parse(fbValue);
                baseTrademarkApplication.findGSbyInternalID(gsID).setFrExpirationDate(date);

            }
            catch(Exception ex){
                return buildResponseEnity("420", "ERROR: Could not save Date, invalid Date format");

            }
            appFieldReadable = "Filing Basis Foreign Registration Expiration Date";

        }

        if(fbField.equals("gs-pfr-renew-date")){
            // ptoUser.setState(param); // sets state code

            // baseTrademarkApplication.findGSbyInternalID(gsID).setFaRegistrationNumber(fbValue);

            try {
                DateFormat format = new SimpleDateFormat("yyyy-mm-dd", Locale.ENGLISH);
                Date date = format.parse(fbValue);
                baseTrademarkApplication.findGSbyInternalID(gsID).setFrRenewlDate(date);

            }
            catch(Exception ex){
                return buildResponseEnity("420", "ERROR: Could not save Date, invalid Date format");

            }
            appFieldReadable = "Filing Basis Foreign Registration Renewal Date";

        }


        baseTradeMarkApplicationService.save(baseTrademarkApplication);
        String responseMsg = appFieldReadable+" has been saved";

        return buildResponseEnity("200", responseMsg);
    }





    @CrossOrigin(origins = {"https://localhost","https://efile-reimagined.com"})
    @RequestMapping(method = GET, value="/REST/apiGateway/CC/fb/update/{ccField}/{ccValue}/{ccNumber}/{appInternalID}")
    @ResponseBody
    ResponseEntity<String> updateFilingBasisForClassCategory(@PathVariable String ccField , @PathVariable String ccValue,  @PathVariable String ccNumber, @PathVariable String appInternalID){

        String appFieldReadable = "";
        BaseTradeMarkApplicationService baseTradeMarkApplicationService = getServiceBeanFactory().getBaseTradeMarkApplicationService();
        BaseTrademarkApplication baseTrademarkApplication = baseTradeMarkApplicationService.findByInternalID(appInternalID);




        if(ccField.equals("cc-spec-descr")){
            // ptoUser.setState(param); // sets state code

            for(Iterator<GoodAndService> iter = baseTrademarkApplication.getGoodAndServices().iterator(); iter.hasNext(); ) {
                GoodAndService current = iter.next();

                if(current.getClassNumber().equals(ccNumber)){
                    current.setClassSpecimenDescr(ccValue);

                }
            }

            appFieldReadable = "Class Specimen Description";

        }




        baseTradeMarkApplicationService.save(baseTrademarkApplication);
        String responseMsg = appFieldReadable+" has been saved";

        return buildResponseEnity("200", responseMsg);
    }
}
