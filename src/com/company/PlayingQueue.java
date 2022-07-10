package com.company;

import javazoom.jl.player.Player;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;

public class PlayingQueue {

    JFrame frame;

    //Creating Label for printing the selected song name
    JLabel songName;

    //Creating button for selecting a song
    JLabel currentSong;

    //Creating Panels
    JPanel playerPanel, controlPanel;

    //Creating icons for buttons
    Icon iconPlay, iconPause, iconResume, iconStop;

    //Creating buttons
    JButton play, pause, resume, stop;
    JButton queueB = new JButton("QUEUE!");


    //Creating FileChooser for choosing the music mp3 file
    JFileChooser fileChooser;
    FileInputStream fileInputStream;
    BufferedInputStream bufferedInputStream;
    File myFile = null;
    String filename, filePath;
    long totalLength, pauseLength;
    Player player;
    Thread playThread, resumeThread;

    public void initUI() {

        //Setting songName Label to center
        songName = new JLabel("", SwingConstants.CENTER);

        //Creating button for selecting a song
        currentSong = new JLabel("Select Mp3");

        //Creating Panels
        playerPanel = new JPanel(); //Music Selection Panel
        controlPanel = new JPanel(); //Control Selection Panel

        //Creating icons for buttons
//        iconPlay = new ImageIcon("src/images/play.png");
        iconPause = new ImageIcon("src/images/pause.png");
        iconResume = new ImageIcon("src/images/resume.png");
        iconStop = new ImageIcon("src/images/stop.png");

        //Creating image buttons
//        play = new JButton("Play");
        pause = new JButton("Pause");
        resume = new JButton("Resume");
        stop = new JButton("Stop");

        //Setting Layout of PlayerPanel
        playerPanel.setLayout(new GridLayout(2, 1));

        //Addings components in PlayerPanel
        playerPanel.add(currentSong);
        playerPanel.add(songName);

        //Setting Layout of ControlPanel
        controlPanel.setLayout(new GridLayout(1, 4));

        //Addings components in ControlPanel
//        controlPanel.add(play);
        controlPanel.add(pause);
        controlPanel.add(resume);
        controlPanel.add(stop);
        controlPanel.add(queueB);

        //Setting buttons background color
//        play.setBackground(Color.WHITE);
        pause.setBackground(Color.WHITE);
        resume.setBackground(Color.WHITE);
        stop.setBackground(Color.WHITE);

        //Initialing the frame
        frame = new JFrame();

        //Setting Frame's Title
        frame.setTitle("Music Player");

        //Adding panels in Frame
        frame.add(playerPanel, BorderLayout.NORTH);
        frame.add(controlPanel, BorderLayout.SOUTH);

        //Setting Frame background color
        frame.setBackground(Color.white);
        frame.setSize(500, 250);
        frame.setVisible(true);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    public void addActionEvents() {
        //registering action listener to buttons
        play.addActionListener((ActionListener) this);
        pause.addActionListener((ActionListener) this);
        resume.addActionListener((ActionListener) this);
        stop.addActionListener((ActionListener) this);
        queueB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                frame.dispose();
                Song.PlayMP3.addToQueueFrame();
            }
        });
    }


}
