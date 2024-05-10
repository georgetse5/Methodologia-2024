package NPC;
import Player.Player;

import java.io.Serializable;
import java.util.Scanner;

public class NPC implements Serializable {
    private String name;
    private boolean introduced;

    private Player player;
    private Scanner scanner;

    public NPC(String name, boolean introduced) {
        this.name = name;
        this.introduced = introduced;
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

    public void introductoryMessage() {
        scanner = new Scanner(System.in);

        if (!introduced) {
            System.out.println("Hello adventurer, I am " + name + "!\nI am informed about your arrival and the investigations");
            System.out.println("May I ask your name ? (y/n)");

            String response = scanner.nextLine();
            if (response.equals("y")) {
                System.out.println("Great! What is your name?");
                String playerName = scanner.nextLine();
                System.out.println("Nice to meet you, " + playerName + "!");
                setIntro(true);
            } else {
                System.out.println("Alright then, keep your secrets!");
            }
        } else {
            System.out.println("Hello " + player.getName() + ".\nWelcome back. How can I help you ?");
        }
        scanner.close();
    }

    public void farewell() {
        System.out.println("Goodbye, " + name + "!");
        System.out.println("Farewell " + player.getName() + "." + "\nMay see you again later.");
    }

    public void askQuestion(String question) {
        System.out.println(name + ": Excuse me, can I ask you something?");
        System.out.println(player.getName() + ": Sure, what is it?");
        System.out.println(name + ": " + question);
    }

    public void answerQuestion(String answer) {
        System.out.println(name + ": Thank you for your answer.");
        System.out.println(name + " nods.");
    }
}