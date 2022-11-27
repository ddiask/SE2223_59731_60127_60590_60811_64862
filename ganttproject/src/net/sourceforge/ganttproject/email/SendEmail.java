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

public class SendEmail {

    public Session session;

    public void mailSend(String fr, String p) {
        // Recipient's email ID needs to be mentioned.
        String to = "afonsonunes19@gmail.com";

        // Sender's email ID needs to be mentioned
        final String from = fr;

        final String password = p;

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

            message.setFrom(new InternetAddress(fr + "@gmail.com"));

            message.setRecipients(Message.RecipientType.TO, to);

            message.setSubject("IMPORTANTE");

           // message.setText("Teste");
            Multipart multipart = new MimeMultipart();

            //Parte do anexamento
            MimeBodyPart attachmentPart = new MimeBodyPart();

            //Parte de texto
            MimeBodyPart textPart = new MimeBodyPart();
    try {
        File file = new File("C:\\Users\\Afonso\\Desktop\\UNI\\memes\\amd.JPG");

        attachmentPart.attachFile(file);
        textPart.setText("O AMD eh lindo... PS: Este foi do gradle");
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