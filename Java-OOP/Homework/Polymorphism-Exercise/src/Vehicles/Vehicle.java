package Vehicles;

import java.text.DecimalFormat;

public abstract class Vehicle {

    protected Double fuelQuantity;
    protected Double fuelConsumption;
    protected final Double tankCapacity;

    public Vehicle(Double fuelQuantity, Double fuelConsumption, Double tankCapacity) {
        this.fuelQuantity = fuelQuantity;
        this.fuelConsumption = fuelConsumption;
        this.tankCapacity = tankCapacity;
    }

    protected String Drive(Double distance){
        DecimalFormat format = new DecimalFormat("##.##");
        if (distance * fuelConsumption <= fuelQuantity){
            fuelQuantity -= distance * fuelConsumption;
            return String.format("%s travelled %s km", this.getClass().getSimpleName(), format.format(distance));
        }

        return String.format("%s needs refueling", this.getClass().getSimpleName());
    }

   protected void Refuel(Double fuel){
        if (fuel < 0){
            throw new IllegalArgumentException("Fuel must be a positive number");
        }

        if (fuel + this. fuelQuantity > this.tankCapacity){
            throw new IllegalArgumentException("Cannot fit fuel in tank");
        }

        this.fuelQuantity += fuel;
   }

    @Override
    public String toString() {
        return String.format("%s: %.2f", this.getClass().getSimpleName(), this.fuelQuantity);
    }

}
