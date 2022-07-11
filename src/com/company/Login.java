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
//UI Part here we make a file called login file which has names and password
import static com.company.MusicPlayer.playChoice;

class LoginFrame extends JFrame implements ActionListener {
// login frame is a frame, it has a container, 2 labels, 1 text field, 1 password field, 1 button, 1 check box, these are java x components part of string, string is the user interface we are using
    Container container = getContentPane();
    JLabel userLabel = new JLabel("USERNAME");
    JLabel passwordLabel = new JLabel("PASSWORD");
    JTextField userTextField = new JTextField();
    JPasswordField passwordField = new JPasswordField();
    JButton loginButton = new JButton("LOGIN");
    JButton resetButton = new JButton("RESET");
    JCheckBox showPassword = new JCheckBox("Show Password");
    static HashMap<String, String> passBook = new HashMap<>() ;
// above we define a hashmap, we will put the values in hashmap later
    public static void main(String[] args){

    }

    LoginFrame() throws FileNotFoundException {
        setLayoutManager();
        setLocationAndSize();
        addComponentsToContainer();
        addActionEvent();

    }



    static File loginFile = new File("namesAndPasswords.csv");// login file
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
// we use set bounds..set bounds help us to place the elements in the right place
    public void setLocationAndSize() {
        userLabel.setBounds(50, 150, 100, 30);
        passwordLabel.setBounds(50, 220, 100, 30);
        userTextField.setBounds(150, 150, 150, 30);
        passwordField.setBounds(150, 220, 150, 30);
        showPassword.setBounds(150, 250, 150, 30);
        loginButton.setBounds(50, 300, 100, 30);
        resetButton.setBounds(200, 300, 100, 30);


    }
//add components to the container, we define these components, we add them to container
    public void addComponentsToContainer() {
        container.add(userLabel);
        container.add(passwordLabel);
        container.add(userTextField);
        container.add(passwordField);
        container.add(showPassword);
        container.add(loginButton);
        container.add(resetButton);
    }
// there are 3 buttons..here it add the action to the component(button)
    public void addActionEvent() {

        loginButton.addActionListener(this);
        resetButton.addActionListener(this);
        showPassword.addActionListener(this);

    }


    @Override
    public void actionPerformed(ActionEvent e) {
// we define line. name and password, we parse through csv file
        String line;
        String name;
        String password;
// splitting the line by ,has next line..if there is next line it will show true vice versa, now hv to initiate it
        while(csvSc.hasNextLine()) {
            line = csvSc.nextLine();
            name = line.split(",")[0];
            password = line.split(",")[1];
            passBook.put(name,password);
        }
// splitting the line by comma, username index 0 and password 1, forms array of these 2 elements 0 is 1st element and 1 is 2nd, name is 1st element and password is 2nd
        passBook.put("megha","megha");
        passBook.put("a","a");
        System.out.println(passBook);

        //Coding Part of LOGIN button, usertext and password text gets text from usertext field and to password  text from password text field
        if (e.getSource() == loginButton) {
            String userText;
            String pwdText;
            userText = userTextField.getText();
            pwdText = passwordField.getText();
// checking if passbook is the name of hashmap, we added the csv file to the hash map , sso the 1st element of the array will become the key and 2nd element becomes the value
// if passbook contains the key which is our username, if it does next we check the corresponding  key ie the password  is equal to the one the user enter
            // if it does print success  and a pop up  box appears which shows login successful
            if (passBook.containsKey(userText)) {
                if(passBook.get(userText).equals(pwdText)){
                    System.out.println("success");
                    JOptionPane.showMessageDialog(this, "Login Successful");
                    Main.Begin();
                    LoginFrame.this.dispose(); // removes current frame
                }

            }
            else {
                System.out.println("fail");
                    JOptionPane.showMessageDialog(this, "Invalid Username or Password");
            }




        }
        //Coding Part of RESET button, it set user text fields and password to null string
        if (e.getSource() == resetButton) {
            userTextField.setText("");
            passwordField.setText("");
        }
        //Coding Part of showPassword JCheckBox, it just shows the password
        if (e.getSource() == showPassword) {
            if (showPassword.isSelected()) {
                passwordField.setEchoChar((char) 0);
            }
            else {
                passwordField.setEchoChar('*');
            }
        }
    }

}
// we are adding frame to the login
public class Login {
    public static void main(String[] a) throws FileNotFoundException {

        LoginFrame frame = new LoginFrame();
        frame.setTitle("Music Player Login");
        frame.setVisible(true);
        frame.setBounds(10, 10, 370, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);

    }

}