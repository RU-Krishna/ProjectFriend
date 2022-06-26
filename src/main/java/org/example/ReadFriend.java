package org.example;

import javax.swing.*;
import java.awt.*;
import java.sql.*;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

public class ReadFriend {
     List<Friend> list=new LinkedList<Friend>();
     public ReadFriend(){
         read();
         readFrame();
     }

     private void  read(){
         final String conn = "jdbc:mysql://localhost:3306/Frienddata";
         final String username="root";
         final String password = "hi@SQL22";
         /*
          * Setting up connection with the "Mysql" through the jdbc...
          * Setting up a ResultSet for retrieving the info of the searched friend...
          * Printing the info of the friend on the showMessageDialogBox of the JOptionPane...
          *
          * */
         try(Connection connection= DriverManager.getConnection(conn,username,password);
             PreparedStatement statement= connection.prepareStatement("SELECT * FROM friend "))
         {
             ResultSet set = statement.executeQuery();
             while(set.next()){
                 Friend info=new Friend();
                 info.setFirst_name(set.getString(2));
                 info.setLast_name(set.getString(3));
                 info.setPhn_num(set.getString(4));
                 info.setEmail_id(set.getString(5));
                 info.setAddress(set.getString(6));
                 info.setD_O_B(set.getString(7));
                 list.add(info);
             }
             set.close();
       }
         catch(SQLException e) {
             JOptionPane.showMessageDialog(null, "Something went wrong");
         }
   }


   private void readFrame(){
         JFrame frame=new JFrame("Read the data");
         frame.setLayout(new GridLayout(list.size()+1,7,10,15));
         frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
         frame.setVisible(true);
         frame.add(new JLabel("first_name"));
         frame.add(new JLabel("last_name"));
         frame.add(new JLabel("Phone_no."));
         frame.add(new JLabel("Email_id"));
         frame.add(new JLabel("Address"));
         frame.add(new JLabel("D_O_B"));
         Collections.sort(list);
         ListIterator<Friend> it=list.listIterator();
         while(it.hasNext()){
            Friend info=it.next();
            frame.add(new JLabel(info.getFirst_name()));
            frame.add((new JLabel(info.getLast_name())));
            frame.add(new JLabel(info.getPhn_num()));
            frame.add(new JLabel(info.getEmail_id()));
            frame.add(new JLabel(info.getAddress()));
            frame.add(new JLabel(info.getD_O_B()));
         }
       frame.pack();
        frame.setLocationRelativeTo(null);

   }

}
