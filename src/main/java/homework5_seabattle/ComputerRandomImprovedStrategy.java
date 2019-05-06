package homework5_seabattle;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

class ComputerRandomImprovedStrategy extends Strategy {
    private ArrayList<String> obligatoryMoves = new ArrayList<String>();

    ComputerRandomImprovedStrategy(Board board) {
        super(board);
    }

    String getNextMove() {
        String prevMove = board.getPreviousShot();
        List<String> possibleMoves = board.getShotableCells();
        Random randomGenerator = new Random();
        if (prevMove.length() > 0 && board.getCellValueByCellAddress(prevMove) == Board.CELL_SHIP_HIT) {
            System.out.println("Previous move was a hit!");
        }
        String move = possibleMoves.get(randomGenerator.nextInt(possibleMoves.size()));

        System.out.println("Computer moves: " + move);
        return move;
    }
}
