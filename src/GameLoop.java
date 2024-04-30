import Container.Container;
import Player.Player;
import Rooms.*;
import Item.Item;

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
        add("upstairs");
        add("downstairs");
        add("northwest");
        add("northeast");
        add("southwest");
        add("southeast");
    }};

    private List<Room> rooms;
    private Player player = new Player("John");
    Room startingRoom;

    String ANSI_RESET = "\u001B[0m";
    String ANSI_RED = "\u001B[31m";
    String ANSI_GREEN = "\u001B[32m";
    String ANSI_GOLD = "\u001B[33m";
    String ANSI_CYAN = "\u001B[36m";
    String ANSI_BLUE = "\u001B[34m";
    int gameTurn = 1;

// ==================================================================================================================

    public GameLoop() { ;
        scanner = new Scanner(System.in);
        rooms = initializeMap();
        startingRoom = rooms.get(0);
        player.setCurrentRoom(startingRoom);
        run();
    }


// =========================  Run Method  ============================== //


    private void run() {
        openingScene();
        mainLoop();
    }


// =========================  Main Loop  ============================== //


    private void mainLoop() {
            while (true) {
                System.out.print(">> ");
                String command = scanner.nextLine();
                processesedCmd = processCommand(command);
                String verb = processesedCmd.get(0);
                String noun = processesedCmd.get(1);


                if (verb.equalsIgnoreCase("quit")) {
                    System.out.println("Exiting the game...");
                    break;
                }

                switch (verb.toLowerCase()) {
                    case "help":
                        helpCommand();
                        break;
                    case "go":
                        goCommand(noun);
                        break;
                    case "take":
                        takeCommand(noun);
                        break;
                    case "drop":
                        if (noun == null) {
                            System.out.println("Command DROP must have a noun.\nFor example LOOK KEY, DROP BROKEN WATCH");
                        } else {
                            dropCommand(noun);
                        }
                        break;
                    case "look":
                        if (noun == null) {
                            System.out.println("Command LOOK must have a noun.\nFor example LOOK INV, LOOK EXITS");
                        }
                        else if (noun.equals("exits")) {
                            lookForExits();
                        }
                        else if (noun.equals("inv")) {
                            player.listInventory();
                        } else {
                        System.out.println("This option for LOOK command does not exist");
                        }
                        break;
                    case "inspect":
                        inspectCommand(noun);
                        break;
                    default:
                        System.out.println("This command does not exists");
                        break;
                }
                System.out.println("===========================================\n");
            }
            scanner.close();
    }


// ==============================  Look For Available Exits  ============================== //


    private void lookForExits() {
        // search for all available exits of player's current location (room)
            Map<String, Room> exits = player.getCurrentRoom().getExit();
            if (!exits.isEmpty()) {
                System.out.println(ANSI_BLUE + "Available exits:" + ANSI_RESET);
                for (String direction : exits.keySet()) {
                    System.out.println("- " + ANSI_CYAN + direction + ANSI_RESET + ": " + ANSI_CYAN + exits.get(direction).getName() + ANSI_RESET);
                }
            } else {
                System.out.println("There are no exits in this room.");
            }
    }


// ==============================  Take Item Method  ============================== //


    private void takeCommand(String noun) {
        // System.out.println("[DEBUG]> Command TAKE selected");
        if (noun == null) {
            System.out.println("Command TAKE must have a noun.\nFor example TAKE KEY");
        } else {
            Vector<Item> items = player.getCurrentRoom().getItems();
            Iterator<Item> iterator = items.iterator();
            while (iterator.hasNext()) {
                Item item = iterator.next();
                if (noun.equalsIgnoreCase(item.getName())) {
                    player.addItemToInventory(item);
                    iterator.remove();
                } else {
                    System.out.println("There is no " + noun + " to take in this room.");
                }
            }
        }
    }


// ==============================  Drop Item Method  ============================== //
  
  
    private void dropCommand(String noun) {
        String itemName = noun;
        List<Item> inventory = player.getInventory();
        boolean found = false;

        // Check if the item exist in player's inventory
        for (Item item : inventory) {
             if (itemName.equalsIgnoreCase(item.getName())) {
                 inventory.remove(item);
                 player.getCurrentRoom().addItem(item);
                 found = true;
                 System.out.println("You dropped " + itemName);
                 break;
           }
        }

        // If item does not exist into the inventory
        if (!found){
            System.out.println("There is no " + itemName + " in your inventory.\nIf you want to see your inventory you can use LOOK INV");
        }
    }


// ==============================  Inspect Item Method  ============================== //


    private void inspectCommand(String noun) {

    }


