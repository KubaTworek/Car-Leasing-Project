package pl.pjatk.Client;

public class ClientFinancial {
    private int idFinance;
    private double maxCash;
    private double maxRate;

    // CONSTRUCTOR

    public ClientFinancial(int idFinance, double maxCash, double maxRate) {
        this.idFinance = idFinance;
        this.maxCash = maxCash;
        this.maxRate = maxRate;
    }

    // GETTERS

    public int getIdFinance() {
        return idFinance;
    }

    public double getMaxCash() {
        return maxCash;
    }

    public double getMaxRate() {
        return maxRate;
    }

    // SETTERS

    public void setIdFinance(int idFinance) {
        this.idFinance = idFinance;
    }

    public void setMaxCash(double maxCash) {
        this.maxCash = maxCash;
    }

    public void setMaxRate(double maxRate) {
        this.maxRate = maxRate;
    }
}
