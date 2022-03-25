package Vehicles;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        String[] carData = scanner.nextLine().split("\\s+");
        String[] truckData = scanner.nextLine().split("\\s+");
        String[] busData = scanner.nextLine().split("\\s+");

        Vehicle car = new Car(Double.parseDouble(carData[1]), Double.parseDouble(carData[2]), Double.parseDouble(carData[3]));
        Vehicle truck = new Truck(Double.parseDouble(truckData[1]), Double.parseDouble(truckData[2]), Double.parseDouble(truckData[3]));
        Bus bus = new Bus(Double.parseDouble(busData[1]), Double.parseDouble(busData[2]), Double.parseDouble(busData[3]));

        int N = Integer.parseInt(scanner.nextLine());

        while (N-- > 0) {
            String[] commands = scanner.nextLine().split("\\s+");
            switch (commands[0]) {
                case "Drive":
                    if (commands[1].equals("Car")) {
                        System.out.println(car.Drive(Double.parseDouble(commands[2])));
                    } else System.out.println(truck.Drive(Double.parseDouble(commands[2])));
                    break;

                case "Refuel":
                    if (commands[1].equals("Car")) {
                        car.Refuel(Double.parseDouble(commands[2]));
                    } else truck.Refuel(Double.parseDouble(commands[2]));
                    break;

                case "DriveEmpty":
                    System.out.println(bus.DriveEmpty(Double.parseDouble(commands[2])));
            }
        }

        System.out.println(car);
        System.out.println(truck);
        System.out.println(bus);
    }
}
