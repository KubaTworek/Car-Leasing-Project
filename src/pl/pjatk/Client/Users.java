package pl.pjatk.Client;

public class Users {
    private int idUser;
    private String name;
    private String surname;
    private String PESEL;
    private int idClient;

    // CONSTRUCTOR

    public Users(int idUser, String name, String surname, String PESEL, int idClient) {
        this.idUser = idUser;
        this.name = name;
        this.surname = surname;
        this.PESEL = PESEL;
        this.idClient = idClient;
    }

    // GETTERS

    public int getIdUser() {
        return idUser;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getPESEL() {
        return PESEL;
    }

    public int getIdClient() {
        return idClient;
    }

    // SETTERS

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setPESEL(String PESEL) {
        this.PESEL = PESEL;
    }

    public void setIdClient(int idClient) {
        this.idClient = idClient;
    }
}
