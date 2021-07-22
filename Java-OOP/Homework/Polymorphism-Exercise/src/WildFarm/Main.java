package WildFarm;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String animalData = scanner.nextLine();
        List<Animal> animalList = new ArrayList<>();

        while (!animalData.equals("End")) {


            Animal animal = createAnimal(animalData.split("\\s+"));
            Food food = createFood(scanner.nextLine().split("\\s+"));

            animal.makeSound();

            try {
                animal.eat(food);
            } catch (IllegalArgumentException ex) {
                System.out.println(ex.getMessage());
            }

            animalList.add(animal);


            animalData = scanner.nextLine();
        }

        for (Animal animal : animalList) {
            System.out.println(animal);
        }
    }

    private static Food createFood(String[] data) {
        return data[0].equals("Meat")
                ? new Meat(Integer.parseInt(data[1]))
                : new Vegetable(Integer.parseInt(data[1]));
    }

    private static Animal createAnimal(String[] data) {
        switch (data[0]) {
            case "Cat":
                return new Cat(data[0], data[1], Double.parseDouble(data[2]), data[3], data[4]);
            case "Tiger":
                return new Tiger(data[0], data[1], Double.parseDouble(data[2]), data[3]);
            case "Zebra":
                return new Zebra(data[0], data[1], Double.parseDouble(data[2]), data[3]);
            case "Mouse":
                return new Mouse(data[0], data[1], Double.parseDouble(data[2]), data[3]);
            default:
                throw new IllegalStateException("unknown animal");
        }

    }
}
