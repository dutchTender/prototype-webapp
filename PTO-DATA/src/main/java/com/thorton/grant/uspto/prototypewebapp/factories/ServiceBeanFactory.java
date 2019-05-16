package com.thorton.grant.uspto.prototypewebapp.factories;

import com.thorton.grant.uspto.prototypewebapp.interfaces.Secruity.AuthenticationTokenService;
import com.thorton.grant.uspto.prototypewebapp.interfaces.USPTO.PTOUserService;
import com.thorton.grant.uspto.prototypewebapp.interfaces.Secruity.UserCredentialsService;
import com.thorton.grant.uspto.prototypewebapp.interfaces.Secruity.UserRoleService;
import com.thorton.grant.uspto.prototypewebapp.interfaces.USPTO.tradeMark.application.actions.OfficeActionsService;
import com.thorton.grant.uspto.prototypewebapp.interfaces.USPTO.tradeMark.application.actions.PetitionService;
import com.thorton.grant.uspto.prototypewebapp.interfaces.USPTO.tradeMark.application.participants.LawyerService;
import com.thorton.grant.uspto.prototypewebapp.interfaces.USPTO.tradeMark.application.participants.OwnerService;
import com.thorton.grant.uspto.prototypewebapp.interfaces.USPTO.tradeMark.application.types.BaseTradeMarkApplicationService;
import com.thorton.grant.uspto.prototypewebapp.interfaces.USPTO.tradeMark.asset.GoodsAndServicesService;
import com.thorton.grant.uspto.prototypewebapp.interfaces.USPTO.tradeMark.asset.TradeMarkService;
import com.thorton.grant.uspto.prototypewebapp.interfaces.registration.VerificationTokenService;
import com.thorton.grant.uspto.prototypewebapp.repositories.jpa.USPTO.tradeMark.application.types.BaseTradeMarkApplicationRepository;
import org.springframework.stereotype.Component;

@Component
public class ServiceBeanFactory {

    private final PTOUserService PTOUserService;
    private final UserCredentialsService userCredentialsService;
    private final UserRoleService userRoleService;
    private final VerificationTokenService verificationTokenService;
    private final LawyerService lawyerService;
    private final OwnerService ownerService;
    private final OfficeActionsService officeActionsService;
    private final TradeMarkService tradeMarkService;
    private final BaseTradeMarkApplicationService baseTradeMarkApplicationService;
    private final AuthenticationTokenService authenticationTokenService;
    private final GoodsAndServicesService goodsAndServicesService;

    private final PetitionService petitionService;

    public ServiceBeanFactory(com.thorton.grant.uspto.prototypewebapp.interfaces.USPTO.PTOUserService PTOUserService, UserCredentialsService userCredentialsService, UserRoleService userRoleService, VerificationTokenService verificationTokenService, LawyerService lawyerService, OwnerService ownerService, OfficeActionsService officeActionsService, TradeMarkService tradeMarkService, BaseTradeMarkApplicationService baseTradeMarkApplicationService, AuthenticationTokenService authenticationTokenService, GoodsAndServicesService goodsAndServicesService, PetitionService petitionService) {
        this.PTOUserService = PTOUserService;
        this.userCredentialsService = userCredentialsService;
        this.userRoleService = userRoleService;
        this.verificationTokenService = verificationTokenService;
        this.lawyerService = lawyerService;
        this.ownerService = ownerService;
        this.officeActionsService = officeActionsService;
        this.tradeMarkService = tradeMarkService;
        this.baseTradeMarkApplicationService = baseTradeMarkApplicationService;
        this.authenticationTokenService = authenticationTokenService;
        this.goodsAndServicesService = goodsAndServicesService;
        this.petitionService = petitionService;
    }

    public com.thorton.grant.uspto.prototypewebapp.interfaces.USPTO.PTOUserService getPTOUserService() {
        return PTOUserService;
    }

    public UserCredentialsService getUserCredentialsService() {
        return userCredentialsService;
    }

    public UserRoleService getUserRoleService() {
        return userRoleService;
    }

    public VerificationTokenService getVerificationTokenService() {
        return verificationTokenService;
    }

    public LawyerService getLawyerService() {
        return lawyerService;
    }

    public OwnerService getOwnerService() {
        return ownerService;
    }

    public OfficeActionsService getOfficeActionsService() {
        return officeActionsService;
    }

    public TradeMarkService getTradeMarkService() {
        return tradeMarkService;
    }

    public BaseTradeMarkApplicationService getBaseTradeMarkApplicationService() {
        return baseTradeMarkApplicationService;
    }

    public AuthenticationTokenService getAuthenticationTokenService() {
        return authenticationTokenService;
    }

    public GoodsAndServicesService getGoodsAndServicesService() {
        return goodsAndServicesService;
    }

    public PetitionService getPetitionService() {
        return petitionService;
    }
}
