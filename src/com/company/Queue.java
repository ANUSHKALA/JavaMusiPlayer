package com.company;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

import static com.company.MusicPlayer.playChoice;
import static com.company.PlayingQueue.queueUI;
import static com.company.Song.optionsToChoose;
import static com.company.SongQueue.*;

public class Queue {
    static int SIZE = 20;
    static String[] items = new String[SIZE];
    static int front, rear;
    static String selSong = "";
    static JFrame queueFrame;
    static JComboBox<String> queueSelMenu = new JComboBox<>(optionsToChoose);


    Queue() {
        front = -1;
        rear = -1;
    }

    // check if the queue is full
    static boolean isFull() {
        if (front == 0 && rear == SIZE - 1) {
            return true;
        }
        return false;
    }

    // check if the queue is empty
    static boolean isEmpty() {
        if (front == -1)
            return true;
        else
            return false;
    }

    // insert elements to the queue
    static void enQueue(String element) {

        // if queue is full
        if (isFull()) {
            System.out.println("Queue is full");
        }
        else {
            if (front == -1) {
                // mark front denote first element of queue
                front = 0;
            }

            rear++;
            // insert element at the rear
            items[rear] = element;
            System.out.println("Insert " + element);
        }
    }

    // delete element from the queue
    static String deQueue() throws IOException {
        String element;

        // if queue is empty
        if (isEmpty()) {
            System.out.println("Playing Default Song!");
            return ("src/tracks/YeJoMohhabbatHai.mp3");
        }
        else {
            // remove element from the front of queue
            element = items[front];

            // if the queue has only one element
            if (front >= rear) {
                front = -1;
                rear = -1;
            }
            else {
                // mark next element as the front
                front++;
            }
            System.out.println( element + " Deleted");


            File input_file = new File("songQ.txt");
            File temp_file = new File("songQue.txt");
            BufferedReader my_reader = new BufferedReader(new FileReader(input_file));
            BufferedWriter my_writer = new BufferedWriter(new FileWriter(temp_file));
            String lineToRemove = element;
            String current_line;
            while((current_line = my_reader.readLine()) != null) {
                String trimmedLine = current_line.trim();
                if(trimmedLine.equals(lineToRemove)) continue;
                my_writer.write(current_line + System.getProperty("line.separator"));
            }
            my_writer.close();
            my_reader.close();
            boolean is_success = temp_file.renameTo(input_file);


            return (element);
        }
    }

    // display element of the queue
    static void display() throws IOException, JavaLayerException {
        int i;
        if (isEmpty()) {
            System.out.println("Empty Queue");
        }
        else {
            // display the front of the queue
            System.out.println("\nFront index-> " + front);

            // display element of the queue
            System.out.println("Items -> ");
            for (i = front; i <= rear; i++){
                if(items[i]==null){
                    deQueue();
                }
                else{
                    System.out.println(items[i]);
                }

            }

            // display the rear of the queue
            System.out.println("\nRear index-> " + rear);
        }
    }

    public static void main(String[] args) throws IOException {

        // create an object of Queue class
        Queue q = new Queue();

        // try to delete element from the queue
        // currently queue is empty
        // so deletion is not possible
        q.deQueue();

        // insert elements to the queue
//        for(int i = 1; i < 6; i ++) {
//            q.enQueue(i);
//        }

        // 6th element can't be added to queue because queue is full
//        q.enQueue("a");
//        q.enQueue("b");
//        q.enQueue("c");
//        q.enQueue("d");
//
//        q.display();
//
//        q.enQueue("e");
//
//        // deQueue removes element entered first i.e. 1
//        q.deQueue();
//
//        // Now we have just 4 elements
//        q.display();

    }

    static void playQueue(String track) throws FileNotFoundException, JavaLayerException {


    }


    static void addToQueue(String track) throws IOException {
        track = selectSong();
        fw.write(track+"\n");
        enQueue(track);
        fw.flush();
        tfw.write(track+"\n");
        tfw.flush();
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


    public static void addToQueueFrame() {
        queueFrame = new JFrame("Music Player");
        queueFrame.setVisible(true);
        queueFrame.setSize(300, 200);
        queueFrame.setLayout(new BorderLayout());
        queueFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        queueSelMenu.setBounds(90, 50, 140, 20);

        queueFrame.add(queueSelMenu);

        JPanel bPanel = new JPanel();
        queueFrame.add(bPanel);
        queueFrame.add(bPanel, BorderLayout.SOUTH);
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

        selSong = queueSelMenu.getItemAt(queueSelMenu.getSelectedIndex());

        quePlayB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                queueFrame.dispose();
                queueUI();
                try {
                    Queue.display();
                } catch (FileNotFoundException ex) {
                    ex.printStackTrace();
                } catch (JavaLayerException ex) {
                    ex.printStackTrace();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }

//                        SongQueue.playing();
            }
        });

        backB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                queueFrame.dispose();
                playChoice();

            }
        });

        addB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

//                    queueFrame.dispose();
                try {
                    addToQueue(selSong);
                }
                catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });

        clearQB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FileWriter fwOb = null;
                FileWriter tempFwOb = null;
                try {
                    fwOb = new FileWriter(file, false);
                    tempFwOb = new FileWriter(file, false);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                PrintWriter pwOb = new PrintWriter(fwOb, false);
                PrintWriter tempPwOb = new PrintWriter(tempFwOb, false);
                pwOb.flush();
                tempPwOb.flush();
                pwOb.close();
                tempPwOb.close();
                try {
                    fwOb.close();
                    tempFwOb.close();
                }
                catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });
    }
}


class PlayingQueue{

    static JFrame playQFrame;
    static JLabel playQTrackName = new JLabel("CHOSEN SONG!!");

    static JLabel songName;

    static JLabel currentSong;

    static JPanel playerPanel;
    static JPanel controlPanel;

    static JButton next;
    static JButton queueB = new JButton("QUEUE!");

    public static void queueUI(){

        playQFrame = new JFrame("Music Player");
        playQFrame.setVisible(true);
        playQFrame.setSize(300, 200);
        playQFrame.setLayout(new BorderLayout());
        playQFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        playQTrackName.setBounds(90, 50, 140, 20);

        playQFrame.add(playQTrackName);

        JPanel bPanel = new JPanel();
        playQFrame.add(bPanel);
        playQFrame.add(bPanel, BorderLayout.SOUTH);
        JButton playB = new JButton("PLAY");
        playB.setBounds(55, 100, 200, 20);
        JButton nextB = new JButton("NEXT");
        nextB.setBounds(55, 100, 200, 20);
        JButton stopB = new JButton("STOP");
        stopB.setBounds(55, 200, 90, 10);
        bPanel.add(playB);
        bPanel.add(nextB);
        bPanel.add(stopB);




        nextB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

//                    playQFrame.dispose();
//                        System.out.println(selSong);
//                        try {
//                            addToQueue(selSong);
//                            playQueue();
//                        }
//                        catch (IOException ex) {
//                            ex.printStackTrace();
//                        }
            }
        });

        stopB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FileWriter fwOb = null;
                try {
                    fwOb = new FileWriter(file, false);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                PrintWriter pwOb = new PrintWriter(fwOb, false);
                pwOb.flush();
                pwOb.close();
                try {
                    fwOb.close();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });

    }




}