package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.Objects;

public class UpdateFriend{
    private JTextField text1,text2,text3;
    private final JComboBox<String> combo=new JComboBox<>();


    public UpdateFriend(){
        UpdateFrame();

    }

    private void UpdateFrame(){
        JFrame frame=new JFrame("Search for the friend");
        text1=new JTextField();
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
        JButton button=new JButton("Update");
        button.setSize(50,10);
        frame.add(button);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Friend friend=new Friend(text1.getText(),text2.getText());
                Update(friend.primary(), Objects.requireNonNull(combo.getSelectedItem()).toString(),text3.getText());
                frame.dispose();
            }
        });
        JButton button1=new JButton("Cancel");
        button1.setSize(50,10);
        frame.add(button1);
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
            }
        });
        frame.setSize(400,300);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLayout(new GridLayout(5,2));
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);

    }


    private void Update(String key,String item,String value){
        final String conn = "jdbc:mysql://localhost:3306/Frienddata";
        final String username="root";
        final String password = "hi@SQL22";
        try(Connection connection= DriverManager.getConnection(conn,username,password);
            Statement statement= connection.createStatement()){
            switch (item) {
                case "Phn_num" ->
                        statement.executeUpdate("UPDATE friend SET friend.Phn_num='" + value + "'WHERE friend.id='" + key + "';");
                case "Email_id" ->
                        statement.executeUpdate("UPDATE friend SET friend.Email_id='" + value + "'WHERE friend.id='" + key + "';");
                case "Address" ->
                        statement.executeUpdate("UPDATE friend SET friend.Address='" + value + "'WHERE friend.id='" + key + "';");
            }









        }
        catch(SQLException e){
            JOptionPane.showMessageDialog(null,"Something went wrong");
        }
    }
}
