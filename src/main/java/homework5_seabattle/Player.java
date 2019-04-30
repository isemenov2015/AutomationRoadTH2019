package homework5_seabattle;

import java.util.List;

public class Player {
    private Strategy strategy;
    private Board board;

    Player(Board board, Strategy strategy) {
        this.board = board;
        this.strategy = strategy;
    }

    //String getNextMove() {
    //    return strategy.getNextMove();
    //}

    void shoot() {
        board.shoot(strategy.getNextMove());
    }
    List<String> getPossibleMoves() { return board.getFreeCells(); }
    Board getBoard() {
        return board;
    }
}
