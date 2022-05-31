package pl.pjatk.Client;

import pl.pjatk.Management.DataSource;

import java.sql.*;

public class ClientDataSource extends DataSource {

    // TABLES

    public void createClientTables() {
        try (Connection conn = super.open();
             Statement statement = conn.createStatement()) {
            statement.execute("CREATE TABLE IF NOT EXISTS Client " +
                    " (idClient INTEGER PRIMARY KEY, ClientName TEXT, NIP TEXT, idClientFinancial INTEGER, FOREIGN KEY(idClientFinancial) REFERENCES ClientFinance(idClientFinancial))");
            statement.execute("CREATE TABLE IF NOT EXISTS ClientFinance " +
                    " (idClientFinancial INTEGER PRIMARY KEY, maxCash REAL, maxRate REAL)");
            statement.execute("CREATE TABLE IF NOT EXISTS Users " +
                    " (idUser INTEGER PRIMARY KEY, UserName TEXT, Surname TEXT, PESEL TEXT, idClient INTEGER, FOREIGN KEY(idClient) REFERENCES Client(idClient))");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    // CREATE
    public void insertClient(int idClient, String clientName, String nip, int idClientFinancial) {
        String sql = "INSERT INTO Client(idClient,ClientName,NIP,idClientFinancial) VALUES(?,?,?,?)";

        try (Connection conn = super.open();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, idClient);
            pstmt.setString(2, clientName);
            pstmt.setString(3, nip);
            pstmt.setInt(4, idClientFinancial);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void insertClientFinance(int idClientFinancial, double maxCash, double maxRate) {
        String sql = "INSERT INTO ClientFinance(idClientFinancial,maxCash,maxRate) VALUES(?,?,?)";

        try (Connection conn = super.open();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, idClientFinancial);
            pstmt.setDouble(2, maxCash);
            pstmt.setDouble(3, maxRate);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void insertUser(int idUser, String userName, String surname, String pesel, int idClient) {
        String sql = "INSERT INTO Users(idUser,UserName,Surname,PESEL,idClient) VALUES(?,?,?,?,?)";

        try (Connection conn = super.open();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, idUser);
            pstmt.setString(2, userName);
            pstmt.setString(3, surname);
            pstmt.setString(4, pesel);
            pstmt.setInt(5, idClient);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }


    // READ
    public void selectClients() {
        String sql = "SELECT DISTINCT ClientName, NIP FROM Client";

        try (Connection conn = super.open();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                System.out.println(rs.getString("ClientName") + ", NIP: " +
                        rs.getString("NIP"));
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public int getNumberOfClients() {
        String sql = "SELECT MAX(idClient) FROM Client";

        try (Connection conn = super.open();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            return rs.getInt(1);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return 0;
        }
    }

    public int getNumberOfUsers() {
        String sql = "SELECT MAX(idUser) FROM Users";

        try (Connection conn = super.open();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            return rs.getInt(1);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return 0;
        }
    }

    public int getNumberOfClientFinances() {
        String sql = "SELECT MAX(idClientFinancial) FROM ClientFinance";

        try (Connection conn = super.open();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            return rs.getInt(1);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return 0;
        }
    }


    // UPDATE


    // DELETE
    public void deleteClient(String nip) {
        String sql = "DELETE FROM Client WHERE NIP = ?";

        try (Connection conn = super.open();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, nip);
            pstmt.executeUpdate();
            System.out.println("Usunięto klienta z bazy danych.");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }


}
