package pl.pjatk.Car;

import pl.pjatk.Management.Management;


public class CarManagement {
    public void startManagement(){
        while(true) {
        System.out.println();
        System.out.println("1. Dodaj auto");
        System.out.println("2. Wyświetl auta");
        System.out.println("3. Zarządzaj autem");
        System.out.println("4. Usuń auto");
        System.out.println("0. Cofnij.");
        System.out.println("Wybierz operację: ");

            switch (Management.choosingSwitchInput(4)) {
                case 1 -> addCar();
                case 2 -> showCars();
                case 3 -> managementCar();
                case 4 -> deleteCar();
            }
            break;
        }
    }

    public void managementCar(){
        while(true) {
            System.out.println();
            System.out.println("1. Zaktualizuj cenę auta");
            System.out.println("2. Zmień dostępność auta");
            System.out.println("Wybierz operację: ");

            switch (Management.choosingSwitchInput(2)) {
                case 1 -> updatePriceCar();
                case 2 -> changeAvailabiltyCar();
            }
            break;
        }
    }

    public void addCar(){
        CarDataSource carDataSource = new CarDataSource();

        System.out.println("Podaj markę: ");
        String mark = Management.choosingStringInput();
        System.out.println("Podaj model: ");
        String model = Management.choosingStringInput();
        System.out.println("Podaj rocznik: ");
        int yearProduced = Management.choosingIntInput();
        System.out.println("Podaj cenę: ");
        double price = Management.choosingDoubleInput();
        System.out.println("Podaj numer rejestracyjny: ");
        String registrationNumber = Management.choosingStringInput();

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

    public void updatePriceCar(){
        CarDataSource carDataSource = new CarDataSource();
        System.out.println("Wpisz numer rejestracyjny auta, którego cenę chcesz zmienić: ");
        String registrationNumber = Management.choosingStringInput();
        if(carDataSource.isExistCar(registrationNumber)){
            System.out.println("Podaj nową cenę: ");
            double newPrice = Management.choosingDoubleInput();
            carDataSource.updatePriceCar(registrationNumber,newPrice);
        } else {
            System.out.println("Nie mamy takiego samochodu");
        }
    }

    public void changeAvailabiltyCar(){
        CarDataSource carDataSource = new CarDataSource();
        System.out.println("Wpisz numer rejestracyjny auta, którego dostępność chcesz zmienić: ");
        String registrationNumber = Management.choosingStringInput();
        if(carDataSource.isExistCar(registrationNumber)){
            carDataSource.updateAvailability(registrationNumber);
        } else {
            System.out.println("Nie mamy takiego samochodu");
        }
    }

    public void deleteCar(){
        CarDataSource carDataSource = new CarDataSource();
        carDataSource.selectCars();
        System.out.println("Wpisz numer rejestracyjny auta, które chcesz usunąć: ");
        String registrationNumber = Management.choosingStringInput();
        if(carDataSource.isExistCar(registrationNumber)){
            carDataSource.deleteCar(registrationNumber);
        } else {
            System.out.println("Nie mamy takiego samochodu");
        }
    }
}