// ==============================  Go Method (direction)  ============================== //


    private void goCommand(String noun) {
        //                        System.out.println("[DEBUG]> Command GO selected");
        if (noun == null) {
            System.out.println("Command GO must have a noun.\nFor example GO EAST");
        }
        else if (directions.contains(noun)) {
            System.out.println("Going " + ANSI_GREEN + noun + ANSI_RESET);

            // Checks if the exit exists for the given direction
            Map<String, Room> exits = player.getCurrentRoom().getExit();
            if (exits.containsKey(noun)) {
                // If the exit exists, moving to the next room
                player.setCurrentRoom(exits.get(noun));
                gameTurn = gameTurn + 1;
                System.out.println("You are in turn: " + ANSI_CYAN + gameTurn + ANSI_RESET);
                System.out.println("You are now in " + ANSI_RED + player.getCurrentRoom().getName() + ANSI_RESET);
                System.out.println("Items found: ");
                for (Object item : player.getCurrentRoom().getItems()) {
                    System.out.println(ANSI_GOLD + "\t\t" + "<> " + item.toString() + ANSI_RESET);
                }
                System.out.println("Containers: " + player.getCurrentRoom().getContainers().toString());
            } else {
                System.out.println("There is no exit in that direction.");
            }

        } else {
            System.out.println("this direction is not valid. You can use (north, south, east, west)");
        }
    }


// ==============================  Help Method  ============================== //


    private void helpCommand() {
        System.out.println("Available commands:");
        System.out.println(ANSI_CYAN + "<>" + ANSI_GOLD + " go [north, south, west, east, upstairs, downstairs,...]" + ANSI_RESET);
        System.out.println(ANSI_CYAN + "<>" + ANSI_GOLD + " take [item]" + ANSI_RESET);
        System.out.println(ANSI_CYAN + "<>" + ANSI_GOLD + " drop [item]" + ANSI_RESET);
        System.out.println(ANSI_CYAN + "<>" + ANSI_GOLD + " look [inv, exits]" + ANSI_RESET);
        System.out.println(ANSI_CYAN + "<>" + ANSI_GOLD + " open [container]" + ANSI_RESET);
        System.out.println(ANSI_CYAN + "<>" + ANSI_GOLD + " quit" + ANSI_RESET);
        System.out.println(ANSI_CYAN + "<>" + ANSI_GOLD + " help" + ANSI_RESET);
    }


// ==============================  Process command method  ============================== //


    public List<String> processCommand(String inputCommand) {
        List<String> processedCommand = new ArrayList<>();
        String[] parts = inputCommand.split("\\s+", 2);

        try {

            if (parts.length >= 1) {
                String verb = parts[0];
                if (parts.length >= 2) {
                    String noun = parts[1];

//                    System.out.println("[DEBUG]> The verb is: " + verb);
//                    System.out.println("[DEBUG]> The noun is: " + noun);

                    processedCommand.add(verb);
                    processedCommand.add(noun);
                } else {
//                    System.out.println("[DEBUG]> The verb is: " + verb);
                    processedCommand.add(verb);
                    processedCommand.add(null);
                }
            } else {
                System.out.println("The string does not contain spaces.");
            }

        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Array index out of bounds error: " + e.getMessage());
        }
        return processedCommand;
    }


