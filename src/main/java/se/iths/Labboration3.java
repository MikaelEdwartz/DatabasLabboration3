package se.iths;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class Labboration3 {
    public static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

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
