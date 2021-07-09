package TrafficLights;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Bulb[] arr = Arrays.stream(scanner.nextLine().split("\\s+"))
                .map(Color::valueOf)
                .map(Bulb::new)
                .toArray(Bulb[]::new);


      int n = Integer.parseInt(scanner.nextLine());

        for (int i = 0; i < n; i++) {
            Arrays.stream(arr)
                    .peek(Bulb::changeColor)
                    .forEach(b -> System.out.print(b + " "));
            System.out.println();
        }

    }
}
