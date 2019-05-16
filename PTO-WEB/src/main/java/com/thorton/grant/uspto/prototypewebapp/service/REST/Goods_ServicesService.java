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
                    current.setMarkInUse(false);
                    current.setMarkInUseSet(false);
                }
                baseTrademarkApplication.setDeclarationMarkInUse(true);
                baseTrademarkApplication.setDeclarationMarkInUseSet(true);
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
                /*
                for(Iterator<GoodAndService> iter = baseTrademarkApplication.getGoodAndServices().iterator(); iter.hasNext(); ) {
                    GoodAndService current = iter.next();
                    current.setMarkInUse(false);
                    current.setMarkInUseSet(true);
                }*/
                // we need to loop though all gs and set its in use to false
                baseTrademarkApplication.setDeclarationMarkInUse(false);
                baseTrademarkApplication.setDeclarationMarkInUseSet(true);

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
                    // if mark foreign is set ...
                    // we need to unset it
                    // we need to unset all of the foreign application/registration settings and fields

                    baseTrademarkApplication.setMarkHasForeignRegistration(false);

                    // for each filing basis, also set this value for foreign app and foreign registration
                    for(Iterator<GoodAndService> iter = baseTrademarkApplication.getGoodAndServicesList().iterator(); iter.hasNext(); ) {
                         GoodAndService current = iter.next();

                         // unset foreign application for each good and service

                         current.setFrCertImageName(null);current.setFaCountry(null);
                        current.setFaRegistrationNumber(null);
                        current.setFaFilingDate(null);
                        current.setPendingFA(false);
                        //unset foreign registration for each good and service
                        current.setFrCountry(null);
                        current.setFrRegistartionNumber(null);
                        current.setFrRegistrationDate(null);
                        current.setFrExpirationDate(null);
                        current.setFrRenewlDate(null);
                        current.setFrCertImagePath(null);
                        current.setForeignRegistration(false);




                    }

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

                // get class category nunber
                GoodAndService goodAndService = baseTrademarkApplication.findGSbyInternalID(gsID);
                String catNumber = goodAndService.getClassNumber();
                int gsCount = 0;
                int gsNo = 0;

                for(Iterator<GoodAndService> iter = baseTrademarkApplication.getGoodAndServices().iterator(); iter.hasNext(); ) {
                    GoodAndService current = iter.next();

                    if(current.getClassNumber().equals(catNumber)){

                       gsCount++;
                       if(current.isMarkInUse() == false && current.isMarkInUseSet() == true){
                           gsNo++;
                       }



                    }
                }


                // get class level in use options
                if(goodAndService.isAtLeastOneGoodInCommerceClassFlag()){
                    if(gsCount - gsNo > 1){

                        baseTrademarkApplication.findGSbyInternalID(gsID).setMarkInUse(false);
                        baseTrademarkApplication.findGSbyInternalID(gsID).setMarkInUseSet(true);

                    }
                    else{

                        return buildResponseEnity("420", "ERROR: Could not save good and service in use options");

                    }

                }
                else{
                     baseTrademarkApplication.findGSbyInternalID(gsID).setMarkInUse(false);
                     baseTrademarkApplication.findGSbyInternalID(gsID).setMarkInUseSet(true);
                }

                // get number of goods and services in class

                // get number of goods and services in use

                // determine if this one can be set to no

                // return success or failure



            }

            appFieldReadable = "Filing Basis Mark In Use";

        }


        if(fbField.equals("fb-gs-date")){
            // ptoUser.setState(param); // sets state code

            if(gsID.equals("all")){

                for(Iterator<GoodAndService> iter = baseTrademarkApplication.getGoodAndServices().iterator(); iter.hasNext(); ) {

                    GoodAndService current = iter.next();

                    try {
                        DateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
                        Date date = format.parse(fbValue);
                        current.setFirstGSDate(date);
                        current.setFirstGSDateSet(true);

                    }
                    catch(Exception ex){
                        return buildResponseEnity("420", "ERROR: Could not save Date, invalid Date format");

                    }

                }

            }
            else {

                try {
                    DateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
                    Date date = format.parse(fbValue);
                    baseTrademarkApplication.findGSbyInternalID(gsID).setFirstGSDate(date);
                    baseTrademarkApplication.findGSbyInternalID(gsID).setFirstGSDateSet(true);

                }
                catch(Exception ex){
                    return buildResponseEnity("420", "ERROR: Could not save Date, invalid Date format");

                }



            }
            appFieldReadable = "Filing Basis First Good and Services Date";

        }


        if(fbField.equals("fb-mark-date")){
            // ptoUser.setState(param); // sets state code

           if (gsID.equals("all")){

               for(Iterator<GoodAndService> iter = baseTrademarkApplication.getGoodAndServices().iterator(); iter.hasNext(); ) {

                   GoodAndService current = iter.next();



                   try {
                       DateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
                       Date date = format.parse(fbValue);
                       current.setFirstCommerceDate(date);
                       current.setFirstCommerceDateSet(true);
                   }
                   catch(Exception ex){
                       return buildResponseEnity("420", "ERROR: Could not save Date, invalid Date format");

                   }

               }

           }
           else {
               try {
                   DateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
                   Date date = format.parse(fbValue);
                   baseTrademarkApplication.findGSbyInternalID(gsID).setFirstCommerceDate(date);
                   baseTrademarkApplication.findGSbyInternalID(gsID).setFirstCommerceDateSet(true);
               }
               catch(Exception ex){
                   return buildResponseEnity("420", "ERROR: Could not save Date, invalid Date format");

               }


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
        // class level equivalent






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


            if(gsID.equals("all")){

                for(Iterator<GoodAndService> iter = baseTrademarkApplication.getGoodAndServices().iterator(); iter.hasNext(); ) {

                    GoodAndService current = iter.next();
                    current.setFaCountry(fbValue);
                }
            }
            else {
                baseTrademarkApplication.findGSbyInternalID(gsID).setFaCountry(fbValue);

            }



            appFieldReadable = "Filing Basis Pending Foreign Application Country";

        }


        if(fbField.equals("gs-pfa-app-Number")){
            // ptoUser.setState(param); // sets state code

            if(gsID.equals("all")){

                for(Iterator<GoodAndService> iter = baseTrademarkApplication.getGoodAndServices().iterator(); iter.hasNext(); ) {

                    GoodAndService current = iter.next();
                    current.setFaRegistrationNumber(fbValue);
                }
            }
            else {
                baseTrademarkApplication.findGSbyInternalID(gsID).setFaRegistrationNumber(fbValue);

            }



            appFieldReadable = "Filing Basis Pending Foreign Application Number";

        }


        if(fbField.equals("gs-pfa-filing-date")){
            if(gsID.equals("all")){

                for(Iterator<GoodAndService> iter = baseTrademarkApplication.getGoodAndServices().iterator(); iter.hasNext(); ) {

                    GoodAndService current = iter.next();

                    try {
                        DateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
                        Date date = format.parse(fbValue);
                        current.setFaFilingDate(date);

                    }
                    catch(Exception ex){
                        return buildResponseEnity("420", "ERROR: Could not save Date, invalid Date format");

                    }
                }
            }
            else {
                try {
                    DateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
                    Date date = format.parse(fbValue);
                    baseTrademarkApplication.findGSbyInternalID(gsID).setFaFilingDate(date);

                }
                catch(Exception ex){
                    return buildResponseEnity("420", "ERROR: Could not save Date, invalid Date format");

                }

            }


            appFieldReadable = "Filing Basis  Pending Foreign Application Filing Date";

        }

        if(fbField.equals("gs-pfr-country")){
            // ptoUser.setState(param); // sets state code

            if(gsID.equals("all")){

                for(Iterator<GoodAndService> iter = baseTrademarkApplication.getGoodAndServices().iterator(); iter.hasNext(); ) {

                    GoodAndService current = iter.next();
                    current.setFrCountry(fbValue);
                }
            }
            else {
                baseTrademarkApplication.findGSbyInternalID(gsID).setFrCountry(fbValue);
            }



            appFieldReadable = "Filing Basis Foreign Registration Country";

        }

        if(fbField.equals("gs-pfr-reg-Number")){
            // ptoUser.setState(param); // sets state code
            if(gsID.equals("all")){

                for(Iterator<GoodAndService> iter = baseTrademarkApplication.getGoodAndServices().iterator(); iter.hasNext(); ) {

                    GoodAndService current = iter.next();
                    current.setFrRegistartionNumber(fbValue);
                }
            }
            else {
                baseTrademarkApplication.findGSbyInternalID(gsID).setFrRegistartionNumber(fbValue);
            }



            appFieldReadable = "Filing Basis Foreign Registration Number";

        }

        if(fbField.equals("gs-pfr-reg-date")){
            if(gsID.equals("all")){

                for(Iterator<GoodAndService> iter = baseTrademarkApplication.getGoodAndServices().iterator(); iter.hasNext(); ) {

                    GoodAndService current = iter.next();

                    try {
                        DateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
                        Date date = format.parse(fbValue);
                        current.setFrRegistrationDate(date);

                    }
                    catch(Exception ex){
                        return buildResponseEnity("420", "ERROR: Could not save Date, invalid Date format");

                    }
                }
            }
            else {
                try {
                    DateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
                    Date date = format.parse(fbValue);
                    baseTrademarkApplication.findGSbyInternalID(gsID).setFrRegistrationDate(date);

                }
                catch(Exception ex){
                    return buildResponseEnity("420", "ERROR: Could not save Date, invalid Date format");

                }

            }


            appFieldReadable = "Filing Basis Foreign Registration Date";

        }


        if(fbField.equals("gs-pfr-exp-date")){
            if(gsID.equals("all")){

                for(Iterator<GoodAndService> iter = baseTrademarkApplication.getGoodAndServices().iterator(); iter.hasNext(); ) {

                    GoodAndService current = iter.next();
                    try {
                        DateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
                        Date date = format.parse(fbValue);
                        current.setFrExpirationDate(date);

                    }
                    catch(Exception ex){
                        return buildResponseEnity("420", "ERROR: Could not save Date, invalid Date format");

                    }
                }
            }
            else {
                try {
                    DateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
                    Date date = format.parse(fbValue);
                    baseTrademarkApplication.findGSbyInternalID(gsID).setFrExpirationDate(date);

                }
                catch(Exception ex){
                    return buildResponseEnity("420", "ERROR: Could not save Date, invalid Date format");

                }
            }



            appFieldReadable = "Filing Basis Foreign Registration Expiration Date";

        }

        if(fbField.equals("gs-pfr-renew-date")){
            // ptoUser.setState(param); // sets state code

            // baseTrademarkApplication.findGSbyInternalID(gsID).setFaRegistrationNumber(fbValue);

            try {
                DateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
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


        if(ccField.equals("cc-in-use-one")){
            // ptoUser.setState(param); // sets state code
            if(ccValue.equals("yes")){

                for(Iterator<GoodAndService> iter = baseTrademarkApplication.getGoodAndServices().iterator(); iter.hasNext(); ) {
                    GoodAndService current = iter.next();

                    if(current.getClassNumber().equals(ccNumber)){
                       current.setAtLeastOneGoodInCommerceClassFlag(true);
                       current.setAtLeastOneGoodInCommerceClassFlagSet(true);
                        current.setMarkInUse(false);
                        current.setMarkInUseSet(false);



                    }
                }

            }
            else {
                for(Iterator<GoodAndService> iter = baseTrademarkApplication.getGoodAndServices().iterator(); iter.hasNext(); ) {
                    GoodAndService current = iter.next();

                    if(current.getClassNumber().equals(ccNumber)){
                        current.setAtLeastOneGoodInCommerceClassFlag(false);
                        current.setAtLeastOneGoodInCommerceClassFlagSet(true);
                        current.setProvideSpecimenForAllGS(false);
                        current.setProvideSpecimenForAllGSSet(false);
                        current.setMarkInUse(false);
                        current.setMarkInUseSet(true);


                    }
                }

            }

            // we need to loop through each goods and services and update its in use flag




            appFieldReadable = "Class level option";

        }

        if(ccField.equals("cc-specimen-class-opt")){
            // ptoUser.setState(param); // sets state code
            if(ccValue.equals("yes")){

                for(Iterator<GoodAndService> iter = baseTrademarkApplication.getGoodAndServices().iterator(); iter.hasNext(); ) {
                    GoodAndService current = iter.next();

                    if(current.getClassNumber().equals(ccNumber)){
                        current.setProvideSpecimenForAllGS(true);
                        current.setProvideSpecimenForAllGSSet(true);


                    }
                }

            }
            else {
                for(Iterator<GoodAndService> iter = baseTrademarkApplication.getGoodAndServices().iterator(); iter.hasNext(); ) {
                    GoodAndService current = iter.next();

                    if(current.getClassNumber().equals(ccNumber)){
                        current.setProvideSpecimenForAllGS(false);
                        current.setProvideSpecimenForAllGSSet(true);

                    }
                }

            }


            appFieldReadable = "Class level option";

        }


        if(ccField.equals("cc-specimen-class-opt")){
            // ptoUser.setState(param); // sets state code
            if(ccValue.equals("yes")){

                for(Iterator<GoodAndService> iter = baseTrademarkApplication.getGoodAndServices().iterator(); iter.hasNext(); ) {
                    GoodAndService current = iter.next();

                    if(current.getClassNumber().equals(ccNumber)){
                        current.setProvideSpecimenForAllGS(true);
                        current.setProvideSpecimenForAllGSSet(true);


                    }
                }

            }
            else {
                for(Iterator<GoodAndService> iter = baseTrademarkApplication.getGoodAndServices().iterator(); iter.hasNext(); ) {
                    GoodAndService current = iter.next();

                    if(current.getClassNumber().equals(ccNumber)){
                        current.setProvideSpecimenForAllGS(false);
                        current.setProvideSpecimenForAllGSSet(true);


                    }
                }

            }


            appFieldReadable = "Class level option";

        }


        if(ccField.equals("cc-pfa-option")) {

            //baseTrademarkApplication.findGSbyInternalID(gsID).setPendingFA(true);
            if (ccValue.equals("yes")) {

                for (Iterator<GoodAndService> iter = baseTrademarkApplication.getGoodAndServices().iterator(); iter.hasNext(); ) {
                    GoodAndService current = iter.next();

                    if (current.getClassNumber().equals(ccNumber)) {
                        current.setPendingFA(true);
                        current.setPendingFAAllGS(true);
                        current.setMarkInUse(true);
                        current.setMarkInUseSet(true);



                    }
                }


            }
            if (ccValue.equals("no")) {

                for (Iterator<GoodAndService> iter = baseTrademarkApplication.getGoodAndServices().iterator(); iter.hasNext(); ) {
                    GoodAndService current = iter.next();

                    if (current.getClassNumber().equals(ccNumber)) {
                        //current.setPendingFA(false);
                        current.setPendingFAAllGS(false);


                    }
                }


            }

            appFieldReadable = "Filing basis pending foreign application class level option";
        }


            if(ccField.equals("cc-pfr-option")) {

                //baseTrademarkApplication.findGSbyInternalID(gsID).setPendingFA(true);
                if (ccValue.equals("yes")) {

                    for (Iterator<GoodAndService> iter = baseTrademarkApplication.getGoodAndServices().iterator(); iter.hasNext(); ) {
                        GoodAndService current = iter.next();

                        if (current.getClassNumber().equals(ccNumber)) {
                            current.setForeignRegistration(true);
                            current.setForenginRegistrationAllGS(true);
                            current.setMarkInUse(true);
                            current.setMarkInUseSet(true);


                        }
                    }


                }
                if (ccValue.equals("no")) {

                    for (Iterator<GoodAndService> iter = baseTrademarkApplication.getGoodAndServices().iterator(); iter.hasNext(); ) {
                        GoodAndService current = iter.next();

                        if (current.getClassNumber().equals(ccNumber)) {
                            current.setForeignRegistration(false);
                            current.setForenginRegistrationAllGS(false);


                        }
                    }


                }

                appFieldReadable = "Filing basis pending foreign registration Class level Option";
            }


        if(ccField.equals("cc-pna-option")) {

            //baseTrademarkApplication.findGSbyInternalID(gsID).setPendingFA(true);
            if (ccValue.equals("yes")) {

                for (Iterator<GoodAndService> iter = baseTrademarkApplication.getGoodAndServices().iterator(); iter.hasNext(); ) {
                    GoodAndService current = iter.next();

                    if (current.getClassNumber().equals(ccNumber)) {
                        current.setForeignAR_NA(true);
                        current.setNA_AllGS(true);
                        current.setMarkInUse(true);
                        current.setMarkInUseSet(true);


                    }
                }


            }
            if (ccValue.equals("no")) {

                for (Iterator<GoodAndService> iter = baseTrademarkApplication.getGoodAndServices().iterator(); iter.hasNext(); ) {
                    GoodAndService current = iter.next();

                    if (current.getClassNumber().equals(ccNumber)) {
                        current.setForeignAR_NA(false);
                        current.setNA_AllGS(false);


                    }
                }


            }

            appFieldReadable = "Filing basis pending foreign registration Class level Option";
        }


        if(ccField.equals("cc-pfa-country")) {

            //baseTrademarkApplication.findGSbyInternalID(gsID).setPendingFA(true);


            for (Iterator<GoodAndService> iter = baseTrademarkApplication.getGoodAndServices().iterator(); iter.hasNext(); ) {
                GoodAndService current = iter.next();

                if (current.getClassNumber().equals(ccNumber)) {
                    current.setFaCountryCC(ccValue);
                    current.setFaCountry(ccValue);




                }
            }





            appFieldReadable = "Filing basis foreign application country Class level Option";
        }


        if(ccField.equals("cc-pfa-app-Number")) {

            //baseTrademarkApplication.findGSbyInternalID(gsID).setPendingFA(true);


            for (Iterator<GoodAndService> iter = baseTrademarkApplication.getGoodAndServices().iterator(); iter.hasNext(); ) {
                GoodAndService current = iter.next();

                if (current.getClassNumber().equals(ccNumber)) {
                    current.setFaRegistrationNumber(ccValue);
                    current.setFaAppNumberCC(ccValue);


                }
            }





            appFieldReadable = "Filing basis foreign application number Class level Option";
        }


        if(ccField.equals("cc-pfa-filing-date")) {

            //baseTrademarkApplication.findGSbyInternalID(gsID).setPendingFA(true);


            for (Iterator<GoodAndService> iter = baseTrademarkApplication.getGoodAndServices().iterator(); iter.hasNext(); ) {
                GoodAndService current = iter.next();

                if (current.getClassNumber().equals(ccNumber)) {
                   //current.setFaFilingDate(ccValue);

                    try {
                        DateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
                        Date date = format.parse(ccValue);
                        current.setFaFilingDate(date);
                        current.setFaFilingDateCC(date);

                    }
                    catch(Exception ex){
                        return buildResponseEnity("420", "ERROR: Could not save Date, invalid Date format");

                    }




                }
            }


            appFieldReadable = "Filing basis foreign application filing date Class level Option";
        }


        if(ccField.equals("cc-pfr-country")) {

            //baseTrademarkApplication.findGSbyInternalID(gsID).setPendingFA(true);


            for (Iterator<GoodAndService> iter = baseTrademarkApplication.getGoodAndServices().iterator(); iter.hasNext(); ) {
                GoodAndService current = iter.next();

                if (current.getClassNumber().equals(ccNumber)) {
                    current.setFrCountry(ccValue);
                    current.setFrCountryCC(ccValue);


                }
            }





            appFieldReadable = "Filing basis foreign registration country Class level Option";
        }


        if(ccField.equals("cc-pfr-reg-Number")) {

            //baseTrademarkApplication.findGSbyInternalID(gsID).setPendingFA(true);


            for (Iterator<GoodAndService> iter = baseTrademarkApplication.getGoodAndServices().iterator(); iter.hasNext(); ) {
                GoodAndService current = iter.next();

                if (current.getClassNumber().equals(ccNumber)) {
                     current.setFrRegistartionNumber(ccValue);
                     current.setFrRegistrationNumberCC(ccValue);


                }
            }





            appFieldReadable = "Filing basis foreign registration number Class level Option";
        }



        if(ccField.equals("cc-pfr-reg-date")) {

            //baseTrademarkApplication.findGSbyInternalID(gsID).setPendingFA(true);


            for (Iterator<GoodAndService> iter = baseTrademarkApplication.getGoodAndServices().iterator(); iter.hasNext(); ) {
                GoodAndService current = iter.next();

                if (current.getClassNumber().equals(ccNumber)) {
                    //current.setFaFilingDate(ccValue);

                    try {
                        DateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
                        Date date = format.parse(ccValue);
                        current.setFrRegistrationDate(date);
                        current.setFrRegistrationDateCC(date);

                    }
                    catch(Exception ex){
                        return buildResponseEnity("420", "ERROR: Could not save Date, invalid Date format");

                    }




                }
            }


            appFieldReadable = "Filing basis foreign registration date Class level Option";
        }

        if(ccField.equals("cc-pfr-exp-date")) {

            //baseTrademarkApplication.findGSbyInternalID(gsID).setPendingFA(true);


            for (Iterator<GoodAndService> iter = baseTrademarkApplication.getGoodAndServices().iterator(); iter.hasNext(); ) {
                GoodAndService current = iter.next();

                if (current.getClassNumber().equals(ccNumber)) {
                    //current.setFaFilingDate(ccValue);

                    try {
                        DateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
                        Date date = format.parse(ccValue);
                        current.setFrExpirationDate(date);
                        current.setFrExpireDateCC(date);

                    }
                    catch(Exception ex){
                        return buildResponseEnity("420", "ERROR: Could not save Date, invalid Date format");

                    }




                }
            }


            appFieldReadable = "Filing basis foreign registration  expire date Class level Option";
        }


        if(ccField.equals("cc-pfr-renew-date")) {

            //baseTrademarkApplication.findGSbyInternalID(gsID).setPendingFA(true);


            for (Iterator<GoodAndService> iter = baseTrademarkApplication.getGoodAndServices().iterator(); iter.hasNext(); ) {
                GoodAndService current = iter.next();

                if (current.getClassNumber().equals(ccNumber)) {
                    //current.setFaFilingDate(ccValue);

                    try {
                        DateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
                        Date date = format.parse(ccValue);
                        current.setFrRenewlDate(date);
                        current.setFrRenewalDateCC(date);

                    }
                    catch(Exception ex){
                        return buildResponseEnity("420", "ERROR: Could not save Date, invalid Date format");

                    }




                }
            }


            appFieldReadable = "Filing basis foreign registration  expire date Class level Option";
        }


        if(ccField.equals("cc-gs-date")) {

            //baseTrademarkApplication.findGSbyInternalID(gsID).setPendingFA(true);


            for (Iterator<GoodAndService> iter = baseTrademarkApplication.getGoodAndServices().iterator(); iter.hasNext(); ) {
                GoodAndService current = iter.next();


                System.out.println("class number : "+ccNumber);

                if (current.getClassNumber().equals(ccNumber)) {
                    //current.setFaFilingDate(ccValue);

                    try {
                        DateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
                        Date date = format.parse(ccValue);
                        //current.setFaFilingDate(date);
                        current.setFirstGSDate(date);
                        current.setFirstGSDateSet(true);
                        // need to create the new class level fields
                        // and update the copy construstuctor process to copy over the new fields

                        //current.setFaFilingDateCC(date);
                        current.setFirstGSDateCC(date);

                    }
                    catch(Exception ex){
                        return buildResponseEnity("420", "ERROR: Could not save Date, invalid Date format");

                    }




                }
            }


            appFieldReadable = "Filing basis first goods and services date Class level Option";
        }

        if(ccField.equals("cc-mark-date")) {

            //baseTrademarkApplication.findGSbyInternalID(gsID).setPendingFA(true);


            for (Iterator<GoodAndService> iter = baseTrademarkApplication.getGoodAndServices().iterator(); iter.hasNext(); ) {
                GoodAndService current = iter.next();

                if (current.getClassNumber().equals(ccNumber)) {
                    //current.setFaFilingDate(ccValue);

                    try {
                        DateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
                        Date date = format.parse(ccValue);
                        //current.setFaFilingDate(date);
                        current.setFirstCommerceDate(date);
                        current.setFirstCommerceDateSet(true);
                        // need to create the new class level fields
                        // and update the copy construstuctor process to copy over the new fields

                        //current.setFaFilingDateCC(date);
                        current.setFirstMarkDateCC(date);

                    }
                    catch(Exception ex){
                        return buildResponseEnity("420", "ERROR: Could not save Date, invalid Date format");

                    }




                }
            }


            appFieldReadable = "Filing basis first use mark in commerce date Class level Option";
        }





        baseTradeMarkApplicationService.save(baseTrademarkApplication);
        String responseMsg = appFieldReadable+" has been saved";

        return buildResponseEnity("200", responseMsg);
    }
}
