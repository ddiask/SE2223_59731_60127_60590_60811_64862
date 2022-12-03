package net.sourceforge.ganttproject.email;

import net.sourceforge.ganttproject.resource.HumanResource;

import java.net.URI;
import java.util.Iterator;

public interface EmailInfo{

       /**
        *
        * @return an Iterator of Human Resources (Persons that gonna receive the email)
        */
       public Iterator<HumanResource> resourceIterator();
       /**
        * The URI returned is the path used for the file in the computer.
        * @return an URI
        */
       public URI getPath();
       /**
        * Change the prefix of the sender.
        * @param userSender user sender is the prefix of the email.
        */
       public void setUserSender(String userSender);

       /**
        * change the password of the sender
        * @param password
        */
       public void setPassword(String password);

       /**
        *
        * @return the password of the sender
        */
       public String getPassword();

       /**
        *
        * @return  the usersender is the prefix of the email.
        */
       public String getUserSender();

       /**
        *
        * @param body content of the email
        */
       public void setBody(String body);

       /**
        *
        * @return the content of the email
        */
       public String getBody();

       /**
        *
        * @param subject of the email
        */
       public void setSubject(String subject);

       /**
        *
        * @return the subject of the email
        */
       public String getSubject();

       /**
        * Verify if the path is valid
        * @return True if the path is valid or False if the path is not valid
        */
       public boolean isValidPath();
}