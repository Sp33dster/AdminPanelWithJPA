package pl.sda.lodz9.adminPanel.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnector {

    private static final String URL = "jdbc:mysql://127.0.0.1:3306/adminPanelDB?useSLL=false&serverTimezone=UTC";
    private static final String LOGIN = "user";
    private static final String PASSWORD = "user";

    public static Connection getConection() {

        try {
            return DriverManager
                    .getConnection(URL,
                            LOGIN, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }




}
