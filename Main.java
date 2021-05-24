package tictactoe;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        char[][] grid = {{' ', ' ', ' '}, {' ', ' ', ' '}, {' ', ' ', ' '}};
        printGrid(grid);
        char currentChar = 'X';
        boolean gameOver = false;

        while (!gameOver) {
            System.out.print("Enter the coordinates: ");
            char[] move = scanner.nextLine().toCharArray();

            if (move[0] < 48 || move[0] > 57 || move[2] < 48 || move[2] > 57) {
                System.out.println("You should enter numbers!");
            } else if (move[0] < 49 || move[0] > 51 || move[2] < 49 || move[2] > 51) {
                System.out.println("Coordinates should be from 1 to 3!");
            } else if (grid[move[0] - 1 - 48][move[2] - 1 - 48] != 32) {
                System.out.println("This cell is occupied! Choose another one!");
            } else {
                grid[move[0] - 1 - 48][move[2] - 1 - 48] = currentChar;
                currentChar = getNextChar(currentChar);
                printGrid(grid);

                if (analyzeGrid(grid)) {
                    gameOver = true;
                }
            }
        }
    }

    public static char getNextChar(char currentChar) {
        char nextChar;
        switch (currentChar) {
            case 'X':
                nextChar = 'O';
                break;

            case 'O':
                nextChar = 'X';
                break;

            default:
                System.out.println("Error");
                nextChar = 'E';
                break;
        }
        return nextChar;
    }

    public static void printGrid(char[][] grid) {
        System.out.println("---------");
        for (int i = 0; i < 3; i++) {
            System.out.println("| "+ grid[i][0] + " " + grid[i][1] + " " + grid[i][2] + " |");
        }
        System.out.println("---------");
    }

    public static boolean analyzeGrid(char[][] grid) {
        // counting characters was necessary for a previous stage of project. can be removed here.
        int countX = countX(grid);
        int countO = countO(grid);
        int countEmpty = countEmpty(grid);

        boolean xWon = didXWin(grid);
        boolean oWon = didOWin(grid);

        return printResults(countX, countO, countEmpty, xWon, oWon);
    }

    public static boolean printResults(int countX, int countO, int countEmpty, boolean xWon, boolean oWon) {
        // first two cases are from previous stage of the project
        if (xWon && oWon || Math.abs(countX - countO) > 1) {
            System.out.println("Impossible");
        } else if (xWon) {
            System.out.print("X wins");
            return true;
        } else if (oWon) {
            System.out.print("O wins");
            return true;
        } else if (countEmpty == 0) {
            System.out.print("Draw");
            return true;
        }
        return false;
    }

    public static boolean didXWin(char[][] grid) {
        boolean xWon = false;
        for (int i = 0; i < 3; i++) {
            if (grid[i][0] + grid[i][1] +grid[i][2] == 264) {
                xWon = true;
            }
            if (grid[0][i] + grid[1][i] +grid[2][i] == 264) {
                xWon = true;
            }
            if (grid[0][0] + grid[1][1] + grid[2][2] == 264) {
                xWon = true;
            }
            if (grid[0][2] + grid[1][1] + grid[2][0] == 264) {
                xWon = true;
            }
        }
        return xWon;
    }

    public static boolean didOWin(char[][] grid) {
        boolean oWon = false;
        for (int i = 0; i < 3; i++) {
            if (grid[i][0] + grid[i][1] +grid[i][2] == 237) {
                oWon = true;
            }
            if (grid[0][i] + grid[1][i] +grid[2][i] == 237) {
                oWon = true;
            }
            if (grid[0][0] + grid[1][1] + grid[2][2] == 237) {
                oWon = true;
            }
            if (grid[0][2] + grid[1][1] + grid[2][0] == 237) {
                oWon = true;
            }
        }
        return oWon;
    }

    public static int countX(char[][] grid) {
        int tempX = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (grid[i][j] == 'X') {
                    tempX++;
                }
            }
        }
        return tempX;
    }

    public static int countO(char[][] grid) {
        int tempO = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (grid[i][j] == 'O') {
                    tempO++;
                }
            }
        }
        return tempO;
    }

    public static int countEmpty(char[][] grid) {
        int temp = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (grid[i][j] == ' ') {
                    temp++;
                }
            }
        }
        return temp;
    }

    // this method was used in previous stage of the project
    public static void formGrid(char[][] grid, char[] input) {
        int k = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                grid[i][j] = input[k];
                k++;
            }
        }
    }
}