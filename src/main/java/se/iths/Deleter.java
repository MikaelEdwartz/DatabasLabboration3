package se.iths;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class Deleter {
    private final static Scanner scanner = new Scanner(System.in);

    public static void deleteFromDatabase() {
        var choice = getIntInput(
                "===========================\n" +
                        "|1. Radera från spel      |\n" +
                        "|2. Radera från kategorier|\n" +
                        "===========================");

        switch (choice) {
            case 1 -> deleteFromGames(getIntInput("Vad är id't på spelet du vill radera?"));
            case 2 -> deleteFromCategories(getIntInput("Vad är id't på kategorin du vill radera?"));
        }
    }

    private static void deleteFromGames(int spelID) {
        var sql = "DELETE FROM spel WHERE spelID = ?";

        try (PreparedStatement preparedStatement = Connector.connect().prepareStatement(sql)) {

            preparedStatement.setInt(1, spelID);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private static void deleteFromCategories(int kategoriID) {
        var sql = "DELETE FROM kategori WHERE kategoriID = ?";

        try (PreparedStatement preparedStatement = Connector.connect().prepareStatement(sql)) {

            preparedStatement.setInt(1, kategoriID);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private static int getIntInput(String prompt) {
        System.out.println(prompt);
        return Integer.parseInt(scanner.nextLine());

    }

}
