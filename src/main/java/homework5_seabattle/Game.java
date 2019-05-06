package homework5_seabattle;

public class Game {
    private static void printBoard(Player player1, Player player2) {
        String[] boardStr1 = player1.getBoard().boardAsString(true);
        String[] boardStr2 = player2.getBoard().boardAsString(false);

        System.out.println("       " + player1.getName() + " board:               " + player2.getName() + " board:");
        for (int i = 0; i < boardStr1.length; i++)
            System.out.println(boardStr1[i] + "   " + boardStr2[i]);
    }

    public static void main(String[] args) {
        Board humanBoard = new Board();
        Board computerBoard = new Board();
        Player humanPlayer = new Player("Your", humanBoard, new HumanInputStrategy(computerBoard));
        Player computerPlayer = new Player("Computer", computerBoard, new ComputerRandomImprovedStrategy(humanBoard));
        int counter = 0;

        while (true) {
            Player currentPlayer = counter % 2 == 0 ? humanPlayer : computerPlayer;
            printBoard(humanPlayer, computerPlayer);
            int shot = currentPlayer.shoot();
            if (shot != Board.CELL_SHOOT_MISS)
                System.out.println("Hit!");
            if (currentPlayer.isWinner())
                break;
            counter++;
        }
        printBoard(humanPlayer, computerPlayer);
        if (humanPlayer.isWinner())
            System.out.println("You won!");
        else
            System.out.println("Computer won!");
    }
}
