package net.sourceforge.ganttproject.email;

import javax.swing.*;
import net.sourceforge.ganttproject.gui.UIFacade;

/**
 * Class used to create the JFrame that supports the e-mail graphic interface
 * and a CreateLoginForm form that will contain the mail recipients and the path to the file.
 */
public class LoginFromDemo
{
    /**
     * The loginMail method that is responsible to create the form containing the resources e-mail, t
     * he file path and the graphic interface facade.
     * @param mail -> The EmailInfo object with the e-mail information: Has the list of recipients and the file to be exported.
     * @param uiFacade -> The facade used to create the graphic interface
     */
    public void loginMail(EmailInfo mail, UIFacade uiFacade)
    {
        try
        {
            CreateLoginForm form = new CreateLoginForm(mail, uiFacade);
            form.pack();
            form.setVisible(true);

        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }
}
