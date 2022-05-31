package pl.pjatk.Car;

import pl.pjatk.Management.DataSource;

import java.sql.*;

public class CarDataSource extends DataSource {

    // TABLES

    public void createCarTables(){
        try (Connection conn = super.open();
             Statement statement = conn.createStatement()) {
            statement.execute("CREATE TABLE IF NOT EXISTS Car " +
                    " (idCar INTEGER PRIMARY KEY, YearProduced INTEGER, idModel INTEGER, FOREIGN KEY(idModel) REFERENCES Model(idModel))");
            statement.execute("CREATE TABLE IF NOT EXISTS SpecificCar " +
                    " (idSpecificCar INTEGER PRIMARY KEY, RegistrationNumber TEXT, Price REAL, isAvailable INTEGER, idCar INTEGER, FOREIGN KEY(idCar) REFERENCES Car(idCar))");
            statement.execute("CREATE TABLE IF NOT EXISTS Model " +
                    " (idModel INTEGER PRIMARY KEY, Model TEXT, Mark TEXT)");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    // CREATE
    public void insertSpecificCar(int idSpecificCar, String registrationNumber, double price, boolean isAvailable, int idCar) {
        String sql = "INSERT INTO SpecificCar(idSpecificCar,RegistrationNumber,Price,isAvailable,idCar) VALUES(?,?,?,?,?)";

        try (Connection conn = super.open();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, idSpecificCar);
            pstmt.setString(2, registrationNumber);
            pstmt.setDouble(3, price);
            pstmt.setBoolean(4, isAvailable);
            pstmt.setInt(5, idCar);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void insertCar(int idCar, int yearProduced, int idModel) {
        String sql = "INSERT INTO Car(idCar,YearProduced,idModel) VALUES(?,?,?)";

        try (Connection conn = super.open();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, idCar);
            pstmt.setInt(2, yearProduced);
            pstmt.setInt(3, idModel);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void insertModel(int idModel, String model, String mark) {
        String sql = "INSERT INTO Model(idModel,Model,Mark) VALUES(?,?,?)";

        try (Connection conn = super.open();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, idModel);
            pstmt.setString(2, model);
            pstmt.setString(3, mark);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    // READ
    public void selectCars(){
        String sql = "SELECT DISTINCT Mark, Model, YearProduced, RegistrationNumber, Price, isAvailable  FROM SpecificCar, Car, Model WHERE SpecificCar.idCar = Car.idCar ";

        try (Connection conn = super.open();
             Statement stmt  = conn.createStatement();
             ResultSet rs    = stmt.executeQuery(sql)){

            while (rs.next()) {
                System.out.println(rs.getString("Mark") + ", " +
                        rs.getString("Model") +  ", " +
                        rs.getInt("YearProduced") + ", " +
                        rs.getString("RegistrationNumber") + ", " +
                        (rs.getBoolean("isAvailable") ? rs.getDouble("Price") : "Niedostępny")
                );
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public int selectModelId(String mark, String model){
        String sql = "SELECT idModel FROM Model WHERE Mark = ? AND MODEL = ?";

        try (Connection conn = super.open();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, mark);
            pstmt.setString(2, model);
            ResultSet rs = pstmt.executeQuery();

            return rs.getInt("idModel");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return 0;
    }

    public int selectCarId(int yearProduced, int idModel){
        String sql = "SELECT idCar FROM Car WHERE YearProduced = ? AND idModel = ?";

        try (Connection conn = super.open();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, yearProduced);
            pstmt.setInt(2, idModel);
            ResultSet rs = pstmt.executeQuery();
            return rs.getInt("idCar");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return 0;
    }

    public int getNumberOfSpecificCars(){
        String sql = "SELECT MAX(idSpecificCar) FROM SpecificCar";

        try (Connection conn = super.open();
             Statement stmt  = conn.createStatement();
             ResultSet rs    = stmt.executeQuery(sql)){

            return rs.getInt(1);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return 0;
        }
    }

    public int getNumberOfCars(){
        String sql = "SELECT MAX(idCar) FROM Car";

        try (Connection conn = super.open();
             Statement stmt  = conn.createStatement();
             ResultSet rs    = stmt.executeQuery(sql)){

            return rs.getInt(1);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return 0;
        }
    }

    public int getNumberOfModels(){
        String sql = "SELECT MAX(idModel) FROM Model";

        try (Connection conn = super.open();
             Statement stmt  = conn.createStatement();
             ResultSet rs    = stmt.executeQuery(sql)){

            return rs.getInt(1);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return 0;
        }
    }

    public boolean isExistCar(String registrationNumber){
        String sql = "SELECT COUNT(idSpecificCar) FROM SpecificCar WHERE RegistrationNumber=?";

        try (Connection conn = super.open();
             PreparedStatement pstmt  = conn.prepareStatement(sql)) {

            pstmt.setString(1,registrationNumber);

            ResultSet rs  = pstmt.executeQuery();

            return rs.getInt(1) > 0;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public boolean getAvailablity(String registrationNumber){
        String sql = "SELECT isAvailable FROM SpecificCar WHERE RegistrationNumber = ?";

        try (Connection conn = super.open();
             PreparedStatement pstmt  = conn.prepareStatement(sql)){


            pstmt.setString(1,registrationNumber);

            ResultSet rs  = pstmt.executeQuery();

            return rs.getBoolean("isAvailable");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return true;
        }
    }


    // UPDATE
    public void updatePriceCar(String registrationNumber, double newPrice){
        String sql = "UPDATE SpecificCar SET Price = ? WHERE RegistrationNumber = ?";

        try (Connection conn = super.open();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setDouble(1, newPrice);
            pstmt.setString(2, registrationNumber);
            pstmt.executeUpdate();
            System.out.println("Cena została zatkualizowana");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void updateAvailability(String registrationNumber){
        String sql;
        if(getAvailablity(registrationNumber)){
            sql = "UPDATE SpecificCar SET isAvailable = 0 WHERE RegistrationNumber = ?";
        } else {
            sql = "UPDATE SpecificCar SET isAvailable = 1 WHERE RegistrationNumber = ?";
        }


        try (Connection conn = super.open();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, registrationNumber);
            pstmt.executeUpdate();
            System.out.println("Została zmieniona dostępność auta");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }


    // DELETE
    public void deleteCar(String registrationNumber) {
        String sql = "DELETE FROM SpecificCar WHERE RegistrationNumber = ?";

        try (Connection conn = super.open();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, registrationNumber);
            pstmt.executeUpdate();
            System.out.println("Usunięto samochód z bazy danych.");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
