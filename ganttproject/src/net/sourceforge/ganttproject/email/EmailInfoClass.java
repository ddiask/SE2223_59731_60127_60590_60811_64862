package net.sourceforge.ganttproject.email;
import java.util.*;
import java.io.File;
import java.net.*;
import net.sourceforge.ganttproject.resource.*;


public class EmailInfoClass implements EmailInfo{

    private URI path;
    private List<HumanResource> recipients;
    private String userSender;
    private String password;

    public EmailInfoClass(List<HumanResource> recipients, URI path){
        this.recipients=recipients;
        this.path=path;
    }

    public Iterator<HumanResource> resourceIterator(){
        return  recipients.iterator();
    }

    public URI getPath(){
        return path;
    }

    public void setUserSender(String userSender){
        this.userSender=userSender;
    }

    public void setPassword(String password){
        this.password=password;
    }

    public String getPassword(){
        return password;
    }

    public String getUserSender(){
        return userSender;
    }

}