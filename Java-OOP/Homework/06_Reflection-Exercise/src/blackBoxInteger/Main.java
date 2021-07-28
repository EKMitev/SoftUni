package blackBoxInteger;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchFieldException {

        Class<BlackBoxInt> clazz = BlackBoxInt.class;

        Constructor<BlackBoxInt> constructor = clazz.getDeclaredConstructor();
        constructor.setAccessible(true);

       BlackBoxInt blackBoxInt = constructor.newInstance();

        Method[] methods = clazz.getDeclaredMethods();

        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();

        while (!input.equals("END")){
            String name = input.split("_")[0];
            Method method = Arrays.stream(methods)
                    .filter(m -> m.getName().equals(name))
                    .findFirst().orElse(null);

            if (method != null) {
                method.setAccessible(true);
                method.invoke(blackBoxInt, Integer.parseInt(input.split("_")[1]));

                Field innerValue = clazz.getDeclaredField("innerValue");
                innerValue.setAccessible(true);
                System.out.println(innerValue.getInt(blackBoxInt));
            }

            input = scanner.nextLine();
        }
    }
}
