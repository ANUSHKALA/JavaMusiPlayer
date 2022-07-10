package com.company;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import static com.company.Song.PlayMP3.selectTrack;
import static com.company.Song.queueSelMenu;
import static com.company.Song.selSong;

public class SongQueue {

    static File file = new File("songQue.txt");
    static FileWriter fw;

    static {
        try {
            fw = new FileWriter(file,true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static JFrame queueFrame;
    static String[] optionsToChoose = {"KhwabHoTumYa", "LikheJoKhatTujhe", "MainePuchaChandSe", "NeeleNeeleAmbarPar", "YeJoMohhabbatHai"};
    static JComboBox<String> queueSel = new JComboBox<>(optionsToChoose);

    public SongQueue() throws IOException {
    }


    public static String selectSong() {

        String track = "";

        String t1 = "KhwabHoTumYa";
        String t2 = "LikheJoKhatTujhe";
        String t3 = "MainePuchaChandSe";
        String t4 = "NeeleNeeleAmbarPar";
        String t5 = "YeJoMohhabbatHai";

        if ((queueSelMenu.getSelectedItem().toString()).equals(t1)) {
            track = "src/tracks/" + t1 + ".mp3";
            System.out.println(track);
        }
        else if((queueSelMenu.getSelectedItem().toString()).equals(t2)) {
            track = "src/tracks/" + t2 + ".mp3";
            System.out.println(track);

        }
        else if((queueSelMenu.getSelectedItem().toString()).equals(t3)){
            track = "src/tracks/" + t3 + ".mp3";
            System.out.println(track);

        }
        else if((queueSelMenu.getSelectedItem().toString()).equals(t4)) {
            track = "src/tracks/" + t4 + ".mp3";
            System.out.println(track);

        }
        else if((queueSelMenu.getSelectedItem().toString()).equals(t5)) {
            track = "src/tracks/" + t5 + ".mp3";
            System.out.println(track);

        }

        return track;
    }


    static void addToQueue(String track) throws IOException {


        track = selectSong();
        fw.write(track+"\n");

        fw.flush();

    }

    public static void playQueue() throws FileNotFoundException {
        Scanner sc = new Scanner(file);
        while (sc.hasNextLine()){
            String x = sc.nextLine();
            Queue.enQueue(x);
            Queue.display();
        }
    }



}
