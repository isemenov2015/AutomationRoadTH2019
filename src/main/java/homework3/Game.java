/*
    QA Autoomation 2019 course
    Реализовать консольную игру крестики-нолики.
Использовать 2х мерный массив символов. Символы только X и O.

На каждом шаге игры выводить на экран массив со значениями текущего состояния "клеток":
X O X
X X O
O X O

У игры должно быть 3 режима:
1. Компьютер против Компьютера
2. Игрок против Компьютера
3. Игрок против Игрока

Дополнительно:

В режиме "Игрок против Компьютера" Сделать 2 уровня сложности: Сложный и Легкий.
*/

package homework3;

import java.util.Arrays;
import java.util.Scanner;

public class Game {

    static void printBoardState(TicTacToe board) {
        for (String[] row : board.boardAsString())
            System.out.println(Arrays.toString(row));
    }

    private static void simulateGame(TicTacToe board, Player player1, Player player2) {
        int round = 0;
        Player[] players = new Player[2];

        players[0] = player1;
        players[1] = player2;
        printBoardState(board);

        while (!board.gameOver()) {
            int currentPlayerNo = round % 2;
            String move = players[currentPlayerNo].getNextMove();
            board.move(players[currentPlayerNo].getId(), move);
            System.out.println("Player " + players[currentPlayerNo].getPlayerSymbol() + " moved: " + move);
            printBoardState(board);
            round++;
        }
        System.out.println("Game over");
        System.out.println(board.winner() != TicTacToe.CELL_EMPTY ? "Winner is: " + board.winnerStr() : "It's a tie");
    }

    private static void humanComputerWeak() {
        TicTacToe ticTacToe = new TicTacToe(3);
        Player humanPlayer = new Player(TicTacToe.CELL_X, true, new HumanInputStrategy(ticTacToe, TicTacToe.CELL_X));
        Player computerPlayer = new Player(TicTacToe.CELL_0, false, new RandomStrategy(ticTacToe, TicTacToe.CELL_0));

        simulateGame(ticTacToe, humanPlayer, computerPlayer);
    }

    private static void humanCHuman() {
        TicTacToe ticTacToe = new TicTacToe(3);
        Player humanPlayer1 = new Player(TicTacToe.CELL_X, true, new HumanInputStrategy(ticTacToe, TicTacToe.CELL_X));
        Player humanPlayer2 = new Player(TicTacToe.CELL_0, true, new HumanInputStrategy(ticTacToe, TicTacToe.CELL_0));

        simulateGame(ticTacToe, humanPlayer1, humanPlayer2);
    }

    private static void computerWeakComputerWeak() {
        TicTacToe ticTacToe = new TicTacToe(3);
        Player compPlayer1 = new Player(TicTacToe.CELL_X, false, new RandomStrategy(ticTacToe, TicTacToe.CELL_X));
        Player compPlayer2 = new Player(TicTacToe.CELL_0, false, new RandomStrategy(ticTacToe, TicTacToe.CELL_0));

        simulateGame(ticTacToe, compPlayer1, compPlayer2);
    }

    private static void computerWeakComputerStrong() {
        TicTacToe ticTacToe = new TicTacToe(3);
        Player compPlayer1 = new Player(TicTacToe.CELL_X, false, new RandomStrategy(ticTacToe, TicTacToe.CELL_X));
        Player compPlayer2 = new Player(TicTacToe.CELL_0, false, new MinimaxStrategy(ticTacToe, TicTacToe.CELL_0));

        simulateGame(ticTacToe, compPlayer1, compPlayer2);
    }

    private static void computerStrongComputerStrong() {
        TicTacToe ticTacToe = new TicTacToe(3);
        Player compPlayer1 = new Player(TicTacToe.CELL_X, false, new MinimaxStrategy(ticTacToe, TicTacToe.CELL_X));
        Player compPlayer2 = new Player(TicTacToe.CELL_0, false, new MinimaxStrategy(ticTacToe, TicTacToe.CELL_0));

        simulateGame(ticTacToe, compPlayer1, compPlayer2);
    }

    private static void humanComputerStrong() {
        TicTacToe ticTacToe = new TicTacToe(3);
        Player humanPlayer1 = new Player(TicTacToe.CELL_X, true, new HumanInputStrategy(ticTacToe, TicTacToe.CELL_X));
        Player compPlayer2 = new Player(TicTacToe.CELL_0, false, new MinimaxStrategy(ticTacToe, TicTacToe.CELL_0));

        simulateGame(ticTacToe, humanPlayer1, compPlayer2);
    }

    public static void main(String[] args) {

        System.out.println("1: Human - Computer (weak)");
        System.out.println("2: Human - Human");
        System.out.println("3: Human - Computer (strong)");
        System.out.println("4: Computer (weak) - Computer (weak)");
        System.out.println("5: Computer (weak) - Computer (strong)");
        System.out.println("6: Computer (strong) - Computer (strong)");
        System.out.println("Select tic-tac-toe game mode:");
        int choice = 0;
        while (choice == 0) {
            String input = (new Scanner(System.in)).nextLine();
            if (input.length() == 1 && "123456".contains(input))
                choice = Integer.parseInt(input);
            else
                System.out.println("Incorrect input, try again");
        }
        System.out.println("You selected: " + choice);
        switch (choice) {
            case 1: humanComputerWeak(); break;
            case 2: humanCHuman(); break;
            case 3: humanComputerStrong(); break;
            case 4: computerWeakComputerWeak(); break;
            case 5: computerWeakComputerStrong(); break;
            case 6: computerStrongComputerStrong();
        }
    }
}
