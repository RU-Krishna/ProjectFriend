package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Friendlies extends JFrame implements ActionListener {

   /*
   *  This is a class for constructing a new frame for taking the info of the friend...
   *  Info is asked in the order:-
   *  first_name
   *  last_name
   *  Phone_num
   *  Email_Id
   *  Address
   *  Date_of_Birth in the form (yyyy-mm-dd)...
   * */


    /*
    *  Making 6 new global private JTextFields for taking the info...
    *
    * */
    private JTextField field1, field2, field3, field4, field5, field6;

    /*
    *   Making a new private button with the title "Save"
    *
    * */
    private  JButton button = new JButton("Save");


    public Friendlies() {
        setTitle("FriendData");  //Title of the frame...
        field1 = new JTextField();
        field2 = new JTextField();  //Constructing 6 text fields...
        field3 = new JTextField();
        field4 = new JTextField();
        field5 = new JTextField();
        field6 = new JTextField();
        add(new JLabel("first_name"));
        add(field1);
        add(new JLabel("last_name"));
        add(field2);
        add(new JLabel("Phone num"));
        add(field3);
        add(new JLabel("Email_id"));
        add(field4);
        add(new JLabel("Address"));
        add(field5);
        add(new JLabel("Date_of_Birth(yyyy-mm-dd)"));
        add(field6);
        setLayout(new GridLayout(8, 2));  //Setting Grid layout for the frame...
        setSize(500, 500);
        setVisible(true);
        button.addActionListener(this);   //Adding button action listener on the save button...
        button.setHorizontalAlignment(JButton.CENTER);
        button.setVerticalAlignment(JButton.CENTER);
        add(button);
        setLocationRelativeTo(null);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
    //Giving the body to the "actionPerformed" method of the interface ActionListener...
        Friend frnd = new Friend();      //Calling a new friend constructor...
        frnd.setFirst_name(field1.getText());
        frnd.setLast_name((field2.getText()));
        frnd.setPhn_num(field3.getText());   //Taking the values from the JTextFields...
        frnd.setEmail_id(field4.getText());  //From the getText method of the JTextField...
        frnd.setAddress(field5.getText());   //Setter methods of the friend class...
        frnd.setD_O_B(field6.getText());

        //Checking for the empty field...
        if(frnd.getFirst_name().equals("") || frnd.getLast_name().equals("")|| frnd.getPhn_num().equals("")|| frnd.getAddress().equals("") || frnd.getEmail_id().equals("") || frnd.getD_O_B().equals("")) {
            JOptionPane.showMessageDialog(this, "No Empty Field Allowed");
            //Displaying the showMessageDialogBox with the message "No Empty Field Allowed"...
        }
         else{
             button.setEnabled(false);  //Deactivating the  Button...
        field1.setText(" ");
        field2.setText(" ");
        field3.setText(" ");
        field4.setText(" ");
        field5.setText(" ");
        field6.setText(" ");
        dispose();
        new frienddata().insert(frnd);
        //Showing the showMessageDialogBox with the message "New Friend Added"...
        JOptionPane.showMessageDialog(this,"New Friend Added\n"+ frnd);
    }
    }
}
