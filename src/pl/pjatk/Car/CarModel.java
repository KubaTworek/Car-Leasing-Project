package pl.pjatk.Car;

public class CarModel {
    private int idModel;
    private String model;
    private String mark;

    // CONSTRUCTOR

    public CarModel(int idModel, String model, String mark) {
        this.idModel = idModel;
        this.model = model;
        this.mark = mark;
    }

    // GETTERS

    public int getIdModel() {
        return idModel;
    }

    public String getModel() {
        return model;
    }

    public String getMark() {
        return mark;
    }

    //SETTERS


    public void setIdModel(int idModel) {
        this.idModel = idModel;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    // METHODS



}
