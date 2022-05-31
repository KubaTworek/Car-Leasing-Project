package pl.pjatk.Car;

public class SpecificCar extends Car{
    private int idCar;
    private String registrationNumber;
    private double price;
    private boolean isAvailable;

    // CONSTRUCTOR

    public SpecificCar(int idCar, int yearProduced, CarModel model, int idCar1, String registrationNumber, double price) {
        super(idCar, yearProduced, model);
        this.idCar = idCar1;
        this.registrationNumber = registrationNumber;
        this.price = price;
        this.isAvailable = true;
    }

    // GETTERS


    @Override
    public int getIdCar() {
        return idCar;
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

    @Override
    public void setIdCar(int idCar) {
        this.idCar = idCar;
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
