package com.company;

import javazoom.jl.player.AudioDevice;
import javazoom.jl.player.Player;
import javazoom.jl.player.advanced.AdvancedPlayer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;

import static com.company.SongQueue.addToQueue;
import static com.company.SongQueue.playQueue;
//import static com.company.SongQueue.stop;

public class Song {

    static JFrame chooseFrame;
    static JFrame queueFrame;
    static String[] optionsToChoose = {"KhwabHoTumYa", "LikheJoKhatTujhe", "MainePuchaChandSe", "NeeleNeeleAmbarPar", "YeJoMohhabbatHai"};
    static JComboBox<String> jComboBox = new JComboBox<>(optionsToChoose);
    static JComboBox<String> queueSelMenu = new JComboBox<>(optionsToChoose);
    static String selSong = "";
//    static boolean playing = false;

    public static void chooseSong() {

        chooseFrame = new JFrame("Choose a song!");


        jComboBox.setBounds(80, 50, 140, 20);

        JButton doneB = new JButton("Play");
        doneB.setBounds(100, 100, 90, 20);

        JButton queueB = new JButton("ADD SONGS TO QUEUE!");
        queueB.setBounds(55, 150, 200, 20);

        chooseFrame.add(doneB);
        chooseFrame.add(jComboBox);
        chooseFrame.add(queueB);

        chooseFrame.setLayout(null);
        chooseFrame.setSize(350, 250);
        chooseFrame.setVisible(true);



        queueB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                chooseFrame.dispose();
                PlayMP3.addToQueueFrame();
            }
        });

    }

    public class PlayMP3 {

        public static String selectTrack() {

            String track = "";

            String t1 = "KhwabHoTumYa";
            String t2 = "LikheJoKhatTujhe";
            String t3 = "MainePuchaChandSe";
            String t4 = "NeeleNeeleAmbarPar";
            String t5 = "YeJoMohhabbatHai";

            if ((jComboBox.getSelectedItem().toString()).equals(t1)) {
                track = "src/tracks/" + t1 + ".mp3";
                System.out.println(track);
            }
            else if((jComboBox.getSelectedItem().toString()).equals(t2)) {
                track = "src/tracks/" + t2 + ".mp3";
                System.out.println(track);

            }
            else if((jComboBox.getSelectedItem().toString()).equals(t3)){
                track = "src/tracks/" + t3 + ".mp3";
                System.out.println(track);

            }
            else if((jComboBox.getSelectedItem().toString()).equals(t4)) {
                track = "src/tracks/" + t4 + ".mp3";
                System.out.println(track);

            }
            else if((jComboBox.getSelectedItem().toString()).equals(t5)) {
                track = "src/tracks/" + t5 + ".mp3";
                System.out.println(track);

            }

            return track;
        }


        public static void addToQueueFrame() {
            queueFrame = new JFrame("Music Player");
            queueFrame.setVisible(true);
            queueFrame.setSize(300, 200);
            queueFrame.setLayout(new BorderLayout());
            queueFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


            queueSelMenu.setBounds(80, 50, 140, 20);

            queueFrame.add(queueSelMenu);

            JPanel bPanel = new JPanel();
            queueFrame.add(bPanel);
            queueFrame.add(bPanel, BorderLayout.SOUTH);
            JButton addB = new JButton("ADD");
            addB.setBounds(55, 100, 200, 20);
            JButton backB = new JButton("Go Back");
            backB.setBounds(55, 150, 90, 10);
            bPanel.add(addB);
            bPanel.add(backB);

            selSong = queueSelMenu.getItemAt(queueSelMenu.getSelectedIndex());

            addB.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {

//                    queueFrame.dispose();
                    System.out.println(selSong);
                    try {
                        addToQueue(selSong);
                        playQueue();

                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }

                }
            });

        }

//        public static void addToQue(String x){
//
//
//
//        }


    }

}
