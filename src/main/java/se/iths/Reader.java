package se.iths;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import static se.iths.Connector.connect;

public class Reader {
    private static Scanner scanner = new Scanner(System.in);

    public static void readFromDatabase() {
        var choice = getIntInput(
                "=========================\n" +
                        "|1. Hela databasen      |\n" +
                        "|2. Spel databasen      |\n" +
                        "|3. kategori databasen  |\n" +
                        "=========================");
        switch (choice) {
            case 1 -> readInnerJoin();
            case 2 -> readfromGames();
            case 3 -> readFromCategories();
        }
    }

    private static void readInnerJoin() {
        var sql = "SELECT spelnamn, spelTillverkare, spelSläpptesÅr," +
                " kategori FROM kategori INNER JOIN spel ON kategori.kategoriID = spel.spelKategori";

        try (Statement statement = connect().createStatement()) {
            System.out.println("Spel" + printTab(getNrOfTabs("Spel")) +
                    "Utvecklare" + printTab(getNrOfTabs("Utvecklare")) +
                    "Släpptes" + printTab(getNrOfTabs("Släpptes")) + "Kategori");

            var resultSet = statement.executeQuery(sql);
            while (resultSet.next())
                printInnerJoinColumn(resultSet);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private static void printInnerJoinColumn(ResultSet resultSet) throws SQLException {
        System.out.println(resultSet.getString("spelNamn") +
                printTab(getNrOfTabs(resultSet.getString("spelNamn"))) +
                resultSet.getString("spelTillverkare") +
                printTab(getNrOfTabs(resultSet.getString("spelTillverkare"))) +
                resultSet.getInt("spelSläpptesÅr") +
                printTab(getNrOfTabs(String.valueOf(resultSet.getInt("spelSläpptesÅr"))))+
                resultSet.getString("kategori"));
    }

    private static void readfromGames() {

        var sql = "SELECT * FROM spel";

        try (Statement statement = connect().createStatement()) {
            var resultSet = statement.executeQuery(sql);
            System.out.println("Spel" + printTab(getNrOfTabs("Spel")) + "Utvecklare" + printTab(getNrOfTabs("Utvecklare")) + "Släpptes");

            while (resultSet.next())
                printGameColumns(resultSet);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    private static void printGameColumns(ResultSet resultSet) throws SQLException {
        System.out.println(resultSet.getString("spelNamn") +
                printTab(getNrOfTabs(resultSet.getString("spelNamn"))) +
                resultSet.getString("spelTillverkare") +
                printTab(getNrOfTabs(resultSet.getString("spelTillverkare"))) +
                resultSet.getInt("spelSläpptesÅr") +
                printTab(getNrOfTabs(String.valueOf(resultSet.getInt("spelSläpptesÅr")))));
    }


    private static void readFromCategories() {
        String sql = "SELECT * FROM kategori";

        try (Statement statement = connect().createStatement()) {

            var resultSet = statement.executeQuery(sql);
            System.out.println("\tkategorier");

            while (resultSet.next()) {
                System.out.println(resultSet.getInt("kategoriID") + "\t" +
                        resultSet.getString("kategori"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public static void searchFromDatabase() {
        var choice = getIntInput(
                "=============================\n" +
                        "|1. Sök bland spel         |\n" +
                        "|2. Sök bland kategorier   |\n" +
                        "|3. Se spel efter ett årtal|\n" +
                        "============================");

        switch (choice) {
            case 1 -> searchInGames(getStringInput("Vad heter spelet du vill söka på?"));
            case 2 -> searchInCategories(getStringInput("Vilken kategori vill du söka på?"));
            case 3 -> searchGamesSinceYear(getIntInput("Vilket är det tidigaste året du vill se spel från?"));

        }
    }

    private static void searchGamesSinceYear(int year) {
        var sql = "SELECT spelNamn FROM spel WHERE spelSläpptesÅr >= ?";

        try (PreparedStatement preparedStatement = connect().prepareStatement(sql)) {
            preparedStatement.setInt(1, year);
            var resultSet = preparedStatement.executeQuery();
            System.out.println("Spel släppta efter år " + year + ":");
            while (resultSet.next())
                System.out.println(resultSet.getString("spelnamn"));

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private static void searchInGames(String name) {
        var sql = "SELECT * FROM spel WHERE spelNamn = ?";

        try (PreparedStatement preparedStatement = connect().prepareStatement(sql)) {
            preparedStatement.setString(1, name);
            var resultSet = preparedStatement.executeQuery();
            System.out.println("Spel" + printTab(getNrOfTabs("Spel")) + "Utvecklare" + printTab(getNrOfTabs("Utvecklare")) + "Släpptes");
            while (resultSet.next()) {
                printGameColumns(resultSet);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private static void searchInCategories(String category) {
        var sql = "SELECT * FROM kategori WHERE kategori = ?";

        try (PreparedStatement preparedStatement = connect().prepareStatement(sql)) {

            preparedStatement.setString(1, category);
            var resultSet = preparedStatement.executeQuery();

            System.out.println("\tkategorier");

            while (resultSet.next()) {
                System.out.println(resultSet.getInt("kategoriID") + "\t" +
                        resultSet.getString("kategori"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    private static String getStringInput(String prompt) {
        System.out.println(prompt);
        return scanner.nextLine();
    }

    private static int getIntInput(String prompt) {
        System.out.println(prompt);
        return Integer.parseInt(scanner.nextLine());
    }

    private static String printTab(int nrOfTabs) {
        return "\t".repeat(Math.max(0, nrOfTabs));
    }

    private static int getNrOfTabs(String string) {
        int length = string.length();
        if (length >= 16)
            return 1;
        if (length >= 12)
            return 2;
        if (length >= 8)
            return 3;
        if (length >= 4)
            return 4;

        return 0;
    }
}
