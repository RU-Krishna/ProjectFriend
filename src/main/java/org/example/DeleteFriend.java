package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class DeleteFriend {
    private JTextField text1,text2;

    public DeleteFriend(){
        deleteFrame();

    }


    private void deleteFrame(){




        JFrame frame=new JFrame("Search for the friend");
        text1=new JTextField();
        text2=new JTextField();
        frame.add(new JLabel("first_name"));
        frame.add(text1);
        frame.add(new JLabel("last_name"));
        frame.add(text2);
        JButton button=new JButton("Delete");
        button.setSize(50,10);
        frame.add(button);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Friend friend=new Friend(text1.getText(),text2.getText());
                delete(friend.primary());
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
    private void delete(String key){
        final String conn = "jdbc:mysql://localhost:3306/Frienddata";
        final String username="root";
        final String password = "hi@SQL22";
        try(Connection connection= DriverManager.getConnection(conn,username,password);
            Statement statement= connection.createStatement()){
                statement.executeUpdate("DELETE FROM friend WHERE friend.id='"+key+"';");
                JOptionPane.showMessageDialog(null, "Successful Deletion:-\n");
        }
        catch(SQLException e){
            JOptionPane.showMessageDialog(null,"Unsuccessful Deletion or \n Friend Absent"+e);
        }
    }
}
