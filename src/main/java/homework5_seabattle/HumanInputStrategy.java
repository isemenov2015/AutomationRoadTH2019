package homework5_seabattle;

import java.util.List;
import java.util.Scanner;

class HumanInputStrategy extends Strategy {

    HumanInputStrategy(Board board) {
        super(board);
    }

    String getNextMove() {
        String move = "";
        Scanner input = new Scanner(System.in);
        List<String> possibleMoves = board.getShotableCells();

        System.out.println("Possible shots: " + possibleMoves);
        System.out.println("Make a shot");
        while (move.length() == 0) {
            move = input.nextLine();
            if (!possibleMoves.contains(move)) {
                System.out.println("Wrong input, try again");
                move = "";
            }
        }
        return move;
    }

    Board getBoard() {return board;}
}
