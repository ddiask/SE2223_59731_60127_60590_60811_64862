package net.sourceforge.ganttproject.email;

import net.sourceforge.ganttproject.gui.UIFacade;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CreateLoginForm extends JFrame implements ActionListener
{
    //initialize button, panel, label, and text field
    JButton b1;
    JPanel newPanel;
    JLabel userLabel, passLabel, subLabel, bodyLabel;
    final JTextField  textField1, textField2, textField3,textField4;

    private EmailInfo mail;


    /**
     *This method creates a form for the user to insert data about the email
     * @param mail the class that saves data about the mail
     * @param uiFacade the class that is responsible for interaction with graphical
     */
    public CreateLoginForm(EmailInfo mail, final UIFacade uiFacade) {
        this.mail=mail;
        userLabel = new JLabel();
        userLabel.setText("Username");      //set label value for textField1
        textField1 = new JTextField(15);    //set length of the text
        passLabel = new JLabel();
        passLabel.setText("Password");      //set label value for textField2
        textField2 = new JPasswordField(15);
        subLabel = new JLabel();
        subLabel.setText("Subject");      //set label value for textField2
        textField3 = new JTextField(15);
        bodyLabel= new JLabel();
        bodyLabel.setText("Body");
        textField4 = new JTextField(15);
        b1 = new JButton("Submit"); //set label to button
        newPanel = new JPanel(new GridLayout(5, 1));
        newPanel.add(userLabel);    //set username label to panel
        newPanel.add(textField1);   //set text field to panel
        newPanel.add(passLabel);    //set password label to panel
        newPanel.add(textField2);
        newPanel.add(subLabel);
        newPanel.add(textField3);
        newPanel.add(bodyLabel);
        newPanel.add(textField4);
        Image icon = Toolkit.getDefaultToolkit().getImage("..\\..\\ganttproject\\data\\resources\\logos\\icon16.png");
        setIconImage(icon);
        uiFacade.getMainFrame().setEnabled(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosed(java.awt.event.WindowEvent windowEvent) {
                uiFacade.getMainFrame().setEnabled(true);
            }
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                uiFacade.getMainFrame().setEnabled(true);
            }
        });
        add(newPanel, BorderLayout.CENTER);
        setLocationRelativeTo(null);
        add(newPanel.add(b1), BorderLayout.PAGE_END);
        b1.addActionListener(this);
        setTitle("E-mail Login");
    }



    /**
     *This method is called when the button submitted is clicked. This method calls the method
     * that sends an email and creates a new pop-up. This new pop-up notifies the user how the
     * email action occurs.
     * @param ae it's an event generated when the user clicks on the button
     */
    public void actionPerformed(ActionEvent ae)     //pass action listener as a parameter
        {
            SendEmail m = new SendEmail();
            String userValue = textField1.getText();
            String passValue = textField2.getText();
            String subject = textField3.getText();
            String body=textField4.getText();
            mail.setUserSender(userValue);
            mail.setBody(body);
            mail.setSubject(subject);
            mail.setPassword(passValue);
            String statusOperation = m.mailSend(mail);
            dispose();
            NewPage page = new NewPage();
            page.setVisible(true);
            JLabel wel_label = new JLabel(statusOperation);
            page.getContentPane().add(wel_label);
        }
}
