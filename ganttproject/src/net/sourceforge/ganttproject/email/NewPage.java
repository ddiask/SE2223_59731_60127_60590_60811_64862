
package net.sourceforge.ganttproject.email;
import javax.swing.*;
import java.awt.*;

/**
 * Create a pop up that informs the user that if the operation was successful
 */
public class NewPage extends JFrame
{

    private final static String TITLE="Information about E-mail";
    //constructor
    NewPage()
    {
        setDefaultCloseOperation(javax.swing.
                WindowConstants.DISPOSE_ON_CLOSE);
        setTitle(TITLE);
        setSize(400, 200);
    }
}