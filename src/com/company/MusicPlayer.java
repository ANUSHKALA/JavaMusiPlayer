
package com.company;

import javazoom.jl.player.Player;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;


public class MusicPlayer implements ActionListener {

    //Stopping thread
    boolean started = false;

    //Creating Frame
    JFrame frame;

    //Creating Label for printing the selected song name
    JLabel songName;

    //Creating button for selecting a song
    JButton select;

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

    public MusicPlayer() {

        //Calling initUI() method to initiliaze UI
        initUI();
        //Calling addActionEvents() methods to add actions
        addActionEvents();
        //Calling Threads
        playThread = new Thread(runnablePlay);
        resumeThread = new Thread(runnableResume);

    }

    public void initUI() {

        //Setting songName Label to center
        songName = new JLabel("", SwingConstants.CENTER);

        //Creating button for selecting a song
        select = new JButton("Select Mp3");

        //Creating Panels
        playerPanel = new JPanel(); //Music Selection Panel
        controlPanel = new JPanel(); //Control Selection Panel

        //Creating icons for buttons
        iconPlay = new ImageIcon("src/images/play.png");
        iconPause = new ImageIcon("src/images/pause.png");
        iconResume = new ImageIcon("src/images/resume.png");
        iconStop = new ImageIcon("src/images/stop.png");

        //Creating image buttons
        play = new JButton("Play");
        pause = new JButton("Pause");
        resume = new JButton("Resume");
        stop = new JButton("Stop");

        //Setting Layout of PlayerPanel
        playerPanel.setLayout(new GridLayout(2, 1));

        //Addings components in PlayerPanel
        playerPanel.add(select);
        playerPanel.add(songName);

        //Setting Layout of ControlPanel
        controlPanel.setLayout(new GridLayout(1, 4));

        //Addings components in ControlPanel
        controlPanel.add(play);
        controlPanel.add(pause);
        controlPanel.add(resume);
        controlPanel.add(stop);
        controlPanel.add(queueB);

        //Setting buttons background color
        play.setBackground(Color.WHITE);
        pause.setBackground(Color.WHITE);
        resume.setBackground(Color.WHITE);
        stop.setBackground(Color.WHITE);

        //Initialing the frame
        frame = new JFrame();

        //Setting Frame's Title
        frame.setTitle("MUSIC PLAYER");

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
        select.addActionListener(this);
        play.addActionListener(this);
        pause.addActionListener(this);
        resume.addActionListener(this);
        stop.addActionListener(this);
        queueB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                frame.dispose();
                Queue.addToQueueFrame();
            }
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(select)) {
            fileChooser = new JFileChooser();
            fileChooser.setCurrentDirectory(new File("C:\\Users"));
            fileChooser.setDialogTitle("Select Mp3");
            fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
            fileChooser.setFileFilter(new FileNameExtensionFilter("Mp3 files", "mp3"));
            if (fileChooser.showOpenDialog(select) == JFileChooser.APPROVE_OPTION) {
                myFile = fileChooser.getSelectedFile();
                filename = fileChooser.getSelectedFile().getName();
                filePath = fileChooser.getSelectedFile().getPath();
                songName.setText("File Selected : " + filename);
            }
        }
        if (e.getSource().equals(play)) {
            //starting play thread
            if (filename != null) {
                if(started){
                    playThread.stop();
                }
                started = true;
                playThread.start();
                songName.setText("Now playing : " + filename);
            } else {
                songName.setText("No File was selected!");
            }
        }
        if (e.getSource().equals(pause)) {
            if (player != null && filename != null) {
                try {
                    pauseLength = fileInputStream.available();
                    player.close();
                }
                catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        }

        if (e.getSource().equals(resume)) {
            if (filename != null) {
                resumeThread.start();
            } else {
                songName.setText("No File was selected!");
            }
        }
        if (e.getSource().equals(stop)) {
            if (player != null) {
                playThread.stop();
                player.close();
                songName.setText("");
            }
        }
    }

    Runnable runnablePlay = new Runnable() {
        @Override
        public void run() {
            try {
                fileInputStream = new FileInputStream(myFile);
                bufferedInputStream = new BufferedInputStream(fileInputStream);
                player = new Player(bufferedInputStream);
                totalLength = fileInputStream.available();
                player.play();//starting music
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
    };

    Runnable runnableResume = new Runnable() {
        @Override
        public void run() {
            try {
                fileInputStream = new FileInputStream(myFile);
                bufferedInputStream = new BufferedInputStream(fileInputStream);
                player = new Player(bufferedInputStream);
                fileInputStream.skip(totalLength - pauseLength);
                player.play();
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
    };

    public static void playChoice() {
        MusicPlayer mp = new MusicPlayer();
    }
}