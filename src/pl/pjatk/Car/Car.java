package pl.pjatk.Car;

public abstract class Car {
    private int idCar;
    private int yearProduced;
    private CarModel model;

    // CONSTRUCTOR
    public Car(int idCar, int yearProduced, CarModel model) {
        this.idCar = idCar;
        this.yearProduced = yearProduced;
        this.model = model;
    }

    // GETTERS

    public int getIdCar() {
        return idCar;
    }

    public int getYearProduced() {
        return yearProduced;
    }

    public CarModel getModel() {
        return model;
    }

    // SETTERS

    public void setIdCar(int idCar) {
        this.idCar = idCar;
    }

    public void setYearProduced(int yearProduced) {
        this.yearProduced = yearProduced;
    }

    public void setModel(CarModel model) {
        this.model = model;
    }

    // METHODS


}
