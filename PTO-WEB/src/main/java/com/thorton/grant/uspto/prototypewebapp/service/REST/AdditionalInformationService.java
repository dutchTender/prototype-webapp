package com.thorton.grant.uspto.prototypewebapp.service.REST;

import com.thorton.grant.uspto.prototypewebapp.config.host.bean.endPoint.HostBean;
import com.thorton.grant.uspto.prototypewebapp.factories.ServiceBeanFactory;
import com.thorton.grant.uspto.prototypewebapp.interfaces.USPTO.tradeMark.application.types.BaseTradeMarkApplicationService;
import com.thorton.grant.uspto.prototypewebapp.model.entities.USPTO.tradeMark.application.types.BaseTrademarkApplication;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import static org.springframework.web.bind.annotation.RequestMethod.GET;


@RestController
@Service
public class AdditionalInformationService extends  BaseRESTapiService {

    public AdditionalInformationService(ServiceBeanFactory serviceBeanFactory, HostBean hostBean) {
        super(serviceBeanFactory, hostBean);
    }


    @CrossOrigin(origins = {"https://localhost","https://efile-reimagined.com"})
    @RequestMapping(method = GET, value="/REST/apiGateway/additionalInfo/update/uncommonType/{fieldName}/{fieldValue}/{appInternalID}")
    @ResponseBody
    ResponseEntity<String> updateApplicationRegistar(@PathVariable String fieldName , @PathVariable String fieldValue, @PathVariable String appInternalID){

        String appFieldReadable = "Register Type";
        BaseTradeMarkApplicationService baseTradeMarkApplicationService = getServiceBeanFactory().getBaseTradeMarkApplicationService();
        BaseTrademarkApplication baseTrademarkApplication = baseTradeMarkApplicationService.findByInternalID(appInternalID);

        if(fieldName.equals("ai-supplemental-reg")){
            if(fieldValue.equals("yes")){
                baseTrademarkApplication.setSupplementalRegister(true);
                baseTrademarkApplication.setPrincipalRegister(false);
                baseTrademarkApplication.setRegisterTypeSet(true);
                baseTrademarkApplication.setBaseFee(275);
            }
        }


        if(fieldName.equals("ai-principal-reg")){
            if(fieldValue.equals("yes")){
                baseTrademarkApplication.setSupplementalRegister(false);
                baseTrademarkApplication.setPrincipalRegister(true);
                baseTrademarkApplication.setRegisterTypeSet(true);
                baseTrademarkApplication.setBaseFee(225);
            }
        }

        if(fieldName.equals("ai-register-type-set")){
            if(fieldValue.equals("yes")) {

                baseTrademarkApplication.setRegisterTypeSet(true);
            }
            else {
                baseTrademarkApplication.setRegisterTypeSet(false);
            }
        }

        if(fieldName.equals("ai-distinctivenss-claim")){
            if(fieldValue.equals("yes")) {

                baseTrademarkApplication.setClaimDistinctiveness(true);
            }
            else {
                baseTrademarkApplication.setClaimDistinctiveness(false);
            }

            appFieldReadable = "2f Claim ";
        }



        String responseMsg = "{{server-message:"+appFieldReadable+" has been saved}";

        // new return message structure

        //  return buildResponseEnity("200", "{image-url:" +filePath+"}");
        // {server-msg:xxxxx},{fee-display-html:xxxx},{total-class-html:xxxxx},{total-extra-class-html:xxxx},{extra-class-fee-info-html:xxxxxx},{extra-class-fee-calc-html},{basic-fee-calc-html:xxxxx},{fee-total-html}
        responseMsg+=",{fee-display-html:"+baseTrademarkApplication.getTotalFeeString()+"}"+",{total-class-html:"+baseTrademarkApplication.getTotalNumberOfclasses()+"}"+",{total-extra-class-html:"+baseTrademarkApplication.getNumberOfExtraClasses()+"}"+",{basic-fee-calc-html:"+baseTrademarkApplication.getBasicFeeCalculationString()+"}"+",{extra-class-fee-calc-html:"+baseTrademarkApplication.getExtraFeeCalculationString()+"}}";
        //return ResponseEntity.ok().headers(responseHeader).body(responseMsg) ;
        return buildResponseEnity("200", responseMsg);



    }


