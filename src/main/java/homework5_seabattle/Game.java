package homework5_seabattle;

public class Game {
    private static void printBoard(Board board) {
        String[] boardStr = board.boardAsString(false);

        for (String str : boardStr)
            System.out.println(str);
    }

    public static void main(String[] args) {
        Board board = new Board();
        Player humanPlayer = new Player(board, new HumanInputStrategy(board));

        for (int i = 0; i < 5; i++) {
            printBoard(humanPlayer.getBoard());
            humanPlayer.shoot();
        }
        printBoard(humanPlayer.getBoard());
    }
}
