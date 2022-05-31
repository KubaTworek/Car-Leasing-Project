package pl.pjatk.Management;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataSource {
    private Connection conn = this.open();

    public Connection open() {
        try {
            Class.forName("org.sqlite.JDBC");
            Connection conn = DriverManager.getConnection("jdbc:sqlite:\\Users\\kubat\\IdeaProjects\\Car-Leasing-Project\\Resources\\CarLeasingDB.db");

            return conn;
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("Couldn't connect to database: " + e.getMessage());
            return null;
        }

    }
}
