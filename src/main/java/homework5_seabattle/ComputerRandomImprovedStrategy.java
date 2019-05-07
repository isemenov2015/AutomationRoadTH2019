package homework5_seabattle;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

class ComputerRandomImprovedStrategy extends Strategy {
    private ArrayList<String> obligatoryMoves;
    private String firstSuccessfulMove;

    ComputerRandomImprovedStrategy(Board board) {
        super(board);
        obligatoryMoves = new ArrayList<String>();
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
                firstSuccessfulMove = "";
                obligatoryMoves = new ArrayList<String>();
                return move;
            }
        }

        if (prevShot.length() > 0 &&
                board.getCellValueByCellAddress(prevShot) == Board.CELL_SHIP_HIT &&
                firstSuccessfulMove.length() == 0) {
            firstSuccessfulMove = board.getPreviousShot();
            obligatoryMoves = board.getShotableNeighborCells(prevShot);
            if (obligatoryMoves.size() > 0) {
                String mv = obligatoryMoves.get(randomGenerator.nextInt(obligatoryMoves.size()));
                obligatoryMoves.remove(mv);
                return mv;
            }
            else {
                firstSuccessfulMove = "";
                obligatoryMoves = new ArrayList<String>();
                return move;
            }
        }

        if (prevShot.length() > 0 &&
                board.getCellValueByCellAddress(prevShot) == Board.CELL_SHOOT_MISS &&
                firstSuccessfulMove.length() > 0) {
            if (obligatoryMoves.size() > 0) {
                String mv = obligatoryMoves.get(randomGenerator.nextInt(obligatoryMoves.size()));
                obligatoryMoves.remove(mv);
                return mv;
            }
            else {
                firstSuccessfulMove = "";
                obligatoryMoves = new ArrayList<String>();
                return move;
            }
        }

        if (prevShot.length() > 0 &&
                board.getCellValueByCellAddress(prevShot) == Board.CELL_SHIP_HIT &&
                firstSuccessfulMove.length() > 0) {
            obligatoryMoves.clear();
            if (firstSuccessfulMove.charAt(0) == prevShot.charAt(0)) {
                int minRowNum = Math.min(Integer.parseInt(prevShot.substring(1)), Integer.parseInt(firstSuccessfulMove.substring(1)));
                int maxRowNum = Math.max(Integer.parseInt(prevShot.substring(1)), Integer.parseInt(firstSuccessfulMove.substring(1)));
                if (minRowNum - 1 > -1) {
                    String addr = firstSuccessfulMove.substring(0, 1) + (minRowNum - 1);
                    if (board.getCellValueByCellAddress(addr) == Board.CELL_EMPTY || board.getCellValueByCellAddress(addr) == Board.CELL_SHIP)
                        obligatoryMoves.add(addr);
                }
                if (maxRowNum + 1 < board.getBoard().length) {
                    String addr = firstSuccessfulMove.substring(0, 1) + (maxRowNum + 1);
                    if (board.getCellValueByCellAddress(addr) == Board.CELL_EMPTY || board.getCellValueByCellAddress(addr) == Board.CELL_SHIP)
                        obligatoryMoves.add(addr);
                }
            }
            else {
                char minColChar = (char) Math.min((int) prevShot.charAt(0), (int) firstSuccessfulMove.charAt(0));
                char maxColChar = (char) Math.max((int) prevShot.charAt(0), (int) firstSuccessfulMove.charAt(0));
                if (minColChar - 'A' - 1 > -1) {
                    String addr = (char) (minColChar - 1) + firstSuccessfulMove.substring(1);
                    if (board.getCellValueByCellAddress(addr) == Board.CELL_EMPTY || board.getCellValueByCellAddress(addr) == Board.CELL_SHIP)
                        obligatoryMoves.add(addr);
                }
                if (maxColChar + 1 - 'A' < board.getBoard().length) {
                    String addr = (char) (maxColChar + 1) + firstSuccessfulMove.substring(1);
                    if (board.getCellValueByCellAddress(addr) == Board.CELL_EMPTY || board.getCellValueByCellAddress(addr) == Board.CELL_SHIP)
                        obligatoryMoves.add(addr);
                }
            }
            if (obligatoryMoves.size() > 0) {
                String mv = obligatoryMoves.get(randomGenerator.nextInt(obligatoryMoves.size()));
                obligatoryMoves.remove(mv);
                return mv;
            }
            else {
                firstSuccessfulMove = "";
                obligatoryMoves = new ArrayList<String>();
                return move;
            }
        }
        return move;
    }
}
