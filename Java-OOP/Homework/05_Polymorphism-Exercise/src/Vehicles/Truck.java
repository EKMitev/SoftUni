package Vehicles;

public class Truck extends Vehicle {

    protected static final Double AC_CONSUMPTION = 1.6;

    public Truck(Double fuelQuantity, Double fuelConsumption, Double tankCapacity) {
        super(fuelQuantity, fuelConsumption, tankCapacity);
    }


    @Override
    public void Refuel(Double fuel) {
        if (fuel < 0){
            throw new IllegalArgumentException("Fuel must be a positive number");
        }

        if (fuel + this. fuelQuantity > this.tankCapacity){
            throw new IllegalArgumentException("Cannot fit fuel in tank");
        }

        this.fuelQuantity += 0.95 * fuel;
    }
}
