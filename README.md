# Adventure text based Zork like game (A brief description)

- (Note) Every Room/scene need to have a different class 

## Architecture
The program consists from different classes such as GameLoop, Main, Items, Containers, Exits and the classes of the
rooms with the class Room as superclass for the common attributes of all classes.

### List of the available and planned Classes
- Main
- GameLoop
- Room
   - Starting Room
   - Room 2
   - Room 3
   - etc
- Items
- Player
- Container
- NPC

<hr>

- <h3>Main class </h3>
  
  - There appears the Starting banner of the game, and the initialization of the GameLoop class.
  
```
    System.out.println(banner);
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
  Room startingRoom = new StartingRoom("Starting Room", "This is where your adventure begins.");
  Room hallOfFame = new HallOfFameRoom("Hall Of Fame", "A Great Place for great people");

  // Adding the exits to startingRoom
  startingRoom.addExit("east", hallOfFame);

  // Adding the room instances into the rooms List
  rooms[0] = startingRoom;
  rooms[1] = hallOfFame;

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
    
<hr>

- <h3>Player Class</h3>
  
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
    - Nothing yet

<hr>

- <h3>NPC Class</h3>
    - The NPC class can be used to create different npc which the player can interact, taking quests, 
      items and more. Could be one class or different classes like the Room for more advanced and unique features, dialogues and quests
      can also give the ability for easy code maintainability and to read.
      
    - <h4>Available NPCs</h4>
      - Nothing yet

<hr>
