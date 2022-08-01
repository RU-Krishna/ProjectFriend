package org.example;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.Collections;

public class ReadFriend {

    private JPanel ReadPanel;
    private JTable table1;

    public ReadFriend() {
        JFrame Frame = new JFrame("Read the Data");
        Frame.setContentPane(ReadPanel);
        Frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        Frame.setLocationRelativeTo(null);
        Frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        Frame.setVisible(true);

    }


    private void createUIComponents() {
        // TODO: place custom component creation code here
        String[] strings = {"First Name", "Last Name", "Phone No.", "Email Id", "Address", "D.O.B."};
        DefaultTableModel model = new DefaultTableModel(strings, 1);
        Collections.sort(Main.login.Data);
        for (Friend friend : Main.login.Data) {
            String[] str = {friend.getFirst_name(), friend.getLast_name(), friend.getPhn_num(), friend.getEmail_id(), friend.getAddress(), friend.getD_O_B()};
            model.addRow(str);
        }
        table1 = new JTable(model);
        table1.setRowHeight(30);
        table1.setFont(new Font(Font.SANS_SERIF, Font.ITALIC, 15));
        table1.setEnabled(false);
    }
}
