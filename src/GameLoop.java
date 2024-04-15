import java.util.*;
import java.util.ArrayList;
import java.util.Scanner;

public class GameLoop {
    private final Scanner scanner;
    private List<String> processesedCmd = new ArrayList<>();

    public GameLoop() { ;
        scanner = new Scanner(System.in);
        run();
    }


    private void run() {
        System.out.println("\nWelcome adventurer! Type 'help' for available commands.");


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
                        System.out.println("- help");
                    break;
                case "go":
                        System.out.println("Command GO selected");
                    break;
                case "take":
                        System.out.println("Command TAKE selected");

                    break;
            }

        }

        scanner.close();
    }


    public List<String> processCommand(String inputCommand) {
        List<String> processedCommand = new ArrayList<>();
        String[] parts = inputCommand.split("\\s+", 2);

        try {

            if (parts.length >= 1) {
                String verb = parts[0];
                if (parts.length >= 2) {
                    String noun = parts[1];

                    System.out.println("The verb is: " + verb);
                    System.out.println("The noun is: " + noun);

                    processedCommand.add(verb);
                    processedCommand.add(noun);
                } else {
                    System.out.println("The verb is: " + verb);
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


    private void initializeMap() {

    }
}

