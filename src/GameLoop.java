import Colors.Colors;
import Container.Container;
import GuiMap.GuiMap;
import NPC.MrAnderson;
import NPC.NPC;
import NPC.SomeRandomGuy;
import Player.Player;
import Rooms.*;
import Item.Item;

import java.io.Serializable;
import java.util.*;
import java.util.ArrayList;
import java.util.Scanner;

public class GameLoop implements Serializable {
    private final Scanner scanner;
    private List<String> processesedCmd = new ArrayList<>();
    private final List<String> directions = new ArrayList<>() {{
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
        add("back");
    }};
    private final List<String> helpCmdList = new ArrayList<>() {{
        add("go [north, south, west, east, upstairs, downstairs,...]");
        add("inspect room");
        add("take [item]");
        add("drop [item]");
        add("look [inv, exits, map]");
        add("open [container]");
        add("talk [NPCs name]");
        add("save");
        add("quit");
        add("help");
    }};

    private boolean debug = false;
    private boolean allowDebug = false;


    private List<Room> rooms;
    private Player player = new Player();

    private final GuiMap map = new GuiMap();
    Colors color = new Colors();

    private int gameTurn = 1;
    private boolean won = false;

    private GameData gameData;
    private final String saveFile = "game.sav";


// ==================================================================================================================


    public GameLoop() {
        scanner = new Scanner(System.in);
        rooms = initializeMap();
    }


// =========================  Run Method  ============================== //


    public void startGame() {
        loadGame(saveFile);
        mainLoop();
    }


// =========================  Main Loop  ============================== //


    private void mainLoop() {
            while (!checksEndgameProgress()) {
                if (player.getProgressPoints() == 100) {
                    break;
                }

                System.out.print(">> ");
                String command = scanner.nextLine();
                processesedCmd = processCommand(command);
                String verb = processesedCmd.get(0);
                String noun = processesedCmd.get(1);


                if (verb.equalsIgnoreCase("quit")) {
                    saveGame(saveFile);
                    System.out.println("Exiting the game...");
                    break;
                }

                switch (verb.toLowerCase()) {
    // Help Command
                    case "help":
                        helpCommand();
                        break;
    // Add Progress Command
                    case "add.progress":
                        progressAddCommand(noun);
                        break;
    // Add Item Command
                    case "add.item":
                        addItemCommand(noun);
                        break;
    // Debug on/off Command
                    case "debug":
                        toggleDebugCommand(noun);
                        break;
    // Save Command
                    case "save":
                        saveGame(saveFile);
                        break;
    // Go Command
                    case "go":
                        goCommand(noun);
                        saveGame(saveFile);
                        break;
    // Take Command
                    case "take":
                        takeCommand(noun);
                        break;
    // Drop Command
                    case "drop":
                        dropCommand(noun);
                        break;
    // Look command
                    case "look":
                        if (noun == null) {
                            System.out.println("Command LOOK must have a noun.\nFor example LOOK INV, LOOK EXITS");
                        }
                        else if (noun.equals("exits")) {
                            lookForExits();
                        }
                        else if (noun.equals("inv")) {
                            player.listInventory();
                        }
                        else if (noun.equals("map")) {
                            lookGUIMap();
                        } else {
                        System.out.println("This option for LOOK command does not exist");
                        }
                        break;
    // Inspect Command
                    case "inspect":
                        inspectCommand(noun);
                        break;
    // Talk Command
                    case "talk":
                        talkCommand(noun);
                        break;
    // Open Command
                    case "open":
                        openCommand(noun);
                        break;
                    default:
                        System.out.println("This command does not exists");
                        break;
                }
                System.out.println("===========================================\n");
            }
            scanner.close();
    }


    // ==============================  Progress Add Command  ============================== //


    private void progressAddCommand(String noun) {
        int temp = Integer.parseInt(noun);
        if (debug) {
            player.addProgressPoints(temp);
            System.out.println("Your new progress is " + player.getProgressPoints() + "/100");
        }
    }


    // ==============================  Add Item Command  ============================== //


    private void addItemCommand(String noun) {
        Item item = new Item(noun, "This item is added with the debugging command", true);

        if (debug) {
            player.addItemToInventory(item);
            System.out.println(noun + " is added to your inventory");
        }
    }


    // ==============================  Toggle Debug Command  ============================== //


