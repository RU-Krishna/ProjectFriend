package org.example;

import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CreateDatabase {


    private CreateDatabase() {  //Create Database Constructor...
        createD_B();  //Method which creates the database...
        createTable(); //Method which creates the Table in that Database...
    }


    /*
     *Enter your MYSQL database name &
     *Password for the database connection
     */
    static private String name = "Frienddata";  //Database name
    static final String conn = "jdbc:mysql://localhost:3306/" + name; //Immutable Connection String...
    //Enter your MYSQL connection username...
    static final String username = "root";        //Static Immutable Username...
    //enter your MYSQL connection password...
    static final String password = "hi@SQL22";    //Static Immutable Password...

    private void createD_B() {
        /*
         * Establishing connection with the MYSQL database
         * And Creating a friend Database for the user...
         *
         */

        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/", username, password);
             PreparedStatement statement = connection.prepareStatement("CREATE DATABASE " + name)) {
            int create = statement.executeUpdate();
            /*If the database created successfully a pane is appeared in front of the user
             * with the message "Database created"...
             */
            JOptionPane.showMessageDialog(null, "Database Created");
        } catch (SQLException e) {
            /*
             *  If there is some error while creating the Database
             *  a pane appears in front of the user with the message "Something went wrong" and
             *  the error message...
             * */

            JOptionPane.showMessageDialog(null, "Something went wrong\n" + e.getMessage());

        }
    }

    private void createTable() {
        /*
         *  Establishing the connection with the MySQL
         *  Creating a table with the name "Friend"
         *  and all the required columns...
         *
         * */
        try (Connection connection = DriverManager.getConnection(conn, username, password);
             PreparedStatement statement = connection.prepareStatement("CREATE TABLE friend(" +
                     "Id VARCHAR(10) PRIMARY KEY," + "first_name VARCHAR(15)," +
                     "last_name VARCHAR(15)," + "Phn_num VARCHAR(10)," +
                     "email_id VARCHAR(50)," + "address VARCHAR(50)," +
                     "D_O_B DATE)")) {
            int check = statement.executeUpdate();
            /*
             * If the table was created successfully
             * statement.executeUpdate returns 0
             * and Pane appears on the screen after "Database Created " message
             * with the message "Table Created Successfully"
             *
             * */
            if (check == 0)
                JOptionPane.showMessageDialog(null, "Table Created Successfully\n");

        } catch (SQLException e) {
            /*
             *  In case something went wrong the user
             *  a pane appears with the message "something went wrong"
             *  and the reason of the error...
             *
             * */
            JOptionPane.showMessageDialog(null, "Something went wrong\n");
        }
    }

    public static void main(String[] args) {
        //Constructor calling for creating a database...
        new CreateDatabase();

    }


}
