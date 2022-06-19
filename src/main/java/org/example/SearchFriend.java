package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class SearchFriend {
    private JTextField text1,text2;

    public SearchFriend(){
     searchFrame();
    }

    private void searchFrame(){
        JFrame frame=new JFrame("Search for the friend");
        text1=new JTextField();
        text2=new JTextField();
        frame.add(new JLabel("first_name"));
        frame.add(text1);
        frame.add(new JLabel("last_name"));
        frame.add(text2);
        JButton button=new JButton("Search");
        button.setSize(50,10);
        frame.add(button);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Friend friend=new Friend(text1.getText(),text2.getText());
                Search(friend.primary());
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
    private void Search(String key){
        boolean flag=false;
        final String conn = "jdbc:mysql://localhost:3306/Frienddata";
        final String username="root";
        final String password = "hi@SQL22";
        try(Connection connection= DriverManager.getConnection(conn,username,password);
            Statement statement= connection.createStatement();
             ResultSet set=statement.executeQuery("SELECT * FROM friend WHERE friend.Id = '"+key+"';")){
                while(set.next()) {
                    flag=true;
                    String str = "first_name= "+set.getString(2) + "\n" +
                            "last_name= "+set.getString(3) + "\n" +
                            "Phn_num= "+set.getString(4) + "\n" +
                            "Email_id= "+set.getString(5) + "\n" +
                            "Address= "+set.getString(6) + "\n" +
                            "D_O_B= "+set.getString(7);
                    JOptionPane.showMessageDialog(null, "Friend info:-\n" + str);
                }
                if(!flag)
                   JOptionPane.showMessageDialog(null,"Friend absent");

        }
        catch(SQLException e){
            JOptionPane.showMessageDialog(null,"Something went wrong");
        }
    }


}
