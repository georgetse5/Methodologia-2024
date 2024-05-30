package Test;

import NPC.MrAnderson;
import Player.Player;
import Quests.ExamineQuestSmith;
import Quests.ExamineQuestSmith_2;
import Quests.SmithMiniGame;
import Item.Item;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class MrAndersonTest {

    private Player testPlayer;
    private ExamineQuestSmith testExamineQuestSmith;
    private ExamineQuestSmith_2 testExamineQuestSmith_2;
    private SmithMiniGame testSmithMiniGame;
    private MrAnderson mrAnderson;

    @Before
    public void setUp() {
        testPlayer = new Player("Test_Player"); // Δημιουργία ενός test παίκτη
        testExamineQuestSmith = new ExamineQuestSmith("Examine Mr Smith's Past", "Find information about Mr Smith's hidden past.", "Suspicious Note");
        testExamineQuestSmith_2 = new ExamineQuestSmith_2("The suspicious Note", "Find more information about the sender of that Note", "Letter", testPlayer);
        testSmithMiniGame = new SmithMiniGame("Find the box", "There is a strange box on the cellar", "Puzzle Box");

        mrAnderson = new MrAnderson("Mr. Anderson", false, testPlayer);
        //mrAnderson.setExamineQuestSmith(testExamineQuestSmith);
       // mrAnderson.setExamineQuestSmith_2(testExamineQuestSmith_2);
        //mrAnderson.setSmithMiniGame(testSmithMiniGame);
    }

    @Test
    public void testIntroductoryMessage_FirstTime() {
        mrAnderson.introductoryMessage(testPlayer.getName());
        assertTrue(mrAnderson.getIntro());
    }

    @Test
    public void testIntroductoryMessage_SecondAndOtherTimes() {
        mrAnderson.setIntro(true);
        mrAnderson.introductoryMessage(testPlayer.getName());
        assertTrue(mrAnderson.getIntro());
    }

    @Test
    public void testStartQuest() {
        mrAnderson.startQuest();
        assertTrue(testExamineQuestSmith.isQuestAccepted());
    }

    @Test
    public void testCheckQuestProgress_ItemFound() {
        System.out.println("Your current progress is " + testPlayer.getProgressPoints() + "/100\n");

        testPlayer.addItemToInventory(new Item("Suspicious Note", "A suspicious note", true));
        mrAnderson.checkQuestProgress();
        assertTrue(testExamineQuestSmith.isCompleted());

        System.out.println("\nYour new progress is " + testPlayer.getProgressPoints() + "/100");
    }

    @Test
    public void testCheckQuestProgress_ItemNotFound() {
        testPlayer.addItemToInventory(new Item("Other Item", "Not the required item", true));
        mrAnderson.checkQuestProgress();
        assertFalse(testExamineQuestSmith.isCompleted());
    }

    @Test
    public void testCheckSecondQuestProgress_ItemFound() {
        System.out.println("Your current progress is " + testPlayer.getProgressPoints() + "/100\n");

        testPlayer.addItemToInventory(new Item("Letter", "A letter", true));
        mrAnderson.checkSecondQuestProgress();
        assertTrue(testExamineQuestSmith_2.isCompleted());

        System.out.println("\nYour new progress is " + testPlayer.getProgressPoints() + "/100");
    }

    @Test
    public void testCheckSecondQuestProgress_ItemNotFound() {
        testPlayer.addItemToInventory(new Item("Other Item", "Not the required item", true));
        mrAnderson.checkSecondQuestProgress();
        assertFalse(testExamineQuestSmith_2.isCompleted());
    }

    @Test
    public void testCheckMiniGameProgress_ItemFound() {
        testPlayer.addItemToInventory(new Item("Puzzle Box", "A puzzle box", true));
        mrAnderson.checkMiniGameProgress();
        assertTrue(testSmithMiniGame.isCompleted());
    }

    @Test
    public void testCheckMiniGameProgress_ItemNotFound() {
        testPlayer.addItemToInventory(new Item("Other Item", "Not the required item", true));
        mrAnderson.checkMiniGameProgress();
        assertFalse(testSmithMiniGame.isCompleted());
    }

    @Test
    public void testSecondQuestFailure() {
        testPlayer.addProgressPoints(20);
        int initialPoints = testPlayer.getProgressPoints();

        System.out.println("Your progress points now is: " + testPlayer.getProgressPoints() + "/100");
        mrAnderson.startSecondQuest();
        assertTrue(testExamineQuestSmith_2.isQuestAccepted());

        System.out.println("\nThe test starting...\nTurns counting...\n");

        int tempTurn;
        for (int i = 0; i < 16; i++) {
            tempTurn = testPlayer.getPlayerTurn();
            tempTurn = tempTurn + 1;
            testPlayer.setPlayerTurn(tempTurn);
        }

        mrAnderson.checkSecondQuestProgress();
        assertFalse(testExamineQuestSmith_2.isCompleted());
        assertEquals(initialPoints - 10, testPlayer.getProgressPoints());
    }
}
