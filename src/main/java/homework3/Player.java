/*
    QA Automation 2019 course
    TicTacToe game

    TicTacToe player class
 */

package homework3;

public class Player {
    private int id;
    private boolean isHuman;
    private Strategy strategy;

    Player(int id, boolean isHuman, Strategy strategy) {
        this.id = id;
        this.isHuman = isHuman;
        this.strategy = strategy;
    }
    // getters
    int getId() {
        return this.id;
    }
    public boolean isHuman() {
        return isHuman;
    }
    String getPlayerSymbol() {
        switch (this.id) {
            case TicTacToe.CELL_X: return "X";
            case TicTacToe.CELL_0: return "O";
            default: return ".";
        }
    }
    String getNextMove() {
        return strategy.getNextMove();
    }
}
