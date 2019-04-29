package homework5_seabattle;

class Board {
    static final int CELL_EMPTY = 0;
    static final int CELL_SHOOT_MISS = -1;
    static final int CELL_SHIP = 1;
    static final int CELL_SHIP_HIT = 2;
    static final int BOARD_SIZE = 8;
    private int[][] board;

    Board() {
        board = new int[BOARD_SIZE][BOARD_SIZE];
        for (int i = 0; i < BOARD_SIZE; i++)
            for (int j = 0; j < BOARD_SIZE; j++)
                board[i][j] = CELL_EMPTY;
    }

    String getSymbolFromCellValue(int value, boolean showShips) {
        switch (value) {
            case CELL_SHOOT_MISS: return "*";
            case CELL_SHIP_HIT: return "X";
            case CELL_SHIP: return (showShips ? "O" : ".");
            default: return ".";
        }
    }

    String[] boardAsString(boolean showShips) {
        String[] boardStr = new String[BOARD_SIZE+1];
        boardStr[0] = "  ";
        for (int i = 0; i < BOARD_SIZE; i++)
            boardStr[0] += (char)('A' + i) + " ";
        for (int i = 1; i < BOARD_SIZE + 1; i++) {
            boardStr[i] = i + " ";
            for (int j = 0; j < BOARD_SIZE; j++)
                boardStr[i] += getSymbolFromCellValue(board[i-1][j], showShips) + " ";
        }
        return boardStr;
    }

    void shoot(String shot) {
        int[] coords = new int[2];

        coords[0] = shot.charAt(0) - 'A';
        coords[1] = shot.charAt(1) - '1';
        switch (board[coords[0]][coords[1]]) {
            case CELL_EMPTY: board[coords[0]][coords[1]] = CELL_SHOOT_MISS; break;
            case CELL_SHIP: board[coords[0]][coords[1]] = CELL_SHIP_HIT; break;
        }
    }
}
