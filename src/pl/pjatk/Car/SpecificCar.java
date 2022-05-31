package pl.pjatk.Car;

public class SpecificCar extends Car{
    private int idSpecificCar;
    private String registrationNumber;
    private double price;
    private boolean isAvailable;

    // CONSTRUCTOR

    public SpecificCar(int idSpecificCar, int yearProduced, CarModel model, int idCar, String registrationNumber, double price) {
        super(idCar, yearProduced, model);
        this.idSpecificCar = idSpecificCar;
        this.registrationNumber = registrationNumber;
        this.price = price;
        this.isAvailable = true;
    }

    // GETTERS

    public int getIdSpecificCar() {
        return idSpecificCar;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public double getPrice() {
        return price;
    }
    public boolean isAvailable() {
        return isAvailable;
    }

    // SETTERS

    public void setIdSpecificCar(int idCar) {
        this.idSpecificCar = idSpecificCar;
    }

    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }
}