    private void toggleDebugCommand(String noun) {
        if (noun == null) {
            if (debug) {
                System.out.println("Debug mode is ON");
            } else {
                System.out.println("Debug mode is OFF");
            }
            System.out.println("debug on/off\tFor example debug on");
        } else if (allowDebug) {
            if (noun.equals("on")) {
                debug = true;
                System.out.println("Debug mode is now ON");
            } else if (noun.equals("off")) {
                debug = false;
                System.out.println("Debug mode is now OFF");
            }
        } else {
            System.out.println("You can not use this command");
        }
    }


    // ==============================  Open Command  ============================== //


    private void openCommand(String noun) {
        if (noun == null) {
            System.out.println("Command OPEN must have a noun.\nFor example OPEN CHEST");
            return;
        }

        List<Container> containers = player.getCurrentRoom().getContainers();
        boolean foundContainer = false;

        for (Container container : containers) {
            if (container.toString().equalsIgnoreCase(noun)) {
                foundContainer = true;
                if (container.requiresKey()) {
                    boolean hasKey = false;
                    for (Item item : player.getInventory()) {
                        if (item.getName().equalsIgnoreCase(container.getKey())) {
                            hasKey = true;
                            break;
                        }
                    }
                    if (!hasKey) {
                        System.out.println("You need a key to open the " + noun + ".");
                        return;
                    }
                    container.setAsUnlocked();
                }

                System.out.println("Opening " + noun + "...");
                ArrayList<Item> items = container.getItems();
                if (items.isEmpty()) {
                    System.out.println("The " + noun + " is empty.");
                } else {
                    System.out.println("The " + noun + " contains:");

                    for (Item item : items) {
                        System.out.println("\t- " + color.gold() + item.getName() + color.reset() + ": " + item.getDescription());

                        player.addItemToInventory(item);
                    }
                    items.clear();
                }
                return;
            }
        }

        if (!foundContainer) {
            System.out.println("There is no " + noun + " in this room.");
        }
    }


    // ==============================  Look The Map Method (GUI)  ============================== //


    private void lookGUIMap() {
         if (GuiMap.isInstanceCreated()) {
             map.dispose();
             map.showMap();
         }
        map.showMap();
    }


// ==============================  Look For Available Exits  ============================== //


    private void lookForExits() {
        // search for all available exits of player's current location (room)
            Map<String, Room> exits = player.getCurrentRoom().getExit();
            if (!exits.isEmpty()) {
                System.out.println(color.blue() + "\nAvailable exits:" + color.reset());
                for (String direction : exits.keySet()) {
                    System.out.println("\t- " + color.gold() + "" + direction + ": " + color.cyan() + exits.get(direction).getName() + color.reset());
                }
            } else {
                System.out.println("\nThere are no exits in this room.");
            }
    }


// ==============================  Look For Available Items  ============================== //


    private void lookForRoomItems() {
        // search for all available playersCurrentRoomItems of player's current location (room)
        List<Item> playersCurrentRoomItems = player.getCurrentRoom().getItems();
        if (!playersCurrentRoomItems.isEmpty()) {
            System.out.println(color.blue() + "\nAvailable items:" + color.reset());
            for (Item item : playersCurrentRoomItems) {
                System.out.println(color.cyan() + "\t<> " + color.gold() + item.getName() + color.reset() + ": " + color.gold() + item.getDescription() + color.reset());
            }
        } else {
            System.out.println("\nThere are no items in this room.");
        }
    }


// ==============================  Look For Available Items  ============================== //


    private void lookForRoomContainers() {
            // search for all available playersCurrentRoomItems of player's current location (room)
            List<Container> roomAvailableContainers = player.getCurrentRoom().getContainers();
            if (!roomAvailableContainers.isEmpty()) {
                System.out.println(color.blue() + "\nAvailable Containers:" + color.reset());
                for (Container container : roomAvailableContainers) {
                    if (!container.requiresKey()) {
                        System.out.println(color.cyan() + "\t<> " + color.green() + container.toString() + color.reset());
                    } else {
                        System.out.println(color.cyan() + "\t<> " + color.red() + container.toString() + color.reset());
                    }
                }
            } else {
                System.out.println("\nThere are no containers in this room.");
            }
    }


// ==============================  Display all available NPCs  ============================== //


