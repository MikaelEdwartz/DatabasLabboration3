package se.iths;

import java.util.Scanner;

public class Deleter {
    private final static Scanner scanner = new Scanner(System.in);


    public static void deleteFromDatabase() {
        var choice = getIntInput(
                "===========================\n" +
                        "|1. Radera från spel      |\n" +
                        "|2. Radera från kategorier|\n" +
                        "===========================");
    }






    private static int getIntInput(String prompt) {
        System.out.println(prompt);
        return Integer.parseInt(scanner.nextLine());

    }

    private static String getStringInput(String prompt) {
        System.out.println(prompt);
        return scanner.nextLine();
    }
}
