package Vehicles;

import java.text.DecimalFormat;

public class Bus extends  Vehicle{

    protected static final Double AC_CONSUMPTION = 1.4;

    public Bus(Double fuelQuantity, Double fuelConsumption, Double tankCapacity) {
        super(fuelQuantity, fuelConsumption, tankCapacity);
    }

    public String DriveEmpty(Double distance){
        return super.Drive(distance);
    }

    @Override
    protected String Drive(Double distance) {
        DecimalFormat format = new DecimalFormat("##.##");
        if (distance * (fuelConsumption + AC_CONSUMPTION) <= fuelQuantity){
            fuelQuantity -= distance * (fuelConsumption + AC_CONSUMPTION);
            return String.format("%s travelled %s km", this.getClass().getSimpleName(), format.format(distance));
        }

        return String.format("%s needs refueling", this.getClass().getSimpleName());
    }
}
