package homework5_seabattle;

abstract class Strategy {
    private Board board;
    private Player player;

    Strategy(Board board, Player player) {
        this.board = board;
        this.player = player;
    }

    abstract String getNextMove();
}
