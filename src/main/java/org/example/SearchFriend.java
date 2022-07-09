package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class SearchFriend {
    private JTextField text1, text2;   //Making Two Global Private JTextField for SearchFrame...

    public SearchFriend() {
        //SearchFriend Constructor....
        searchFrame(); //calling searchFrame method...
    }

    private void searchFrame() {    //private searchFrame method...

        /*
         *   Making a new frame With the title search for friend...
         *    Constructing two JTextFields....
         *
         * */
        JFrame frame = new JFrame("Search for the friend");
        text1 = new JTextField();
        text2 = new JTextField();
        frame.add(new JLabel("first_name"));
        frame.add(text1);
        frame.add(new JLabel("last_name"));
        frame.add(text2);
        JButton button = new JButton("Search"); //Making a new button with the title 'Search'...
        button.setSize(50, 10);
        frame.add(button);        //Adding a new button on the frame...
        button.addActionListener(new ActionListener() {  //Adding Action Listener...

            //Setting up "Anonymous Class" for action listener...
            @Override
            public void actionPerformed(ActionEvent e) {
                /*
                 *
                 * If any of the fields is empty
                 * when the 'Search' button is pressed
                 * a "MessageDialogBox" appears in front of the user
                 * with the message "Empty Fields not allowed"*
                 * Root frame="searchFrame"
                 * */
                if (text1.getText().equals("") || text2.getText().equals(""))
                    JOptionPane.showMessageDialog(frame, "Empty fields not allowed");
                    /*
                     *
                     * In case of no empty fields
                     * the friend is searched into the database...
                     *
                     * */
                else {
                    Friend friend = new Friend(text1.getText(), text2.getText());  //Making a new constructor for the friend class having only name field...
                    Search(friend.primary());         //Giving the primary key to the Search method...
                    frame.dispose();    //After completing the search the frame gets dispose...
                }
            }
        });
        JButton button1 = new JButton("Cancel");   //Adding a new cancel button on the frame...
        button1.setSize(50, 10);
        frame.add(button1);    //Adding the 'Cancel' button on the frame...
        button1.addActionListener(new ActionListener() {    //Listening the Cancel Button of the frame Class
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();  //Disposing/Closing the Search Frame...
            }
        });
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLayout(new GridLayout(5, 2));  //Adding the components into the grid layout into the ...
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);  //Locating the frame on the centre of the screen...


    }

    private void Search(String key) {
        boolean flag = false;  //Flag
        final String conn = "jdbc:mysql://localhost:3306/Frienddata";
        final String username = "root";
        final String password = "hi@SQL22";
        /*
         * Setting up connection with the "Mysql" through the jdbc...
         * Setting up a ResultSet for retrieving the info of the searched friend...
         * Printing the info of the friend on the showMessageDialogBox of the JOptionPane...
         *
         * */
        try (Connection connection = DriverManager.getConnection(conn, username, password);
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM friend WHERE friend.Id = ?");) {
            statement.setString(1, key);
            ResultSet set = statement.executeQuery();
            while (set.next()) {
                flag = true;
                String str = "first_name= " + set.getString(2) + "\n" +
                        "last_name= " + set.getString(3) + "\n" +
                        "Phn_num= " + set.getString(4) + "\n" +
                        "Email_id= " + set.getString(5) + "\n" +
                        "Address= " + set.getString(6) + "\n" +
                        "D_O_B= " + set.getString(7);
                JOptionPane.showMessageDialog(null, "Friend info:-\n" + str);
            }
            set.close();
            if (!flag)  //If the friend was not found...
                //showMessageDialogBox with message "FriendAbsent" comes in front of u...
                JOptionPane.showMessageDialog(null, "Friend absent");

        } catch (SQLException e) {

            //If there is an SQLException showMessageDialogBox with message "Something Went Wrong" is shown...
            JOptionPane.showMessageDialog(null, "Something went wrong");
        }
    }


}
