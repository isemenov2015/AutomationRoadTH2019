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
        String move = strategy.getNextMove();
        System.out.println(name + " move: " + move);
        return strategy.getBoard().shoot(move);
    }
    Board getBoard() {
        return board;
    }
    String getName() {return this.name;}
    Boolean isWinner() {return strategy.getBoard().gameOver();}
}
