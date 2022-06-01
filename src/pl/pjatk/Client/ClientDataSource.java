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

    public void selectUsers() {
        String sql = "SELECT DISTINCT UserName, Surname, PESEL, ClientName FROM Users, Client WHERE Users.idClient = Client.idClient ORDER BY ClientName";

        try (Connection conn = super.open();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                System.out.println(rs.getString("ClientName") + ": " +
                        rs.getString("UserName") + " " +
                        rs.getString("Surname") + ", PESEL: " +
                        rs.getString("PESEL"));
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public int selectClientId(String nip){
        String sql = "SELECT idClient FROM Client WHERE NIP = ?";

        try (Connection conn = super.open();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, nip);
            ResultSet rs = pstmt.executeQuery();
            return rs.getInt("idClient");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return 0;
    }

    public int selectClientFinanceId(String nip){
        String sql = "SELECT idClientFinancial FROM Client WHERE NIP = ?";

        try (Connection conn = super.open();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, nip);
            ResultSet rs = pstmt.executeQuery();
            return rs.getInt("idClientFinancial");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return 0;
    }

    public double selectMaxCash(int idClient){
        String sql = "SELECT maxCash FROM ClientFinance, Client WHERE Client.idClientFinancial = ClientFinance.idClientFinancial AND Client.idClient = ?";

        try (Connection conn = super.open();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, idClient);
            ResultSet rs = pstmt.executeQuery();
            return rs.getDouble("maxCash");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return 0;
    }

    public double selectMaxRate(int idClient){
        String sql = "SELECT maxRate FROM ClientFinance, Client WHERE Client.idClientFinancial = ClientFinance.idClientFinancial AND Client.idClient = ?";

        try (Connection conn = super.open();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, idClient);
            ResultSet rs = pstmt.executeQuery();
            return rs.getDouble("maxRate");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return 0;
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

    public boolean isExistClient(String nip){
        String sql = "SELECT COUNT(idClient) FROM Client WHERE NIP=?";

        try (Connection conn = super.open();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, nip);
            ResultSet rs = pstmt.executeQuery();

            return rs.getInt(1) > 0;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public boolean isExistUser(String pesel){
        String sql = "SELECT COUNT(idUser) FROM Users WHERE PESEL=?";

        try (Connection conn = super.open();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, pesel);
            ResultSet rs = pstmt.executeQuery();

            return rs.getInt(1) > 0;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }


    // UPDATE
    public void updateFinance(int clientFinanceId, double maxCash, double maxRate){
        String sql = "UPDATE ClientFinance SET maxCash = ?, maxRate = ? WHERE idClientFinancial = ? ";

        try (Connection conn = super.open();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setDouble(1, maxCash);
            pstmt.setDouble(2, maxRate);
            pstmt.setInt(3, clientFinanceId);
            pstmt.executeUpdate();
            System.out.println("Finanse zostały zaktualizowane");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }


    // DELETE
    public void deleteClient(String nip) {
        String sql1 = "DELETE FROM Client WHERE NIP = ?";

        try (Connection conn = super.open();
             PreparedStatement pstmt1 = conn.prepareStatement(sql1)) {

            deleteUsersFromCompany(selectClientId(nip));
            pstmt1.setString(1, nip);
            pstmt1.executeUpdate();

            System.out.println("Usunięto klienta z bazy danych.");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void deleteUser(String pesel) {
        String sql = "DELETE FROM Users WHERE PESEL = ?";

        try (Connection conn = super.open();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, pesel);
            pstmt.executeUpdate();
            System.out.println("Usunięto użytkownika z bazy danych.");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void deleteUsersFromCompany(int idClient) {
        String sql = "DELETE FROM Users WHERE idClient = ?";

        try (Connection conn = super.open();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, idClient);
            pstmt.executeUpdate();
            System.out.println("Usunięto użytkowników z bazy danych.");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }


}
