package needForSpeed;

public class Main {
    public static void main(String[] args) {
        FamilyCar familyCar = new FamilyCar(4, 10);
        RaceMotorcycle raceMotorcycle = new RaceMotorcycle(1, 3);
        SportCar sportCar = new SportCar(1, 1);
        System.out.println(familyCar.getFuelConsumption());
        System.out.println(raceMotorcycle.getFuelConsumption());
        System.out.println(sportCar.getFuelConsumption());
        System.out.println();

        Vehicle vehicle = new Vehicle(1,1);
        Motorcycle motorcycle = new Motorcycle(1, 1);
        CrossMotorcycle crossMotorcycle = new CrossMotorcycle(1, 1);
        Car c = new Car(1, 1);
        System.out.println(vehicle.getFuelConsumption());
        System.out.println(motorcycle.getFuelConsumption());
        System.out.println(crossMotorcycle.getFuelConsumption());
        System.out.println(c.getFuelConsumption());
    }
}