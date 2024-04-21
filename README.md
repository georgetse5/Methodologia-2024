# Adventure text based Zork like game (A brief description)

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
- Player.Player
- Container
- NPC

<hr>

- <h3>Main class </h3>
  
  - There appears the Starting banner of the game, and the initialization of the GameLoop class.
  
```
        new GameLoop();
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

  // Adding items to the rooms
  startingRoom.addItem(broken_watch);

  List<Room> rooms = new ArrayList<>();

  // Adding the rooms to the list rooms
  rooms.add(startingRoom);
  rooms.add(hall_1);
  rooms.add(Living_Room);

        return rooms;
    }
```

<h4>Available methods:</h4>

- run()
- processCommand()
- initializeMap()

<hr>

- <h3>Rooms package</h3>
  
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
```

<h4>Available methods:</h4>

- getName()
- getDescription()
- addExit()
- getExit()
- addItem()
- removeItem()
- getItems()
    
<hr>

- <h3>Player.Player Class</h3>
  
  - Name, Personal data, Inventory, Current room

<h4>Available methods:</h4>

- getName()
- getInventory()
- setCurrentRoom()
- getCurrentRoom()
- addItemToInventory()
- removeItemFromInventory()
- listInventory()
    
<hr>

- <h3>Items Class</h3>

<h4>Available methods:</h4>

- getName()
- getDescription()

<hr>

- <h3>Container Class</h3>

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

- <h3>NPC Class</h3>
    - The NPC class can be used to create different npc which the player can interact, taking quests, 
      items and more. Could be one class or different classes like the Room for more advanced and unique features, dialogues and quests
      can also give the ability for easy code maintainability and to read.
      
    - <h4>Available NPCs</h4>
      - Nothing yet

<hr>
