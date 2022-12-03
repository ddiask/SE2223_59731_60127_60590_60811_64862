//import required classes and packages
package net.sourceforge.ganttproject.email;

import net.sourceforge.ganttproject.gui.UIFacade;

import javax.swing.*;




public class LoginFromDemo
{

    public void loginMail(EmailInfo mail, UIFacade uiFacade)
    {
        try
        {
            CreateLoginForm form = new CreateLoginForm(mail, uiFacade);
            form.pack();
            form.setVisible(true);  //make form visible to the user

        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }
}