package pl.pjatk.Client;

import pl.pjatk.Car.CarDataSource;
import pl.pjatk.Management.Management;

import java.util.Scanner;

public class ClientManagement {
    public void startManagement(){
        while(true) {
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
                case 3 -> changeClients();
                case 4 -> deleteClient();
            }
            break;
        }
    }

    public void addClient(){
        ClientDataSource clientDataSource = new ClientDataSource();

        System.out.println("Podaj nazwę firmy: ");
        String name = Management.choosingStringInput();
        System.out.println("Podaj NIP: ");
        String nip = Management.choosingStringInput();
        System.out.println("Podaj maksymalna gotówkę, jaką możesz przeznaczyć na auto: ");
        double maxCash = Management.choosingDoubleInput();
        System.out.println("Podaj maksymalna ratę, jaką możesz płacić za auto: ");
        double maxRate = Management.choosingDoubleInput();
        int idClient = clientDataSource.getNumberOfClients()+1;
        int idClientFinance = clientDataSource.getNumberOfClientFinances()+1;
        clientDataSource.insertClient(idClient,name,nip,idClientFinance);
        clientDataSource.insertClientFinance(idClientFinance,maxCash,maxRate);
        while(true){
            System.out.println("Podaj imię użytkownika");
            String userName = Management.choosingStringInput();
            System.out.println("Podaj nazwisko użytkownika");
            String userSurname = Management.choosingStringInput();
            System.out.println("Podaj PESEL użytkownika");
            String userPESEL = Management.choosingStringInput();
            System.out.println("Czy chcesz zakończyć? (Y/N)");
            String resp = Management.choosingStringInput();
            int idUser = clientDataSource.getNumberOfUsers()+1;
            clientDataSource.insertUser(idUser,userName,userSurname,userPESEL,idClient);
            if(resp.equalsIgnoreCase("y")) break;
        }

    }

    public void showClients(){
        ClientDataSource clientDataSource = new ClientDataSource();
        clientDataSource.selectClients();
    }

    public void changeClients(){

    }

    public void deleteClient(){
        ClientDataSource clientDataSource = new ClientDataSource();
        System.out.println("Wpisz NIP klienta, którego chcesz usunąć: ");
        String nip = Management.choosingStringInput();
        if(clientDataSource.isExistClient(nip)){
            clientDataSource.deleteClient(nip);
        } else {
            System.out.println("Nie mamy takiego klienta");
        }
    }

}
