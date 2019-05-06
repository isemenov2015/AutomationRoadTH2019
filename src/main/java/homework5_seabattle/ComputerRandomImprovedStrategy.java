package homework5_seabattle;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

class ComputerRandomImprovedStrategy extends Strategy {
    private ArrayList<String> obligatoryMoves;
    private String prevSuccessfulMove;

    ComputerRandomImprovedStrategy(Board board) {
        super(board);
        obligatoryMoves = new ArrayList<String>();
        prevSuccessfulMove = "";
    }

    String getNextMove() {
        List<String> possibleMoves = board.getShotableCells();
        Random randomGenerator = new Random();
        String move = possibleMoves.get(randomGenerator.nextInt(possibleMoves.size()));

        if (board.getCellValueByCellAddress(board.getPreviousShot()) == Board.CELL_SHIP_HIT) {
            // previous shot has sunk the ship
            boolean shipSunk = true;
            ArrayList<String> ship = board.getFullShipFromCell(board.getBoard(), board.getPreviousShot());
            for (String cell : ship)
                if (board.getCellValueByCellAddress(cell) != Board.CELL_SHIP_HIT) {
                    shipSunk = false;
                    break;
                }
            if (shipSunk) {
                prevSuccessfulMove = "";
                obligatoryMoves = new ArrayList<String>();
                return move;
            }
        }

        if (board.getCellValueByCellAddress(board.getPreviousShot()) == Board.CELL_SHIP_HIT && prevSuccessfulMove.length() == 0) {
            prevSuccessfulMove = board.getPreviousShot();
            obligatoryMoves = board.getShotableNeighborCells(prevSuccessfulMove);
            if (obligatoryMoves.size() > 0)
                return obligatoryMoves.get(randomGenerator.nextInt(obligatoryMoves.size()));
            else
                return move;
        }

        System.out.println("Computer moves: " + move);
        return move;
    }
}
