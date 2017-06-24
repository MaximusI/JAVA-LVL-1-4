package Main;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    enum LINE_TYPE {
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
        char currentStep = X;   currentStep = (currentStep == X)  ? O : X;

        do {
            if (isMapFull(map)) {
                System.out.println("Standoff, no one win!");
                return;
            }
            currentStep = (currentStep == X)  ? O : X;
            nextTurn(currentStep, map);
            printField(map);
        } while (!winCondition(map, currentStep));
        System.out.printf("The winner is - %c", currentStep);
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
            System.out.print("Enter raw - ");i = scanner.nextInt();
            System.out.print("Enter column - ");j = scanner.nextInt();
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

    private static boolean checkLine(LINE_TYPE type, char[][] map, char sign) {
        switch (type) {
            case HORZ:
                for (int i = 0; i < SIZE; i++) {
                    for (int j = 0; j < SIZE; j++) {
                        if (map[i][j] == sign) {
                            if (j == SIZE - 1) {
                                System.out.println("HORZ");
                                return true;
                            }
                            continue;
                        } else break;
                    }
                }
                break;
            case VERT:
                for (int j = 0; j < SIZE; j++) {
                    for (int i = 0; i < SIZE; i++) {
                        if (map[i][j] == sign) {
                            if (i == SIZE - 1){
                                System.out.println("VERT");
                                return true;
                            }
                            continue;
                        } else break;
                    }
                }
                break;
            case DIAG:
                if (SIZE % 2 == 1){
                    // Check major diagonal, and update the boolean if our assumption is wrong.
                    for(int i=0; i < map.length; i++){
                        if (map[i][i] == sign) { //(0,0);(1,1);(3,3);...
                            if (i == map.length - 1){
                                System.out.println("Major DIAG");
                                return true;
                            }
                            continue;
                        } else break;
                    }
                    // Check minor diagonal, and update the boolean if our assumption is wrong.
                    for (int k = 0, j = map.length - 1; k < map.length; k++, j--) {
                        if (map[k][j] == sign) { //(0,7);(1,6);(2,5);...
                            if (k == map.length - 1) {
                                System.out.println("Minor DIAG");
                                return true;
                            }
                            continue;
                        } else break;
                    }

                }
                break;
        }
        return false;
    }

    private static boolean winCondition(char[][] map, char sign) {
        return  checkLine(LINE_TYPE.HORZ, map, sign) |
                checkLine(LINE_TYPE.VERT, map, sign) |
                checkLine(LINE_TYPE.DIAG, map, sign);

    }

    public static boolean isMapFull(char[][]map) {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (map[i][j] == DOT) return false;
            }
        }
        return true;
    }

}
