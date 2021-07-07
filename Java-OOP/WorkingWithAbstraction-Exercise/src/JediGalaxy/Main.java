package JediGalaxy;

import java.util.Arrays;
import java.util.Scanner;

public class Main {

    private static long COLLECTED_STARS;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int[] dimensions = Arrays.stream(scanner.nextLine().split("\\s+")).mapToInt(Integer::parseInt).toArray();

        int[][] matrix = fillMatrix(dimensions[0], dimensions[1]);

        String command = scanner.nextLine();

        while (!command.equals("Let the Force be with you")) {

            int[] jediPosition = Arrays.stream(command.split(" ")).mapToInt(Integer::parseInt).toArray();
            int[] sithPosition = Arrays.stream(scanner.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();

            destroySars(matrix, sithPosition[0], sithPosition[1]);

            collectStars(matrix, jediPosition[0], jediPosition[1]);


            command = scanner.nextLine();
        }

        System.out.println(COLLECTED_STARS);


    }

    private static void collectStars(int[][] matrix, int row, int col) {
        while (row >= 0 && col < matrix[1].length) {
            if (isInBounds(matrix, row, col)) {
                COLLECTED_STARS += matrix[row][col];
            }

            row--;
            col++;
        }
    }

    private static boolean isInBounds(int[][] matrix, int row, int col) {
        return row >= 0 && row < matrix.length && col >= 0 && col < matrix[row].length;
    }

    private static void destroySars(int[][] matrix, int row, int col) {
        while (row >= 0 && col >= 0) {
            if (isInBounds(matrix, row, col)) {
                matrix[row][col] = 0;
            }
            row--;
            col--;
        }
    }

    private static int[][] fillMatrix(int rows, int cols) {
        int[][] matrix = new int[rows][cols];

        int value = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                matrix[i][j] = value++;
            }
        }
        return matrix;
    }
}
