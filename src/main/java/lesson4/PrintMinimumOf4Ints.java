package lesson4;

import java.util.Scanner;

public class PrintMinimumOf4Ints {
    private static final int N_NUMBERS = 4;

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int[] values = new int[N_NUMBERS];
        System.out.println("Enter " + N_NUMBERS + " integers");
        for (int i = 0; i < N_NUMBERS; i++) {
            values[i] = scan.nextInt();
        }
        int minimum = values[0];
        for (int val : values) {
            if (val < minimum) {
                minimum = val;
            }
        }

        System.out.println("Minimum number entered is: " + minimum);
    }
}