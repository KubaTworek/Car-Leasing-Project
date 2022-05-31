package pl.pjatk.Car;

import pl.pjatk.Management.Management;

import java.util.Scanner;

public class CarManagement {
    public void startManagement(){
        System.out.println();
        System.out.println("1. Dodaj auto");
        System.out.println("2. Wyświetl auta");
        System.out.println("3. Zmień dane auta");
        System.out.println("4. Usuń auto");
        System.out.println("0. Cofnij.");
        System.out.println("Wybierz operację: ");

        switch(Management.choosing(4)){
            case 1 -> addCar();
            case 2 -> showCars();
            case 3 -> changeCar();
            case 4 -> deleteCar();
            case 0 -> {
                System.out.println("Cofamy");
                break;
            }
        }
    }

    public void addCar(){
        CarDataSource carDataSource = new CarDataSource();

        Scanner scanner = new Scanner(System.in);
        System.out.println("Podaj markę: ");
        String mark = scanner.nextLine();
        System.out.println("Podaj model: ");
        String model = scanner.nextLine();
        System.out.println("Podaj rocznik: ");
        int yearProduced = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Podaj cenę: ");
        double price = scanner.nextDouble();
        scanner.nextLine();
        System.out.println("Podaj numer rejestracyjny: ");
        String registrationNumber = scanner.nextLine();

        int idSpecificCar = carDataSource.getNumberOfSpecificCars()+1;
        int idModel = carDataSource.selectModelId(mark, model);
        int idCar = carDataSource.selectCarId(yearProduced, idModel);


        if(idModel != 0){
            if (idCar == 0) {
                idCar = carDataSource.getNumberOfCars() + 1;
                carDataSource.insertCar(idCar, yearProduced, idModel);
            }
            carDataSource.insertSpecificCar(idSpecificCar,registrationNumber,price,true,idCar);
        } else {
            idCar = carDataSource.getNumberOfCars()+1;
            idModel = carDataSource.getNumberOfModels()+1;
            carDataSource.insertCar(idCar,yearProduced,idModel);
            carDataSource.insertSpecificCar(idSpecificCar,registrationNumber,price,true,idCar);
            carDataSource.insertModel(idModel,model,mark);
        }
    }

    public void showCars(){
        CarDataSource carDataSource = new CarDataSource();
        carDataSource.selectCars();
    }

    public void changeCar(){

    }

    public void deleteCar(){
        Scanner scanner = new Scanner(System.in);
        CarDataSource carDataSource = new CarDataSource();
        carDataSource.selectCars();
        System.out.println("Wpisz numer rejestracyjny auta, które chcesz usunąć: ");
        String registrationNumber = scanner.nextLine();
        carDataSource.deleteCar(registrationNumber);
    }
}
