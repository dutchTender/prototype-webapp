package com.thorton.grant.uspto.prototypewebapp.service.mail.gmail;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


@Component
public class GmailJavaMailSenderService extends JavaMailSenderBase {


    public GmailJavaMailSenderService() {
    }


    public void sendAuthenticationToken(String token, String address){


        Properties props = new Properties();
        props.put(getSmtp_host_property_name(), getSmtp_host_propety_value());

        props.put(getSmtp_port_property_name(), getSmtp_port_propety_value());
        props.put(getSmtp_class_property_name(), getSmtp_class_propety_value());
        props.put(getSmtp_auth_property_name(), getSmtp_auth_propety_value());
        props.put(getSmtp_auth_port_property_name(), getSmtp_auth_port_propety_value());


        Session session = Session.getDefaultInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(getAccount(), getAcctPW());
                    }
                });

        try {

            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(getAccount()));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(address));
            message.setSubject("MyUSPTO: Two Factor Authentication.");

            ///////////////////////////////////////////////////////
            // build email body.
            ///////////////////////////////////////////////////////
            String emailText = "<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\"><html xmlns=\"http://www.w3.org/1999/xhtml\"><head>\n" +
                    "<meta http-equiv=\"Content-Type\" content=\"text/html; charset=Windows-1252\">\n" +
                    "<meta name=\"viewport\" content=\"width=device-width\">\n" +
                    "<title>Activate Account</title>\n" +
                    "<style type=\"text/css\">\n" +
                    "            body {\n" +
                    "                font-family: \"Segoe UI\", \"Helvetica Neue\", Tahoma, Arial, sans-serif;\n" +
                    "                font-size: 13px;\n" +
                    "           zoom:80%; }\n" +
                    "            h1 {\n" +
                    "                font-size: 24px;\n" +
                    "                font-weight: 400;\n" +
                    "            }\n" +
                    "            .btn-primary {\n" +
                    "                color: #fff;\n" +
                    "                background-color: #004c97;\n" +
                    "                border-color: #003f7e;\n" +
                    "            }\n" +
                    "            .btn {\n" +
                    "                display: inline-block;\n" +
                    "                padding: 6px 12px;\n" +
                    "                margin-bottom: 0;\n" +
                    "                font-size: 14px;\n" +
                    "                font-weight: 600;\n" +
                    "                line-height: 1.42857143;\n" +
                    "                text-align: center;\n" +
                    "                white-space: nowrap;\n" +
                    "                vertical-align: middle;\n" +
                    "                cursor: pointer;\n" +
                    "                background-image: none;\n" +
                    "                border: 1px solid transparent;\n" +
                    "                border-radius: 2px;\n" +
                    "                box-shadow: 0 1px 1px rgba(0,0,0,0.025);\n" +
                    "            }\n" +
                    "            .btn a {\n" +
                    "                color: #fff;\n" +
                    "                text-decoration: none;\n" +
                    "            }\n" +
                    "        </style>\n" +
                    "</head>\n" +
                    "<body>\n" +
                    "<h1>United States Patent and Trademark Office</h1>\n" +
                    "Please login to " +
                    "uspto.gov account using security token <span class=\"txt-blue\">"+token+"</span>.</p>\n" +

                    "<p>The 6 digit code will expire in 30 minutes. to keep your account secure.</p>\n" +
                    "<p>If this login attempt is not you. please consider updating your password.\n" +
                    "<div><a href=\"https://protect-us.mimecast.com/s/nmQXCW6qDMcwYRPWTKuTJ1?domain=uspto.gov\" target=\"_blank\">Terms of Use</a> |\n" +
                    "<a href=\"https://protect-us.mimecast.com/s/hiqKCXD5ENs7NVqzIkcSnV?domain=uspto.gov\" target=\"_blank\">\n" +
                    "Privacy Policy</a> <a href=\"https://protect-us.mimecast.com/s/6FrfCYE5GOFoZEN4IMk7Xm?domain=uspto.gov\" target=\"_blank\">\n" +
                    "Account FAQs</a> </div>\n" +
                    "</body>\n" +
                    "</html>\n";

            message.setText(emailText, "utf-8", "html");

            Transport.send(message);

            System.out.println("Done");

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }


    }




    public void sendEmailverificationLink(String link, String address){


        Properties props = new Properties();
        props.put(getSmtp_host_property_name(), getSmtp_host_propety_value());

        props.put(getSmtp_port_property_name(), getSmtp_port_propety_value());
        props.put(getSmtp_class_property_name(), getSmtp_class_propety_value());
        props.put(getSmtp_auth_property_name(), getSmtp_auth_propety_value());
        props.put(getSmtp_auth_port_property_name(), getSmtp_auth_port_propety_value());


        Session session = Session.getDefaultInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(getAccount(), getAcctPW());
                    }
                });

        try {

            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(getAccount()));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(address));
            message.setSubject("MyUSPTO: Activate your uspto.gov account");

            ///////////////////////////////////////////////////////
            // build email body.
            ///////////////////////////////////////////////////////
            String emailText = "<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\"><html xmlns=\"http://www.w3.org/1999/xhtml\"><head>\n" +
                    "<meta http-equiv=\"Content-Type\" content=\"text/html; charset=Windows-1252\">\n" +
                    "<meta name=\"viewport\" content=\"width=device-width\">\n" +
                    "<title>Activate Account</title>\n" +
                    "<style type=\"text/css\">\n" +
                    "            body {\n" +
                    "                font-family: \"Segoe UI\", \"Helvetica Neue\", Tahoma, Arial, sans-serif;\n" +
                    "                font-size: 13px;\n" +
                    "           zoom:80%; }\n" +
                    "            h1 {\n" +
                    "                font-size: 24px;\n" +
                    "                font-weight: 400;\n" +
                    "            }\n" +
                    "            .btn-primary {\n" +
                    "                color: #fff;\n" +
                    "                background-color: #004c97;\n" +
                    "                border-color: #003f7e;\n" +
                    "            }\n" +
                    "            .btn {\n" +
                    "                display: inline-block;\n" +
                    "                padding: 6px 12px;\n" +
                    "                margin-bottom: 0;\n" +
                    "                font-size: 14px;\n" +
                    "                font-weight: 600;\n" +
                    "                line-height: 1.42857143;\n" +
                    "                text-align: center;\n" +
                    "                white-space: nowrap;\n" +
                    "                vertical-align: middle;\n" +
                    "                cursor: pointer;\n" +
                    "                background-image: none;\n" +
                    "                border: 1px solid transparent;\n" +
                    "                border-radius: 2px;\n" +
                    "                box-shadow: 0 1px 1px rgba(0,0,0,0.025);\n" +
                    "            }\n" +
                    "            .btn a {\n" +
                    "                color: #fff;\n" +
                    "                text-decoration: none;\n" +
                    "            }\n" +
                    "        </style>\n" +
                    "</head>\n" +
                    "<body>\n" +
                    "<h1>United States Patent and Trademark Office</h1>\n" +
                    "<p>You created a <a href=\"https://"+link+"\" target=\"_blank\">\n" +
                    "uspto.gov</a> account using <span class=\"txt-blue\">"+address+"</span>.</p>\n" +
                    "<p></p>\n" +
                    "<div class=\"btn btn-primary\"><a href=\""+link+"\" target=\"_blank\">Activate account</a></div>\n" +
                    "<p></p>\n" +
                    "<p>If the button does not work then copy and paste the URL into your web browser.</p>\n" +
                    "<p><span class=\"txt-blue\"><a href=\""+link+"\" target=\"_blank\">"+link+"</a></span></p>\n" +
                    "<p>The link will expire in 48 hours to keep your account secure.</p>\n" +
                    "<p>If you didn't create this <a href=\"https://protect-us.mimecast.com/s/AelUCR61xEcPljRzf90DuV?domain=uspto.gov\" target=\"_blank\">\n" +
                    "uspto.gov</a> account, there is no need to do anything.</p>\n" +
                    "<div><a href=\"https://protect-us.mimecast.com/s/nmQXCW6qDMcwYRPWTKuTJ1?domain=uspto.gov\" target=\"_blank\">Terms of Use</a> |\n" +
                    "<a href=\"https://protect-us.mimecast.com/s/hiqKCXD5ENs7NVqzIkcSnV?domain=uspto.gov\" target=\"_blank\">\n" +
                    "Privacy Policy</a> <a href=\"https://protect-us.mimecast.com/s/6FrfCYE5GOFoZEN4IMk7Xm?domain=uspto.gov\" target=\"_blank\">\n" +
                    "Account FAQs</a> </div>\n" +
                    "</body>\n" +
                    "</html>\n";

            message.setText(emailText, "utf-8", "html");

            Transport.send(message);

            System.out.println("Done");

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }


    }




    public void sendAccountRestLink(String link, String address){


        Properties props = new Properties();
        props.put(getSmtp_host_property_name(), getSmtp_host_propety_value());

        props.put(getSmtp_port_property_name(), getSmtp_port_propety_value());
        props.put(getSmtp_class_property_name(), getSmtp_class_propety_value());
        props.put(getSmtp_auth_property_name(), getSmtp_auth_propety_value());
        props.put(getSmtp_auth_port_property_name(), getSmtp_auth_port_propety_value());


        Session session = Session.getDefaultInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(getAccount(), getAcctPW());
                    }
                });

        try {

            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(getAccount()));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(address));
            message.setSubject("MyUSPTO: Reset your password");

            ///////////////////////////////////////////////////////
            // build email body.
            ///////////////////////////////////////////////////////
            String emailText = "<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\"><html xmlns=\"http://www.w3.org/1999/xhtml\"><head>\n" +
                    "<meta http-equiv=\"Content-Type\" content=\"text/html; charset=Windows-1252\">\n" +
                    "<meta name=\"viewport\" content=\"width=device-width\">\n" +
                    "<title>Activate Account</title>\n" +
                    "<style type=\"text/css\">\n" +
                    "            body {\n" +
                    "                font-family: \"Segoe UI\", \"Helvetica Neue\", Tahoma, Arial, sans-serif;\n" +
                    "                font-size: 13px;\n" +
                    "           zoom:80%; }\n" +
                    "            h1 {\n" +
                    "                font-size: 24px;\n" +
                    "                font-weight: 400;\n" +
                    "            }\n" +
                    "            .btn-primary {\n" +
                    "                color: #fff;\n" +
                    "                background-color: #004c97;\n" +
                    "                border-color: #003f7e;\n" +
                    "            }\n" +
                    "            .btn {\n" +
                    "                display: inline-block;\n" +
                    "                padding: 6px 12px;\n" +
                    "                margin-bottom: 0;\n" +
                    "                font-size: 14px;\n" +
                    "                font-weight: 600;\n" +
                    "                line-height: 1.42857143;\n" +
                    "                text-align: center;\n" +
                    "                white-space: nowrap;\n" +
                    "                vertical-align: middle;\n" +
                    "                cursor: pointer;\n" +
                    "                background-image: none;\n" +
                    "                border: 1px solid transparent;\n" +
                    "                border-radius: 2px;\n" +
                    "                box-shadow: 0 1px 1px rgba(0,0,0,0.025);\n" +
                    "            }\n" +
                    "            .btn a {\n" +
                    "                color: #fff;\n" +
                    "                text-decoration: none;\n" +
                    "            }\n" +
                    "        </style>\n" +
                    "</head>\n" +
                    "<body>\n" +
                    "<h1>United States Patent and Trademark Office</h1>\n" +
                    "<p>Reset password for a  <a href=\"https://"+link+"\" target=\"_blank\">\n" +
                    "uspto.gov</a> account using <span class=\"txt-blue\">"+address+"</span>.</p>\n" +
                    "<p></p>\n" +
                    "<div class=\"btn btn-primary\"><a href=\""+link+"\" target=\"_blank\">Reset password</a></div>\n" +
                    "<p></p>\n" +
                    "<p>If the button does not work then copy and paste the URL into your web browser.</p>\n" +
                    "<p><span class=\"txt-blue\"><a href=\""+link+"\" target=\"_blank\">"+link+"</a></span></p>\n" +
                    "<p>The link will expire in 48 hours to keep your account secure.</p>\n" +
                    "<p>If you didn't create this <a href=\"https://protect-us.mimecast.com/s/AelUCR61xEcPljRzf90DuV?domain=uspto.gov\" target=\"_blank\">\n" +
                    "uspto.gov</a> request, there is no need to do anything.</p>\n" +
                    "<div><a href=\"https://protect-us.mimecast.com/s/nmQXCW6qDMcwYRPWTKuTJ1?domain=uspto.gov\" target=\"_blank\">Terms of Use</a> |\n" +
                    "<a href=\"https://protect-us.mimecast.com/s/hiqKCXD5ENs7NVqzIkcSnV?domain=uspto.gov\" target=\"_blank\">\n" +
                    "Privacy Policy</a> <a href=\"https://protect-us.mimecast.com/s/6FrfCYE5GOFoZEN4IMk7Xm?domain=uspto.gov\" target=\"_blank\">\n" +
                    "Account FAQs</a> </div>\n" +
                    "</body>\n" +
                    "</html>\n";

            message.setText(emailText, "utf-8", "html");

            Transport.send(message);

            System.out.println("Done");

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }


    }



    public static void main(String[] args) {


            Properties props = new Properties();
            props.put("mail.smtp.host", "smtp.gmail.com");

            props.put("mail.smtp.socketFactory.port", "465");
            props.put("mail.smtp.socketFactory.class",
                    "javax.net.ssl.SSLSocketFactory");
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.port", "465");

            Session session = Session.getDefaultInstance(props,
                    new javax.mail.Authenticator() {
                        protected PasswordAuthentication getPasswordAuthentication() {
                            return new PasswordAuthentication("gtUSPTO@gmail.com","HBOsho@#23");
                        }
                    });

            try {

                Message message = new MimeMessage(session);
                message.setFrom(new InternetAddress("gtUSPTO@gmail.com"));
                message.setRecipients(Message.RecipientType.TO,
                        InternetAddress.parse("lzhang421@gmail.com"));
                message.setSubject("Testing Subject");
                message.setText("\"USPTO.gov eFile Reimagined account activation\");\n" +
                        "            message.setText(\"Dear User please click on teh following link to activate your new USPTO.gove efile account." +
                        "\n\n No spam to my email, please!");

                Transport.send(message);

                System.out.println("Done");

            } catch (MessagingException e) {
                throw new RuntimeException(e);
            }
        }
    }
