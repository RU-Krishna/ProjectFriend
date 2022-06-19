package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main  {

    // * Creator :- Krishna Purwar
    // * Purpose:- For storing the friend/family details like
    // *              *First name
    // *              *Last name
    // *              *Phone number
    // *              *Email id
    // *              *Address
    // *              *Date of birth...
    // *
    // *
    // *


    public static void main(String[] args) {

        /*
        * Here we made a frame for menu options for friend class...
        * This consists of :-
        * 1. Add a friend.
        * 2. Update a friend details...
        *     a. Phone number
        *     b. Email id
        *     c. Address
        * 3. Search for a friend...
        * 4. Delete a friend...
        *
        * We made a frame here,
        *      in which we add labels for all menu options...
        *      we also add a JTextField for taking the input from the user for a menu...
        *      and also add a button for proceeding the further options...
        * */
        JFrame option=new JFrame("Friend Menu");
        option.add(new JLabel("1. Add A friend"));
        option.add(new JLabel("2. Update the friend"));
        option.add(new JLabel("3. Search for a friend"));
        option.add(new JLabel("4. Delete the data of a friend"));
        option.add(new JLabel("5. Exit"));
        JTextField field=new JTextField(2);
        option.add(field);
        JButton op=new JButton("Proceed");
        op.setBorderPainted(true);
        option.add(op);
        option.setSize(400,200);
        option.setLayout(new GridLayout(8,1));
        option.setLocationRelativeTo(null);
        option.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        option.setVisible(true);
        op.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String str=field.getText();
                field.setText("");
                switch (str) {
                    case "1" -> new Friendlies();
                    case "2" -> new UpdateFriend();
                    case "3" -> new SearchFriend();
                    case "4" -> new DeleteFriend();
                    case "5" -> option.dispose();
                    default -> JOptionPane.showMessageDialog(option, "Incorrect option");
                }
                }
        });


    }


}