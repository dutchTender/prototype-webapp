package com.thorton.grant.uspto.prototypewebapp.service.REST;

import com.thorton.grant.uspto.prototypewebapp.config.host.bean.endPoint.HostBean;
import com.thorton.grant.uspto.prototypewebapp.factories.ServiceBeanFactory;
import com.thorton.grant.uspto.prototypewebapp.interfaces.USPTO.tradeMark.application.types.BaseTradeMarkApplicationService;
import com.thorton.grant.uspto.prototypewebapp.model.entities.USPTO.tradeMark.application.types.BaseTrademarkApplication;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
@RestController
@Service
public class TradeMarkService extends BaseRESTapiService {

    public TradeMarkService(ServiceBeanFactory serviceBeanFactory, HostBean hostBean) {
        super(serviceBeanFactory, hostBean);
    }

    @CrossOrigin(origins = {"https://localhost","https://efile-reimagined.com"})
    @RequestMapping(method = GET, value="/REST/apiGateway/mark/update/{markField}/{markValue}/{appInternalID}")
    @ResponseBody
    ResponseEntity<String> updateApplicationFields(@PathVariable String markField , @PathVariable String markValue, @PathVariable String appInternalID){


        if(verifyValidUserSession("xxx") == false){

            String responseMsg = markField+" has not been saved. invalid user session.";
            return buildResponseEnity("404", responseMsg );

        }

        // retrieve application using passed internal id
        //BaseTrademarkApplication baseTrademarkApplication = baseTradeMarkApplicationService.findByInternalID(appInternalID);
        String appFieldReadable = "";
        BaseTradeMarkApplicationService baseTradeMarkApplicationService = getServiceBeanFactory().getBaseTradeMarkApplicationService();
        BaseTrademarkApplication baseTrademarkApplication = baseTradeMarkApplicationService.findByInternalID(appInternalID);

        if(markField.equals("mark-literal")){
            // ptoUser.setState(param); // sets state code
            baseTrademarkApplication.getTradeMark().setMarkLiteral(markValue);
            appFieldReadable = "Mark Literal";

        }

        if(markField.equals("mark-color-claim")){
            // ptoUser.setState(param); // sets state code

            if(markValue.equals("yes")){

                baseTrademarkApplication.getTradeMark().setMarkColorClaim(true);
                baseTrademarkApplication.getTradeMark().setMarkColorClaimBW(false);
                baseTrademarkApplication.getTradeMark().setColorClaimSet(true);


            }
            if(markValue.equals("no")){

                baseTrademarkApplication.getTradeMark().setMarkColorClaim(false);
                baseTrademarkApplication.getTradeMark().setColorClaimSet(true);

            }

            appFieldReadable = "Color Claim";

        }

        if(markField.equals("mark-accept-BW")){
            // ptoUser.setState(param); // sets state code

           baseTrademarkApplication.getTradeMark().setMarkColorClaimBW(true);
            appFieldReadable = "Accept BW Drawing ";

        }


        if(markField.equals("mark-description")){
            // ptoUser.setState(param); // sets state code
            baseTrademarkApplication.getTradeMark().setMarkDescription(markValue);
            appFieldReadable = "Mark Description";

        }


        if(markField.equals("mark-BW-description")){
            // ptoUser.setState(param); // sets state code
            baseTrademarkApplication.getTradeMark().setMarkBWDescription(markValue);
            appFieldReadable = "Mark BW Description";

        }

        if(markField.equals("mark-color-list")){
            // ptoUser.setState(param); // sets state code
            baseTrademarkApplication.getTradeMark().setMarkColors(markValue);
            appFieldReadable = "Mark Colors ";

        }

        if(markField.equals("mark-color-description")){
            // ptoUser.setState(param); // sets state code
            baseTrademarkApplication.getTradeMark().setMarkColorDescription(markValue);
            appFieldReadable = "Mark Colors Description";

        }

        if(markField.equals("mark-fw-translation")){
            // ptoUser.setState(param); // sets state code

            if(markValue.equals("yes")){
                baseTrademarkApplication.getTradeMark().setForeignLanguageTranslationWording(true);
                baseTrademarkApplication.getTradeMark().setTranslationSet(true);

            }
            if(markValue.equals("no")){
                baseTrademarkApplication.getTradeMark().setForeignLanguageTranslationWording(false);
                baseTrademarkApplication.getTradeMark().setTranslationSet(true);

            }

            appFieldReadable = "Mark Translation Foreign Wording";

        }

        if(markField.equals("mark-fw-languageType")){
            // ptoUser.setState(param); // sets state code
            baseTrademarkApplication.getTradeMark().setForeignLanguageType_translation(markValue);

            appFieldReadable = "Mark Foreign Language Type - Translation";

        }

        if(markField.equals("mark-fw-fwText")){
            // ptoUser.setState(param); // sets state code
            baseTrademarkApplication.getTradeMark().setForeignLanguageTranslationOriginalText(markValue);

            appFieldReadable = "Mark Foreign Language Text - Translation ";

        }
        if(markField.equals("mark-fw-engText")){
            // ptoUser.setState(param); // sets state code
            baseTrademarkApplication.getTradeMark().setForeignLanguageTranslationUSText(markValue);

            appFieldReadable = "Mark Foreign Language English Text - Translation";

        }

        if(markField.equals("mark-fw-transliteration")){
            // ptoUser.setState(param); // sets state code

            if(markValue.equals("yes")){
                baseTrademarkApplication.getTradeMark().setForeignLanguateTransliterationWording(true);
                baseTrademarkApplication.getTradeMark().setTranlierationSet(true);

            }
            if(markValue.equals("no")){
                baseTrademarkApplication.getTradeMark().setForeignLanguateTransliterationWording(false);
                baseTrademarkApplication.getTradeMark().setTranlierationSet(true);

            }

            appFieldReadable = "Mark Transliteration Foreign Wording";

        }



        if(markField.equals("mark-fw-translit-languageType")){
            // ptoUser.setState(param); // sets state code
            baseTrademarkApplication.getTradeMark().setForeignLanguageType_transliteration(markValue);

            appFieldReadable = "Mark Foreign Language Type - Transliteration";

        }

        if(markField.equals("mark-fw-translit-fwText")){
            // ptoUser.setState(param); // sets state code
            baseTrademarkApplication.getTradeMark().setForeignLanguateTransliterationOriginalText(markValue);

            appFieldReadable = "Mark Foreign Language Text - Transliteration";

        }
        if(markField.equals("mark-fw-translit-engText")){
            // ptoUser.setState(param); // sets state code
            baseTrademarkApplication.getTradeMark().setForeignLanguateTransliterationUSText(markValue);

            appFieldReadable = "Mark Foreign Language English Text - Transliteration";

        }

        if(markField.equals("mark-name-sig-portrait")){
            // ptoUser.setState(param); // sets state code

            if(markValue.equals("yes")){
                baseTrademarkApplication.getTradeMark().setContainNamePortaitSignature(true);
                baseTrademarkApplication.getTradeMark().setNamePortraitSet(true);

            }
            if(markValue.equals("no")){
                baseTrademarkApplication.getTradeMark().setContainNamePortaitSignature(false);
                baseTrademarkApplication.getTradeMark().setNamePortraitSet(true);

            }

            appFieldReadable = "Mark NPS - Option";

        }

        if(markField.equals("mark-NPS-name")){
            // ptoUser.setState(param); // sets state code

            System.out.println("mark nps value : "+markValue);


            if(markValue.equals("yes")){
                baseTrademarkApplication.getTradeMark().setName(true);

            }
            if(markValue.equals("no")){
                baseTrademarkApplication.getTradeMark().setName(false);

            }

            appFieldReadable = "Mark NPS - Name";

        }

        if(markField.equals("mark-NPS-portrait")){
            // ptoUser.setState(param); // sets state code

            if(markValue.equals("yes")){
                baseTrademarkApplication.getTradeMark().setPortrait(true);

            }
            if(markValue.equals("no")){
                baseTrademarkApplication.getTradeMark().setPortrait(false);

            }

            appFieldReadable = "Mark NPS - Portrait";

        }

        if(markField.equals("mark-NPS-signature")){
            // ptoUser.setState(param); // sets state code

            if(markValue.equals("yes")){
                baseTrademarkApplication.getTradeMark().setSignature(true);

            }
            if(markValue.equals("no")){
                baseTrademarkApplication.getTradeMark().setSignature(false);

            }

            appFieldReadable = "Mark NPS - Signature";

        }


        if(markField.equals("mark-NPS-name-firstName")){
            // ptoUser.setState(param); // sets state code

            baseTrademarkApplication.getTradeMark().setNameFirstName(markValue);
            appFieldReadable = "Mark NPS - name First Name";

        }
        if(markField.equals("mark-NPS-name-middleName")){
            // ptoUser.setState(param); // sets state code

            baseTrademarkApplication.getTradeMark().setNameMiddleName(markValue);
            appFieldReadable = "Mark NPS - name Middle Name";

        }

        if(markField.equals("mark-NPS-name-lastName")){
            // ptoUser.setState(param); // sets state code

            baseTrademarkApplication.getTradeMark().setNameLastName(markValue);
            appFieldReadable = "Mark NPS - name Last Name";

        }

        if(markField.equals("mark-NPS-signature-firstName")){
            // ptoUser.setState(param); // sets state code

            baseTrademarkApplication.getTradeMark().setSignatureFirstName(markValue);
            appFieldReadable = "Mark NPS - signature First Name";

        }
        if(markField.equals("mark-NPS-signature-middleName")){
            // ptoUser.setState(param); // sets state code

            baseTrademarkApplication.getTradeMark().setSignatureMiddleName(markValue);
            appFieldReadable = "Mark NPS - signature Middle Name";

        }


        if(markField.equals("mark-NPS-signature-lastName")){
            // ptoUser.setState(param); // sets state code

            baseTrademarkApplication.getTradeMark().setSignatureLastName(markValue);
            appFieldReadable = "Mark NPS - signature Last Name";

        }

        if(markField.equals("mark-NPS-portrait-firstName")){
            // ptoUser.setState(param); // sets state code

            baseTrademarkApplication.getTradeMark().setPortraitFirstName(markValue);
            appFieldReadable = "Mark NPS - portrait First Name";

        }
        if(markField.equals("mark-NPS-portrait-middleName")){
            // ptoUser.setState(param); // sets state code

            baseTrademarkApplication.getTradeMark().setPortraitMiddleName(markValue);
            appFieldReadable = "Mark NPS - portrait Middle Name";

        }
        if(markField.equals("mark-NPS-portrait-lastName")){
            // ptoUser.setState(param); // sets state code

            baseTrademarkApplication.getTradeMark().setPortraitLastName(markValue);
            appFieldReadable = "Mark NPS - portrait Last Name";

        }

        if(markField.equals("mark-nps-living")){
            // ptoUser.setState(param); // sets state code

            if(markValue.equals("yes")){
                baseTrademarkApplication.getTradeMark().setNPSLivingPerson(true);

            }
            if(markValue.equals("no")){
                baseTrademarkApplication.getTradeMark().setNPSLivingPerson(false);

            }

            appFieldReadable = "Mark NPS - Living Person";

        }



        //////////////////////////////////////////////////////////////////////////////////////////////////////
        // mark disclaimer fields
        //////////////////////////////////////////////////////////////////////////////////////////////////////


        if(markField.equals("mark-active-disclaimer")){
            // ptoUser.setState(param); // sets state code

            if(markValue.equals("yes")){
                baseTrademarkApplication.getTradeMark().setActvieDisclaimer(true);
                baseTrademarkApplication.getTradeMark().setDisclaimerSet(true);

            }
            if(markValue.equals("no")){
                baseTrademarkApplication.getTradeMark().setActvieDisclaimer(false);
                baseTrademarkApplication.getTradeMark().setDisclaimerSet(true);

            }

            appFieldReadable = "Mark Disclaimer";

        }

        if(markField.equals("mark-disclaimer-declaration")){
            // ptoUser.setState(param); // sets state code
            baseTrademarkApplication.getTradeMark().setDisclaimerDeclaration(markValue);

            appFieldReadable = "Mark Disclaimer Declaration";

        }



        if(markField.equals("mark-prior-registration")){
            // ptoUser.setState(param); // sets state code

            if(markValue.equals("yes")){
                baseTrademarkApplication.getTradeMark().setPriorRegistratoin(true);

                baseTrademarkApplication.getTradeMark().setPriorRegistratoinSet(true);

            }
            if(markValue.equals("no")){
                baseTrademarkApplication.getTradeMark().setPriorRegistratoin(false);
                baseTrademarkApplication.getTradeMark().setPriorRegistratoinSet(true);

            }

            appFieldReadable = "Mark Prior U.S Registration";

        }

        if(markField.equals("mark-prior-reg-number")){
            // ptoUser.setState(param); // sets state code
            baseTrademarkApplication.getTradeMark().setPriorRegistrationNumber(markValue);

            appFieldReadable = "Mark Prior U.S Registration Number";

        }



        if(markField.equals("mark-has-meaning")){
            // ptoUser.setState(param); // sets state code

            if(markValue.equals("yes")){
                baseTrademarkApplication.getTradeMark().setMarkWordingHasSignifigance(true);
                baseTrademarkApplication.getTradeMark().setMeaningSet(true);

            }
            if(markValue.equals("no")){
                baseTrademarkApplication.getTradeMark().setMarkWordingHasSignifigance(false);
                baseTrademarkApplication.getTradeMark().setMeaningSet(true);

            }

            appFieldReadable = "Mark has Meaning";

        }

        if(markField.equals("mark-meaning-text")){
            // ptoUser.setState(param); // sets state code
            baseTrademarkApplication.getTradeMark().setMarkWordingSignifiganceText(markValue);

            appFieldReadable = "Mark has Meaning Text";

        }

        if(markField.equals("mark-Industry-text")){
            // ptoUser.setState(param); // sets state code
            baseTrademarkApplication.getTradeMark().setMarkWordingIndustryText(markValue);

            appFieldReadable = "Mark Relevance in Trade or Industry";

        }




        // start standard character mark type auto save fields

        if(markField.equals("mark-standard-text")){
            // ptoUser.setState(param); // sets state code
            baseTrademarkApplication.getTradeMark().setTrademarkStandardCharacterText(markValue);
            baseTrademarkApplication.getTradeMark().setStandardCharacterMark(true);

            appFieldReadable = "Mark Standard Character Text";

        }


        baseTradeMarkApplicationService.save(baseTrademarkApplication);

        String responseMsg = appFieldReadable+" has been saved";

        //return ResponseEntity.ok().headers(responseHeader).body(responseMsg) ;
        return buildResponseEnity("200", responseMsg);
    }




}
