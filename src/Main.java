import java.io.IOException;
public class Main {

    public static void main(String[] args) {


        System.out.println("\033[36m" +"--------------------------------------------------------------------------------------------------"+ "\033[0m");
        System.out.println("\033[36m" + "|                                  The Mystery of the Smith Manor                                 | " + "\033[0m");
        System.out.println("|  Welcome to 'The Mystery of the Smith Manor'!                                                   |");
        System.out.println("|  After an elegant reception at the Smith Manor, friends and relatives depart,                   |");
        System.out.println("|  leaving behind a manor shrouded in mystery.                                                    |");
        System.out.println("|  The next morning, Mr. Smith is found dead in one of the rooms of his house,                    |");
        System.out.println("|  lying on the floor in a pool of blood.                                                         |");
        System.out.println("|  You, as a detective, intrude into the manor to uncover the truth behind this mystery           |");
        System.out.println("\033[36m"+"---------------------------------------------------------------------------------------------------"+ "\033[0m");

        GameLoop gameLoop = new GameLoop();
        gameLoop.startGame();
    }
}

// TODO: 7/5/2024:
//  1) Add more items and containers,
//  2) Player can use items to OPEN containers and the room OBJECTIVES like mini quests
//  3) Create Classes for the NPCs
//  4) Adding NPCs to the map
//  5) User can interact with the available NPCs
//  6) Load the save from the save file
