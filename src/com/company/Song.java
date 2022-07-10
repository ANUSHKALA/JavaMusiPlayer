package com.company;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.AudioDevice;
import javazoom.jl.player.Player;
import javazoom.jl.player.advanced.AdvancedPlayer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.util.Scanner;

import static com.company.MusicPlayer.playChoice;
import static com.company.PlayingQueue.queueUI;
import static com.company.SongQueue.*;
//import static com.company.SongQueue.stop;

public class Song {

    static JFrame chooseFrame;
    static String[] optionsToChoose = {"KhwabHoTumYa", "LikheJoKhatTujhe", "MainePuchaChandSe", "NeeleNeeleAmbarPar", "YeJoMohhabbatHai"};
    static JComboBox<String> jComboBox = new JComboBox<>(optionsToChoose);
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
                Queue.addToQueueFrame();
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
            } else if ((jComboBox.getSelectedItem().toString()).equals(t2)) {
                track = "src/tracks/" + t2 + ".mp3";
                System.out.println(track);

            } else if ((jComboBox.getSelectedItem().toString()).equals(t3)) {
                track = "src/tracks/" + t3 + ".mp3";
                System.out.println(track);

            } else if ((jComboBox.getSelectedItem().toString()).equals(t4)) {
                track = "src/tracks/" + t4 + ".mp3";
                System.out.println(track);

            } else if ((jComboBox.getSelectedItem().toString()).equals(t5)) {
                track = "src/tracks/" + t5 + ".mp3";
                System.out.println(track);

            }

            return track;
        }


    }
}
