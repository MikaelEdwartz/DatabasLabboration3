package se.iths;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class Updater {
    private final static Scanner scanner = new Scanner(System.in);

    public static void updateDatabase() {

        var choice = getIntInput(
                "=========================\n" +
                        "|1. Uppdatera spel      |\n" +
                        "|2. Uppdatera kategorier|\n" +
                        "=========================");

        switch (choice){
            case 1 -> updateGameCategory();
            case 2 -> updateCategoriesCategory();
        }

    }


    private static void updateGameCategory() {
        update(getIntInput("Vilken kolumn vill du uppdatera?"),
               getStringInput("Vad vill du uppdatera namnet till?"),
               getStringInput("Vad vill du uppdatera tillverkaren till?"),
               getIntInput("Vilken kategori vill du uppdatera till?"),
               getIntInput("Vilket år vill du uppdatera till?"));

    }
    private static void updateCategoriesCategory() {
        update(getIntInput("Vilken kategori vill du uppdatera?"),
               getStringInput("Vad vill du uppdatera kategorin till?"));
    }
    private static void update(int iD,String name, String manufacturor, int category, int releaseYear){
        var sql = "UPDATE spel SET spelNamn = ?, spelTillverkare = ?, spelSläpptesÅr = ?, spelKategori = ? WHERE spelID = ?";

        try(PreparedStatement preparedStatement = Connector.connect().prepareStatement(sql)){
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, manufacturor);
            preparedStatement.setInt(3, releaseYear);
            preparedStatement.setInt(4, category);
            preparedStatement.setInt(5, iD);
            preparedStatement.executeUpdate();
            System.out.println("Done!");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    private static void update(int iD, String category){
        var sql = "UPDATE kategori SET kategori = ? WHERE kategoriID = ?";

        try(PreparedStatement preparedStatement = Connector.connect().prepareStatement(sql)){
            preparedStatement.setString(1, category);
            preparedStatement.setInt(2, iD);
            preparedStatement.executeUpdate();
            System.out.println("Uppdaterat!");

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
