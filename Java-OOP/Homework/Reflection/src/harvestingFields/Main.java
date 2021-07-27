package harvestingFields;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.*;
import java.util.function.Predicate;

public class Main {
    public static void main(String[] args) {

        Class<RichSoilLand> clazz = RichSoilLand.class;
        Field[] fields = clazz.getDeclaredFields();

        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();

        while (!input.equals("HARVEST")) {
            printFields(input, fields);
            input = scanner.nextLine();
        }

    }

    private static void printFields(String modifier, Field[] fields) {

        Predicate<Field> predicate = f -> {
            if (modifier.equals("all")) {
                return true;
            }

            return Modifier.toString(f.getModifiers()).equals(modifier);
        };

        Arrays.stream(fields)
                .filter(predicate)
                .forEach(f -> {
                    StringBuilder builder = new StringBuilder();
                    builder.append(Modifier.toString(f.getModifiers())).append(" ").append(f.getType().getSimpleName()).append(" ").append(f.getName());
                    System.out.println(builder.toString());
                });
    }
}
