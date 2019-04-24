/*
    QA Automation 2019
    TicTacToe

    Human console input
 */
package homework3;

import java.util.Scanner;

class HumanInputStrategy extends Strategy {

    HumanInputStrategy(TicTacToe board, int playerID) {
        super(board, playerID);
    }

    public String getNextMove() {
        String input = "";
        Scanner scanner = new Scanner(System.in);

        System.out.println("Free cells:");
        System.out.println(board.getFreeCells());
        System.out.println("Enter move for player " + TicTacToe.getSymbolFromCellValue(playerID) + " in '01' format, 0 - No of row, 1 - No of column. Starting from ZERO!");
        while (input.isEmpty()) {
            input = scanner.nextLine();
            if (input.length() != 2 || !board.getFreeCells().contains(input)) {
                System.out.println("Invalid input, try again");
                input = "";
            }
        }
        return input;
    }
}
