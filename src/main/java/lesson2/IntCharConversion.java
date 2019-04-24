package lesson2;

import java.util.Scanner;

public class IntCharConversion {

    private static char getCharInput(boolean digitsOnly) {

        Scanner scanner = new Scanner(System.in);
        String s = "";
        String messageTail = (digitsOnly) ? "digit" : "character";

        while (s.length() == 0) {
            System.out.println("Enter one " + messageTail + ": ");
            s = scanner.nextLine();
            if (s.length() == 0) {
                System.out.println("Empty input. Repeat, please...");
                continue;
            }
            if (digitsOnly && !Character.isDigit(s.charAt(0))) {
                System.out.println("Char is not a digit");
                s = "";
            }
        }
        return s.charAt(0);
    }

    public static void main(String[] args) {
        char symbol = getCharInput(false);
        int symbolCode = (int) symbol;
        System.out.println("You entered symbol: " + symbol + ", its (int) code is " + symbolCode);
        symbol = getCharInput(true);
        symbolCode = (int) symbol;
        System.out.println("You entered digit with (int) code: " + symbolCode +
                ", its numeric value is " + symbol);
    }
}