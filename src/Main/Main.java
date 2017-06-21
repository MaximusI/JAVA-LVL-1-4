package Main;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    enum TYPE_DIAG {
        HORZ,
        VERT,
        DIAG
    }
    protected static Scanner scanner = new Scanner(System.in);
    protected static final char DOT = '•';
    protected static final char X = 'X';
    protected static final char O = 'O';
    protected static int SIZE = 0;

    public static void main(String...args) {
        char[][] map = initGame();
//        while (winCondition()) {
            nextTurn(X, map);
            printField(map);
            nextTurn(O, map);
            printField(map);
//        }
    }

    public static char[][] initGame() {
        System.out.println("Let's play a game!");
        System.out.println("Set field size: ");
        while (((SIZE = scanner.nextInt()) < 3)) {
            System.out.println("Only int more 3");
        }
        char[][] map = new char[SIZE][SIZE];
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                map[i][j] = DOT;
            }
        }
        printField(map);
        return map;
    }

    public static void printField(char[][] map) {
        StringBuilder sb = new StringBuilder("Game field: \n");
        for (char[] line: map) {
            for (char value : line) {
                sb.append(value + " ");
            }
            sb.append("\n");
        }
        System.out.println(sb.toString());
    }

    public static char[][] nextTurn (char sign, char[][] map) {
        System.out.println("Set " + sign + " of your sign:");
        int i,j;
        try {
            i = scanner.nextInt();
            j = scanner.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("You need input a correct number");
            scanner.nextLine();
            return map;
        }
        if ((i >= SIZE | i < 0) | (j >= SIZE | j < 0)) {
            System.out.println("The field size is than allowable, be careful. Next move...");
            return map;
        }
        if (map[i][j] == DOT)
            map[i][j] = sign;
        else if (map[i][j] == X || map[i][j] == O) {
            System.out.println("The field is engaged. Next move...");
        }
        return map;
    }

}
