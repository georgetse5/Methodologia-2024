import java.util.Scanner;

public class GameLoop {
    private Scanner scanner;

    public GameLoop(Room startingRoom) {
        scanner = new Scanner(System.in);
        run();
    }

    private void run() {
        System.out.println("Welcome adventurer! Type 'help' for available commands.");


        while (true) {
            System.out.print("\n>> ");
            String command = scanner.nextLine();


            if (command.equalsIgnoreCase("quit")) {
                System.out.println("Exiting the game...");
                break;
            }

            switch (command.toLowerCase()) {
                case "help":
                    System.out.println("Available commands:");
                    System.out.println("- go (north, south, west, east)");
                    System.out.println("- take [item]");
                    System.out.println("- look [a, around]");
                    System.out.println("- help");
                    break;

            }


            scanner.close();
        }
    }
}

