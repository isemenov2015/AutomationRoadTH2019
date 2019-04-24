package lesson7;

import java.util.Scanner;

public class CheckPalindrom {
    public static void main(String[] args) {
        System.out.println("Enter string:");
        String input = (new Scanner(System.in)).nextLine();

        if (input.isEmpty()) {
            System.out.println("Invalid input: enmpty string");
            return;
        }
        input = input.replaceAll(" ", "");
        for (int i = 0; i < input.length() / 2; i++) {
            if (input.charAt(i) != input.charAt(input.length() - 1 - i)) {
                System.out.println(input + " is not a palindrome");
                return;
            }
        }
        System.out.println(input + " is a palindrome");
    }
}
