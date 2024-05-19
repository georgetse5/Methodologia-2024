package NPC;

import Player.Player;
import Quests.ExamineQuestSmith;

import java.util.Scanner;

public class MrAnderson extends NPC {

    private ExamineQuestSmith examineQuestSmith;

    public MrAnderson(String name, boolean intro, Player player) {
        super(name, intro, player);
        this.examineQuestSmith = new ExamineQuestSmith("Examine Mr Smith's Past", "Find information about Mr Smith's hidden past.", "Dusty Letter");
    }

    @Override
    public void talk(String playerName) {
        Scanner scanner = new Scanner(System.in);
        String pName = playerName;

        introductoryMessage(playerName);
        boolean keepLoop = true;

        while (keepLoop) {
        selectOption();

        int playerChoice = scanner.nextInt();
        switch (playerChoice) {
            case 1:
                System.out.println("What can you tell me about the Smith Family?");
                break;
            case 2:
                if (examineQuestSmith.isQuestAccepted()) {
                    checkQuestProgress();
                } else {
                    startQuest();
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
        System.out.println("\t1. What can you tell me about the Smith Family ?\n" +
                           "\t2. There is something I need to know about Mr Smith ?\n" +
                           "\t3. Farewell");
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
        System.out.println("MrAnderson: Yes there are rumors about MrSmith's secret life\n" +
                           "A secret affair I heard, but I do not know if it's true" +
                           "If you can investigate about it maybe you can find useful info");
        System.out.println(player.getName() + ": Alright. I will investigate it");
        examineQuestSmith.setQuestAccepted();
        examineQuestSmith.startQuest();
    }

    public void checkQuestProgress() {
        if (examineQuestSmith != null) {
            examineQuestSmith.checkItems(player);
            if (examineQuestSmith.isItemFound()) {
                examineQuestSmith.completeQuest(player);
            } else {
                System.out.println("You need to find the required item to complete the quest.");
            }
        } else {
            System.out.println("There are no active quests.");
        }
    }
}