// ==============================  InitializeMap Method  ============================== //


    private List<Room> initializeMap() {

        // Room initialization
        Room Living_Room = new Living_Room("Living Room", "Living Room");
        Room hall_1 = new Hall_1("Hall", "First Room");
        Room startingRoom = new StartingRoom("Starting Room", "This is where your adventure begins.");
        Room kitchen = new KitchenRoom("Kitchen", "A Great Place for a great cook");
        Room dinningRoom = new Dinning_Room("Dinning Room", "A fancy table with fancy tableware");
        Room wineCellar = new Wine_Cellar("Wine Cellar", "You can smell the wood of the barrels used to age the fine wine in this room");
        Room hall_2 = new Hall_2("First floor hall", "Another long hallway");
        Room library = new Library("Library" , "A well persevered book collection");
        Room office = new Office("Office", "Seems like an ordinary office, you feel a draft behind the east bookcase");
        Room secretRoom = new Secret_Room("Secret Room", "This is a very weird room");
        // edw vazw text otan mpeni mesa you see 4 doors. kai epilegi me (go door1 h door 2 ) h (go southwest h go southeast)
        Room hall_3 = new Hall_3 ("Second floor hall", "A room with 4 doors");
        Room bedroom_1 = new Bedroom_1("First Bedroom", "A room with a huge bed");
        Room bedroom_2 = new Bedroom_2("Second Bedroom", "A room with a weird swing");
        Room bedroom_3 = new Bedroom_3("Third Bedroom", "A room with a huge bed");
        Room bedroom_guest = new Bedroom_Guest("Guest Bedroom", "A room for the guests");
        Room attic = new Attic("Attic","A room with a lot of dusty stuff and some spider colonies");

        // Add exits to the rooms
        //Room1
        startingRoom.addExit("east", hall_1);
        //Main Hallway
        hall_1.addExit("west", startingRoom);
        hall_1.addExit("north", dinningRoom);
        hall_1.addExit("south",Living_Room);
        hall_1.addExit("upstairs" , hall_2);
        //Dinning Room
        dinningRoom.addExit("south", hall_1);
        dinningRoom.addExit("east", kitchen);
        //Kitchen
        kitchen.addExit("west", dinningRoom);
        kitchen.addExit("east", wineCellar);
        //Wine Cellar
        wineCellar.addExit("west", kitchen);
        //Floor 1 Hallway
        hall_2.addExit("downstairs", hall_1);
        hall_2.addExit("north", library);
        hall_2.addExit("south", office);
        hall_2.addExit("upstairs", hall_3);
        //Library
        library.addExit("south" , hall_2);
        //Office
        office.addExit("north", hall_2);
        office.addExit("bookcase", secretRoom);
        //Secret Room
        secretRoom.addExit("west", secretRoom);
        //Floor 2 Hallway
        hall_3.addExit("downstairs", hall_2);
        hall_3.addExit("northwest", bedroom_1);
        hall_3.addExit("northeast", bedroom_2);
        hall_3.addExit("southwest", bedroom_3);
        hall_3.addExit("southeast", bedroom_guest);
        hall_3.addExit("upstairs", attic);
        //Bedroom 1
        bedroom_1.addExit("back",hall_3);
        //Bedroom 2
        bedroom_2.addExit("back", hall_3);
        //Bedroom 3
        bedroom_3.addExit("back", hall_3);
        //Bedroom Guest
        bedroom_guest.addExit("back", hall_3);
        //Attic
        attic.addExit("downstairs", hall_3);


        // Container initialization
        Container Mystery_Box = new Container("Mystery Box", false, "");
        startingRoom.addContainer(Mystery_Box);






        // Item initialization
          //Starting room Items
        Item Mystery_box = new Item("Mystery Box","Mystery Box",false);
        Item Wardrobe = new Item("Wardrobe", "Wardrobe",false);
        Item key = new Item ("Rusty Key", "It's just a key",true);
        Item broken_watch = new Item ("Broken watch", "A vintage broken watch",true);
        Item Mirror = new Item("Mirror","Mirror",false);
          //Hall_1 Items
        Item Portraits = new Item("Portraits","Portraits",false);
        Item Small_sofa = new Item("Small Sofa","Small sofa",false);
          //Living room Items
        Item Sofa = new Item("Sofa","Sofa",false);
        Item Whiskey_glass = new Item("Whiskey glass","Whiskey glass with traces of red lips",true);
        Item Paintings = new Item("Paintings","Paintings on the wall",false);
          //Kitchen Items
        Item Table = new Item("Table", "Table",false);
        Item knife = new Item("knife","A sharp knife used for cutting.",true);
        Item Fridge = new Item("Fridge","A big white fridge.",false);
        Item Poisson = new Item("Poisson","A small bottle with Poisson.",true);



        // Adding items to containers
        Mystery_Box.addItem(Mystery_box);

        // Adding items to the rooms
        startingRoom.addItem(key);
        startingRoom.addItem(broken_watch);
        startingRoom.addItem(Wardrobe);
        startingRoom.addItem(Mirror);

        hall_1.addItem(Portraits);
        hall_1.addItem(Small_sofa);

        Living_Room.addItem(Sofa);
        Living_Room.addItem(Whiskey_glass);
        Living_Room.addItem(Paintings);

        kitchen.addItem(Table);
        kitchen.addItem(knife);
        kitchen.addItem(Fridge);
        kitchen.addItem(Poisson);

        List<Room> rooms = new ArrayList<>();

        // Adding the rooms to the list rooms
        rooms.add(startingRoom);
        rooms.add(attic);
        rooms.add(bedroom_1);
        rooms.add(bedroom_2);
        rooms.add(bedroom_3);
        rooms.add(bedroom_guest);
        rooms.add(dinningRoom);
        rooms.add(hall_1);
        rooms.add(hall_2);
        rooms.add(hall_3);
        rooms.add(kitchen);
        rooms.add(library);
        rooms.add(Living_Room);
        rooms.add(office);
        rooms.add(secretRoom);
        rooms.add(wineCellar);

        return rooms;
    }


// ==================================================================================================================


    private void openingScene () {
        System.out.println("\nWelcome" + player.getName() + "! Type" + ANSI_CYAN + " 'help' " + ANSI_RESET + "for available commands.");

        System.out.println("You are in turn: " + ANSI_CYAN + gameTurn + ANSI_RESET);
        System.out.println("You are now to " + ANSI_RED + player.getCurrentRoom().getName() + ANSI_RESET);
        System.out.println(player.getCurrentRoom().getDescription());

        System.out.println("Items found: ");
        for (Object item : player.getCurrentRoom().getItems()) {
            System.out.println(ANSI_GOLD + "\t\t" + "<> " + item.toString() + ANSI_RESET);
        }
        System.out.println("Containers: " + player.getCurrentRoom().getContainers().toString());

        Room currentRoom = player.getCurrentRoom();
        for (Room room : rooms) {
            if (room.equals(currentRoom)) {
//                System.out.println("Player.Player is currently in room: " + room.getName());

                break;
            }
        }
    }

}

