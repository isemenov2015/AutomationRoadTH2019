package homework5_seabattle;

public class Player {
    private Strategy strategy;
    private Board board;

    Player(Board board, Strategy strategy) {
        this.board = board;
        this.strategy = strategy;
    }

    String getNextMove() {
        return strategy.getNextMove();
    }

    void shoot(String move) {
        board.shoot(move);
    }

    Board getBoard() {
        return board;
    }
}
