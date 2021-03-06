package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main {

    public static void Begin(){

        JFrame frame = new JFrame("Music Player");
        frame.setVisible(true);
        frame.setSize(300,200);
        frame.setLayout(new BorderLayout());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel mPanel = new JPanel();
        JPanel cPanel = new JPanel();
        frame.add(mPanel,BorderLayout.NORTH);
        frame.add(cPanel,BorderLayout.WEST);
        JLabel label1 = new JLabel("Welcome to the music player!");
        mPanel.add(label1);

        JPanel bPanel = new JPanel();
        frame.add(bPanel);
        frame.add(bPanel, BorderLayout.SOUTH);
        JButton b1 = new JButton("Play a song");
        bPanel.add(b1);
        JButton b2 = new JButton("Play a playlist");
        bPanel.add(b2);

        b1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                MusicPlayer.playChoice();
            }
        });
        b2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                Playlist.playListUI();
            }
        });

    }

}
