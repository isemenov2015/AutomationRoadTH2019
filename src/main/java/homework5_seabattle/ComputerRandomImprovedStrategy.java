package homework5_seabattle;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

class ComputerRandomImprovedStrategy extends Strategy {
    private ArrayList<String> obligatoryMoves;

    ComputerRandomImprovedStrategy(Board board) {
        super(board);
        obligatoryMoves = new ArrayList<String>();
    }

    String getNextMove() {
        String prevMove = board.getPreviousShot();
        List<String> possibleMoves = board.getShotableCells();
        Random randomGenerator = new Random();
        if (prevMove.length() > 0 && board.getCellValueByCellAddress(prevMove) == Board.CELL_SHIP_HIT && board.getFullShipFromCell(board.getBoard(), prevMove).size() > 1) {
            obligatoryMoves = board.getShotableNeighborCells(prevMove);
            System.out.println("Previous move was a hit! Obligatory moves: " + obligatoryMoves);
        }
        else
            obligatoryMoves = new ArrayList<String>();
        String move = possibleMoves.get(randomGenerator.nextInt(possibleMoves.size()));

        System.out.println("Computer moves: " + move);
        return move;
    }
}
