package Main;

import java.util.Scanner;
import java.util.SimpleTimeZone;

public class Main {
    protected static final Scanner scanner = new Scanner(System.in);
    protected static final char DOT = '•';
    protected static final char X = 'X';
    protected static final char O = 'O';
    protected static int SIZE = 0;

    public static void main(String...args) {
        char[][] map = initGame();
        printField(map);
//        do {
//            nextTurn(X, map);
//        } while (winCondition());
    }

    public static char[][] initGame() {
        System.out.println("Let's play a game!");
        System.out.println("Set field size: ");
        while (!scanner.hasNextInt() & ((SIZE = scanner.nextInt()) > 2)) {
            System.out.println("Only int more 3");
        }
        char[][] map = new char[SIZE][SIZE];
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                map[i][j] = DOT;
            }
        }return map;
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

//    public static char nextTurn (char sign, char[][] map) {
//        System.out.println("Set x and y of your sign:");
//        int x,y;
//        do {
//            x = chooseIndex();
//            y = chooseIndex();
//            if (map[x][y] == DOT)
//                map[x][y] = sign;
//        }   while (map[x][y] != sign);
//        return winCondition(sign ,map);
//    }

//    private static boolean isCellValid(int x, int y) {
//    }
//
//    public static int chooseIndex() {
//        int value = -1;
//        while (!scanner.hasNextInt() && (value = scanner.nextInt()) < SIZE - 1) {
//            System.out.println();
//        }
//    }
}
