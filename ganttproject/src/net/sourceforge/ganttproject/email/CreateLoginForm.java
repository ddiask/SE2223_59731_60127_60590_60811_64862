package net.sourceforge.ganttproject.email;

import javax.swing.*;
//import javax.validation.constraints.Email;
import java.awt.*;
import java.awt.event.*;
import java.lang.Exception;

public class CreateLoginForm extends JFrame implements ActionListener
{
    //initialize button, panel, label, and text field
    JButton b1;
    JPanel newPanel;
    JLabel userLabel, passLabel;
    final JTextField  textField1, textField2;

    //calling constructor
    public CreateLoginForm() {
        //create label for username
        userLabel = new JLabel();
        userLabel.setText("Username");      //set label value for textField1

        //create text field to get username from the user
        textField1 = new JTextField(15);    //set length of the text

        //create label for password
        passLabel = new JLabel();
        passLabel.setText("Password");      //set label value for textField2

        //create text field to get password from the user
        textField2 = new JPasswordField(15);    //set length for the password

        //create submit button
        b1 = new JButton("SUBMIT"); //set label to button

        //create panel to put form elements
        newPanel = new JPanel(new GridLayout(3, 1));
        newPanel.add(userLabel);    //set username label to panel
        newPanel.add(textField1);   //set text field to panel
        newPanel.add(passLabel);    //set password label to panel
        newPanel.add(textField2);   //set text field to panel
        newPanel.add(b1);           //set button to panel

        //set border to panel
        add(newPanel, BorderLayout.CENTER);

        //perform action on button click
        b1.addActionListener(this);     //add action listener to button
        setTitle("LOGIN FORM");         //set title to the login form
    }
        public void actionPerformed(ActionEvent ae)     //pass action listener as a parameter
        {
            System.out.println("Antes de enviar o mail");
            SendEmail m = new SendEmail();
            String userValue = textField1.getText();        //get user entered username from the textField1
            String passValue = textField2.getText();        //get user entered pasword from the textField2

            m.mailSend(userValue, passValue);

            System.out.println("Depois de enviar");

            //create instance of the NewPage
            NewPage page = new NewPage();

            //make page visible to the user
            page.setVisible(true);

            //create a welcome label and set it to the new page
            JLabel wel_label = new JLabel("Welcome: "+userValue+"@gmail.com");
            page.getContentPane().add(wel_label);
        }
}
