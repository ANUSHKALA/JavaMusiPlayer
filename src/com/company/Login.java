package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import static com.company.MusicPlayer.playChoice;

class LoginFrame extends JFrame implements ActionListener {

    Container container = getContentPane();
    JLabel userLabel = new JLabel("USERNAME");
    JLabel passwordLabel = new JLabel("PASSWORD");
    JTextField userTextField = new JTextField();
    JPasswordField passwordField = new JPasswordField();
    JButton loginButton = new JButton("LOGIN");
    JButton resetButton = new JButton("RESET");
    JCheckBox showPassword = new JCheckBox("Show Password");
    static HashMap<String, String> passBook = new HashMap<>() ;

    public static void main(String[] args){

    }

    LoginFrame() throws FileNotFoundException {
        setLayoutManager();
        setLocationAndSize();
        addComponentsToContainer();
        addActionEvent();

    }



    static File loginFile = new File("namesAndPasswords.csv");
    static Scanner csvSc;

    static {
        try {
            csvSc = new Scanner(loginFile);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void setLayoutManager() {
        container.setLayout(null);
    }

    public void setLocationAndSize() {
        userLabel.setBounds(50, 150, 100, 30);
        passwordLabel.setBounds(50, 220, 100, 30);
        userTextField.setBounds(150, 150, 150, 30);
        passwordField.setBounds(150, 220, 150, 30);
        showPassword.setBounds(150, 250, 150, 30);
        loginButton.setBounds(50, 300, 100, 30);
        resetButton.setBounds(200, 300, 100, 30);


    }

    public void addComponentsToContainer() {
        container.add(userLabel);
        container.add(passwordLabel);
        container.add(userTextField);
        container.add(passwordField);
        container.add(showPassword);
        container.add(loginButton);
        container.add(resetButton);
    }

    public void addActionEvent() {



        loginButton.addActionListener(this);
        resetButton.addActionListener(this);
        showPassword.addActionListener(this);



    }


    @Override
    public void actionPerformed(ActionEvent e) {


        String line;
        String name;
        String password;


        while(csvSc.hasNextLine()) {
            line = csvSc.nextLine();
            name = line.split(",")[0];
            password = line.split(",")[1];
            passBook.put(name,password);


        }

        passBook.put("megha","megha");
        passBook.put("aiswu","aiswu");
        System.out.println(passBook);

        //Coding Part of LOGIN button
        if (e.getSource() == loginButton) {
            String userText;
            String pwdText;
            userText = userTextField.getText();
            pwdText = passwordField.getText();


            if (passBook.containsKey(userText)) {
                if(passBook.get(userText).equals(pwdText)){
                    System.out.println("success");
                    JOptionPane.showMessageDialog(this, "Login Successful");
                    playChoice();
                    LoginFrame.this.dispose();
                }

            }
            else {
                System.out.println("fail");
                    JOptionPane.showMessageDialog(this, "Invalid Username or Password");
            }




        }
        //Coding Part of RESET button
        if (e.getSource() == resetButton) {
            userTextField.setText("");
            passwordField.setText("");
        }
        //Coding Part of showPassword JCheckBox
        if (e.getSource() == showPassword) {
            if (showPassword.isSelected()) {
                passwordField.setEchoChar((char) 0);
            } else {
                passwordField.setEchoChar('*');
            }


        }
    }

}

public class Login {
    public static void main(String[] a) throws FileNotFoundException {


        LoginFrame frame = new LoginFrame();
        frame.setTitle("Login Form");
        frame.setVisible(true);
        frame.setBounds(10, 10, 370, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);

    }

}