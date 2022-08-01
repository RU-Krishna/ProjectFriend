package org.example;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class SearchFriend {
    private JPanel SearchPanel;
    private JTextField first_name;
    private JTextField last_name;
    private JButton Search;
    private JButton Cancel;


    public SearchFriend() {
        JFrame Frame = new JFrame("Search the Friend");
        Frame.setContentPane(SearchPanel);
        Frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        Frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        Frame.setLocationRelativeTo(null);
        Frame.setVisible(true);
        Frame.getRootPane().setDefaultButton(Search);
        Search.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean flag = false;
                if (first_name.getText().equals("") || last_name.getText().equals("")) {
                    JOptionPane.showMessageDialog(Frame, "Empty Fields not allowed");
                    flag = true;
                } else {
                    for (Friend friend : Main.login.Data) {
                        var f_n = first_name.getText().replace(first_name.getText().charAt(0), Character.toUpperCase(first_name.getText().charAt(0))).trim();
                        var l_n = last_name.getText().replace(last_name.getText().charAt(0), Character.toUpperCase(last_name.getText().charAt(0))).trim();
                        if (friend.getFirst_name().equals(f_n) && friend.getLast_name().equals(l_n)) {
                            JOptionPane.showMessageDialog(Frame,
                                    "first_name = " + friend.getFirst_name() + "\n" +
                                            "last_name = " + friend.getLast_name() + "\n" +
                                            "Phone_num = " + friend.getPhn_num() + "\n" +
                                            "Email_id = " + friend.getEmail_id() + "\n" +
                                            "Address = " + friend.getAddress() + "\n" +
                                            "D_O_B = " + friend.getD_O_B());
                            first_name.setText("");
                            last_name.setText("");
                            flag = true;
                            break;

                        }

                    }
                }
                if (!flag)
                    JOptionPane.showMessageDialog(Frame, first_name.getText() + " " + last_name.getText() + " is absent");
            }
        });
        Cancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Frame.dispose();

            }
        });
    }


}
