package com.company;

import javazoom.jl.decoder.JavaLayerException;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import static com.company.Song.PlayMP3.selectTrack;
import static com.company.Queue.queueSelMenu;
import static com.company.Queue.selSong;

public class SongQueue {

    static File file = new File("songQue.txt");
    static File tempFile = new File("songQ.txt");
    static FileWriter fw;
    static FileWriter tfw;

    static {
        try {
            fw = new FileWriter(file,true);
            tfw = new FileWriter(tempFile,true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }





//
//
//    public static void playing() throws IOException {
//        while(!Queue.isEmpty()){
//            if(Queue.deQueue() == null){
//                Queue.deQueue();
//            }
//            PlayingQueue.initUI();
//        }
//
//    }



}
