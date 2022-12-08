package se.iths;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

import static se.iths.Connector.connect;
public class Inserter {
        private final static Scanner scanner = new Scanner(System.in);
    public static void addToDatabase() {
        System.out.println("I vilken databas vill du lägga till i?" +
                "\n 1. Spel \n" +
                " 2. Kategorier");
        var input = Integer.parseInt(scanner.nextLine());

        switch (input) {
            case 1 -> addToGames();
            case 2 -> updateCategories();
        }
    }

    private static void updateCategories() {
        insert(getStringInput("Vad heter kategorin?"));
    }

    private static void addToGames() {
        insert(getStringInput("Namn på spelet:"),
                getStringInput("Namn på tillverkaren:"),
                getIntInput("Vilket år släpptes spelet?"),
                getIntInput("Vad för kategori tillhör spelet?"));
    }
    private static void insert(String name, String manufacturor, int releaseYear, int categoryNumber) {
        String sql = "INSERT INTO spel(spelNamn, spelTillverkare, spelSläpptesÅr, spelkategori) VALUES(?,?,?,?)";

        var connector = connect();
        try (PreparedStatement preparedStatement = connector.prepareStatement(sql)) {
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, manufacturor);
            preparedStatement.setInt(3, releaseYear);
            preparedStatement.setInt(4, categoryNumber);
            preparedStatement.executeUpdate();
            System.out.println("Tillagd!");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void insert(String kategori) {
        String sql = "INSERT INTO kategori (kategori) VALUES(?)";


        try (PreparedStatement preparedStatement = connect().prepareStatement(sql)) {
            preparedStatement.setString(1, kategori);
            preparedStatement.executeUpdate();
            System.out.println("Tillagd!");
        } catch (SQLException e) {
            throw new RuntimeException(e);
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

}
