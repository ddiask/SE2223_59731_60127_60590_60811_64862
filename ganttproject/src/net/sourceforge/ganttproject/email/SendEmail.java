// File Name SendEmail.java
// Password: jakerrcdnahtavyl
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

    public Session session;

    private InternetAddress[] addressesList;

    private ArrayList<String> emails = new ArrayList();

    public void mailSend(EmailInfo mail) {
        // Recipient's email ID needs to be mentioned.
        // String to = "afonsonunes19@gmail.com";

        Iterator<HumanResource> mails= mail.resourceIterator();
        while(mails.hasNext()){
            emails.add(mails.next().getMail());
        }
        /*
        emails.add("lm.abreu@campus.fct.unl.pt");
        emails.add("pedro.gouveia01@gmail.com");
        emails.add("fx.vale@campus.fct.unl.pt");
        emails.add("tr.francisco@campus.fct.unl.pt");
        emails.add("dfg.dias@campus.fct.unl.pt");
        emails.add("p.gasparinho@campus.fct.unl.pt");
        emails.add("t.meirim@campus.fct.unl.pt");
        emails.add("ane.nunes@campus.fct.unl.pt");
        emails.add("brunojoaquimmelo@gmail.com");
        emails.add("jae.ribeiro@campus.fct.unl.pt");
        emails.add("gg.franco@campus.fct.unl.pt");
        emails.add("dmc.cruz@campus.fct.unl.pt");*/

        addressesList = new InternetAddress[emails.size()];

        for(int i = 0; i < emails.size(); i++) {
            try {
                addressesList[i] = new InternetAddress(emails.get(i));
            } catch (AddressException e) {
                throw new RuntimeException(e);
            }
        }

        // Sender's email ID needs to be mentioned
        final String from = mail.getUserSender();

        final String password = mail.getPassword();

        // Get system properties
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

        try {

            MimeMessage message = new MimeMessage(session);

            message.setFrom(new InternetAddress(from + "@gmail.com"));

            message.setRecipients(Message.RecipientType.TO, addressesList);

            message.setSubject("IMPORTANTE CASO DE VIDA OU DE MORTE");

            // message.setText("Teste");
            Multipart multipart = new MimeMultipart();

            //Parte do anexamento
            MimeBodyPart attachmentPart = new MimeBodyPart();

            //Parte de texto
            MimeBodyPart textPart = new MimeBodyPart();
            try {
                File file = new File(mail.getPath());

                attachmentPart.attachFile(file);
                textPart.setText("Mekielas escravos, trabalhem!!!!!!!!!!");
                multipart.addBodyPart(textPart);
                multipart.addBodyPart(attachmentPart);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            message.setContent(multipart);
    /*
         try {
                Transport.send(message);
            }
          catch (Exception e) {
                e.printStackTrace();
              System.out.println("Erro que deu -> "+e);
            }
            System.out.println("Sent message successfully....");
        */
            Thread t =  Thread.currentThread();
            ClassLoader ccl = t.getContextClassLoader();
            t.setContextClassLoader(session.getClass().getClassLoader());
            try {
                Transport.send(message);
            } finally {
                t.setContextClassLoader(ccl);
            }
        } catch (MessagingException mex) {
            mex.printStackTrace();
        }
    }
}