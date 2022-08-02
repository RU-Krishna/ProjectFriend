package org.example;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.Objects;
import java.util.Queue;
import java.util.regex.Pattern;

public class UpdateFriend {
    private JTextField first_name;
    private JTextField last_name;
    private JComboBox<String> comboBox;
    private JTextField updated_Value;
    private JButton updateButton;
    private JButton cancelButton;
    private JPanel Panel;
    static Queue<String[]> updateQueue = new LinkedList<>();

    private void createUIComponents() {
        // TODO: place custom component creation code here
        comboBox = new JComboBox<>();
        comboBox.addItem("Phn_num");
        comboBox.addItem("Address");
        comboBox.addItem("Email_id");
    }

    public UpdateFriend() {
        JFrame frame = new JFrame("Update");
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        frame.add(Panel);
        frame.getRootPane().setDefaultButton(updateButton);
        frame.setVisible(true);

        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (first_name.getText().isEmpty() || last_name.getText().isEmpty() || updated_Value.getText().isEmpty())
                    JOptionPane.showMessageDialog(frame, "Empty fields not allowed");
                else
                    Update();
            }
        });
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
            }
        });

    }


    private void Update() {
        boolean flag = false;
        var op = Objects.requireNonNull(comboBox.getSelectedItem()).toString();
        var f_n = first_name.getText().replace(first_name.getText().charAt(0), Character.toUpperCase(first_name.getText().charAt(0))).trim();
        var l_n = last_name.getText().replace(last_name.getText().charAt(0), Character.toUpperCase(last_name.getText().charAt(0))).trim();
        for (Friend friend : Main.login.Data) {
            if (f_n.equals(friend.getFirst_name()) && l_n.equals(friend.getLast_name())) {
                flag=true;
                if (validate()) {
                    Main.login.Data.remove(friend);
                    String[] updated = {friend.primary(), op, updated_Value.getText().trim()};
                    updateQueue.add(updated);
                    if (op.equals("Phn_num"))
                        friend.setPhn_num(updated_Value.getText().trim());
                    else if (op.equals("Address"))
                        friend.setAddress(updated_Value.getText().trim());
                    else
                        friend.setEmail_id(updated_Value.getText().trim());
                    Main.login.Data.add(friend);
                    first_name.setText("");
                    last_name.setText("");
                    updated_Value.setText("");
                    JOptionPane.showMessageDialog(null,friend.getFirst_name()+" "+friend.getLast_name()+" is updated");
                    break;
                } else {
                    JOptionPane.showMessageDialog(null, "Invalid Updated Value");
                }
            }

        }
        if (!flag)
            JOptionPane.showMessageDialog(null, "Friend Absent");
    }

    private boolean validate() {
        var item = Objects.requireNonNull(comboBox.getSelectedItem()).toString();
        var Phn_pattern = "\\d{10}";
        var Address_pattern = "[\\w.,-_\\s]+";
        var Email_patten = "^\\w+@[a-z]+\\.[a-z]+$";
        if (item.equals("Phn_num"))
            return Pattern.compile(Phn_pattern).matcher(updated_Value.getText().trim()).matches();
        else if (item.equals("Address"))
            return Pattern.compile(Address_pattern).matcher(updated_Value.getText().trim()).matches();
        else
            return Pattern.compile(Email_patten).matcher(updated_Value.getText().trim()).matches();
    }

}
