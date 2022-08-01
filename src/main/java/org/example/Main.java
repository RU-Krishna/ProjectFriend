package org.example;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Main {

    private JLabel Add;
    private JLabel Update;
    private JLabel Search;
    private JLabel Delete;
    private JLabel Read;
    private JLabel Exit;
    private JPanel WelcomePanel;
    static LoginAndClose login;

    public Main() {
        JFrame frame = new JFrame("Welcome Page");
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setContentPane(WelcomePanel);
        frame.setVisible(true);

        Add.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                new AddFriend();
            }
        });
        Update.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                new UpdateFriend();
            }
        });
        Search.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                new SearchFriend();
            }
        });
        Delete.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                new DeleteFriend();
            }
        });
        Read.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                new ReadFriend();
            }
        });
        Exit.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                login.SaveOnClose();
                System.exit(0);
            }
        });
    }

    public static void main(String[] args) {
        login = new LoginAndClose();
        new Main();
    }


}

