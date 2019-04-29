package homework5_seabattle;

public class Game {
    public static void main(String[] args) {
        Board board = new Board();

        String[] boardStr = board.boardAsString(false);

        for (String str : boardStr)
            System.out.println(str);
    }
}
