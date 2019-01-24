package com.thorton.grant.uspto.prototypewebapp.service.mail.gmail;

public class JavaMailSenderBase {


    private final String account = "gtUSPTO@gmail.com";
    private final String acctPW = "HBOsho@#23";

    public String getAccount() {
        return account;
    }



    public String getAcctPW() {
        return acctPW;
    }



    private final String smtp_host_property_name = "mail.smtp.host";
    private final String smtp_host_propety_value = "smtp.gmail.com";

    private final String smtp_port_property_name = "mail.smtp.socketFactory.port";
    private final String smtp_port_propety_value = "465";

    private final String smtp_class_property_name = "mail.smtp.socketFactory.class";
    private final String smtp_class_propety_value = "javax.net.ssl.SSLSocketFactory";


    private final String smtp_auth_property_name ="mail.smtp.auth";
    private final String smtp_auth_propety_value ="true";

    private final String smtp_auth_port_property_name ="mail.smtp.port";
    private final String smtp_auth_port_propety_value ="465";


    public String getSmtp_host_property_name() {
        return smtp_host_property_name;
    }



    public String getSmtp_host_propety_value() {
        return smtp_host_propety_value;
    }



    public String getSmtp_port_property_name() {
        return smtp_port_property_name;
    }



    public String getSmtp_port_propety_value() {
        return smtp_port_propety_value;
    }


    public String getSmtp_class_property_name() {
        return smtp_class_property_name;
    }


    public String getSmtp_class_propety_value() {
        return smtp_class_propety_value;
    }



    public String getSmtp_auth_property_name() {
        return smtp_auth_property_name;
    }


    public String getSmtp_auth_propety_value() {
        return smtp_auth_propety_value;
    }


    public String getSmtp_auth_port_property_name() {
        return smtp_auth_port_property_name;
    }



    public String getSmtp_auth_port_propety_value() {
        return smtp_auth_port_propety_value;
    }


}
