package com.kodilla.circleandcross;

import javax.swing.*;
import java.awt.*;
import  java.awt.event.*;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class CircleAndCross implements ActionListener {

    JFrame frame = new JFrame();
    JPanel titlePanel = new JPanel();
    JPanel buttomPanel = new JPanel();
    JLabel textField = new JLabel(); //odpowiada za tworzenie etykiet
    JButton [] buttons = new JButton[9];
    int exitGame;


    CircleAndCross() {

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // możemy faktycznie zamykać aplikacje krzyżykiem
        frame.setSize(1200,800);                  // ustawiam wielkość ramki
        frame.getContentPane().setBackground(Color.gray);    // ustawiam kolor tła
        frame.setLayout(new BorderLayout());                 //
        frame.setVisible(true);                             //pokazanie okna
        frame.setResizable(true);                           // zablokowanie zmiany wielkosci okna programu jak fals

        textField.setBackground(Color.lightGray);                //ustawienie koloru tekstu obrysu
        textField.setForeground(Color.black);                //ustawienie koloru t ekstu głównego
        textField.setFont(new Font("Times New Roman",Font.BOLD,80)); //wybór czcionki i jej wielkosci
        textField.setHorizontalAlignment(JLabel.CENTER);        // rozkład tekstu
        textField.setText("Circle And Cross");                  // ustawienie tekstu
        textField.setOpaque(true);

        titlePanel.setLayout(new BorderLayout());
        titlePanel.setBounds(0,0,800,100);      //przemieszcz i zmienia rozmiar

        buttomPanel.setLayout(new GridLayout(3,3));         // zapis girdlayout tworzy równej wielkości przyciski w zadanej ilości rzedów i kolumn
        buttomPanel.setBackground(Color.LIGHT_GRAY);

        for(int i =0; i<9; i++) {

            buttons[i] = new JButton();               //dodawanie przycisków
            buttons[i].setSize(150,150);
            buttons[i].setText("");
            buttons[i].setBackground(Color.white);
            buttomPanel.add(buttons[i]);
            buttons[i].setFont(new Font("Times New Roman",Font.BOLD,120));
            buttons[i].setFocusable(false);
            buttons[i].addActionListener(this); //this przekazuje obiekt klasy
        }

        titlePanel.add(textField);
        frame.add(titlePanel, BorderLayout.NORTH);
        frame.add(buttomPanel);

    }
    @Override
    public void actionPerformed(ActionEvent e){

        for(int i =0; i<9;i++){

            if(e.getSource()==buttons[i]){

                if(buttons[i].getText().equals("")) {
                    buttons[i].setForeground(Color.black);
                    buttons[i].setText("X");
                    check("X");//check("X"); - konkretne wywołanie z metody check(String sign)
                    computerTurn();
                    check("O");
                }
            }
        }
    }

    public void check(String sign) {

        List<JButton> collect = Arrays.stream(buttons)
                .filter(button -> button.getText() == null || button.getText().equals(""))
                .collect(Collectors.toList());

        if(collect.isEmpty()) {
            Object[] option={"Restart", "Exit"};
            int j = JOptionPane.showOptionDialog(frame, "Anyone Win The Game it is a Draw "+ " " ," It is a Draw!",JOptionPane.YES_NO_CANCEL_OPTION,JOptionPane.QUESTION_MESSAGE,null,option,option[0]);

            if(j==0) {

                for(JButton button:buttons) {
                    button.setText("");
                    button.setEnabled(true);
                    button.setBackground(Color.white);
                }
            }else {
                System.exit(0);
            }
        }

        if(
                        (buttons[0].getText().equals(sign))&&
                        (buttons[1].getText().equals(sign))&&
                        (buttons[2].getText().equals(sign))
        ){
            playerOneWins(0,1,2,sign);
        }

        else if(
                        (buttons[3].getText().equals(sign))&&
                        (buttons[4].getText().equals(sign))&&
                        (buttons[5].getText().equals(sign))
        ){
            playerOneWins(3,4,5,sign);
        }
        else if(
                        (buttons[6].getText().equals(sign))&&
                        (buttons[7].getText().equals(sign))&&
                        (buttons[8].getText().equals(sign))
        ){
            playerOneWins(6,7,8, sign);
        }
        else if(
                        (buttons[0].getText().equals(sign))&&
                        (buttons[4].getText().equals(sign))&&
                        (buttons[8].getText().equals(sign))
        ){
            playerOneWins(0,4,8, sign);
        }
        else if(
                        (buttons[2].getText().equals(sign))&&
                        (buttons[4].getText().equals(sign))&&
                        (buttons[6].getText().equals(sign))
        ){
            playerOneWins(2,4,6,sign);
        }
        else if(
                        (buttons[0].getText().equals(sign))&&
                        (buttons[3].getText().equals(sign))&&
                        (buttons[6].getText().equals(sign))
        ){
            playerOneWins(0,3,6,sign);
        }
        else if(
                        (buttons[1].getText().equals(sign))&&
                        (buttons[4].getText().equals(sign))&&
                        (buttons[7].getText().equals(sign))
        ){
            playerOneWins(1,4,7,sign);
        }
        else if(
                        (buttons[2].getText().equals(sign))&&
                        (buttons[5].getText().equals(sign))&&
                        (buttons[8].getText().equals(sign))
        ){
            playerOneWins(2,5,8,sign);
        }
        else if(
                        (buttons[2].getText().equals(sign))&&
                        (buttons[1].getText().equals(sign))&&
                        (buttons[0].getText().equals(sign))
        ){
            playerOneWins(2,1,0,sign);
        }
        else if(
                        (buttons[5].getText().equals(sign))&&
                        (buttons[4].getText().equals(sign))&&
                        (buttons[3].getText().equals(sign))
        ){
            playerOneWins(5,4,3,sign);
        }
        else if(
                        (buttons[8].getText().equals(sign))&&
                        (buttons[7].getText().equals(sign))&&
                        (buttons[6].getText().equals(sign))
        ){
            playerOneWins(8,7,6,sign);
        }
        else if(
                        (buttons[8].getText().equals(sign))&&
                        (buttons[4].getText().equals(sign))&&
                        (buttons[0].getText().equals(sign))
        ){
            playerOneWins(8,4,0,sign);
        }
        else if(
                        (buttons[6].getText().equals(sign))&&
                        (buttons[4].getText().equals(sign))&&
                        (buttons[2].getText().equals(sign))
        ){
            playerOneWins(6,4,2,sign);
        }
    }

    public void playerOneWins(int a, int b, int c, String sign) {

        Color color = Color.RED;
        String name = "Computer";

        if("X".equals(sign)) {
            color = Color.GREEN;
            name = "Player";
        }

        buttons[a].setBackground(color);
        buttons[b].setBackground(color);
        buttons[c].setBackground(color);

        for(int i =0; i<9; i++) {

            buttons[i].setEnabled(false);
        }

        finishGame(name + " Win The Game ");

        }


    public void finishGame(String s) {
        exitGame = 0;
        Object[] option={"Restart", "Exit"};
        int n = JOptionPane.showOptionDialog(frame, "Game Ended "+ " " + s," Game Over",JOptionPane.YES_NO_CANCEL_OPTION,JOptionPane.QUESTION_MESSAGE,null,option,option[0]);

        if(n==0) {
            for(JButton button:buttons) {
                button.setText("");
                button.setEnabled(true);
                button.setBackground(Color.white);
            }
        }else {
            System.exit(0);
        }
    }

    public void computerTurn() {

        List<JButton> collect = Arrays.stream(buttons)
                .filter(button -> button.getText() == null || button.getText().equals(""))
                .collect(Collectors.toList());
        Random randomMoves = new Random();
        System.out.println(collect);
        int number = randomMoves.nextInt(collect.size());
        JButton jButton = collect.get(number);
        jButton.setText("O");

    }
}