    private void displayNPC() {
        // search for all available NPCs in player's current location (room)
        List<NPC> availableNPC = player.getCurrentRoom().getNPCs();
        if (!availableNPC.isEmpty()) {
            System.out.println(color.blue() + "\nI can see people in this room\nLet's get closer to talk to them:" + color.reset());
            for (NPC npc : availableNPC) {
                System.out.println(color.cyan() + "\t<> " + color.gold() + npc.getName() + color.reset());
            }
        } else {
            System.out.println("\nThere is none in this room.");
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
            boolean foundItem = false;
            while (iterator.hasNext()) {
                Item item = iterator.next();
                if (noun.equalsIgnoreCase(item.getName())) {
                        foundItem = true;
                    if (item.isPickable()) {
                        player.addItemToInventory(item);
                        iterator.remove();
                    } else {
                        System.out.println("You can't pick up " + color.gold() + noun + color.reset());
                    }
                }
            }
            if (!foundItem){
                    System.out.println("There is no " + noun + " to take in this room.");
                }
            }
        }


// ==============================  Drop Item Method  ============================== //


    private void dropCommand(String noun) {
        if (noun == null) {
            System.out.println("Command DROP must have a noun.\nFor example LOOK KEY, DROP BROKEN WATCH");
        } else {
            List<Item> inventory = player.getInventory();
            boolean found = false;

            // Check if the item exist in player's inventory
            for (Item item : inventory) {
                if (noun.equalsIgnoreCase(item.getName())) {
                    inventory.remove(item);
                    player.getCurrentRoom().addItem(item);
                    found = true;
                    System.out.println("You dropped " + noun);
                    break;
                }
            }

            // If item does not exist into the inventory
            if (!found) {
                System.out.println("There is no " + noun + " in your inventory.\nIf you want to see your inventory you can use LOOK INV");
            }
        }
    }


// ==============================  Inspect Item Method  ============================== //


    private void inspectCommand(String noun) {
        if (noun == null) {
            System.out.println("Command INSPECT must have a noun.\nFor example INSPECT ROOM");
        }
        else if (noun.equals("room")) {

            System.out.println("It seems you are currently in " + player.getCurrentRoom().getName());
            lookForRoomItems();
            displayNPC();
            lookForRoomContainers();
            lookForExits();

        } else {
            System.out.println("This option for INSPECT command does not exist");
        }
    }


// ==============================  Go Method (direction)  ============================== //


    private void goCommand(String noun) {
        //                        System.out.println("[DEBUG]> Command GO selected");
        if (noun == null) {
            System.out.println("Command GO must have a noun.\nFor example GO EAST");
        }
        else if (directions.contains(noun)) {

            // Checks if the exit exists for the given direction
            Map<String, Room> exits = player.getCurrentRoom().getExit();
            if (exits.containsKey(noun)) {
                System.out.println("Going " + color.green() + noun + color.reset());
                // If the exit exists, moving to the next room
                player.setCurrentRoom(exits.get(noun));
                gameTurn = gameTurn + 1;
                player.setPlayerTurn(gameTurn);
                System.out.println("You are in turn: " + color.cyan() + player.getPlayerTurn() + color.reset());
                System.out.println("Game progress: " + player.getProgressPoints() + "/100");
                System.out.println("You entered the " + color.red() + player.getCurrentRoom().getName() + color.reset());

                displayNPC();

//                System.out.println("Containers: " + player.getCurrentRoom().getContainers().toString());

            } else {
                System.out.println("There is no exit in that direction.");
            }

        } else {
            System.out.println("this direction is not valid. You can use (north, south, east, west)");
        }
    }


// ==============================  Speak Method  ============================== //


    // When this method called the player can interact with the NPCs
    private void talkCommand(String noun) {
        if (noun == null) {
            System.out.println("Command SPEAK must have a noun.\nFor example SPEAK ANDERSON");
        } else {
            List<NPC> roomNPC = player.getCurrentRoom().getNPCs();
            boolean found = false;

            // Check if the npc exist in player's current room
            for (NPC npc : roomNPC) {
                if (noun.equalsIgnoreCase(npc.getName())) {
                    found = true;
//                    System.out.println("[DEBUG]> You speak with " + noun);
                    npc.talk(player.getName());
                    break;
                }
            }

            // If item does not exist into the inventory
            if (!found) {
                System.out.println("There is no such person in this room.\nIf you want to take look around you can use INSPECT ROOM");
            }
        }
    }


// ==============================  Help Method  ============================== //


