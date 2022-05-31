package pl.pjatk.Client;

import pl.pjatk.Car.CarDataSource;
import pl.pjatk.Management.Management;


public class ClientManagement {
    public void startManagement() {
        while (true) {
            System.out.println();
            System.out.println("1. Dodaj klienta");
            System.out.println("2. Wyświetl klientów");
            System.out.println("3. Zarządzaj klientem");
            System.out.println("4. Usuń klienta");
            System.out.println("0. Cofnij.");
            System.out.println("Wybierz operację: ");

            switch (Management.choosingSwitchInput(4)) {
                case 1 -> addClient();
                case 2 -> showClients();
                case 3 -> manageClients();
                case 4 -> deleteClient();
            }
            break;
        }
    }

    public void manageClients() {
        while (true) {
            System.out.println();
            System.out.println("1. Dodaj użytkowników");
            System.out.println("2. Wyświetl użytkowników");
            System.out.println("3. Edytuj finanse");
            System.out.println("4. Usuń użytkownika");
            System.out.println("0. Cofnij.");
            System.out.println("Wybierz operację: ");

            switch (Management.choosingSwitchInput(4)) {
                case 1 -> addUser();
                case 2 -> showUsers();
                case 3 -> editFinance();
                case 4 -> deleteUser();
            }
            break;
        }
    }

    // CLIENTS

    //CREATE
    public void addClient() {
        ClientDataSource clientDataSource = new ClientDataSource();

        System.out.println("Podaj nazwę firmy: ");
        String name = Management.choosingStringInput();
        System.out.println("Podaj NIP: ");
        String nip = Management.choosingStringInput();
        System.out.println("Podaj maksymalna gotówkę, jaką możesz przeznaczyć na auto: ");
        double maxCash = Management.choosingDoubleInput();
        System.out.println("Podaj maksymalna ratę, jaką możesz płacić za auto: ");
        double maxRate = Management.choosingDoubleInput();
        int idClient = clientDataSource.getNumberOfClients() + 1;
        int idClientFinance = clientDataSource.getNumberOfClientFinances() + 1;
        clientDataSource.insertClient(idClient, name, nip, idClientFinance);
        clientDataSource.insertClientFinance(idClientFinance, maxCash, maxRate);
        while (true) {
            System.out.println("Podaj imię użytkownika");
            String userName = Management.choosingStringInput();
            System.out.println("Podaj nazwisko użytkownika");
            String userSurname = Management.choosingStringInput();
            System.out.println("Podaj PESEL użytkownika");
            String userPESEL = Management.choosingStringInput();
            System.out.println("Czy chcesz zakończyć? (Y/N)");
            String resp = Management.choosingStringInput();
            int idUser = clientDataSource.getNumberOfUsers() + 1;
            clientDataSource.insertUser(idUser, userName, userSurname, userPESEL, idClient);
            if (resp.equalsIgnoreCase("y")) break;
        }

    }

    //READ
    public void showClients() {
        ClientDataSource clientDataSource = new ClientDataSource();
        clientDataSource.selectClients();
    }

    //DELETE
    public void deleteClient() {
        ClientDataSource clientDataSource = new ClientDataSource();
        System.out.println("Wpisz NIP klienta, którego chcesz usunąć: ");
        String nip = Management.choosingStringInput();
        if (clientDataSource.isExistClient(nip)) {
            clientDataSource.deleteClient(nip);
        } else {
            System.out.println("Nie mamy takiego klienta");
        }
    }

    // USERS
    //CREATE
    public void addUser() {
        ClientDataSource clientDataSource = new ClientDataSource();
        System.out.println("Wpisz numer NIP klienta, któremu chcesz dodać użytkowników: ");
        String nip = Management.choosingStringInput();
        if (clientDataSource.isExistClient(nip)) {
            while (true) {
                System.out.println("Podaj imię użytkownika");
                String userName = Management.choosingStringInput();
                System.out.println("Podaj nazwisko użytkownika");
                String userSurname = Management.choosingStringInput();
                System.out.println("Podaj PESEL użytkownika");
                String userPESEL = Management.choosingStringInput();
                System.out.println("Czy chcesz zakończyć? (Y/N)");
                String resp = Management.choosingStringInput();
                int idUser = clientDataSource.getNumberOfUsers() + 1;
                int idClient = clientDataSource.selectClientId(nip);
                clientDataSource.insertUser(idUser, userName, userSurname, userPESEL, idClient);
                if (resp.equalsIgnoreCase("y")) break;
            }
        } else {
            System.out.println("Nie mama takiego klienta");
        }
    }

    //READ
    public void showUsers() {
        ClientDataSource clientDataSource = new ClientDataSource();
        clientDataSource.selectUsers();
    }

    //UPDATE
    public void editFinance() {
        ClientDataSource clientDataSource = new ClientDataSource();
        System.out.println("Wpisz numer NIP klienta, któremu chcesz edytować finanse: ");
        String nip = Management.choosingStringInput();
        if (clientDataSource.isExistClient(nip)) {
            System.out.println("Podaj maksymalna gotówkę, jaką możesz przeznaczyć na auto: ");
            double maxCash = Management.choosingDoubleInput();
            System.out.println("Podaj maksymalna ratę, jaką możesz płacić za auto: ");
            double maxRate = Management.choosingDoubleInput();
            clientDataSource.updateFinance(clientDataSource.selectClientFinanceId(nip), maxCash, maxRate);
        } else {
            System.out.println("Nie mama takiego klienta");
        }

    }

    //DELETE
    public void deleteUser() {
        ClientDataSource clientDataSource = new ClientDataSource();
        System.out.println("Wpisz PESEL użytkownika, którego chcesz usunąć: ");
        String pesel = Management.choosingStringInput();
        if (clientDataSource.isExistUser(pesel)) {
            clientDataSource.deleteUser(pesel);
        } else {
            System.out.println("Nie mamy takiego klienta");
        }
    }

}
