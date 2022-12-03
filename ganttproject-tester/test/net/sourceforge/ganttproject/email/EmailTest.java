package net.sourceforge.ganttproject.email;

import junit.framework.TestCase;

import net.sourceforge.ganttproject.resource.HumanResource;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class EmailTest extends TestCase{



    public void testHasRightSize() {
        List<HumanResource> resourceList = new ArrayList<HumanResource>();
        resourceList.add(new HumanResource("Asdrubal", 0));
        resourceList.get(0).setMail("ane.nunes@campus.fct.unl.pt");
        resourceList.add(new HumanResource("Miquelina", 1));
        resourceList.get(1).setMail("dfg.dias@campus.fct.unl.pt");
        resourceList.add(new HumanResource("Marinho", 2));
        resourceList.get(2).setMail("p.gasparinho@campus.fct.unl.pt");
        assert(resourceList.size() == 3);
    }

    //The path should be a valid path in the computer that is running the program
    public void testEmail1() {
        try {
            URI path = new URI("file:/C:/Users/diogo/OneDrive/Imagens/harvey.JPG");
           List<HumanResource> resourceList = new ArrayList<HumanResource>();
           resourceList.add(new HumanResource("Asdrubal", 0));
           resourceList.get(0).setMail("ane.nunes@campus.fct.unl.pt");
           resourceList.add(new HumanResource("Miquelina", 1));
           resourceList.get(1).setMail("dfg.dias@campus.fct.unl.pt");
           resourceList.add(new HumanResource("Marinho", 2));
           resourceList.get(2).setMail("p.gasparinho@campus.fct.unl.pt");
           EmailInfoClass mail = new EmailInfoClass(resourceList, path);
           mail.setUserSender("ganttprojectes2022");
           mail.setPassword("klollclbwcizthzl");
           mail.setBody("");
           mail.setSubject("");
           SendEmail m = new SendEmail();
           String statusOperation = m.mailSend(mail);
           assert (statusOperation.equals("The e-mail was sent successfully"));
        }catch (URISyntaxException e) {
        }
    }


    public void testEmail2() {
        try {
            URI path =new URI("file:/C:/ERRO");
            List<HumanResource> resourceList = new ArrayList<HumanResource>();
            resourceList.add(new HumanResource("Asdrubal", 0));
            resourceList.get(0).setMail("ane.nunes@campus.fct.unl.pt");
            resourceList.add(new HumanResource("Miquelina", 1));
            resourceList.get(1).setMail("dfg.dias@campus.fct.unl.pt");
            resourceList.add(new HumanResource("Marinho", 2));
            resourceList.get(2).setMail("p.gasparinho@campus.fct.unl.pt");
            EmailInfoClass mail = new EmailInfoClass(resourceList, path);
            mail.setUserSender("ganttprojectes2022");
            mail.setPassword("klollclbwcizthzl");
            mail.setBody("");
            mail.setSubject("");
            SendEmail m = new SendEmail();
            String statusOperation = m.mailSend(mail);
            assert (statusOperation.equals("There was an error sending the e-mail"));
        }catch (URISyntaxException e) {
        }
    }

    //The path should be a valid path in the computer that is running the program
    public void testEmail3() {
        try {
            URI path = new URI("file:/C:/Users/diogo/OneDrive/Imagens/harvey.JPG");
            List<HumanResource> resourceList = new ArrayList<HumanResource>();
            EmailInfoClass mail = new EmailInfoClass(resourceList, path);
            mail.setUserSender("ganttprojectes2022");
            mail.setPassword("klollclbwcizthzl");
            mail.setBody("");
            mail.setSubject("");
            SendEmail m = new SendEmail();
            String statusOperation = m.mailSend(mail);
            assert (statusOperation.equals("There was an error sending the e-mail"));
        }catch (URISyntaxException e) {
        }
    }



    //The path should be a valid path in the computer that is running the program
    public void testEmail4() {
        try {
            URI path = new URI("file:/C:/Users/diogo/OneDrive/Imagens/harvey.JPG");
            List<HumanResource> resourceList = new ArrayList<HumanResource>();
            resourceList.add(new HumanResource("Asdrubal", 1));
            resourceList.get(0).setMail("wrongMail");
            EmailInfoClass mail = new EmailInfoClass(resourceList, path);
            mail.setUserSender("userWrong");
            mail.setPassword("klollclbwcizthzl");
            mail.setBody("");
            mail.setSubject("");
            SendEmail m = new SendEmail();
            String statusOperation = m.mailSend(mail);
        assert (statusOperation.equals("There was an error sending the e-mail"));
        }catch (URISyntaxException e) {
        }
    }

    //The path should be a valid path in the computer that is running the program
    public void testEmail5() {
        try {
            URI path = new URI("file:/C:/Users/diogo/OneDrive/Imagens/harvey.JPG");
            List<HumanResource> resourceList = new ArrayList<HumanResource>();
            resourceList.add(new HumanResource("Asdrubal", 0));
            resourceList.get(0).setMail("ane.nunes@campus.fct.unl.pt");
            resourceList.add(new HumanResource("Miquelina", 1));
            resourceList.get(1).setMail("dfg.dias@campus.fct.unl.pt");
            resourceList.add(new HumanResource("Marinho", 2));
            resourceList.get(2).setMail("p.gasparinho@campus.fct.unl.pt");
            EmailInfoClass mail = new EmailInfoClass(resourceList, path);
            mail.setUserSender("ganttprojectes2022");
            mail.setPassword("wrongPassword");
            mail.setBody("");
            mail.setSubject("");
            SendEmail m = new SendEmail();
            String statusOperation = m.mailSend(mail);
            assert (statusOperation.equals("There was an error sending the e-mail"));
        }catch (URISyntaxException e) {
        }
    }

    //The path should be a valid path in the computer that is running the program
    public void testEmail6() {
        try {
            URI path = new URI("file:/C:/Users/diogo/OneDrive/Imagens/harvey.JPG");
            List<HumanResource> resourceList = new ArrayList<HumanResource>();
            resourceList.add(new HumanResource("Asdrubal", 0));
            resourceList.get(0).setMail("ane.nunes@campus.fct.unl.pt");
            resourceList.add(new HumanResource("Miquelina", 1));
            resourceList.get(1).setMail("dfg.dias@campus.fct.unl.pt");
            resourceList.add(new HumanResource("Marinho", 2));
            resourceList.get(2).setMail("p.gasparinho@campus.fct.unl.pt");
            EmailInfoClass mail = new EmailInfoClass(resourceList, path);
            mail.setUserSender("userWrong");
            mail.setPassword("wrongPassword");
            mail.setBody("");
            mail.setSubject("");
            SendEmail m = new SendEmail();
            String statusOperation = m.mailSend(mail);
            assert (statusOperation.equals("There was an error sending the e-mail"));
        } catch (URISyntaxException e) {
        }
    }


    public void testEmail7() {
        try {
            URI path = new URI("file:/C:/ERRO");
            List<HumanResource> resourceList = new ArrayList<HumanResource>();
            EmailInfoClass mail = new EmailInfoClass(resourceList, path);
            mail.setUserSender("");
            mail.setPassword("");
            mail.setBody("");
            mail.setSubject("");
            SendEmail m = new SendEmail();
            String statusOperation = m.mailSend(mail);
            assert (statusOperation.equals("There was an error sending the e-mail"));
        } catch (URISyntaxException e) {
        }
    }



}








