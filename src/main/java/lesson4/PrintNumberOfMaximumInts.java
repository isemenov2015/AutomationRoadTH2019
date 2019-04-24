package lesson4;

import java.util.Scanner;

public class PrintNumberOfMaximumInts {
    private static final int N_NUMBERS = 4;

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int[] values = new int[N_NUMBERS];
        System.out.println("Enter " + N_NUMBERS + " integers");
        for (int i = 0; i < N_NUMBERS; i++) {
            values[i] = scan.nextInt();
        }
        int maximum = values[0];
        int n_max = 0;
        for (int val : values) {
            if (val > maximum) {
                maximum = val;
                n_max = 1;
            }
            else if (val == maximum) {
                n_max += 1;
            }
        }

        System.out.println("Number of maximum elements is: " + n_max);
    }
}