package lesson4;

import java.util.Scanner;

public class PrintMinAndMaxOf5Numbers {
    private static final int N_NUMBERS = 5;

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int[] values = new int[N_NUMBERS];
        System.out.println("Enter " + N_NUMBERS + " integers");
        for (int i = 0; i < N_NUMBERS; i++) {
            values[i] = scan.nextInt();
        }
        int maximum = values[0];
        int minimum = values[0];
        for (int val : values) {
            if (val > maximum) {
                maximum = val;
            }
            else if (val < minimum) {
                minimum = val;
            }
        }

        System.out.println("Maximum element is: " + maximum + ", minimum element is: " + minimum);
    }
}