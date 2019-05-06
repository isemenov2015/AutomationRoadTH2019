package homework5_seabattle;

public class Game {
    private static void printBoard(Player player1, Player player2) {
        String[] boardStr1 = player1.getBoard().boardAsString(true);
        String[] boardStr2 = player2.getBoard().boardAsString(true);

        System.out.println("       " + player1.getName() + " board:               " + player2.getName() + " board:");
        for (int i = 0; i < boardStr1.length; i++)
            System.out.println(boardStr1[i] + "   " + boardStr2[i]);
    }

    public static void main(String[] args) {
        Board humanBoard = new Board();
        Board computerBoard = new Board();
        Player humanPlayer = new Player("Your", humanBoard, new HumanInputStrategy(computerBoard));
        Player computerPlayer = new Player("Computer", computerBoard, new HumanInputStrategy(humanBoard));

        for (int i = 0; i < 15; i++) {
            printBoard(humanPlayer, computerPlayer);
            int shot = humanPlayer.shoot();
            if (shot != Board.CELL_SHOOT_MISS)
                System.out.println("Hit!");
        }
        printBoard(humanPlayer, computerPlayer);
    }
}
