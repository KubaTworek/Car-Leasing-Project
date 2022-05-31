package pl.pjatk.Client;

public class Client {
    private int idClient;
    private String name;
    private String NIP;
    private ClientFinancial finance;

    // CONSTRUCTOR

    public Client(int idClient, String name, String NIP, ClientFinancial finance) {
        this.idClient = idClient;
        this.name = name;
        this.NIP = NIP;
        this.finance = finance;
    }

    // GETTERS

    public int getIdClient() {
        return idClient;
    }

    public String getName() {
        return name;
    }

    public String getNIP() {
        return NIP;
    }

    public ClientFinancial getFinance() {
        return finance;
    }

    // SETTERS

    public void setIdClient(int idClient) {
        this.idClient = idClient;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setNIP(String NIP) {
        this.NIP = NIP;
    }

    public void setFinance(ClientFinancial finance) {
        this.finance = finance;
    }
}
