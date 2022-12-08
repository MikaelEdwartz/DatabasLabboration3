package se.iths;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connector {
    public static Connection connect() {

        var pathToSQLite = "jdbc:sqlite:/Users/mikaeledwartz/Desktop/Databas/SQLite/datbas";
        Connection connector = null;
        try {
            connector = DriverManager.getConnection(pathToSQLite);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return connector;
    }
}