    @CrossOrigin(origins = {"https://localhost","https://efile-reimagined.com"})
    @RequestMapping(method = GET, value="/REST/apiGateway/additionalInfo/update/{fieldName}/{fieldValue}/{appInternalID}")
    @ResponseBody
    ResponseEntity<String> updateApplicationFields(@PathVariable String fieldName , @PathVariable String fieldValue, @PathVariable String appInternalID){


        if(verifyValidUserSession("xxx") == false){

            String responseMsg = fieldName+" has not been saved. invalid user session.";
            return buildResponseEnity("404", responseMsg );

        }

        // retrieve application using passed internal id
        //BaseTrademarkApplication baseTrademarkApplication = baseTradeMarkApplicationService.findByInternalID(appInternalID);
        String appFieldReadable = "";
        BaseTradeMarkApplicationService baseTradeMarkApplicationService = getServiceBeanFactory().getBaseTradeMarkApplicationService();
        BaseTrademarkApplication baseTrademarkApplication = baseTradeMarkApplicationService.findByInternalID(appInternalID);


        if(fieldName.equals("ai-misc-info-opt")){
            // ptoUser.setState(param); // sets state code

            if(fieldValue.equals("yes")){

                baseTrademarkApplication.setProvideMiscInfo(true);
                baseTrademarkApplication.setProvideMiscInfoFlagSet(true);

            }
            if(fieldValue.equals("no")){
                baseTrademarkApplication.setProvideMiscInfo(false);
                baseTrademarkApplication.setProvideMiscInfoFlagSet(true);

            }


            appFieldReadable = "Miscellaneous info option";


        }



        if(fieldName.equals("ai-misc-statement")){
            // ptoUser.setState(param); // sets state code
            baseTrademarkApplication.setMiscInformation(fieldValue);
            appFieldReadable = "Miscellaneous statement";

        }

        if(fieldName.equals("ai-concurrent-use")){
            // ptoUser.setState(param); // sets state code

            if(fieldValue.equals("yes")){

                baseTrademarkApplication.setConcurrentUse(true);
                baseTrademarkApplication.setDeclarationConcurrentUser(true);
            }
            if(fieldValue.equals("no")){
                baseTrademarkApplication.setConcurrentUse(false);
                baseTrademarkApplication.setDeclarationConcurrentUser(false);

            }
            baseTrademarkApplication.setDeclarationConcurrentUserSet(true);

            appFieldReadable = "Concurrent use declaration";


        }



        if(fieldName.equals("ai-concurrent-type-court")){
            // ptoUser.setState(param); // sets state code

            if(fieldValue.equals("yes")){

                baseTrademarkApplication.setConcurrentTypeCourtDecree(true);
                baseTrademarkApplication.setConcurrentTypeSet(true);
            }
            if(fieldValue.equals("no")){
                baseTrademarkApplication.setConcurrentTypeCourtDecree(false);
                if(baseTrademarkApplication.isConcurrentTypeEarlierFirstUse() == false && baseTrademarkApplication.isConcurrentTypePriorDecision() == false && baseTrademarkApplication.isConcurrentTypeWrittenConsent() == false){
                    baseTrademarkApplication.setConcurrentTypeSet(false);
                }


            }

            appFieldReadable = "Concurrent use type ";


        }

        if(fieldName.equals("ai-concurrent-type-prior")){
            // ptoUser.setState(param); // sets state code

            if(fieldValue.equals("yes")){

                baseTrademarkApplication.setConcurrentTypePriorDecision(true);
                baseTrademarkApplication.setConcurrentTypeSet(true);
            }
            if(fieldValue.equals("no")){
                baseTrademarkApplication.setConcurrentTypePriorDecision(false);
                if(baseTrademarkApplication.isConcurrentTypeEarlierFirstUse() == false && baseTrademarkApplication.isConcurrentTypeCourtDecree() == false && baseTrademarkApplication.isConcurrentTypeWrittenConsent() == false){
                    baseTrademarkApplication.setConcurrentTypeSet(false);
                }


            }

            appFieldReadable = "Concurrent use type ";


        }

        if(fieldName.equals("ai-concurrent-type-written")){
            // ptoUser.setState(param); // sets state code

            if(fieldValue.equals("yes")){

                baseTrademarkApplication.setConcurrentTypeWrittenConsent(true);
                baseTrademarkApplication.setConcurrentTypeSet(true);
            }
            if(fieldValue.equals("no")){
                baseTrademarkApplication.setConcurrentTypeWrittenConsent(false);
                if(baseTrademarkApplication.isConcurrentTypeEarlierFirstUse() == false && baseTrademarkApplication.isConcurrentTypePriorDecision() == false && baseTrademarkApplication.isConcurrentTypeCourtDecree() == false){
                    baseTrademarkApplication.setConcurrentTypeSet(false);
                }


            }

            appFieldReadable = "Concurrent use type ";


        }
        if(fieldName.equals("ai-concurrent-type-earlier")){
            // ptoUser.setState(param); // sets state code

            if(fieldValue.equals("yes")){

                baseTrademarkApplication.setConcurrentTypeEarlierFirstUse(true);
                baseTrademarkApplication.setConcurrentTypeSet(true);
            }
            if(fieldValue.equals("no")){
                baseTrademarkApplication.setConcurrentTypeEarlierFirstUse(false);
                if(baseTrademarkApplication.isConcurrentTypeCourtDecree() == false && baseTrademarkApplication.isConcurrentTypePriorDecision() == false && baseTrademarkApplication.isConcurrentTypeWrittenConsent() == false){
                    baseTrademarkApplication.setConcurrentTypeSet(false);
                }


            }

            appFieldReadable = "Concurrent use type ";


        }


        if(fieldName.equals("ai-concurrent-image-desc")){
            // ptoUser.setState(param); // sets state code

            baseTrademarkApplication.setConcurentEvidenceDescription(fieldValue);
            appFieldReadable = "Concurrent use evidence description ";

        }


        if(fieldName.equals("ai-concurrent-mode")){
            // ptoUser.setState(param); // sets state code

            baseTrademarkApplication.setModeOfUse(fieldValue);
            appFieldReadable = "Concurrent use mode of use";

        }
        if(fieldName.equals("ai-concurrent-geo-commerce")){
            // ptoUser.setState(param); // sets state code

            baseTrademarkApplication.setGeoAreaMarkInCommerce(fieldValue);
            appFieldReadable = "Concurrent use geographic area in commerce";

        }


        if(fieldName.equals("ai-concurrent-ttab-num")){
            // ptoUser.setState(param); // sets state code

            baseTrademarkApplication.setTtabProceedingNumber(fieldValue);
            appFieldReadable = "Concurrent use TTAB proceeding number";

        }
        if(fieldName.equals("ai-concurrent-reg-num")){
            // ptoUser.setState(param); // sets state code

            baseTrademarkApplication.setConcurrentUserRegistrationNumber(fieldValue);
            appFieldReadable = "Concurrent user registration number";

        }
        if(fieldName.equals("ai-concurrent-user-name")){
            // ptoUser.setState(param); // sets state code

            baseTrademarkApplication.setConcurrentUserName(fieldValue);
            appFieldReadable = "Concurrent user name";

        }

        if(fieldName.equals("ai-concurrent-no-registration")){
            // ptoUser.setState(param); // sets state code

            if(fieldValue.equals("yes")){
                baseTrademarkApplication.setConcurrentUserNoRegistrationClaim(true);
                baseTrademarkApplication.setConcurrentUserNoRegistrationClaimSet(true);
            }
            else {
                baseTrademarkApplication.setConcurrentUserNoRegistrationClaim(false);
                baseTrademarkApplication.setConcurrentUserNoRegistrationClaimSet(true);
            }

            appFieldReadable = "Concurrent claim no registration";

        }



        if(fieldName.equals("ai-concurrent-user-country")){
            // ptoUser.setState(param); // sets state code

            baseTrademarkApplication.setConcurrentUserCountry(fieldValue);
            appFieldReadable = "Concurrent user country";

        }


        if(fieldName.equals("ai-concurrent-user-address1")){
            // ptoUser.setState(param); // sets state code

            baseTrademarkApplication.setConcurrentUserAddress1(fieldValue);
            appFieldReadable = "Concurrent user address";

        }

        if(fieldName.equals("ai-concurrent-user-city")){
            // ptoUser.setState(param); // sets state code

            baseTrademarkApplication.setConcurrentUserCity(fieldValue);
            appFieldReadable = "Concurrent user city";

        }
        if(fieldName.equals("ai-concurrent-user-state")){
            // ptoUser.setState(param); // sets state code

            baseTrademarkApplication.setConcurrentUserState(fieldValue);
            appFieldReadable = "Concurrent user state";

        }

        if(fieldName.equals("ai-concurrent-user-zip")){
            // ptoUser.setState(param); // sets state code

            baseTrademarkApplication.setConcurrentUserZipcode(fieldValue);
            appFieldReadable = "Concurrent user zip code";

        }

        if(fieldName.equals("ai-concurrent-gs")){
            // ptoUser.setState(param); // sets state code

            baseTrademarkApplication.setConcurrentUserGoodsAndService(fieldValue);
            appFieldReadable = "Concurrent goods and services";

        }

        if(fieldName.equals("ai-concurrent-geo-area")){
            // ptoUser.setState(param); // sets state code

            baseTrademarkApplication.setGeoAreaConcurrentUser(fieldValue);
            appFieldReadable = "Concurrent geographic area";

        }

        if(fieldName.equals("ai-concurrent-mode-use")){
            // ptoUser.setState(param); // sets state code

            baseTrademarkApplication.setModeOfuseConcurrentUser(fieldValue);
            appFieldReadable = "Concurrent mode of use";

        }

        if(fieldName.equals("ai-concurrent-time-use")){
            // ptoUser.setState(param); // sets state code

            baseTrademarkApplication.setTimePeriodConcurrentUser(fieldValue);
            appFieldReadable = "Concurrent period of use";

        }
        //////////////////////////////////////////////////////////////////////////////




        if(fieldName.equals("ai-inheritantly-distinct")){
            // ptoUser.setState(param); // sets state code

            if(fieldValue.equals("yes")){

                baseTrademarkApplication.setInheritantlyDistinctive(true);
                baseTrademarkApplication.setInheritantlyDistinctiveSet(true);
            }
            if(fieldValue.equals("no")){
                baseTrademarkApplication.setInheritantlyDistinctive(false);
                baseTrademarkApplication.setInheritantlyDistinctiveSet(true);

            }

            appFieldReadable = "Claim of inherently distinctive ";


        }


        if(fieldName.equals("ai-whole-distinct")){
            // ptoUser.setState(param); // sets state code

            if(fieldValue.equals("yes")){

                baseTrademarkApplication.setInheritantlyWhole(true);
                baseTrademarkApplication.setWholePartSet(true);

            }
            if(fieldValue.equals("no")){
                baseTrademarkApplication.setInheritantlyWhole(false);


            }

            appFieldReadable = "Claim of inherently distinctive whole";


        }


        if(fieldName.equals("ai-part-distinct")){
            // ptoUser.setState(param); // sets state code

            if(fieldValue.equals("yes")){

                baseTrademarkApplication.setInheritantlyPart(true);
                baseTrademarkApplication.setWholePartSet(true);

            }
            if(fieldValue.equals("no")){
                baseTrademarkApplication.setInheritantlyPart(false);


            }

            appFieldReadable = "Claim of inherently distinctive part";


        }


        if(fieldName.equals("ai-part-claim-desc")){
            // ptoUser.setState(param); // sets state code

            baseTrademarkApplication.setInPartClaimDescription(fieldValue);

            appFieldReadable = "Claim of inherently distinctive part description";


        }

        if(fieldName.equals("ai-claim-distinct-evidence")){
            // ptoUser.setState(param); // sets state code

            if(fieldValue.equals("yes")){

               baseTrademarkApplication.setDistinctClaimBasedEvidence(true);
               baseTrademarkApplication.setDistinctClaimBasedPriorReg(false);
              baseTrademarkApplication.setDistinctClaimBasedFiveYOU(false);
            }

            appFieldReadable = "Distinctive claim base";


        }
        if(fieldName.equals("ai-claim-distinct-PR")){
            // ptoUser.setState(param); // sets state code

            if(fieldValue.equals("yes")){

                baseTrademarkApplication.setDistinctClaimBasedEvidence(false);
                baseTrademarkApplication.setDistinctClaimBasedPriorReg(true);
                baseTrademarkApplication.setDistinctClaimBasedFiveYOU(false);
            }

            appFieldReadable = "Distinctive claim base";


        }

        if(fieldName.equals("ai-claim-distinct-FIVE")){
            // ptoUser.setState(param); // sets state code

            if(fieldValue.equals("yes")){

                baseTrademarkApplication.setDistinctClaimBasedEvidence(false);
                baseTrademarkApplication.setDistinctClaimBasedPriorReg(false);
                baseTrademarkApplication.setDistinctClaimBasedFiveYOU(true);
            }

            appFieldReadable = "Distinctive claim base";


        }


        if(fieldName.equals("ai-claim-distinct-PR-number")){
            // ptoUser.setState(param); // sets state code

            baseTrademarkApplication.setDistinctClaimBasedPriorRegNumber(fieldValue);

            appFieldReadable = "Distinct claim prior registration number";


        }
        //////////////////////////////////////////////////////////////////////////////////////////////////////////////

        if(fieldName.equals("ai-use-another-form")){
            // ptoUser.setState(param); // sets state code

            if(fieldValue.equals("yes")){

                baseTrademarkApplication.setUseInAnotherForm(true);

            }
            if(fieldValue.equals("no")){
                baseTrademarkApplication.setUseInAnotherForm(false);


            }


            appFieldReadable = "Use in another form declaration";


        }

        if(fieldName.equals("ai-use-another-form-current")){
            // ptoUser.setState(param); // sets state code

            if(fieldValue.equals("yes")){

                baseTrademarkApplication.setUseInAnotherFormCurrent(true);
                baseTrademarkApplication.setUseInAnotherFormCurrentSet(true);

            }
            if(fieldValue.equals("no")){
                baseTrademarkApplication.setUseInAnotherFormCurrent(false);
                baseTrademarkApplication.setUseInAnotherFormCurrentSet(true);


            }


            appFieldReadable = "Use in another form declaration";


        }

        if(fieldName.equals("ai-use-another-form-whole")){
            // ptoUser.setState(param); // sets state code



                baseTrademarkApplication.setUserInAnotherFormWhole(true);
                baseTrademarkApplication.setUseInAnotherFormPart(false);
                baseTrademarkApplication.setUserInAnotherFormWholePartSet(true);





            appFieldReadable = "Use in another form whole";


        }
        if(fieldName.equals("ai-use-another-form-part")){
            // ptoUser.setState(param); // sets state code



            baseTrademarkApplication.setUserInAnotherFormWhole(false);
            baseTrademarkApplication.setUseInAnotherFormPart(true);
            baseTrademarkApplication.setUserInAnotherFormWholePartSet(true);


            appFieldReadable = "Use in another form part";


        }

        if(fieldName.equals("ai-use-another-form-part-desc")){
            // ptoUser.setState(param); // sets state code



            baseTrademarkApplication.setUserInAnotherFormMarkpart(fieldValue);



            appFieldReadable = "Use in another form which part";


        }


        if(fieldName.equals("ai-use-another-form-first-use-date")){

            try {
                DateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
                Date date = format.parse(fieldValue);
               baseTrademarkApplication.setUseInAnotherFormFirstUseDate(date);
            }
            catch(Exception ex){
                return buildResponseEnity("420", "ERROR: Could not save Date, invalid Date format");

            }




            appFieldReadable = "Use in another form first in use date";


        }

        if(fieldName.equals("ai-use-another-form-first-commerce-date")){
            // ptoUser.setState(param); // sets state code




            try {
                DateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
                Date date = format.parse(fieldValue);
                baseTrademarkApplication.setUseInAnotherFormFirstCommerceDate(date);
            }
            catch(Exception ex){
                return buildResponseEnity("420", "ERROR: Could not save Date, invalid Date format");

            }


            appFieldReadable = "Use in another form first commerce date";


        }






        String responseMsg = appFieldReadable+" has been saved";

        baseTradeMarkApplicationService.save(baseTrademarkApplication);

        //return ResponseEntity.ok().headers(responseHeader).body(responseMsg) ;
        return buildResponseEnity("200", responseMsg);
    }

}
