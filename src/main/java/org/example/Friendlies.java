package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Friendlies extends JFrame implements ActionListener {


    public Friendlies() {
        setTitle("FriendData");
        field1 = new JTextField();
        field2 = new JTextField();
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
        setLayout(new GridLayout(8, 2));
        setSize(500, 500);
        setVisible(true);
        button.addActionListener(this);
        button.setHorizontalAlignment(JButton.CENTER);
        button.setVerticalAlignment(JButton.CENTER);
        add(button);
        setLocationRelativeTo(null);
    }
    JButton button = new JButton("Save");

    JTextField field1, field2, field3, field4, field5, field6;

    @Override
    public void actionPerformed(ActionEvent e) {

        Friend frnd = new Friend();
        frnd.setFirst_name(field1.getText());
        frnd.setLast_name((field2.getText()));
        frnd.setPhn_num(field3.getText());
        frnd.setEmail_id(field4.getText());
        frnd.setAddress(field5.getText());
        frnd.setD_O_B(field6.getText());
        if(frnd.getFirst_name().equals(" ") || frnd.getLast_name().equals("")|| frnd.getPhn_num().equals("")|| frnd.getAddress().equals("") || frnd.getEmail_id().equals("") || frnd.getD_O_B().equals("")) {
            JOptionPane.showMessageDialog(this, "No Empty Field Allowed");
        }
         else{
             button.setEnabled(false);
        field1.setText(" ");
        field2.setText(" ");
        field3.setText(" ");
        field4.setText(" ");
        field5.setText(" ");
        field6.setText(" ");
        dispose();
        new frienddata().insert(frnd);
        JOptionPane.showMessageDialog(this,"New Friend Added\n"+ frnd);
    }
    }
}
