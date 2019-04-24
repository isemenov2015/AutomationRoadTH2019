package homework3;

abstract class Strategy {
    TicTacToe board;
    int playerID;

    Strategy(TicTacToe board, int playerID) {
        this.board = board;
        this.playerID = playerID;
    }

    abstract String getNextMove();
}
