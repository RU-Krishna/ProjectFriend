package org.example;

import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.sql.*;

public class frienddata {
        final String conn = "jdbc:mysql://localhost:3306/Frienddata";
        final String username="root";
        final String password = "hi@SQL22";

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
