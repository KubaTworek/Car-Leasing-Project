package pl.pjatk.Pricing;

import pl.pjatk.Car.CarDataSource;
import pl.pjatk.Client.ClientDataSource;
import pl.pjatk.Management.Management;

public class PricingManagement {
    public void startManagement(){
        CarDataSource carDataSource = new CarDataSource();
        carDataSource.createCarTables();
        ClientDataSource clientDataSource = new ClientDataSource();
        clientDataSource.createClientTables();
        PricingDataSource pricingDataSource = new PricingDataSource();
        pricingDataSource.createPricingTables();

        clientDataSource.selectClients();
        System.out.println("Dla jakiego klienta byś chciał zrobic wycenę, podaj NIP:");
        String nip = Management.choosingStringInput();
        carDataSource.selectCars();
        System.out.println("Dla jakiego auta przygotowujemy wycenę, podaj numer rejestracyjny:");
        String registrationNumber = Management.choosingStringInput();
        int idClient = clientDataSource.selectClientId(nip);
        int idSpecificCar = carDataSource.selectSpecificCarId(registrationNumber);
        double carPrice = carDataSource.selectCarPrice(idSpecificCar);
        double priceInit = clientDataSource.selectMaxCash(idClient);
        double restCost = 0;
        double rateMax = 0;
        int amountOfRate = 0;
        double lastRate = 0;
        if(priceInit > carPrice){
            priceInit = carPrice;
        } else {
            restCost = carPrice-priceInit;
            rateMax = clientDataSource.selectMaxRate(idClient);
            amountOfRate = (int) (restCost/rateMax) + 1;
            lastRate = restCost%rateMax;
        }

        System.out.println(lastRate);
        System.out.println("Wpłata własna wynosi: " + priceInit + ", będziesz płacić " + amountOfRate + " rat, po " + rateMax + ", ostatnia rata wyniesie: " + lastRate);
        System.out.println("Zgadzasz się? (Y/N)");
        String resp = Management.choosingStringInput();
        if (resp.equalsIgnoreCase("y")) {
            pricingDataSource.insertPricing(idClient,priceInit,rateMax,amountOfRate,lastRate,idSpecificCar);
        } else {
            System.out.println("Usuwamy wycenę.");
        }
    }
}
