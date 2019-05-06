package homework5_seabattle;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

class ComputerRandomImprovedStrategy extends Strategy {
    private ArrayList<String> obligatoryMoves;
    private String prevSuccessfulMove;
    private String firstSuccessfulMove;

    ComputerRandomImprovedStrategy(Board board) {
        super(board);
        obligatoryMoves = new ArrayList<String>();
        prevSuccessfulMove = "";
        firstSuccessfulMove = "";
    }

    String getNextMove() {
        List<String> possibleMoves = board.getShotableCells();
        Random randomGenerator = new Random();
        String move = possibleMoves.get(randomGenerator.nextInt(possibleMoves.size()));

        String prevShot = board.getPreviousShot();
        if (prevShot.length() > 0 &&
                board.getCellValueByCellAddress(prevShot) == Board.CELL_SHIP_HIT) {
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

        if (prevShot.length() > 0 &&
                board.getCellValueByCellAddress(board.getPreviousShot()) == Board.CELL_SHIP_HIT &&
                prevSuccessfulMove.length() == 0) {
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
