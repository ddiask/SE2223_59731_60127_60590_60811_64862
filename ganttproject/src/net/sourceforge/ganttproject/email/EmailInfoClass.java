package net.sourceforge.ganttproject.email;

import net.sourceforge.ganttproject.resource.HumanResource;

import java.net.URI;
import java.util.Iterator;
import java.util.List;


public class EmailInfoClass implements EmailInfo{

    private URI path;
    private List<HumanResource> recipients;
    private String userSender;
    private String password;
    private String subject;
    private String body;

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

    public void setBody(String body){
        this.body=body;
    }

    public String getBody(){
        return body;
    }

    public void setSubject(String subject){
        this.subject=subject;
    }

    public String getSubject() {
        return subject;
    }

    public boolean isValidPath() {
        if(path.getPath()==null)
            return false;
        return true;
    }
}