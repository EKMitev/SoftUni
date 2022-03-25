import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.Collectors;

public class OS_Planning {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        ArrayDeque<Integer> tasks  = new ArrayDeque<>();
        Arrays.stream(scanner.nextLine().split(", "))
                .map(Integer::parseInt)
                .forEach(tasks::push);

        ArrayDeque<Integer> threads = Arrays.stream(scanner.nextLine().split("\\s+"))
                .map(Integer::parseInt)
                .collect(Collectors.toCollection(ArrayDeque::new));

        int taskToKill = Integer.parseInt(scanner.nextLine());
        int lastThread = -1;

        while (tasks.contains(taskToKill)){
            int task = tasks.pop();
            int thread = threads.poll();

            if (task == taskToKill){
                lastThread = thread;
                break;
            }

            if(thread < task){
                tasks.push(task);
            }
        }
        String out = String.format("Thread with value %d killed task %d", lastThread, taskToKill);
        System.out.println(out);
        threads.push(lastThread);
        threads.forEach(e -> System.out.print(e + " "));

    }
}
