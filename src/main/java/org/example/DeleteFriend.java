package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class DeleteFriend {
    //Delete friend class

    private JTextField text1, text2;   //Global private text fields for asking the name of the user to be deleted

    public DeleteFriend() {    //DeleteFriend Constructor...
        deleteFrame();          //frame for taking the info of the user...

    }


    private void deleteFrame() {
        //Making new frame for asking the user_name and delete him/her from the database...
        JFrame frame = new JFrame("Delete the friend");  //Constructing a new frame...
        text1 = new JTextField();    //Constructing new fields for taking input...
        text2 = new JTextField();
        frame.add(new JLabel("first_name"));  //Adding new label as a hint for first_name...
        frame.add(text1);
        frame.add(new JLabel("last_name"));  //Adding new label as a hint for last_name...
        frame.add(text2);
        JButton button = new JButton("Delete");  //Constructing a new delete button...
        button.setSize(50, 10);
        frame.add(button);     //Adding button to the frame
        button.addActionListener(new ActionListener() {   //Anonymous Action listener class for the 'Delete' button...
            @Override
            public void actionPerformed(ActionEvent e) {  //Action performed method of the action listener...
                /*
                 * If the user clicks on the 'Delete' button with any
                 * of the empty fields:-
                 * then a "MessageDialogBox" appears in front of you with the message:-
                 *        "Empty fields not allowed"...
                 * */


                if (text1.getText().equals("") || text2.getText().equals(""))
                    JOptionPane.showMessageDialog(frame, "Empty fields not allowed");
                    /*
                     * If all the fields are filled then
                     * Delete the friend data given by the user...
                     * and Dispose the frame...
                     * */
                else {
                    Friend friend = new Friend(text1.getText(), text2.getText());   //Friend class constructor...
                    delete(friend.primary());
                    frame.dispose();   //Disposing/Closing the new frame after the deletion of the friend...
                }
            }
        });
        JButton button1 = new JButton("Cancel");  //Adding a new Cancel button...
        button1.setSize(50, 10);
        frame.add(button1);
        button1.addActionListener(new ActionListener() {    //Anonymous class...
            //For listening the cancel button...
            @Override   //Disposing the frame on clicking Cancel button...
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
            }
        });
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLayout(new GridLayout(5, 2));   //Grid layout for all the components of the frame...
        frame.setVisible(true);
        frame.setLocationRelativeTo(null); //setting frame to the centre of the screen...

    }

    private void delete(String key) {  //Delete method for removing the friend from the database...

        /*
         *    Try catch with resources...
         *    Setting up connection with the jdbc in try with resources...
         *    Setting up a statement with the jdbc...
         *     Executing to delete query with the "executeUpdate" method of statement...
         *     catching a sql exception and representing a MessageDialogBox with the message "Unsuccessful Deletion or absenteism of that
         *     friend in the database..."
         * */
        try (Connection connection = DriverManager.getConnection(CreateDatabase.conn, CreateDatabase.username, CreateDatabase.password);
             PreparedStatement statement = connection.prepareStatement("DELETE FROM friend WHERE friend.id = ? ")) {
            statement.setString(1, key);
            int check = statement.executeUpdate();
            if (check == 1)
                JOptionPane.showMessageDialog(null, "Successful Deletion:-\n");
            else
                JOptionPane.showMessageDialog(null, "Unsuccessful Deletion\n Friend absent");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Unsuccessful Deletion or \n Friend Absent" + e);
        }
    }
}
