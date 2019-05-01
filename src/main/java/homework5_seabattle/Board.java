package homework5_seabattle;

import java.util.ArrayList;
import java.util.List;

class Board {
    private static final int CELL_EMPTY = 0;
    static final int CELL_SHOOT_MISS = -1;
    private static final int CELL_SHIP = 1;
    private static final int CELL_SHIP_HIT = 2;
    private static final int BOARD_SIZE = 8;
    private int[][] board;

    Board() {
        board = new int[BOARD_SIZE][BOARD_SIZE];
        int[] shipsList = {4}; // {4, 3, 3, 2, 2, 2, 1, 1, 1, 1}; // list of ships length
        initializeBoard(board);
        placeShips(board, shipsList);
    }

    private static void initializeBoard(int[][] board) {
        // fills the board with EMPTY_CELLs
        for (int i = 0; i < BOARD_SIZE; i++)
            for (int j = 0; j < BOARD_SIZE; j++)
                board[i][j] = CELL_EMPTY;
    }

    private static void placeShips(int[][] board, int[] shipsList) {
        // randomly places ships on an empty board
        List<String> emptyCells = getEmptyCells(board);
        // TODO
    }

    private String getSymbolFromCellValue(int value, boolean showShips) {
        switch (value) {
            case CELL_SHOOT_MISS: return "*";
            case CELL_SHIP_HIT: return "X";
            case CELL_SHIP: return (showShips ? "O" : ".");
            default: return ".";
        }
    }

    String[] boardAsString(boolean showShips) {
        String[] boardStr = new String[BOARD_SIZE+1];
        boardStr[0] = "   ";
        for (int i = 0; i < BOARD_SIZE; i++)
            boardStr[0] += (char)('A' + i) + " ";
        for (int i = 1; i < BOARD_SIZE + 1; i++) {
            boardStr[i] = i + "  ";
            for (int j = 0; j < BOARD_SIZE; j++)
                boardStr[i] += getSymbolFromCellValue(board[i-1][j], showShips) + " ";
        }
        return boardStr;
    }

    private static String getCellAddress(int c0, int c1) {
        return "" + (char)(c1 + 'A') + (char)(c0 + '0');
    }

    private static int[] getCoordsFromString(String coords) {
        int[] c = new int[2];

        c[1] = coords.charAt(0) - 'A';
        c[0] = coords.charAt(1) - '1';
        return c;
    }

    int shoot(String shot) {
        int[] coords = getCoordsFromString(shot);

        switch (board[coords[0]][coords[1]]) {
            case CELL_EMPTY: board[coords[0]][coords[1]] = CELL_SHOOT_MISS; break;
            case CELL_SHIP: board[coords[0]][coords[1]] = CELL_SHIP_HIT; break;
        }
        return board[coords[0]][coords[1]];
    }

    static List<String> getEmptyCells(int[][] board) {
        ArrayList<String> freeCells = new ArrayList<String>();

        for (int i = 0; i < BOARD_SIZE; i++)
            for (int j = 0; j < BOARD_SIZE; j++)
                if (board[j][i] == CELL_EMPTY) {
                    freeCells.add("" + (char) ('A' + i) + (j + 1));
                }
        return freeCells;
    }

    List<String> getShotableCells() {
        ArrayList<String> freeCells = new ArrayList<String>();

        for (int i = 0; i < BOARD_SIZE; i++)
            for (int j = 0; j < BOARD_SIZE; j++)
                if (board[j][i] == CELL_EMPTY || board[j][i] == CELL_SHIP) {
                    freeCells.add("" + (char) ('A' + i) + (j + 1));
                }
        return freeCells;
    }
}
