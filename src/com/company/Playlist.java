package com.company;

import javazoom.jl.decoder.JavaLayerException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import static com.company.MusicPlayer.playChoice;
import static com.company.PlayingQueue.queueUI;
import static com.company.SongQueue.file;

public class Playlist {

    static JFrame playlistFrame;
    static JTextField makePL = new JTextField();

    public static void  playListUI(){

        playlistFrame = new JFrame("Music Player");
        playlistFrame.setVisible(true);
        playlistFrame.setSize(300, 200);
        playlistFrame.setLayout(new BorderLayout());
        playlistFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        makePL.setBounds(90, 50, 140, 20);

        playlistFrame.add(makePL);

        JPanel bPanel = new JPanel();
        playlistFrame.add(bPanel);
        playlistFrame.add(bPanel, BorderLayout.SOUTH);
        JButton addB = new JButton("ADD");
        addB.setBounds(55, 100, 200, 20);
        JButton quePlayB = new JButton("Play");
        quePlayB.setBounds(55, 200, 90, 10);
        JButton backB = new JButton("Go Back");
        backB.setBounds(55, 150, 90, 10);
        JButton clearQB = new JButton("Clear");
        clearQB.setBounds(55, 250, 90, 10);
        bPanel.add(addB);
        bPanel.add(quePlayB);
        bPanel.add(backB);
        bPanel.add(clearQB);


        quePlayB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                playlistFrame.dispose();
                queueUI();
                try {
                    PlayingQueue.playQueue();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                try {
                    Queue.display();
                } catch (FileNotFoundException ex) {
                    ex.printStackTrace();
                } catch (JavaLayerException ex) {
                    ex.printStackTrace();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
        });

        backB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                playlistFrame.dispose();
                playChoice();
            }
        });

//        addB.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//
////                    playlistFrame.dispose();
//                try {
//                    addToQueue(selSong);
//                }
//                catch (IOException ex) {
//                    ex.printStackTrace();
//                }
//            }
//        });

//        clearQB.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                FileWriter fwOb = null;
//                FileWriter tempFwOb = null;
//                try {
//                    fwOb = new FileWriter(file, false);
//                    tempFwOb = new FileWriter(file, false);
//                } catch (IOException ex) {
//                    ex.printStackTrace();
//                }
//                PrintWriter pwOb = new PrintWriter(fwOb, false);
//                PrintWriter tempPwOb = new PrintWriter(tempFwOb, false);
//                pwOb.flush();
//                tempPwOb.flush();
//                pwOb.close();
//                tempPwOb.close();
//                try {
//                    fwOb.close();
//                    tempFwOb.close();
//                }
//                catch (IOException ex) {
//                    ex.printStackTrace();
//                }
//            }
//        });
//    }


    }


}
