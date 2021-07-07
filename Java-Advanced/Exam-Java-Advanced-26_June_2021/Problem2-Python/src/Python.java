import java.util.Scanner;

public class Python {

    private static int ROW;
    private static int COL;
    private static int LENGTH = 1;
    private static int FOOD;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int matrixSize = Integer.parseInt(scanner.nextLine());
        String[] commands = scanner.nextLine().split(", ");

        char[][] matrix = new char[matrixSize][matrixSize];

        for (int i = 0; i < matrix.length; i++) {
            String str = scanner.nextLine().replace(" ", "");
            if (str.contains("s")) {
                ROW = i;
                COL = str.indexOf("s");
            }
            for (int j = 0; j < str.length(); j++) {
                if (str.charAt(j) == 'f') {
                    FOOD++;
                }
            }
            matrix[i] = str.toCharArray();
        }

        boolean killed = false;
        for (String command : commands) {
            switch (command) {
                case "up" -> killed = movePython(matrix, ROW, COL, -1, 0);
                case "down" -> killed = movePython(matrix, ROW, COL, +1, 0);
                case "right" -> killed = movePython(matrix, ROW, COL, 0, +1);
                case "left" -> killed = movePython(matrix, ROW, COL, 0, -1);
            }
            if (killed || FOOD <= 0) {
                break;
            }
        }
        if (killed) {
            System.out.println("You lose! Killed by an enemy!");
        } else if (FOOD > 0) {
            System.out.printf("You lose! There is still %d food to be eaten.", FOOD);
        } else System.out.printf("You win! Final python length is %d", LENGTH);

    }

    private static boolean movePython(char[][] matrix, int row, int col, int rowAdd, int colAdd) {
        int newRow = ensureIndexIsInBounds(row + rowAdd, matrix.length);
        int newCol = ensureIndexIsInBounds(col + colAdd, matrix.length);

        if (matrix[newRow][newCol] == 'f') {
            LENGTH++;
            FOOD--;
        } else if (matrix[newRow][newCol] == 'e') {
            return true;
        }

        matrix[newRow][newCol] = 's';
        matrix[row][col] = '*';
        ROW = newRow;
        COL = newCol;

        return false;
    }

    private static int ensureIndexIsInBounds(int index, int bounds) {
        if (index < 0) {
            index = bounds - 1;
        } else if (index >= bounds) {
            index = 0;
        }

        return index;
    }
}