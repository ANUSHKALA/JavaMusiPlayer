package com.company;

import javazoom.jl.player.Player;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.util.Scanner;

public class Song {

    public static void chooseSong() {
        String[] optionsToChoose = {"Apple", "Orange", "Banana", "Pineapple", "None of the listed"};

        JFrame jFrame = new JFrame("Choose a song!");


        JComboBox<String> jComboBox = new JComboBox<>(optionsToChoose);
        jComboBox.setBounds(80, 50, 140, 20);

        JButton jButton = new JButton("Done");
        jButton.setBounds(100, 100, 90, 20);

        JLabel jLabel = new JLabel("CHOOSE A SONG!");
        jLabel.setBounds(90, 120, 400, 100);

        jFrame.add(jButton);
        jFrame.add(jComboBox);
        jFrame.add(jLabel);

        jFrame.setLayout(null);
        jFrame.setSize(350, 250);
        jFrame.setVisible(true);

        jButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedFruit = "Playing " + jComboBox.getItemAt(jComboBox.getSelectedIndex())+" !";
                jLabel.setText(selectedFruit);
            }
        });

    }

    public class PlayMP3 {
        public static void playSong() {

            String filename = "M1.mp3";
            MP3Player mp3Player = new MP3Player(filename);
            mp3Player.play();

            Scanner sc = new Scanner(System.in);

            System.out.println("Write stop to stop the music: ");

            if (sc.nextLine().equalsIgnoreCase("stop")) {
                mp3Player.close();
            }

        }
    }

    static class MP3Player {
        private final String mp3FileToPlay;
        private Player jlPlayer;

        public MP3Player(String mp3FileToPlay) {
            this.mp3FileToPlay = mp3FileToPlay;
        }

        public void play() {
            try {
                FileInputStream fileInputStream = new FileInputStream(mp3FileToPlay);
                BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream);
                jlPlayer = new Player(bufferedInputStream);
            } catch (Exception e) {
                System.out.println("Problem playing mp3 file " + mp3FileToPlay);
                System.out.println(e.getMessage());
            }

            new Thread(() -> {
                try {
                    jlPlayer.play();
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }).start();


        }

        public void close() {
            if (jlPlayer != null) jlPlayer.close();
        }
    }

}