    private void helpCommand() {
        System.out.println("Available commands:");
        for (String commandHelp : helpCmdList) {
            System.out.println(color.cyan() + "<> " + color.gold() + commandHelp + color.reset());
        }
        if (debug) {
            System.out.println();
            System.out.println(color.cyan() + "<> " + color.gold() + "add.progress <amount>\tFor example: add.progress 30"+ color.reset());
            System.out.println(color.cyan() + "<> " + color.gold() + "add.item <name>\t\t\tFor example: add.item Rusty Key"+ color.reset());
        }
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


// ============================== Load Game Method ============================== //


    public void loadGame(String fileName) {

        GameData loadedData = LoadSave.load(fileName);
        if (loadedData != null) {
            // Load Save successful
            this.rooms = loadedData.getRooms();
            this.player = loadedData.getPlayer();
            this.gameTurn = loadedData.getTurns();
            System.out.println("Welcome back, " + player.getName() + "!");
            System.out.println("You are currently in " + player.getCurrentRoom().getName());
            System.out.println("Turns lapsed: " + gameTurn);
            System.out.println("Use the 'help' command if you need more info about the available commands");
        } else {
            // Load Save failed
            System.out.println("Previous save not found. Starting a new game.");
            openingScene();
        }


    }


// ==============================  SaveGame Method  ============================== //


    public void saveGame(String fileName) {
        GameData gameData = saveData(rooms, player, gameTurn);
        SaveGame.save(gameData, fileName);
    }


// ==============================  SaveData Method  ============================== //


    private GameData saveData(List<Room> roomsToSave, Player playerData, int turns) {
        GameData gameData = new GameData(roomsToSave, playerData, turns);

        return gameData;
    }


// =========================  Checks Player's Progress Method  ========================= //


    private boolean checksEndgameProgress() {
        int playersProgress = player.getProgressPoints();
        if (playersProgress == 100) {
            System.out.println("Congratulations " + player.getName() + " you have won");
            won = true;
        }

        return won;
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
        Container mystery_box = new Container("Mystery Box", false, "");
        Container chest = new Container("Chest", false, "");
        Container chest2 = new Container("Chest2", true, "Rusty Key");
        Container drawer = new Container("Drawer", true, "Key1");
        bedroom_guest.addContainer(drawer);
        startingRoom.addContainer(chest);
        startingRoom.addContainer(chest2);
        startingRoom.addContainer(mystery_box);
        Container vase = new Container("Vase",false, "");
        Living_Room.addContainer(vase);
        Container Jewelry_box = new Container("Jewelry box",true,"");
        bedroom_1.addContainer(Jewelry_box);


        // NPC Initialization
        MrAnderson anderson = new MrAnderson("Anderson", false, player);
        SomeRandomGuy randomGuy = new SomeRandomGuy("RandomGuy", false, player);

        startingRoom.addNPC(anderson);
        startingRoom.addNPC(randomGuy);


        // Item initialization
          //Starting room Items
        Item key1 = new Item("Key1", "A small key. Maybe can be used to a bedroom drawer", true);
        Item suspicious_note = new Item("Suspicious Note", "I know what you are hiding ~JR", true);
        Item letter = new Item("Letter", "A letter for my beloved", true);
        Item wardrobe = new Item("Wardrobe", "Wardrobe",false);
        Item goldCoin = new Item("Gold Coin","A gold coin from an old era",true);
        Item silverCoin = new Item("Silver Coin","An old silver coin",true);
        Item knife = new Item("Small knife","A small knife",true);
        Item key = new Item ("Rusty Key", "It's just a key",true);
        Item broken_watch = new Item ("Broken watch", "A vintage broken watch",true);
        Item Mirror = new Item("Mirror","Mirror",false);
          //Hall_1 Items
        Item Portraits = new Item("Portraits","Portraits",false);
        Item Small_sofa = new Item("Small Sofa","Small sofa",false);
        Item Small_table = new Item("Small Table","A small table with a black telephone on top.",false);
        Item Black_telephone = new Item("Black telephone","A black telephone on the small table.",false);
        Item Paper_With_Number = new Item("Paper with phone number","A piece of paper with a phone number and the name Scarlet, fallen under the small table.",true);
          //Living room Items
        Item Sofa = new Item("Sofa","Sofa",false);
        Item Whiskey_glass = new Item("Whiskey glass","Whiskey glass with traces of red lips",true);
        Item Paintings = new Item("Paintings","Paintings on the wall",false);
        Item Porcelain_Vase = new Item ("Vase","A porcelain vase on the table.",false);
          //Kitchen Items
        Item Table = new Item("Table", "Table",false);
        Item smallKnife = new Item("knife","A sharp knife used for cutting.",true);
        Item Fridge = new Item("Fridge","A big white fridge.",false);
        Item Poisson = new Item("Poisson","A small bottle with Poisson.",true);
          //Dinning Room items
        Item Big_Table = new Item("Big Table","A large table with chairs",false);
        Item Wine_Bottle = new Item("Wine Bottle","Half a bottle of red wine",true);
        Item Fireplace = new Item("Fireplace","A large fireplace with a half-burned letter inside.",false);
        Item Burned_letter = new Item("Burnet letter","A half-burned letter inside the fireplace",true);
          //Wine Cellar items
        Item Shelves = new Item("Shelves with wines","Aged wines on shelves.",false);
        Item Tirbuson = new Item("Tirbuson","A tirbuson on a table with two glasses of wine.",true);
        Item Glasses_of_wine = new Item("Glasses of Wine","Two glasses of wine, one of which with a mark from red lipstick.",false);
         //Library items
        Item Bookcase = new Item("Bookcase","A big old bookcase with many books",false);
         //Office items
        Item Desc = new Item("Wooden Desc","A big wooden deck",false);
        Item Employee_List = new Item("Employee List","A list with names of staff",true);
        Item Telephone_list = new Item("Telephone List","A notebook with tell numbers",true);
         //Bedroom1 Items
        Item Bed = new Item("Bed","A double bed ",false);
        Item jewlery = new Item("jelery","Mrs. Smith's jewelry",true);






        // Adding items to containers
        mystery_box.addItem(silverCoin);
        mystery_box.addItem(smallKnife);
        chest2.addItem(goldCoin);
        vase.addItem(Porcelain_Vase);
        drawer.addItem(letter);
        Jewelry_box.addItem(jewlery);

        // Adding items to the rooms
        startingRoom.addItem(letter);
        startingRoom.addItem(suspicious_note);
        startingRoom.addItem(key);
        startingRoom.addItem(broken_watch);
        startingRoom.addItem(wardrobe);
        startingRoom.addItem(Mirror);

        hall_1.addItem(Portraits);
        hall_1.addItem(Small_sofa);
        hall_1.addItem(Small_table);
        hall_1.addItem(Black_telephone);
        hall_1.addItem(Paper_With_Number);

        Living_Room.addItem(Sofa);
        Living_Room.addItem(Whiskey_glass);
        Living_Room.addItem(Paintings);

        kitchen.addItem(Table);
        kitchen.addItem(knife);
        kitchen.addItem(Fridge);
        kitchen.addItem(Poisson);

        office.addItem(key1);

        dinningRoom.addItem(Big_Table);
        dinningRoom.addItem(Wine_Bottle);
        dinningRoom.addItem(Fireplace);
        dinningRoom.addItem(Burned_letter);

        wineCellar.addItem(Shelves);
        wineCellar.addItem(Tirbuson);
        wineCellar.addItem(Glasses_of_wine);

        library.addItem(Bookcase);

        office.addItem(Desc);
        office.addItem(Employee_List);
        office.addItem(Telephone_list);

        bedroom_1.addItem(Bed);

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


    private void promptPlayerName() {
        System.out.print("\nPlease enter the player's name: ");
        String playerName = scanner.nextLine();
        if (playerName.equals("")) {
            player.setName("Player");
        } else {
            player.setName(playerName);
        }
    }


// ==================================================================================================================

// Opening scene is the first scene when the player is new or it does not have a previous save
    private void openingScene () {
        Room startingRoom;
        promptPlayerName();

        startingRoom = rooms.get(0);
        player.setCurrentRoom(startingRoom);

        System.out.println("\nWelcome " + color.green() + player.getName() + color.reset() + "! Type" + color.cyan() + " 'help' " + color.reset() + "for available commands.");

        System.out.println("You are in turn: " + color.cyan() + gameTurn + color.reset());
        System.out.println("Game progress: " + color.cyan() + player.getProgressPoints() + "/100" + color.reset());
        System.out.println("You are now to " + color.red() + player.getCurrentRoom().getName() + color.reset());
        System.out.println(player.getCurrentRoom().getDescription());

        lookForRoomItems();
        lookForRoomContainers();
        displayNPC();

        Room currentRoom = player.getCurrentRoom();
        for (Room room : rooms) {
            if (room.equals(currentRoom)) {
//                System.out.println("Player.Player is currently in room: " + room.getName());

                break;
            }
        }
    }

}

