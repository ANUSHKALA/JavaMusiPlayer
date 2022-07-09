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
import java.util.Scanner;

import static com.company.Song.PlayMP3.doneButton;
import static com.company.Song.PlayMP3.selectTrack;

public class Song {

    static JFrame chooseFrame;
    static JFrame queueFrame;
    static String[] optionsToChoose = {"KhwabHoTumYa", "LikheJoKhatTujhe", "MainePuchaChandSe", "NeeleNeeleAmbarPar", "YeJoMohhabbatHai"};
    static JComboBox<String> jComboBox = new JComboBox<>(optionsToChoose);
    static JComboBox<String> queueSelMenu = new JComboBox<>(optionsToChoose);
    static MP3Player mp3Player = new MP3Player(selectTrack());
    static boolean playing = false;

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

        doneB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String a = jComboBox.getItemAt(jComboBox.getSelectedIndex());
                String selectedFruit = "Playing " + a +" !";
                queueB.setText(selectedFruit);
                PlayMP3.playSong();
                chooseFrame.dispose();
                chooseFrame = null;
                doneButton();
            }
        });

        queueB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                chooseFrame.dispose();
                PlayMP3.addToQueueFrame();
            }
        });

    }

    public class PlayMP3 {

        public static String selectTrack(){

            String track = "";

            String t1 = "KhwabHoTumYa";
            String t2 = "LikheJoKhatTujhe";
            String t3 = "MainePuchaChandSe";
            String t4 = "NeeleNeeleAmbarPar";
            String t5 = "YeJoMohhabbatHai";

            if((jComboBox.getItemAt(jComboBox.getSelectedIndex())).equals(t1)){
                track = "src/tracks/"+t1+".mp3";
                System.out.println(track);
            }
            else if((jComboBox.getItemAt(jComboBox.getSelectedIndex())).equals(t2)){
                track = "src/tracks/"+t2+".mp3";
                System.out.println(track);

            }
            else if((jComboBox.getItemAt(jComboBox.getSelectedIndex())).equals(t3)){
                track = "src/tracks/"+t3+".mp3";
                System.out.println(track);

            }
            else if((jComboBox.getItemAt(jComboBox.getSelectedIndex())).equals(t4)){
                track = "src/tracks/"+t4+".mp3";
                System.out.println(track);

            }
            else if((jComboBox.getItemAt(jComboBox.getSelectedIndex())).equals(t5)){
                track = "src/tracks/"+t5+".mp3";
                System.out.println(track);

            }

            return track;
        }


        public static void addToQueueFrame(){
            queueFrame = new JFrame("Music Player");
            queueFrame.setVisible(true);
            queueFrame.setSize(300,200);
            queueFrame.setLayout(new BorderLayout());
            queueFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


            queueSelMenu = new JComboBox<>(optionsToChoose);
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

        }

        public static void addToQue(String x){



        }




        public static void playSong() {
            mp3Player.play();
        }

        public static void doneButton(){

            JFrame frame2 = new JFrame("Music Player");
            frame2.setVisible(true);
            frame2.setSize(300,200);
            frame2.setLayout(new BorderLayout());
            frame2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            JPanel mPanel = new JPanel();
            frame2.add(mPanel,BorderLayout.CENTER);
            JLabel label1 = new JLabel(jComboBox.getItemAt(jComboBox.getSelectedIndex()));
            mPanel.add(label1);

            JPanel bPanel = new JPanel();
            frame2.add(bPanel);
            frame2.add(bPanel, BorderLayout.SOUTH);
            JButton stopB = new JButton("Stop");
            JButton playB = new JButton("Restart");
            JButton pauseB = new JButton("Play/Pause");
            bPanel.add(stopB);
            bPanel.add(playB);
            bPanel.add(pauseB);

            stopB.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    mp3Player.close();
                }
            });
            playB.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    mp3Player.play();
                }
            });
            pauseB.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    mp3Player.pause();
                }
            });
        }
    }

    static class MP3Player {
        private AdvancedPlayer player;
        private final String mp3FileToPlay;
        private Player jlPlayer;
        private AudioDevice device;
        private int position = 0;

        public MP3Player(String mp3FileToPlay) {
            this.mp3FileToPlay = mp3FileToPlay;
        }

        public void play() {
            if (playing){
                jlPlayer.close();
            }
            playing = true;
            try {
                FileInputStream fileInputStream = new FileInputStream(mp3FileToPlay);
                BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream);
                jlPlayer = new Player(bufferedInputStream);
            } catch(Exception e){
                System.out.println("Problem playing mp3 file " + mp3FileToPlay);
                System.out.println(e.getMessage());
            }

            new Thread(() -> {
                try {
                    jlPlayer.play();
                }
                 catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }).start();


        }

        public void pause(){

                int positionInMillis = device.getPosition();
                position = positionInMillis / 26;

                System.out.println("Pause : " + position);
                //thread.suspend();
                player.stop();

        }

        public void close() {

            if (jlPlayer != null) jlPlayer.close();
        }

    }

}
