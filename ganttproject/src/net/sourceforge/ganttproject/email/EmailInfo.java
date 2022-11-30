package net.sourceforge.ganttproject.email;
import java.util.*;
import net.sourceforge.ganttproject.resource.*;
import java.io.File;
import java.net.*;

public interface EmailInfo{
       public Iterator<HumanResource> resourceIterator();
       public URI getPath();
       public void setUserSender(String userSender);
       public void setPassword(String password);
       public String getPassword();
       public String getUserSender();
       public void setBody(String body);
       public String getBody();
       public void setSubject(String subject);
       public String getSubject();
       public boolean isValidPath();
}