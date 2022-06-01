package pl.pjatk.Management;

import pl.pjatk.Car.Car;
import pl.pjatk.Car.CarDataSource;
import pl.pjatk.Car.CarManagement;
import pl.pjatk.Client.Client;
import pl.pjatk.Client.ClientDataSource;
import pl.pjatk.Client.ClientManagement;
import pl.pjatk.Pricing.PricingManagement;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Management {

    public Management() {
        startManagement();
    }

    public void startManagement(){
        CarDataSource carDataSource = new CarDataSource();
        carDataSource.createCarTables();
        ClientDataSource clientDataSource = new ClientDataSource();
        clientDataSource.createClientTables();

        CarManagement carManagement = new CarManagement();
        ClientManagement clientManagement = new ClientManagement();
        PricingManagement pricingManagement = new PricingManagement();

        while(true){
        System.out.println();
        System.out.println("1. Zarzadzaj klientami");
        System.out.println("2. Zarzadzaj samochodami");
        System.out.println("3. Stworz wycenę dla klienta");
        //System.out.println("4. Wykonaj leasing");
        System.out.println("0. Wyjdź.");
        System.out.println("Wybierz operację: ");

            switch(Management.choosingSwitchInput(4)){
                case 1 -> clientManagement.startManagement();
                case 2 -> carManagement.startManagement();
                case 3 -> pricingManagement.startManagement();
                //case 4 ->
                case 0 -> System.exit(0);
            }
        }
    }

    public static int choosingSwitchInput(int max){
        Scanner scanner = new Scanner(System.in);
        int choose = -1;
        while(choose < 0 || choose > max){
            try {
                choose = scanner.nextInt();
                if(choose < 0 || choose > max) throw new InputMismatchException();
            } catch (InputMismatchException e) {
                scanner.nextLine();
                System.out.println("Podaj prawidłowy numer.");
            }
        }
        return choose;
    }

    public static int choosingIntInput(){
        Scanner scanner = new Scanner(System.in);
        int choose = -1;
        while(choose < 0){
            try {
                choose = scanner.nextInt();
                if(choose < 0) throw new InputMismatchException();
            } catch (InputMismatchException e) {
                scanner.nextLine();
                System.out.println("Podaj prawidłowy numer.");
            }
        }
        return choose;
    }

    public static double choosingDoubleInput(){
        Scanner scanner = new Scanner(System.in);
        double choose = -1;
        while(choose < 0){
            try {
                choose = scanner.nextInt();
                if(choose < 0) throw new InputMismatchException();
            } catch (InputMismatchException e) {
                scanner.nextLine();
                System.out.println("Podaj prawidłowy numer.");
            }
        }
        return choose;
    }

    public static String choosingStringInput(){
        Scanner scanner = new Scanner(System.in);
        String choose = "";
        while(choose.trim() == ""){
            try {
                choose = scanner.nextLine();
                if(choose.trim() == "") throw new InputMismatchException();
            } catch (InputMismatchException e) {
                scanner.nextLine();
                System.out.println("Podaj prawidłowy numer.");
            }
        }
        return choose.trim();
    }

}
