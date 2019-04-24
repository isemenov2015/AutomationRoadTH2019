/*
    QA Automation 2019
    TicTacToe

    Minimax move selection (strong computer-driven) strategy
 */

package homework3;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class MinimaxStrategy extends Strategy {
    private static final int WIN_SCORE = 10;

    MinimaxStrategy(TicTacToe board, int playerID) {
        super(board, playerID);
    }

    private int score(TicTacToe board, int playerID, int level) {
        if (board.winner() == playerID)
            return WIN_SCORE - level;
        else if (board.winner() == TicTacToe.CELL_EMPTY)
            return 0;
        else
            return level - WIN_SCORE;
    }

    private int minimax(TicTacToe board, int playerID, int level, boolean isMaximizing) {
        if (board.gameOver())
            return score(board, isMaximizing ? -playerID : playerID, level);

        if (isMaximizing) {
            int bestValue = Integer.MIN_VALUE;
            for (String move : board.getFreeCells()) {
                TicTacToe newBoard = new TicTacToe(board);
                newBoard.move(-playerID, move);
                bestValue = Math.max(bestValue, minimax(newBoard, -playerID, level+1, false));
            }
            return bestValue;
        }
        else {
            int bestValue = Integer.MAX_VALUE;
            for (String move : board.getFreeCells()) {
                TicTacToe newBoard = new TicTacToe(board);
                newBoard.move(-playerID, move);
                bestValue = Math.min(bestValue, minimax(newBoard, -playerID, level+1, true));
            }
            return bestValue;
        }
    }

    String getNextMove() {
        ArrayList<String> validMoves = board.getFreeCells();
        List<Integer> scores = new ArrayList<Integer>();
        for (String move : validMoves) {
            TicTacToe newBoard = new TicTacToe(board);
            newBoard.move(playerID, move);
            scores.add(minimax(newBoard, playerID, 0, false));
        }
        return validMoves.get(scores.indexOf(Collections.max(scores)));
    }
}
