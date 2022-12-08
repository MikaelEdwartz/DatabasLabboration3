package se.iths;

import java.sql.*;
import java.util.Scanner;

public class Labboration3 {
    public static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            printMenu();
            var option = Integer.parseInt(scanner.nextLine());

            switch (option) {
                case 1 -> Inserter.addToDatabase();
                case 2 -> Reader.readFromDatabase();
                case 3 -> Updater.updateDatabase();
                case 4 -> Deleter.deleteFromDatabase();

            }

        }

    }


    private static int getIntInput(String prompt) {
        System.out.println(prompt);
        return Integer.parseInt(scanner.nextLine());

    }

    private static String getStringInput(String prompt) {
        System.out.println(prompt);
        return scanner.nextLine();
    }

    private static void printMenu() {
        System.out.println("===========================");
        System.out.println("|1: Lägg till i databasen |");
        System.out.println("|2: Läs från databasen    |");
        System.out.println("|3: Uppdatera databasen   |");
        System.out.println("|4: Radera från databasen |");
        System.out.println("===========================");
    }
}
