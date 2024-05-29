package NPC;

import Colors.Colors;
import Player.Player;
import Quests.*;

import java.util.Scanner;

public class MrAnderson extends NPC {

    private ExamineQuestSmith examineQuestSmith;
    private ExamineQuestSmith_2 examineQuestSmith_2;
    Colors color = new Colors();

    public MrAnderson(String name, boolean intro, Player player) {
        super(name, intro, player);
        this.examineQuestSmith = new ExamineQuestSmith("Examine Mr Smith's Past", "Find information about Mr Smith's hidden past.", "Suspicious Note");
        this.examineQuestSmith_2 = new ExamineQuestSmith_2("The suspicious Note", "Find more information about the sender of that Note", "Letter", player);
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
                if (!examineQuestSmith.isCompleted()) {
                    if (examineQuestSmith.isQuestAccepted()) {
                        checkQuestProgress();
                    } else {
                        startQuest();
                    }
                } else if (!examineQuestSmith_2.isCompleted()) {
                    if (examineQuestSmith_2.isQuestAccepted()) {
                        checkSecondQuestProgress();
                    } else {
                        startSecondQuest();
                    }
                } else if (examineQuestSmith.isCompleted() && examineQuestSmith_2.isCompleted()) {
                    System.out.println();
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
        System.out.println("\t1. What can you tell me about the Smith Family ?");
        if (!examineQuestSmith.isCompleted()) {
            System.out.println("\t2. There is something I need to know about Mr Smith ?");
        } else if (!examineQuestSmith_2.isCompleted()){
            System.out.println("\t2. Find the unknown sender of the mysterious note");
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
                    + ". Also you can call me Mr Anderson.\nI am family's Smith butler, It's nice to meet you!"
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
        System.out.println(color.gold() + "MrAnderson: " + color.reset() + "Yes there are rumors about MrSmith's secret life\n" +
                           "A secret affair I heard, but I do not know if it's true\n" +
                           "If you can investigate about it maybe you can find useful info\n");
        System.out.println(color.gold() + player.getName() + ": " + color.reset() + "Alright. I will investigate it\n");
        examineQuestSmith.setQuestAccepted();
        examineQuestSmith.startQuest();
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


    private void familySmithInfo() {
        System.out.println(color.blue() + "Anderson: " + color.gold() + "Here we write something about Family Smith" + color.reset());
    }
}