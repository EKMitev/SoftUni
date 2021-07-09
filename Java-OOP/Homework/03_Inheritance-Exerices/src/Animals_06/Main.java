package animals;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        List<Animal> animals = new ArrayList<>();

        String type = scanner.nextLine();
        while (!type.equals("Beast!")) {
            String[] data = scanner.nextLine().split("\\s+");

            Animal animal = null;

            switch (type) {
                case "Cat":
                    animal = new Cat(data[0], Integer.parseInt(data[1]), data[2]);
                    break;
                case "Dog":
                    animal = new Dog(data[0], Integer.parseInt(data[1]), data[2]);
                    break;
                case "Frog":
                    animal = new Frog(data[0], Integer.parseInt(data[1]), data[2]);
                    break;
                case "Kitten":
                    animal = new Kitten(data[0], Integer.parseInt(data[1]));
                    break;
                case "Tomcat":
                    animal = new Tomcat(data[0], Integer.parseInt(data[1]));
                    break;
            }
            animals.add(animal);

            type = scanner.nextLine();
        }

        animals.forEach(System.out::println);
    }
}
