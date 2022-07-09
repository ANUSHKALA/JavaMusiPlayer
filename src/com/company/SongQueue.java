package com.company;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static com.company.Song.PlayMP3.selectTrack;
import static com.company.Song.selSong;

public class SongQueue {

    static JFrame queueFrame;
    static String[] optionsToChoose = {"KhwabHoTumYa", "LikheJoKhatTujhe", "MainePuchaChandSe", "NeeleNeeleAmbarPar", "YeJoMohhabbatHai"};
    static JComboBox<String> queueSel = new JComboBox<>(optionsToChoose);





    static void addToQueue(String track){

        Queue songQueue = new Queue(20);
        System.out.println("dfgefds");

        track = selectTrack();
        songQueue.insert(track);
        songQueue.queueDisplay();

    }

}
