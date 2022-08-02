package org.example;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.Queue;
import java.util.regex.Pattern;

public class AddFriend {
    private JTextField first_name;
    private JTextField last_name;
    private JTextField Phn_num;
    private JTextField Email_id;
    private JTextField Address;
    private JTextField D_O_B;
    private JButton addButton;
    private JButton cancelButton;
    private JPanel AddPanel;

    static Queue<Friend> addQueue = new LinkedList<>();

    public AddFriend() {
        JFrame frame = new JFrame("Add the Friend");
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        frame.setContentPane(AddPanel);
        frame.getRootPane().setDefaultButton(addButton);
        frame.setVisible(true);


        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (first_name.getText().isEmpty() || last_name.getText().isEmpty() || Phn_num.getText().isEmpty() || Email_id.getText().isEmpty() || Address.getText().isEmpty() || D_O_B.getText().isEmpty())
                    JOptionPane.showMessageDialog(frame, "Empty Fields not allowed");
                else {
                    boolean check = validate();
                    if (check) {
                        var f_n = first_name.getText().replace(first_name.getText().charAt(0), Character.toUpperCase(first_name.getText().charAt(0))).trim();
                        var l_n = last_name.getText().replace(last_name.getText().charAt(0), Character.toUpperCase(last_name.getText().charAt(0))).trim();
                        var friend = new Friend();
                        friend.setFirst_name(f_n);
                        friend.setLast_name(l_n);
                        friend.setPhn_num(Phn_num.getText().trim());
                        friend.setEmail_id(Email_id.getText().trim());
                        friend.setAddress(Address.getText().trim());
                        friend.setD_O_B(D_O_B.getText().replace('/', '-').trim());
                        boolean find = check(friend);
                        if (!find) {
                            addQueue.add(friend);
                            JOptionPane.showMessageDialog(frame, addQueue.element());
                        } else
                            JOptionPane.showMessageDialog(frame, "Friend Already Present");
                        first_name.setText("");
                        last_name.setText("");
                        Phn_num.setText("");
                        Email_id.setText("");
                        Address.setText("");
                        D_O_B.setText("");
                        Main.login.Data.add(friend);
                    }
                }

            }
        });
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();

            }
        });

    }

    private boolean validate() {
        String Email_patten = "^\\w+@[a-z]+\\.[a-z]+$";
        String Phn_pattern = "\\d{10}";
        String Address_pattern = "[\\w.,-_\\s]+";
        String D_O_B_pattern = "(\\d{4})/(\\d{2})/(\\d{2})";
        var email_pattern = Pattern.compile(Email_patten).matcher(Email_id.getText().trim());
        var phn_pattern = Pattern.compile(Phn_pattern).matcher(Phn_num.getText().trim());
        var address_pattern = Pattern.compile(Address_pattern).matcher(Address.getText().trim());
        var d_o_b_pattern = Pattern.compile(D_O_B_pattern).matcher(D_O_B.getText().trim());
            var year = Integer.parseInt(D_O_B.getText().trim().substring(0,4));
            var month = Integer.parseInt(D_O_B.getText().trim().substring(5,7));
            var day = Integer.parseInt(D_O_B.getText().trim().substring(8,10));
        if (!(email_pattern.matches() && phn_pattern.matches() && address_pattern.matches() && validateD_O_B(year, month, day) && d_o_b_pattern.matches())) {
            JOptionPane.showMessageDialog(null, "Invalid Email or Phone num or Address or D.O.B.");
            return false;
        }

        return true;
    }


    private boolean check(Friend friend) {
        for (Friend friend1 : Main.login.Data) {
            if (friend.primary().equals(friend1.primary()))
                return true;
        }
        return false;
    }


    private boolean validateD_O_B(int year, int month, int day) {
        boolean flag=false;
        switch (month) {
            case 1:
            case 3:
            case 5:
            case 7:
            case 8:
            case 10:
            case 12:
                if (day > 0 && day < 32)
                    flag=true;
                break;
            case 2:
                if (year % 4 == 0) {
                    if (day > 0 && day < 30)
                        flag=true;
                }
                else
                    if (day > 0 && day < 29)
                        flag= true;
                break;
            case 4:
            case 6:
            case 9:
            case 11:
                if (day > 0 && day < 31)
                    flag= true;
                 break;
        }
        return flag;
    }
}

