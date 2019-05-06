package homework5_seabattle;

import java.util.*;

class Board {
    private static final int CELL_EMPTY = 0;
    static final int CELL_SHOOT_MISS = -1;
    private static final int CELL_SHIP = 1;
    private static final int CELL_SHIP_HIT = 2;
    private static final int BOARD_SIZE = 10;
    private static final int[] shipsList = {4, 3, 3, 2, 2, 2, 1, 1, 1, 1}; // list of ships lengths
    private int[][] board;

    Board() {
        board = new int[BOARD_SIZE][BOARD_SIZE];
        do {
            initializeBoard(board, true);
        } while (!placeShips(board, shipsList));
        initializeBoard(board, false); // clean unnecessary missed shoots
    }

    private static void initializeBoard(int[][] board, boolean fullInitialise) {
        // fills the board with EMPTY_CELLs
        // if fullInitialize is false, only changes CELL_SHOOT_MISS for CELL_EMPTY
        for (int i = 0; i < BOARD_SIZE; i++)
            for (int j = 0; j < BOARD_SIZE; j++)
                if (fullInitialise)
                    board[i][j] = CELL_EMPTY;
                else
                    if (board[i][j] == CELL_SHOOT_MISS)
                        board[i][j] = CELL_EMPTY;
    }

    private static boolean placeShips(int[][] board, int[] shipsList) {
        // randomly places ships on an empty board
        for (int shipLength : shipsList) {
            ArrayList<ArrayList<String>> shipPlacements = new ArrayList<ArrayList<String>>();
            for (int i = 0; i < board.length; i++)  // all possible horizontal placements
                for (int j = 0; j <= board.length - shipLength; j++) {
                    ArrayList<String> busyCells = new ArrayList<String>();
                    for (int k = 0; k < shipLength; k++)
                        if (board[i][j + k] == CELL_EMPTY) {
                            busyCells.add(getCellAddress(i, j + k));
                        }
                    if (busyCells.size() == shipLength)
                        shipPlacements.add(busyCells);
                }
            for (int i = 0; i <= board.length - shipLength; i++)  // all possible vertical placements
                for (int j = 0; j < board.length; j++) {
                    ArrayList<String> busyCells = new ArrayList<String>();
                    for (int k = 0; k < shipLength; k++)
                        if (board[i + k][j] == CELL_EMPTY) {
                            busyCells.add(getCellAddress(i + k, j));
                        }
                    if (busyCells.size() == shipLength)
                        shipPlacements.add(busyCells);
                }
            if (shipPlacements.size() == 0)
                return false;
            Random randomGenerator = new Random();
            ArrayList<String> ship = shipPlacements.get(randomGenerator.nextInt(shipPlacements.size()));
            //System.out.println(shipPlacements);
            //System.out.println("Ship selected: " + ship);
            placeShip(board, ship);
        }
        return true;
    }

    private static void placeShip(int[][] board, List<String> ship) {
        for (String coordsStr : ship) {
            int[] coords = getCoordsFromString(coordsStr);
            board[coords[0]][coords[1]] = CELL_SHIP;
            surroundCellWithMissedShots(board, coords);
        }
    }

    private static void surroundCellWithMissedShots(int[][] board, int[] coords) {
        // straights
        if (coords[0] - 1 > -1 && board[coords[0] - 1][coords[1]] == CELL_EMPTY)
            board[coords[0] - 1][coords[1]] = CELL_SHOOT_MISS;
        if (coords[0] + 1 < board.length && board[coords[0] + 1][coords[1]] == CELL_EMPTY)
            board[coords[0] + 1][coords[1]] = CELL_SHOOT_MISS;
        if (coords[1] - 1 > -1 && board[coords[0]][coords[1] - 1] == CELL_EMPTY)
            board[coords[0]][coords[1] - 1] = CELL_SHOOT_MISS;
        if (coords[1] + 1 < board.length && board[coords[0]][coords[1] + 1] == CELL_EMPTY)
            board[coords[0]][coords[1] + 1] = CELL_SHOOT_MISS;
        // diagonals
        if (coords[0] - 1 > -1 && coords[1] - 1 > -1 && board[coords[0] - 1][coords[1] - 1] == CELL_EMPTY)
            board[coords[0] - 1][coords[1] - 1] = CELL_SHOOT_MISS;
        if (coords[0] - 1 > -1 && coords[1] + 1 < board.length && board[coords[0] - 1][coords[1] + 1] == CELL_EMPTY)
            board[coords[0] - 1][coords[1] + 1] = CELL_SHOOT_MISS;
        if (coords[0] + 1 < board.length && coords[1] - 1 > -1 && board[coords[0] + 1][coords[1] - 1] == CELL_EMPTY)
            board[coords[0] + 1][coords[1] - 1] = CELL_SHOOT_MISS;
        if (coords[0] + 1 < board.length && coords[1] + 1 < board.length && board[coords[0] + 1][coords[1] + 1] == CELL_EMPTY)
            board[coords[0] + 1][coords[1] + 1] = CELL_SHOOT_MISS;
    }

