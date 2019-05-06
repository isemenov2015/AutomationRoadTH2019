package homework5_seabattle;

class Player {
    private Strategy strategy;
    private Board board;
    private String name;

    Player(String name, Board board, Strategy strategy) {
        this.board = board;
        this.strategy = strategy;
        this.name = name;
    }

    int shoot() {
        return strategy.getBoard().shoot(strategy.getNextMove());
    }
    Board getBoard() {
        return board;
    }
    String getName() {return this.name;}
    Boolean isWinner() {return strategy.getBoard().gameOver();}
}
