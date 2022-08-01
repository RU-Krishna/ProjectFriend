package org.example;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.Queue;

public class DeleteFriend {
    private JTextField first_text;
    private JTextField last_text;
    private JButton deleteButton;
    private JButton cancelButton;
    private JPanel deletePanel;
    static Queue<String> deleteQueue = new LinkedList<>();

    public DeleteFriend() {
        JFrame frame = new JFrame("Delete");
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.getRootPane().setDefaultButton(deleteButton);
        frame.setContentPane(deletePanel);
        frame.setVisible(true);

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (first_text.getText().isEmpty() || last_text.getText().isEmpty())
                    JOptionPane.showMessageDialog(frame, "Empty Fields not allowed");
                else
                    delete();
            }
        });
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();

            }
        });
    }

    private void delete() {
        boolean flag = true;
        var f_n = first_text.getText().replace(first_text.getText().charAt(0), Character.toUpperCase(first_text.getText().charAt(0))).trim();
        var l_n = last_text.getText().replace(last_text.getText().charAt(0), Character.toUpperCase(last_text.getText().charAt(0))).trim();
        for (Friend friend : Main.login.Data) {
            if (f_n.equals(friend.getFirst_name()) && l_n.equals(friend.getLast_name())) {
                deleteQueue.add(friend.primary());
                Main.login.Data.remove(friend);
                first_text.setText("");
                last_text.setText("");
                flag = false;
                break;
            }
        }
        if (flag)
            JOptionPane.showMessageDialog(null, "Friend Absent");

    }


}
