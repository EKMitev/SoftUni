package Vehicles;

public class Car extends Vehicle{

    protected static final Double AC_CONSUMPTION = 0.9;


    public Car(Double fuelQuantity, Double fuelConsumption, Double tankCapacity) {
        super(fuelQuantity, fuelConsumption, tankCapacity);
    }
}
