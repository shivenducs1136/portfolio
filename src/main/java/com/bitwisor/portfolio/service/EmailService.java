package com.bitwisor.portfolio.service;

import jakarta.mail.*;
import jakarta.mail.internet.AddressException;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.security.InvalidParameterException;
import java.util.Properties;

@Service
public class EmailService {

    @Value("${user.email}")
    private String email;
    @Value("${user.password}")
    private String password;

    public boolean sendEmail(String to, String subject, String body) {
        Properties prop = new Properties();
        prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.port", "465");
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.socketFactory.port", "465");
        prop.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");

        Session session = Session.getInstance(prop,
                new jakarta.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(email, password);
                    }
                });

        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(email));
            message.setRecipients(
                    Message.RecipientType.TO,
                    InternetAddress.parse("shivenduaps986@gmail.com")
            );
            message.setSubject("Portfolio"+subject+" Contact");
            message.setText("From email: "+to+"\n\n"+
                     body);

            Transport.send(message);

            Message msg = new MimeMessage(session);
            msg.setFrom(new InternetAddress(email));
            msg.setRecipients(
                    Message.RecipientType.TO,
                    InternetAddress.parse(to)
            );
            msg.setSubject("Your email successfully sent! ");
            msg.setText("Hi \n\n " +
                    "Thank you for making an effort to reach me out! " +
                    "\n\nThis is a confirmation email that your mail has been sent successfully!\n" +
                    "I'll be reaching out you soon. \n\n Regards,\nShivendu Mishra");

            Transport.send(msg);
            System.out.println("Done");

        }
        catch (Exception e) {
            System.out.println(e.getMessage());
           return false;
        }
        return true;
    }

}
