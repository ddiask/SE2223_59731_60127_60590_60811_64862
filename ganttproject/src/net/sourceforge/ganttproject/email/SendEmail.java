// File Name SendEmail.java
package net.sourceforge.ganttproject.email;

import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;




public class SendEmail {

    private Session session;

    public void mailSend(String f, String p) {
        // Recipient's email ID needs to be mentioned.
        String to = "afonsonunes19@gmail.com";

        // Sender's email ID needs to be mentioned
        final String from = f;

        final String password = p;

        // Assuming you are sending email from localhost
        String host = "localhost";

        // Get system properties
        Properties properties = System.getProperties();
    /*
        // Setup mail server
        properties.setProperty("mail.smtp.host", "smtp.gmail.com");

        properties.setProperty("mail.smtp.port", "587");

        properties.setProperty("mail.smtp.auth", "true");

        properties.setProperty("mail.smtp.starttls.enable", "true");*/
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");




        // Get the default Session object.


        //jakerrcdnahtavyl
        session = Session.getInstance(properties, new Authenticator() {
            public PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(from, password);
            }
        });

        try {
    /*
            System.out.println("Durante o envio P1.");
            // Create a default MimeMessage object.
            MimeMessage message = new MimeMessage(session);
            System.out.println("Durante o envio P2.");
            // Set From: header field of the header.
            message.setFrom(new InternetAddress(f+"@gmail.com"));
            System.out.println("Durante o envio P3.");
            // Set To: header field of the header.
            message.addRecipient(Message.RecipientType.TO,new InternetAddress(to));
            System.out.println("Durante o envio P4.");
            // Set Subject: header field
            message.setSubject("This is the Subject Line!");
            System.out.println("Durante o envio P5.");
            // Create the message part
            BodyPart messageBodyPart = new MimeBodyPart();
            System.out.println("Durante o envio P6.");
            // Fill the message
            messageBodyPart.setText("teste");
            System.out.println("Durante o envio P7.");
            // Create a multipar message
            Multipart multipart = new MimeMultipart();
            System.out.println("Durante o envio P8.");
            // Set text message part
            multipart.addBodyPart(messageBodyPart);
            System.out.println("Durante o envio P9.");
            // Send the complete message parts
            System.out.println("Durante o envio P10.");
            message.setContent(multipart);
            System.out.println("Durante o envio P11.");



            // Send message
            try {
                Transport.send(message);
            }
            catch (Exception e) {
              // e.printStackTrace());
               System.out.println(e+": Sou um email burro que nao funciona");
            }

            System.out.println("Sent message successfully....");
        } catch (MessagingException mex) {
            mex.printStackTrace();
        }
    }
     */
            System.out.println("Durante o envio P1.");
// Create a default MimeMessage object.
            MimeMessage message = new MimeMessage(session);
            System.out.println("Durante o envio P2.");

            // Set From: header field of the header.
            message.setFrom(new InternetAddress(f + "@gmail.com"));
// Set To: header field of the header.
            message.setRecipients(Message.RecipientType.TO, to);
            System.out.println("Durante o envio P4.");
// Set Subject: header field
            message.setSubject("This is the Subject Line!");
            System.out.println("Durante o envio P5.");
// Fill the message
          //  BodyPart messageBodyPart = new MimeBodyPart();
            // Fill the message
            message.setText("Teste");
            System.out.println("Durante o envio P7.");
// Send message
           /* // Create a multipar message
            Multipart multipart = new MimeMultipart();
            System.out.println("Durante o envio P8.");
            // Set text message part
            multipart.addBodyPart(messageBodyPart);
            System.out.println("Durante o envio P9.");
            // Send the complete message parts
            System.out.println("Durante o envio P10.");
            message.setContent(multipart);

            Thread.currentThread().setContextClassLoader( getClass().getClassLoader() );
*/
            try {

                Transport.send(message);
            }
            catch (Exception e) {
                // e.printStackTrace());
                System.out.println(e+": teste");
            }
            System.out.println("Sent message successfully....");

        } catch (MessagingException mex) {
            mex.printStackTrace();
        }
    }
}