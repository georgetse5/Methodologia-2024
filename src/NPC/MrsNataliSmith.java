package NPC;

import Colors.Colors;
import Player.Player;
import Quests.*;

import java.util.Scanner;

public class MrsNataliSmith extends NPC {

    private ExamineQuestSmith examineQuestSmith;
    private ExamineQuestSmith_2 examineQuestSmith_2;
    private ExamineQuestSmith_3 examineQuestSmith_3;
    private ExamineQuestSmith_4 examineQuestSmith_4;
    Colors color = new Colors();

    public MrsNataliSmith(String name, boolean intro, Player player) {
        super(name, intro, player);

        this.examineQuestSmith_4 = new ExamineQuestSmith_4("The Photos", "Find the Photos ", "Photos");
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
                    familySmithInfo();
                    break;
                case 2:
                     if (!examineQuestSmith_4.isCompleted()) {
                        if (examineQuestSmith_4.isQuestAccepted()) {
                            checkFourthQuestProgress();
                        } else {
                            startFourthQuest();
                        }
                    }
                    break;
                case 3:
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
        System.out.println("\t1. What can you tell me about your husband ?");
        if (!examineQuestSmith_4.isCompleted()) {
            System.out.println("\t2. There is something I need to know about Mr Smith ?");
        } else if (!examineQuestSmith_4.isCompleted()){
            System.out.println("\t2. Find the photos");
        } else {
            System.out.println("\t2. ...");
        }
        System.out.println("\t3. Farewell");
    }


    @Override
    public void introductoryMessage(String playerName) {

        if (!getIntro()) {
            System.out.println("Hello, my name is "
                    + getName()
                    + "\nI am Smith wife, It's nice to meet you!"
                    + "\nI am very upset"
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
        System.out.println(color.gold() + "Mrs Natali: " + color.reset() + "Υes you must know that my husband had a mistress\n" +
                "Υou can find photos in the secret room next to the office\n" +
                "If you can investigate about it maybe you can find useful info\n");
        System.out.println(color.gold() + player.getName() + ": " + color.reset() + "Alright. I will investigate it\n");
        examineQuestSmith_4.setQuestAccepted();
        examineQuestSmith_4.startQuest();
    }


    public void checkQuestProgress() {
        if (examineQuestSmith != null) {
            examineQuestSmith.checkItems(player);
            if (examineQuestSmith.isItemFound()) {
                examineQuestSmith.completeQuest(player);
            } else {
                System.out.println(color.red() + "You need to find the required item to complete the quest." + color.reset());
            }
        } else {
            System.out.println("There are no active quests.");
        }
    }


    public void startSecondQuest() {
        examineQuestSmith_2.setQuestAccepted();
        examineQuestSmith_2.startQuest();
    }

    public void startThirdQuest() {
        examineQuestSmith_3.setQuestAccepted();
        examineQuestSmith_3.startQuest();
    }
    public void startFourthQuest() {
        examineQuestSmith_4.setQuestAccepted();
        examineQuestSmith_4.startQuest();
    }

    public void checkSecondQuestProgress() {
        if (examineQuestSmith_2 != null) {
            examineQuestSmith_2.checkItems(player);
            if (examineQuestSmith_2.isItemFound()) {
                examineQuestSmith_2.completeQuest(player);
            } else {
                System.out.println(color.gold() + "You need to find the required item to complete the quest." + color.reset());
            }
        } else {
            System.out.println("There are no active quests.");
        }
    }


    public void checkThirdQuestProgress() {
        if (examineQuestSmith_3 != null) {
            examineQuestSmith_3.checkItems(player);
            if (examineQuestSmith_3.isItemFound()) {
                examineQuestSmith_3.completeQuest(player);
            } else {
                System.out.println(color.gold() + "You need to find the required item to complete the quest." + color.reset());
            }
        } else {
            System.out.println("There are no active quests.");
        }
    }
    public void checkFourthQuestProgress() {
        if (examineQuestSmith_4 != null) {
            examineQuestSmith_4.checkItems(player);
            if (examineQuestSmith_4.isItemFound()) {
                examineQuestSmith_4.completeQuest(player);
            } else {
                System.out.println(color.gold() + "You need to find the required item to complete the quest." + color.reset());
            }
        } else {
            System.out.println("There are no active quests.");
        }
    }


    private void familySmithInfo() {
        System.out.println(color.blue() + "Mrs Natali: " + color.gold() + "I can't believe what is happening to my husband"
        + " but you must know that my husband had a mistress\n" +
                "Υou can find photos in the secret room next to the office\n" +
                        "If you can investigate about it maybe you can find useful info\n" + color.reset());
    }
}