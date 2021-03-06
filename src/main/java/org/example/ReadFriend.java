package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;


public class ReadFriend {

    /*
     *  Made a ReadFriend class which displays the whole friend data...
     *  The entire data is retrieved on a Linked List from my database...
     *  Then we sort the entire data we obtained, and we represent it on a JFrame with an "OK" button...
     *  This "OK" button disposes the frame on pressing the "ENTER", "SPACE" or "MOUSE" clicks...
     *  We can also dispose the frame on the Close button present in the top right corner of the JFrame...
     * */
    private final List<Friend> list = new LinkedList<>(); //Here we made a Linked List of the List Interface...

    private boolean check = true;

    public ReadFriend() {  //ReadFriend constructor...
        read();  //read method...
        if (check)
            readFriendFrame();  //readFriendMethod...
    }

    private void read() {

        /*
         * Setting up connection with the "Mysql" through the jdbc...
         * Making a prepared statement For reading the whole data from the database...
         * Taking all the data into the ResultSet...
         * Then retrieving all the data into the friend object...
         * Adding Friend object into the linked list one at a go...
         *
         * */
        try (Connection connection = DriverManager.getConnection(CreateDatabase.conn, CreateDatabase.username, CreateDatabase.password); PreparedStatement statement = connection.prepareStatement("SELECT * FROM friend ")) {
            ResultSet set = statement.executeQuery();
            while (set.next()) {
                Friend info = new Friend();
                info.setFirst_name(set.getString(2));
                info.setLast_name(set.getString(3));
                info.setPhn_num(set.getString(4));
                info.setEmail_id(set.getString(5));
                info.setAddress(set.getString(6));
                info.setD_O_B(set.getString(7));
                list.add(info);
            }
            set.close();  //Closing the ResultSet...
        }
        /*
         *  Catching the SQL exception...
         *  In case if so,
         *  A showMessageDialogBox appears with the message "Something Went wrong"...
         *
         * */ catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Something went wrong");
            check = false;
        }
    }

    /*
     *  Read Friend Frame method which reads the data from the Linked List,
     *  and displays the entire data stored in the linked list
     *  in a JFrame with the labels...
     *
     *  The horizontal order of representation of friend info is like:-
     *  1. First_name...
     *  2. Last_name...
     *  3. Phone_num...
     *  4. Email_id...
     *  5. Address...
     *  6. Date_of_Birth...
     *
     *
     * */

    private void readFriendFrame() {
        JFrame frame = new JFrame("Read the data");  //A JFrame with the label "Read the Data"...
        JButton button = new JButton("OK");   //An "OK" button...
        /*
         *   Sets a new Grid Layout on the JFrame with the...
         *   Row count= Size of linked list+2;
         *   Column count= 7;
         *   Horizontal Gap= 10;
         *   Vertical Gap= 15;
         *
         * */
        frame.setLayout(new GridLayout(list.size() + 2, 7, 10, 15));
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); //Throwing away "Read Frame" on clicking the "Cross" button...
        frame.setVisible(true);  //Making the frame visible...
        /*
         *  Adding the headings on the JFrame...
         *  For each field of the friend class...
         * */
        frame.add(new JLabel("first_name"));
        frame.add(new JLabel("last_name"));
        frame.add(new JLabel("Phone_no."));
        frame.add(new JLabel("Email_id"));
        frame.add(new JLabel("Address"));
        frame.add(new JLabel("D_O_B"));
        /*
         *  Sorting the entire data
         *  in the order of the name...
         *  with the use of the "sort" method of the "Collections" class...
         * */
        Collections.sort(list);
        /*
         *  Retrieving all the data from the LinkedList...
         *  with the ListIterator...
         *
         * */
        for (Friend info : list) {
            frame.add(new JLabel(info.getFirst_name()));
            frame.add((new JLabel(info.getLast_name())));
            frame.add(new JLabel(info.getPhn_num()));
            frame.add(new JLabel(info.getEmail_id()));
            frame.add(new JLabel(info.getAddress()));
            frame.add(new JLabel(info.getD_O_B()));
        }
        frame.add(button);  //add a button on the frame...
        frame.getRootPane().setDefaultButton(button);  //make the button to hear the "Enter" and the "Space" key...
        frame.pack();  //Frame the ReadFrame as per the info...
        frame.setLocationRelativeTo(null); //Make the frame to present in the center on invocation...


        /*
         * On pressing the button
         * Dispose the frame...
         * */
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
            }
        });
    }

}
