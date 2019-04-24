package lesson4;

import java.util.Scanner;

public class PrintSimilarNames {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter two names");
        String name1 = scan.nextLine();
        String name2 = scan.nextLine();

        if (name1.equals(name2)) {
            System.out.println("You entered similar names");
        }
    }
}