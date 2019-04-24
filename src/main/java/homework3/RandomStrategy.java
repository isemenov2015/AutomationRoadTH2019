/*
    QA Automation 2019
    TicTacToe

    Random move selection (weak computer-driven) strategy
 */

package homework3;

import java.util.ArrayList;
import java.util.Random;

class RandomStrategy extends Strategy {

    RandomStrategy(TicTacToe board, int playerID) {
        super(board, playerID);
    }

    String getNextMove() {
        Random randomGenerator = new Random();
        ArrayList<String> validMoves = board.getFreeCells();
        int index = randomGenerator.nextInt(validMoves.size());

        return validMoves.get(index);
    }
}
