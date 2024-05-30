package NPC;

import Colors.Colors;
import Player.Player;
import Quests.*;

import java.util.Scanner;

public class MrLouis extends NPC {

    private ExamineQuestLouis examineQuestLouis;
    private ExamineQuestLouis_2 examineQuestLouis_2;
    private ExamineQuestLouis_3 examineQuestLouis_3;
    Colors color = new Colors();

    public MrLouis(String name, boolean intro, Player player) {
        super(name, intro, player);
        this.examineQuestLouis = new ExamineQuestLouis("Find the old photos", "There is a lot of old memorys in the attic", "Old Photo");
        this.examineQuestLouis_2 = new ExamineQuestLouis_2("The Suspicious Photo", "Find more information about the woman in the photo", "Old Photo");
        this.examineQuestLouis_3 = new ExamineQuestLouis_3("Maybe Anderson knows more", "Go ask anderson about the woman in the photo ", "Old Photo");
    }

    @Override
    public void talk(String playerName) {
        Scanner scanner = new Scanner(System.in);
        String pName = playerName;

        introductoryMessage(playerName);
        boolean keepLoop = true;

        while (keepLoop) {
            selectOption();

            System.out.print(color.gold() + "$> " + color.reset());
            int playerChoice = scanner.nextInt();
            switch (playerChoice) {
                case 1:
                    if (!examineQuestLouis.isCompleted()) {
                        if (examineQuestLouis.isQuestAccepted()) {
                            checkQuestProgress();
                        } else {
                            startQuest();
                        }
                    } else if (!examineQuestLouis_2.isCompleted()) {
                        if (examineQuestLouis_2.isQuestAccepted()) {
                            checkSecondQuestProgress();
                        } else {
                            startSecondQuest();
                        }
                    } else if (!examineQuestLouis_3.isCompleted()) {
                        if (!examineQuestLouis_3.isQuestAccepted()) {
                            //checkThirdQuestProgress();
                        } else {
                            //startThirdQuest();
                        }
                    } else if (examineQuestLouis.isCompleted() && examineQuestLouis_2.isCompleted() && examineQuestLouis_3.isCompleted() ) {
                        System.out.println();
                    }
                    keepLoop = false;
                    break;
                case 2:
                    farewell();
                    keepLoop = false;
                    break;
                default:
                    System.out.println("(-_-)");
                    break;
            }
        }
    }

    @Override
    void selectOption() {
        if (!examineQuestLouis.isCompleted()) {
            System.out.println("\t1. I heard about the secret affair Mr.Smith had do you know anything about that?");
        } else if (!examineQuestLouis_2.isCompleted()){
            System.out.println("\t1. I got the photo. Do you know who this woman is?");
        } else if (!examineQuestLouis_3.isCompleted()){
            System.out.println("\t1. ...");
        }
        System.out.println("\t2. Farewell");
    }


    @Override
    public void introductoryMessage(String playerName) {

        if (!getIntro()) {
            System.out.println("Hello, my name is "
                    + getName()
                    + "\nI am family's Smith chef, It's nice to meet you!"
                    + "\nIf you have any questions do not hesitate to ask.");
            setIntro(true);
        } else {
            System.out.println("Hey " + playerName + ". How the investigation is going ?");
            System.out.println("If you have any questions do not hesitate to ask.");
        }
    }

    public void offerHelp() {
        System.out.println("How can I help you ?");
    }

    public void startQuest() {
        System.out.println(color.gold() + "MrLouis: " + color.reset() + "I do not know anything about a secret afair. \n" +
                "But i know that Mr.Smith keeps old keepsakes in the attic\n" +
                "Maybe if you go to the attic you will find what you are looking for\n");
        System.out.println(color.gold() + player.getName() + ": " + color.reset() + "Alright. I will investigate it\n");
        examineQuestLouis.setQuestAccepted();
        examineQuestLouis.startQuest();
    }


    public void checkQuestProgress() {
        if (examineQuestLouis != null) {
            examineQuestLouis.checkItems(player);
            if (examineQuestLouis.isItemFound()) {
                examineQuestLouis.completeQuest(player);
            } else {
                System.out.println(color.red() + "You need to find the required item to complete the quest." + color.reset());
            }
        } else {
            System.out.println("There are no active quests.");
        }
    }


    public void startSecondQuest() {
        System.out.println(color.gold() + "MrLouis: " + color.reset() + "This is the first time i see this woman. \n" +
                "Maybe you should ask Mr.Anderson about the woman, he's been working here for longer\n");
        System.out.println(color.gold() + player.getName() + ": " + color.reset() + "Alright. I will go ask Mr.Anderson\n");
        examineQuestLouis_2.setQuestAccepted();
        examineQuestLouis_2.startQuest();
    }

    public void startThirdQuest() {
        examineQuestLouis_3.setQuestAccepted();
        examineQuestLouis_3.startQuest();
    }

    public void checkSecondQuestProgress() {
        if (examineQuestLouis_2 != null) {
            examineQuestLouis_2.checkItems(player);
            if (examineQuestLouis_2.isItemFound()) {
                examineQuestLouis_2.completeQuest(player);
            } else {
                System.out.println(color.gold() + "You need to find the required item to complete the quest." + color.reset());
            }
        } else {
            System.out.println("There are no active quests.");
        }
    }


    public void checkThirdQuestProgress() {
        if (examineQuestLouis_3 != null) {
            examineQuestLouis_3.checkItems(player);
            if (examineQuestLouis_3.isItemFound()) {
                examineQuestLouis_3.completeQuest(player);
            } else {
                System.out.println(color.gold() + "You need to find the required item to complete the quest." + color.reset());
            }
        } else {
            System.out.println("There are no active quests.");
        }
    }


    private void familySmithInfo() {
        System.out.println(color.blue() + "Louis: " + color.gold() + "I am shocked. I didn't think Mr. Smith was in any danger" + color.reset());
    }
}