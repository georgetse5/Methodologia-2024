import Rooms.*;
import Item.Item;

import java.io.IOException;
import java.util.*;
import java.util.ArrayList;
import java.util.Scanner;

public class GameLoop {
    private final Scanner scanner;
    private List<String> processesedCmd = new ArrayList<>();
    List<String> directions = new ArrayList<>() {{
        add("north");
        add("south");
        add("east");
        add("west");
    }};
    private List<Room> rooms;
    private Player player = new Player("John");
    Room startingRoom, nextRoom, currentRoom;

// ==================================================================================================================

    public GameLoop() { ;
        scanner = new Scanner(System.in);
        rooms = initializeMap();
        startingRoom = rooms.get(0);
        player.setCurrentRoom(startingRoom);
        run();
    }

// ==================================================================================================================

    private void run() {
        System.out.println("\nWelcome adventurer! Type 'help' for available commands.");

// =============  For testing purposes  ===============================
        System.out.println("You are now to " + player.getCurrentRoom().getName());
        System.out.println(player.getCurrentRoom().getDescription());
        System.out.println("Items found: " + player.getCurrentRoom().getItems().toString());

        Room currentRoom = player.getCurrentRoom();
        for (Room room : rooms) {
            if (room.equals(currentRoom)) {
//                System.out.println("Player is currently in room: " + room.getName());

                if (room instanceof StartingRoom) {
                    StartingRoom startingRoom = (StartingRoom) room;
                    startingRoom.startingRoomMessage();

                } else if (room instanceof HallOfFameRoom) {
                    HallOfFameRoom hallOfFameRoom = (HallOfFameRoom) room;
                    hallOfFameRoom.AnotherTestMethod();
                }

                break;
            }
        }
// =============  End of testing purposes  ===============================

// ====================================================================


        while (true) {
            System.out.print(">> ");
            String command = scanner.nextLine();
            processesedCmd = processCommand(command);


            if (processesedCmd.get(0).equalsIgnoreCase("quit")) {
                System.out.println("Exiting the game...");
                break;
            }

            switch (processesedCmd.get(0).toLowerCase()) {
                case "help":
                        System.out.println("Available commands:");
                        System.out.println("- go (north, south, west, east)");
                        System.out.println("- take [item]");
                        System.out.println("- look [a, around]");
                        System.out.println("- quit");
                        System.out.println("- help");
                    break;
                case "go":
                        System.out.println("[DEBUG]> Command GO selected");
                    if (processesedCmd.size() == 1) {
                        System.out.println("Command GO must have a noun.\nFor example GO EAST");
                        break;
                    }
                    if (directions.contains(processesedCmd.get(1))) {
                        System.out.println("You are going " + processesedCmd.get(1));

                        // Checks if the exit exists for the given direction
                        Map<String, Room> exits = player.getCurrentRoom().getExit();
                        if (exits.containsKey(processesedCmd.get(1))) {
                            // If the exit exists, moving to the next room
                            player.setCurrentRoom(exits.get(processesedCmd.get(1)));
                            System.out.println("You are now in " + player.getCurrentRoom().getName());
                            System.out.println("Items found: " + player.getCurrentRoom().getItems().toString());
                        } else {
                            System.out.println("There is no exit in that direction.");
                        }


                    } else {
                        System.out.println("this direction is not valid. You can use (north, south, east, west)");
                    }
                    break;
                case "take":
                        System.out.println("[DEBUG]> Command TAKE selected");
                    if (processesedCmd.size() == 1) {
                        System.out.println("Command TAKE must have a noun.\nFor example TAKE KEY");
                    }
                    break;
                default:
                    System.out.println("This command does not exists");
                    break;
            }

        }

        scanner.close();
    }

// ==================================================================================================================

    public List<String> processCommand(String inputCommand) {
        List<String> processedCommand = new ArrayList<>();
        String[] parts = inputCommand.split("\\s+", 2);

        try {

            if (parts.length >= 1) {
                String verb = parts[0];
                if (parts.length >= 2) {
                    String noun = parts[1];

                    System.out.println("[DEBUG]> The verb is: " + verb);
                    System.out.println("[DEBUG]> The noun is: " + noun);

                    processedCommand.add(verb);
                    processedCommand.add(noun);
                } else {
                    System.out.println("[DEBUG]> The verb is: " + verb);
                    processedCommand.add(verb);
                }
            } else {
                System.out.println("The string does not contain spaces.");
            }

        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Array index out of bounds error: " + e.getMessage());
        }
        return processedCommand;
    }

// ==================================================================================================================

    private List<Room> initializeMap() {

        Room startingRoom = new StartingRoom("Starting Room", "This is where your adventure begins.");
        Room hallOfFame = new HallOfFameRoom("Hall Of Fame", "A Great Place for great people");

        startingRoom.addExit("east", hallOfFame);
        hallOfFame.addExit("west", startingRoom);

        Item key = new Item ("Rusty Key", "It's just a key");
        Item teddy_bear = new Item ("Broken watch", "A vintage broken watch");
        Item item2 = new Item("Item2", "Item2 Desc");

        startingRoom.addItem(key);
        startingRoom.addItem(teddy_bear);

        hallOfFame.addItem(item2);

        List<Room> rooms = new ArrayList<>();
        rooms.add(startingRoom);
        rooms.add(hallOfFame);

        return rooms;
    }

// ==================================================================================================================

}

