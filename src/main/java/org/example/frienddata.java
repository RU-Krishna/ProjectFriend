package org.example;

import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.sql.*;

public class frienddata {
        final String conn = "jdbc:mysql://localhost:3306/Frienddata";
        final String username="root";
        final String password = "hi@SQL22";

        /*
        *   Inserting a new friend into the MySQL database...
        *   Establishing a connection with the mysql database in the try with resources...
        *   Inserting a friend into the database with 'executeUpdate' method...
        *   In case if we met with a "SQLException" A messageDialogBox comes in front of us with the
        *   message :- "Something went Wrong"...
        * */

        public void insert(@NotNull Friend friend){
            try (Connection con= DriverManager.getConnection(conn,username,password);
                 PreparedStatement stat=con.prepareStatement("INSERT INTO friend VALUES (?,?,?,?,?,?,?)")){
                stat.setString(1,friend.primary());
                stat.setString(2,friend.getFirst_name());
                stat.setString(3,friend.getLast_name());
                stat.setString(4,friend.getPhn_num());
                stat.setString(5,friend.getEmail_id());
                stat.setString(6,friend.getAddress());
                stat.setDate(7,Date.valueOf(friend.getD_O_B()));
                 stat.executeUpdate();

            }
            catch(SQLException e){
                JOptionPane.showMessageDialog(null,"Something went wrong try again"+e);
            }

        }

}
