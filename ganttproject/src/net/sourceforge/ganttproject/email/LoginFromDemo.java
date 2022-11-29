//import required classes and packages
package net.sourceforge.ganttproject.email;

import javax.swing.*;
//import javax.validation.constraints.Email;
import java.awt.*;
import java.awt.event.*;
import java.lang.Exception;
import net.sourceforge.ganttproject.email.CreateLoginForm;
import net.sourceforge.ganttproject.email.*;
//create CreateLoginForm class to create login form
//class extends JFrame to create a window where our component add
//class implements ActionListener to perform an action on button click


    //define abstract method actionPerformed() which will be called on button click

//create the main class
public class LoginFromDemo
{
    //main() method start
    public void loginMail(EmailInfo mail)
    {
        try
        {
            //create instance of the CreateLoginForm
            CreateLoginForm form = new CreateLoginForm(mail);
            //form.setSize(300,300);  //set size of the frame
            form.pack();

            form.setVisible(true);  //make form visible to the user

        }
        catch(Exception e)
        {
            //handle exception
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }
}