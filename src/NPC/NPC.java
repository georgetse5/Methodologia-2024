package NPC;

import Player.Player;

import java.io.Serializable;
import java.util.Scanner;

public class NPC implements Serializable {
    private String name, pName;
    private boolean introduced;
    Player player;

    public NPC(String name, boolean introduced, Player player) {
        this.name = name;
        this.introduced = introduced;
        this.player = player;
    }


//  ======================================================  //


    public void talk(String playerName) {
        Scanner scanner = new Scanner(System.in);
        pName = playerName;

        introductoryMessage(playerName);
        selectOption();

        int playerChoice = scanner.nextInt();
        if (playerChoice == 1) {
            System.out.println("I have not any questions yet");
        } else if (playerChoice == 2) {
            farewell();
        } else {
            System.out.println("(-_-)");
        }

    }


//  ======================================================  //


    public boolean getIntro() {
        return introduced;
    }

    public void setIntro(boolean intro) {
        introduced = intro;
    }

    public String getName() {
        return name;
    }


    public void introductoryMessage(String playerName) {
        if (!introduced) {
            System.out.println("Hello adventurer, I am " + name + "!\nI am informed about your arrival and the investigations");
        } else {
            System.out.println("Hello " + playerName + ".\nWelcome back. How can I help you ?");
        }
    }


    public void farewell() {
        System.out.println("Farewell " + pName + "." + "\nMay see you again later.");
    }


    public void askQuestion(String question) {
        System.out.println(name + ": Excuse me, can I ask you something?");
        System.out.println(pName + ": Sure, what is it?");
        System.out.println(name + ": " + question);
    }


    public void answerQuestion(String answer) {
        System.out.println(name + ": Thank you for your answer.");
        System.out.println(name + " nods.");
    }


    void selectOption() {
        System.out.println("\t1. I have a question.\n\t2. I am leaving for now.\n");
    }

}