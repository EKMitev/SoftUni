package DefineAnInterfacePerson_01_02_03_04;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        List<Buyer> buyers = new ArrayList<>();

        Scanner scanner = new Scanner(System.in);

        int buyersCount = Integer.parseInt(scanner.nextLine());
        for (int i = 0; i < buyersCount; i++) {

            Buyer buyer = null;
            String[] data = scanner.nextLine().split("\\s+");

            if (data.length == 4) {
                buyer = new Citizen(data[0], Integer.parseInt(data[1]), data[2], data[3]);
            } else buyer = new Rebel(data[0], Integer.parseInt(data[1]), data[2]);

            buyers.add(buyer);
        }

        int totalFoodBought = 0;

        String name = scanner.nextLine();
        while (!name.equals("End")) {

           totalFoodBought += buyFood(name, buyers);
            name = scanner.nextLine();
        }
        System.out.println(totalFoodBought);
    }

    private static int buyFood(String name, List<Buyer> buyers) {
        for (Buyer buyer : buyers) {
            Person person = (Person) buyer;
            if (name.equals(person.getName())) {
                buyer.buyFood();
                if (buyer.getClass().getSimpleName().equals("Citizen")){
                    return 10;
                }else return 5;
            }
        }
        return 0;
    }
}
