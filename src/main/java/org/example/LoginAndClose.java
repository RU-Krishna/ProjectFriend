package org.example;

import javax.swing.*;
import java.sql.*;
import java.util.LinkedList;
import java.util.List;


public class LoginAndClose {

    List<Friend> Data = new LinkedList<>();

    public LoginAndClose() {
        Open();
    }

    private void Open() {
        try (Connection connection = DriverManager.getConnection(CreateDatabase.conn, CreateDatabase.username, CreateDatabase.password);
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM friend")) {
            ResultSet set = statement.executeQuery();
            while (set.next()) {
                Friend friend = new Friend();
                friend.setFirst_name(set.getString(2));
                friend.setLast_name(set.getString(3));
                friend.setPhn_num(set.getString(4));
                friend.setEmail_id(set.getString(5));
                friend.setAddress(set.getString(6));
                friend.setD_O_B(set.getDate(7).toString());
                Data.add(friend);
            }
            connection.close();
            statement.close();
            set.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    void SaveOnClose() {
        insert();
        update();
        delete();
    }

    private void insert() {
        if (!AddFriend.addQueue.isEmpty()) {
            String insertSQL = "INSERT INTO friend VALUES(?,?,?,?,?,?,?)";
            try (Connection connection = DriverManager.getConnection(CreateDatabase.conn, CreateDatabase.username, CreateDatabase.password);
                 PreparedStatement statement = connection.prepareStatement(insertSQL)) {
                while (!AddFriend.addQueue.isEmpty()) {
                    Friend friend = AddFriend.addQueue.poll();
                    statement.setString(1, friend.primary());
                    statement.setString(2, friend.getFirst_name());
                    statement.setString(3, friend.getLast_name());
                    statement.setString(4, friend.getPhn_num());
                    statement.setString(5, friend.getEmail_id());
                    statement.setString(6, friend.getAddress());
                    statement.setDate(7, Date.valueOf(friend.getD_O_B()));
                    statement.executeUpdate();
                }
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, e);
            }
        }
    }

    private void update() {
        if (!UpdateFriend.updateQueue.isEmpty()) {

            try (Connection connection = DriverManager.getConnection(CreateDatabase.conn, CreateDatabase.username, CreateDatabase.password)
            ) {
                while (!UpdateFriend.updateQueue.isEmpty()) {
                    String[] update = UpdateFriend.updateQueue.poll();
                    String updateSQL = "UPDATE friend SET " + update[1] + " = ? WHERE friend.Id = ? ";
                    PreparedStatement statement = connection.prepareStatement(updateSQL);
                    statement.setString(1, update[2]);
                    statement.setString(2, update[0]);
                    statement.executeUpdate();
                }
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, e);
            }
        }
    }

    private void delete() {
        String deleteSQL = "DELETE FROM friend WHERE friend.Id = ?";
        if (!DeleteFriend.deleteQueue.isEmpty()) {
            try (Connection connection = DriverManager.getConnection(CreateDatabase.conn, CreateDatabase.username, CreateDatabase.password);
                 PreparedStatement statement = connection.prepareStatement(deleteSQL)) {
                while (!DeleteFriend.deleteQueue.isEmpty()) {
                    var del = DeleteFriend.deleteQueue.poll();
                    statement.setString(1, del);
                    statement.executeUpdate();
                }
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, e);
            }
        }

    }

}
