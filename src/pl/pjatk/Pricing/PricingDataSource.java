package pl.pjatk.Pricing;

import pl.pjatk.Management.DataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class PricingDataSource extends DataSource {

    //TABLES
    public void createPricingTables() {
        try (Connection conn = super.open();
             Statement statement = conn.createStatement()) {
            statement.execute("CREATE TABLE IF NOT EXISTS IndividualPricing " +
                    " (idClient INTEGER , PriceInit REAL, Rate REAL, AmountOfRate INTEGER, LastRate REAL, idSpecificCar INTEGER, " +
                    "FOREIGN KEY(idClient) REFERENCES Client(idClient),  " +
                    "FOREIGN KEY(idSpecificCar) REFERENCES SpecificCaar(idSpecificCar))");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    // CRUD
    //CREATE
    public void insertPricing(int idClient, double priceInit, double rate, int amountOfRate, double lastRate, int idSpecificCar){
        String sql = "INSERT INTO IndividualPricing(idClient,PriceInit,Rate,AmountOfRate,LastRate,idSpecificCar) VALUES(?,?,?,?,?,?)";

        try (Connection conn = super.open();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, idClient);
            pstmt.setDouble(2, priceInit);
            pstmt.setDouble(3, rate);
            pstmt.setInt(4, amountOfRate);
            pstmt.setDouble(5, lastRate);
            pstmt.setInt(6, idSpecificCar);
            pstmt.executeUpdate();

            System.out.println("Zapisano wycenę do bazy danych");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
