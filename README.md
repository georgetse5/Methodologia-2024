# Adventure text based Zork like game (A brief description)

<hr>
<b><u>The ReadMe file is currently outdated</u></b>

- (Note) Every Room/scene need to have a different class 

## Architecture
The program consists from different classes such as GameLoop, Main, Items, Containers, Exits and the classes of the
rooms with the class Room as superclass for the common attributes of all classes.

### List of the available and planned Classes
- Main
- GameLoop
- Room
  - StartingRoom
  - Hall_1
  - Kitchen
  - Cellar
  - Living_Room
  - Dinning_Room 
  - Hall_2
  - Library
  - Office
  - Secret_Room
  - Bedroom_1
  - Bedroom_2
  - Secondary_Room_1 (It's like "bridge" between the Bedroom_1 and Bedroom_3)
  - Secondary_Room_2 (It's like "bridge" between the Bedroom_2 and Staff_Bedroom)
  - Bedroom_3
  - Staff_Bedroom
  - Attic
- Items
- Player
- Container
- NPC
- Quest
- GuiMap
- GameData
- LoadSave
- SaveGame

<hr>

- <h3>Main class </h3>
  
  - There appears the Starting banner of the game, and the initialization of the GameLoop class and the call of the startGame method.
  
```
        GameLoop gameLoop = new GameLoop();
        gameLoop.startGame();
```

<hr>

- <h3>GameLoop Class</h3>
  
  - There will be the main game loop. The initialization of the Rooms, the infinite loop which runs until
    the player exit the game, the input and the analysis of the player prompt. Also, the decision-making
    about the navigation based on players prompt all made here.
    
Extra Rooms can be added into the initializeMap() method like the code snippet bellow, by changing
the size of the rooms list and make the following steps:
```
  // For example (code snippet from the project)
  
  //Initializing the rooms by giving them as arguments Name and Description
        Room Living_Room = new Living_Room("Living Room", "Living Room");
        Room hall_1 = new Hall_1("Hall 1 ", "First Room");
        Room startingRoom = new StartingRoom("Starting Room", "This is where your adventure begins.");

  // Add exits to the rooms
        hall_1.addExit("south",Living_Room);

        startingRoom.addExit("east", hall_1);

  // Item initialization
        Item broken_watch = new Item ("Broken watch", "A vintage broken watch");
        
  // Container initialization
        Container chest = new Container("Chest", true, "Rusty Key");
  // Adding items to container
        chest.addItem(broken_watch);
  // Adding the container to the room
        startingRoom.addContainer(chest);
  
  // NPC Initialization
        MrAnderson anderson = new MrAnderson("Anderson", false, player);
        SomeRandomGuy randomGuy = new SomeRandomGuy("RandomGuy", false, player);

        startingRoom.addNPC(anderson);
        startingRoom.addNPC(randomGuy);

  // Adding items to the rooms
        startingRoom.addItem(broken_watch);

  List<Room> rooms = new ArrayList<>();

  // Adding the rooms to the ArrayList rooms
        rooms.add(startingRoom);
        rooms.add(hall_1);
        rooms.add(Living_Room);

        return rooms;
    }
```

<br>
Now the switch has changed to:

```
                     switch (verb.toLowerCase()) {
    // Help Command
                    case "help":
                        helpCommand();
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
```

<h4>Available methods:</h4>

- <b>run()</b>
- <b>mainLoop()</b>
  - Main loop which contains the above switch code block
- <b>openCommand()</b>
  - Opens containers which they can contain useful items. Makes all the necessary checks
  if the container is locked or unlocked. If it's locked It searches into the inverntory for
    the required item. If the item found then opens it and takes all the items into the
    players inventory
- <b>lookGUIMap()</b>
  - It creates an instance of Java awt GUI map which shows an image of the visualized
  game's map.
- <b>lookForExits()</b>
  - Checks for all available exits of player's current room
- <b>lookForRoomItems()</b>
  - Checks for all available items of player's current room
- <b>lookForRoomContainers()</b>
  - Checks for all available containers of player's current room
- <b>displayNPC()</b>
  - Checks for all available NPCs of player's current room
- <b>takeCommand()</b>
  - It checks if an items is in the current room and if there is then it took it 
    into the inventory
- <b>dropCommand()</b>
  - drops an item from the inventory into the current room
- <b>inspectCommand()</b>
  - Inspects kai display for every available item, npc, container, 
    exits in the current room
- <b>goCommand()</b>
  - navigation command from one room to another
- <b>talkCommand()</b>
  - Player can interact with the NPCs
- <b>helpCommand()</b>
- <b>processCommand()</b>
  - Process the player's prompt, separates the verb and the noun and returns
  a list of 2 elements
- <b>loadSave()</b>
- <b>saveGame()</b>
- <b>saveData()</b>
- <b>initializeMap()</b>
- <b>promptPlayerName()</b>
- <b>openingScene()</b>

<hr>

- <h3> Rooms package </h3>
  
  - There are all the classes of every room.
    
The super class has the basic constructor, methods and arguments such as Name, Description, 
adding-getting exits, adding-getting containers.

The sub-classes extends the superclass and also can have extra features like a special item or objective
of a quest.

On registering of the exits used a HashMap which can store the direction and also the destination which leads to.
```
Map<String, Room> exits;

public void addExit(String direction, Room destination) {
        exits.put(direction, destination);
    }
    
    
// Code block snipet from GameLoop
// Add exits to the rooms
//Room1
        startingRoom.addExit("east", hall_1);
//Main Hallway
        hall_1.addExit("west", startingRoom);
        hall_1.addExit("north", dinningRoom);
        hall_1.addExit("south",Living_Room);
        hall_1.addExit("upstairs" , hall_2);
```

<h4> Available methods: </h4>

- getName()
- getDescription()
- addExit()
- getExit()
- addItem()
- removeItem()
- getItems()
- addContainer()
- removeContainer()
- getContainer()
- addNPC()
- removeNPC()
- getNPCs()
    
<hr>

- <h3> Player Class </h3>
  
  - Name, Personal data, Inventory, Current room

<h4>Available methods:</h4>

- setName()
- getName()
- getInventory()
- setCurrentRoom()
- getCurrentRoom()
- addItemToInventory()
- removeItemFromInventory()
- listInventory()
    
<hr>

- <h3> Items Class </h3>

<h4>Available methods:</h4>

- getName()
- getDescription()
- isPickable()

<hr>

- <h3> Container Class </h3>

Player.Player can find and pickup stored items which can help the scenario and his progress.

The available parameters of the constructor:

```
Container(String name, boolean requiresKey, String key)
```

Initialize a container in GameLoop class, adding an item, and the container
to room:

```
Container testingContainer = new Container("Mystery Box", false, "");

Item item = new Item("item", "An item for testing purposes");
testingContainer.addItem(item);
startingRoom.addContainer(testingContainer);
```

<h4>Available methods:</h4>

- requiresKey()
- setAsUnlocked()
- getKey()
- getItems()
- addItem()
- removeItem()
- containsItem()

<hr>

- <h3> NPC Class </h3>
    - The NPC class can be used to create different npc which the player can interact, taking quests, 
      items and more. Could be one class or different classes like the Room for more advanced and unique features, dialogues and quests
      can also give the ability for easy code maintainability and to read.
      
    - <h4>Available NPCs Classes</h4>
      
      - NPC
        - talk()
        - getIntro()
        - setIntro()
        - getName()
        - introductoryMessage()
        - farewell()
        - askQuestion()
        - answerQuestion()
        - selectOption
      - MrAnderson
        - talk()
        - selectOption()
        - introductoryMessage()
        - startQuest()
        - checkQuestProgress()
        - startSecondQuest()
        - checkSecondQuestProgress()
        - familySmithInfo()
      - SomeRandomGuy (It's just for testing purposes)

<hr>

- <h3> Quests Package </h3>

- <h4>Available Quest Classes</h4>
  
  - Quest
  - ExamineQuestSmith
  - ExamineQuestSmith_2
  
<hr>