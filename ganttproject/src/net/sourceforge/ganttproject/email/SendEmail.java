package net.sourceforge.ganttproject.email;

import java.io.File;
import java.io.IOException;
import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;
import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import net.sourceforge.ganttproject.email.*;
import net.sourceforge.ganttproject.resource.*;
import java.io.File;
import java.net.*;

public class SendEmail {

    private final static String GMAIL="@gmail.com";
    private final static String SUCCESS= "The e-mail was sent successfully";
    private final static String PROBLEM= "There was an error sending the e-mail";
    public Session session;

    private InternetAddress[] addressesList;

    private ArrayList<String> emails = new ArrayList();

    /**
     * This method sends a email with attachemment to the collaborators of ganttproject
     * @param mail: information needed to send mails
     */
    public String mailSend(EmailInfo mail) {
        fillAdresses(mail.resourceIterator());
        final String from = mail.getUserSender();
        final String password = mail.getPassword();
        setUpMailSystem(from, password);
        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from + GMAIL));
            message.setRecipients(Message.RecipientType.TO, addressesList);
            message.setSubject(mail.getSubject());
            Multipart multipart = new MimeMultipart();
            MimeBodyPart attachmentPart = new MimeBodyPart();
            MimeBodyPart textPart = new MimeBodyPart();
            try {
                File file;
                if(mail.isValidPath()) {
                    file = new File(mail.getPath());
                }
                else {
                    return PROBLEM;
                }
                attachmentPart.attachFile(file);
                textPart.setText(mail.getBody());
                multipart.addBodyPart(textPart);
                multipart.addBodyPart(attachmentPart);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            message.setContent(multipart);
            Thread t =  Thread.currentThread();
            ClassLoader ccl = t.getContextClassLoader();
            t.setContextClassLoader(session.getClass().getClassLoader());
            try {
                Transport.send(message);

            } catch (Exception e){
                return PROBLEM;
            }
            t.setContextClassLoader(ccl);
            return SUCCESS;
        } catch (MessagingException mex) {
            return PROBLEM;
            //mex.printStackTrace();
        }
    }

    /**
     * Fills the internet adresses array with emails
     * @param mail Iterator of Human Resources
     */
    private void fillAdresses(Iterator<HumanResource> mails){
        while(mails.hasNext()){
            emails.add(mails.next().getMail());
        }
        addressesList = new InternetAddress[emails.size()];
        for(int i = 0; i < emails.size(); i++) {
            try {
                addressesList[i] = new InternetAddress(emails.get(i));
            } catch (AddressException e) {
                throw new RuntimeException(e);
            }
        }
    }

    /**
     * Get system properties
     */
    private void setUpMailSystem(final String from, final String password){
        Properties properties = System.getProperties();
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.ssl.trust", "smtp.gmail.com");
        session = Session.getInstance(properties, new Authenticator() {
            public PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(from, password);
            }
        });
    }
}