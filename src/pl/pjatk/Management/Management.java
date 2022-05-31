package pl.pjatk.Management;

import pl.pjatk.Car.Car;
import pl.pjatk.Car.CarDataSource;
import pl.pjatk.Car.CarManagement;
import pl.pjatk.Client.Client;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Management {

    public Management() {
        startManagement();
    }

    public void startManagement(){
        CarDataSource carDataSource = new CarDataSource();
        carDataSource.createTables();

        CarManagement carManagement = new CarManagement();

        System.out.println();
        System.out.println("1. Zarzadzaj klientem");
        System.out.println("2. Zarzadzaj samochodami");
        System.out.println("3. Stworz wycenę dla klienta");
        System.out.println("4. Wykonaj leasing");
        System.out.println("0. Wyjdź.");
        System.out.println("Wybierz operację: ");

        switch(Management.choosing(4)){
            case 1 -> Client.startManagement();
            case 2 -> carManagement.startManagement();
            //case 3 ->
            //case 4 ->
            case 0 -> System.exit(0);
        }
    }

    public static int choosing(int max){
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
}
