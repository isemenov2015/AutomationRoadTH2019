/*
    QA Autoomation 2019 course
    Реализовать консольную игру крестики-нолики.
Использовать 2х мерный массив символов. Символы только X и O.

На каждом шаге игры выводить на экран массив со значениями текущего состояния "клеток":
X O X
X X O
O X O

У игры должно быть 3 режима:
1. Компьютер против Компьютера
2. Игрок против Компьютера
3. Игрок против Игрока

Дополнительно:

В режиме "Игрок против Компьютера" Сделать 2 уровня сложности: Сложный и Легкий.
*/

package homework3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

class  TicTacToe {
    static final int CELL_EMPTY = 0;
    static final int CELL_X = 1;
    static final int CELL_0 = -1;
    private int[][] board;

    TicTacToe(int boardSize) {
        board = new int[boardSize][boardSize];
        for (int i = 0; i < boardSize; i++)
            for (int j = 0; j < boardSize; j++)
                board[i][j] = 0;
    }

    TicTacToe(TicTacToe game) {
        board = new int[game.getBoard().length][game.getBoard().length];
        for (int i = 0; i < board.length; i++) {
            board[i] = Arrays.copyOf(game.getBoard()[i], game.getBoard()[i].length);
        }
    }

    public static String getSymbolFromCellValue(int value) {
        switch (value) {
            case CELL_EMPTY: return ".";
            case CELL_0: return "O";
            case CELL_X: return "X";
        }
        return ".";
    }

    String[][] boardAsString() {
        // returns String representation of a board with X and O
        String[][] boardStr = new String[board.length][board.length];
        for (int row = 0; row < board.length; row++)
            for (int col = 0; col < board.length; col++)
                boardStr[row][col] = getSymbolFromCellValue(board[row][col]);
        return boardStr;
    }

    void move(int playerID, String cell) {
        // performs a move, valid cell format: "02", 0 - row, 2 - column
        board[cell.charAt(0) - '0'][cell.charAt(1) - '0'] = playerID;
    }

    ArrayList<String> getFreeCells() {
        // Returns a list of a free cells in "02" format
        ArrayList<String> freeCells = new ArrayList<String>();
        for (int row = 0; row < board.length; row++)
            for (int col = 0; col < board.length; col++)
                if (board[row][col] == CELL_EMPTY)
                    freeCells.add(Integer.toString(row) + col);
        return freeCells;
    }

    int winner() {
        // returns a winner if exists, CELL_EMPTY otherwise
        for (int row = 0; row < board.length; row++) {  // check rows
            Set<Integer> values = new HashSet<Integer>();
            for (int col = 0; col < board.length; col++)
                values.add(board[row][col]);
            if (values.size() == 1 && board[row][0] != CELL_EMPTY)
                return board[row][0];
        }
        for (int col = 0; col < board.length; col++) { // check columns
            Set<Integer> values = new HashSet<Integer>();
            for (int row = 0; row < board.length; row++)
                values.add(board[row][col]);
            if (values.size() == 1 && board[0][col] != CELL_EMPTY)
                return board[0][col];
        }
        Set<Integer> values = new HashSet<Integer>();
        for (int i = 0; i < board.length; i++) // top-left bottom-right diagonal
            values.add(board[i][i]);
        if (values.size() == 1 && board[0][0] != CELL_EMPTY)
            return board[0][0];
        values = new HashSet<Integer>();
        for (int i = 0; i < board.length; i++) // top-right bottom-left diagonal
            values.add(board[i][board.length - 1 - i]);
        if (values.size() == 1 && board[0][board.length-1] != CELL_EMPTY)
            return board[0][board.length-1];
        return CELL_EMPTY;
    }

    String winnerStr() {
        return getSymbolFromCellValue(winner());
    }

    int[][] getBoard() {
        // returns board array 'as is'
        return board;
    }

    boolean gameOver() {
        // returns true if game is over, false otherwise
        if (getFreeCells().size() == 0) // no free cells left
            return true;
        return winner() != CELL_EMPTY; // there's a winner
    }
}
