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
            String str="INSERT INTO friend VALUES('"+friend.primary()+"','"+friend.getFirst_name()+"','"+friend.getLast_name()+"','"+friend.getPhn_num()+"','"+friend.getEmail_id()+"','"+friend.getAddress()+"','"+Date.valueOf(friend.getD_O_B())+"');";
            try (Connection con= DriverManager.getConnection(conn,username,password);
                 Statement stat=con.createStatement()){
                 stat.executeUpdate(str);

            }
            catch(SQLException e){
                JOptionPane.showMessageDialog(null,"Something went wrong try again"+e);
            }

        }

}
