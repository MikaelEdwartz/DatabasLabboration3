package se.iths;

import java.util.Scanner;

public class Labboration3 {
    public static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true)
            findAndExecuteChoice();

    }

    private static void findAndExecuteChoice() {
        printMenu();
        executeChoice();
    }

    private static void executeChoice() {
        var option = Integer.parseInt(scanner.nextLine());
        switch (option) {
            case 1 -> Inserter.addToDatabase();
            case 2 -> Reader.readFromDatabase();
            case 3 -> Updater.updateDatabase();
            case 4 -> Deleter.deleteFromDatabase();
            case 5 -> Reader.searchFromDatabase();
        }
    }

    private static void printMenu() {
        System.out.println("===========================");
        System.out.println("|1: Lägg till i databasen |");
        System.out.println("|2: Läs från databasen    |");
        System.out.println("|3: Uppdatera databasen   |");
        System.out.println("|4: Radera från databasen |");
        System.out.println("|5: Sök i databasen       |");
        System.out.println("===========================");
    }
}
