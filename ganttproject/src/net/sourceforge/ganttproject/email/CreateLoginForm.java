package net.sourceforge.ganttproject.email;
import javax.swing.*;
//import javax.validation.constraints.Email;
import java.awt.*;
import java.awt.event.*;
import java.lang.Exception;
import net.sourceforge.ganttproject.email.*;

public class CreateLoginForm extends JFrame implements ActionListener
{
    //initialize button, panel, label, and text field
    JButton b1;
    JPanel newPanel;
    JLabel userLabel, passLabel, subLabel, bodyLabel;
    final JTextField  textField1, textField2, textField3,textField4;

    private EmailInfo mail;

    //calling constructor
    public CreateLoginForm(EmailInfo mail) {
        this.mail=mail;
        //create label for username
        userLabel = new JLabel();
        userLabel.setText("Username");      //set label value for textField1
        textField1 = new JTextField(15);    //set length of the text

        //create label for password
        passLabel = new JLabel();
        passLabel.setText("Password");      //set label value for textField2
        textField2 = new JPasswordField(15);

        //create text field to get password from the user
        subLabel = new JLabel();
        subLabel.setText("Subject");      //set label value for textField2
        textField3 = new JTextField(15);

        //create text field to get password from the user

        //create submit button
        bodyLabel= new JLabel();
        bodyLabel.setText("Body");
        textField4 = new JTextField(15);

        b1 = new JButton("Submit"); //set label to button


        //create panel to put form elements
        newPanel = new JPanel(new GridLayout(5, 1));
        newPanel.add(userLabel);    //set username label to panel
        newPanel.add(textField1);   //set text field to panel
        newPanel.add(passLabel);    //set password label to panel
        newPanel.add(textField2);   //set text field to panel
        newPanel.add(subLabel);    //set password label to panel
        newPanel.add(textField3);   //set text field to panel
        newPanel.add(bodyLabel);    //set password label to panel
        newPanel.add(textField4);   //set text field to panel
        newPanel.add(b1);           //set button to panel

        //set border to panel
        add(newPanel, BorderLayout.CENTER);

        //perform action on button click
        b1.addActionListener(this);     //add action listener to button
        setTitle("E-mail Login");         //set title to the login form
    }
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
