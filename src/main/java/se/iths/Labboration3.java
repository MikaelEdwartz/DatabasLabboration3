package se.iths;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class Labboration3 {
    public static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            printMenu();
            var option = Integer.parseInt(scanner.nextLine());

            switch (option) {
                case 1 -> addToDatabase();
                case 2 -> readWholeDatabase();
                case 3 -> updateDatabase();
                case 4 -> deleteFromDatabase();

            }

        }

    }
    private static void addToDatabase() {
        var connector = connect();
        var titel = scanner.nextLine();
        var forfattare = scanner.nextLine();
        var pris = Integer.parseInt(scanner.nextLine());

        String sql = "INSERT INTO bok(bokTitel, bokForfattare, bokPris) VALUES(?,?,?)";

        try (PreparedStatement preparedStatement = connector.prepareStatement(sql)) {
            preparedStatement.setString(1, titel);
            preparedStatement.setString(2, forfattare);
            preparedStatement.setInt(3, pris);
            preparedStatement.executeUpdate();
            System.out.println("Tillagd!");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }

    private static void updateDatabase() {

    }

    private static void readWholeDatabase() {

    }

    private static void deleteFromDatabase() {
    }


    private static Connection connect() {

        var pathToSQLite = "jdbc:sqlite:/Users/mikaeledwartz/Desktop/Databas/SQLite/datbas";
        Connection connector = null;
        try {
            connector = DriverManager.getConnection(pathToSQLite);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return connector;
    }
    private static void printMenu() {
        System.out.println("1: Lägg till i databasen ");
        System.out.println("2: ");
        System.out.println("3: ");
        System.out.println("4: ");
        System.out.println("5: ");
    }
}
