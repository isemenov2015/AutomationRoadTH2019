package homework5_seabattle;

abstract class Strategy {
    Board board;

    Strategy(Board board) {
        this.board = board;
    }

    abstract String getNextMove();

    Board getBoard() {return board;}
}