    private static ArrayList<String> getFullShipFromCell(int[][] board, String cell) {
        Stack<String> cellsStack = new Stack<String>();
        ArrayList<String> ship = new ArrayList<String>();
        Set<String> visited = new HashSet<String>();
        cellsStack.push(cell);
        while (!cellsStack.empty()) {
            String newCell = cellsStack.pop();
            ship.add(newCell);
            visited.add(newCell);
            int[] coords = getCoordsFromString(newCell);
            int[] newCoords = new int[2];
            newCoords[0] = coords[0] - 1;
            newCoords[1] = coords[1];
            if ((newCoords[0] > -1 && board[newCoords[0]][newCoords[1]] > CELL_EMPTY) &&
                    !visited.contains(getCellAddress(newCoords[0], newCoords[1])))
                cellsStack.add(getCellAddress(newCoords[0], newCoords[1]));
            newCoords[0] = coords[0] + 1;
            newCoords[1] = coords[1];
            if ((newCoords[0] < board.length && board[newCoords[0]][newCoords[1]] > CELL_EMPTY) &&
                !visited.contains(getCellAddress(newCoords[0], newCoords[1])))
                cellsStack.add(getCellAddress(newCoords[0], newCoords[1]));
            newCoords[0] = coords[0];
            newCoords[1] = coords[1] - 1;
            if ((newCoords[1] > -1 && board[newCoords[0]][newCoords[1]] > CELL_EMPTY) &&
                    !visited.contains(getCellAddress(newCoords[0], newCoords[1])))
                cellsStack.add(getCellAddress(newCoords[0], newCoords[1]));
            newCoords[0] = coords[0];
            newCoords[1] = coords[1] + 1;
            if ((newCoords[1] < board.length && board[newCoords[0]][newCoords[1]] > CELL_EMPTY) &&
                    !visited.contains(getCellAddress(newCoords[0], newCoords[1])))
                cellsStack.add(getCellAddress(newCoords[0], newCoords[1]));
        }
        return ship;
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
        boardStr[0] = "    ";
        for (int i = 0; i < BOARD_SIZE; i++)
            boardStr[0] += (char)('A' + i) + " ";
        for (int i = 1; i < BOARD_SIZE + 1; i++) {
            boardStr[i] = i + (i < 10 ? "   " : "  ");
            for (int j = 0; j < BOARD_SIZE; j++)
                boardStr[i] += getSymbolFromCellValue(board[i-1][j], showShips) + " ";
        }
        return boardStr;
    }

    private static String getCellAddress(int c0, int c1) {
        return "" + (char)(c1 + 'A') + (c0+1);
    }

    private static int[] getCoordsFromString(String coords) {
        int[] c = new int[2];

        c[1] = coords.charAt(0) - 'A';
        c[0] = Integer.parseInt(coords.substring(1)) - 1;
        return c;
    }

    int shoot(String shot) {
        int[] coords = getCoordsFromString(shot);

        switch (board[coords[0]][coords[1]]) {
            case CELL_EMPTY: board[coords[0]][coords[1]] = CELL_SHOOT_MISS; break;
            case CELL_SHIP: {
                board[coords[0]][coords[1]] = CELL_SHIP_HIT;
                boolean fullHit = true;
                for (String cell : getFullShipFromCell(board, shot))
                    if (board[getCoordsFromString(cell)[0]][getCoordsFromString(cell)[1]] != CELL_SHIP_HIT) {
                        fullHit = false;
                        break;
                    }
                if (fullHit) {  // put shots around ship that sunk
                    for (String cell : getFullShipFromCell(board, shot))
                        surroundCellWithMissedShots(board, getCoordsFromString(cell));
                }
                break;
            }
        }
        return board[coords[0]][coords[1]];
    }
/*
    static List<String> getEmptyCells(int[][] board) {
        ArrayList<String> freeCells = new ArrayList<String>();

        for (int i = 0; i < BOARD_SIZE; i++)
            for (int j = 0; j < BOARD_SIZE; j++)
                if (board[j][i] == CELL_EMPTY) {
                    freeCells.add("" + (char) ('A' + i) + (j + 1));
                }
        return freeCells;
    }
*/

    boolean gameOver() {
        int totalShipCells = 0;
        int totalHitCells = 0;

        for (int tCells : shipsList)
            totalShipCells += tCells;
        for (int i = 0; i < board.length; ++i)
            for (int j = 0; j < board.length; j++)
                if (board[i][j] == CELL_SHIP_HIT)
                    totalHitCells++;
        return totalHitCells == totalShipCells;
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
