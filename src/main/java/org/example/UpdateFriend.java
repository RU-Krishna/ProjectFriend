package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.Objects;

public class UpdateFriend{
    private JTextField text1,text2,text3;  //Setting up three JTextFields for the interaction with the user...
    private final JComboBox<String> combo=new JComboBox<>();
     /*
     * Setting up JComboBox with the options of updating
     * Phone_num...
     * Address...
     * Email_id...
     * */

    public UpdateFriend(){
        //UpdateFriend constructor....

        UpdateFrame();  //Frame for taking the info of the friend...

    }

    private void UpdateFrame(){
        JFrame frame=new JFrame("Search for the friend");
        /*Constructing a new frame for asking the updated info of the user...
        * with the title "Search for the friend..."
        */
        text1=new JTextField();  //Making three new JTextField....
        text2=new JTextField();
        text3=new JTextField();
        frame.add(new JLabel("first_name"));
        frame.add(text1);
        frame.add(new JLabel("last_name"));
        frame.add(text2);
        combo.addItem("Phn_num");
        combo.addItem("Email_id");
        combo.addItem("Address");
        frame.add(combo);
        frame.add(text3);
        JButton button=new JButton("Update");  //Constructing a new "Update" button for Updating the friend info...
        button.setSize(50,10);
        frame.add(button);    //Adding this "Update" button to the frame...
        button.addActionListener(new ActionListener() {
            //Listening the "Update" button...
            @Override
            public void actionPerformed(ActionEvent e) {
                /*
                *
                * If the user pressed the 'Update' button with
                * any of the empty fields then
                * a "MessageDialogBox" appears in front of the user with
                * the message "Empty Fields not allowed"...
                *
                * */
                if(text1.getText().equals("") || text2.getText().equals("") || text3.getText().equals(""))
                    JOptionPane.showMessageDialog(frame,"Empty fields not allowed");
                /*
                *
                * In case of no empty fields
                * given info is fetched by the program
                * and the friend info updated...
                *
                * */
                else {
                    Friend friend = new Friend(text1.getText(), text2.getText());
                    Update(friend.primary(), Objects.requireNonNull(combo.getSelectedItem()).toString(), text3.getText());
                    frame.dispose();   //After updating the info the frame gets info...
                }
            }
        });
        JButton button1=new JButton("Cancel");  //Making a new "Cancel" button...
        button1.setSize(50,10);
        frame.add(button1);  //Adding a new Cancel button to the frame...
        button1.addActionListener(new ActionListener() {

            //Listening the "Cancel" button...
            @Override
            public void actionPerformed(ActionEvent e) {
                //Disposing/Closing frame on clicking the  "Cancel" button...
                frame.dispose();
            }
        });
        frame.setSize(400,300);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLayout(new GridLayout(5,2));  //Adding components into Grid layout in the frame...
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);  //Locating frame in the center on calling...

    }


    private void Update(String key,String item,String value){
        final String conn = "jdbc:mysql://localhost:3306/Frienddata";
        final String username="root";
        final String password = "hi@SQL22";

        //Connecting to the MYSQL JDBC in the try catch with resources ...
        try(Connection connection= DriverManager.getConnection(conn,username,password);
            PreparedStatement statement= connection.prepareStatement("UPDATE friend SET "+item+" = ? WHERE friend.Id= ?")
            ){
            statement.setString(1,value);
            statement.setString(2,key);
            int check=statement.executeUpdate();
            if(check==1)
                JOptionPane.showMessageDialog(null,"Updated Successfully");
            else
                JOptionPane.showMessageDialog(null,"Friend absent");
        }
        catch(SQLException e){
            JOptionPane.showMessageDialog(null,"Something went wrong\n Please try Again\n"+e);
        }
    }
}
