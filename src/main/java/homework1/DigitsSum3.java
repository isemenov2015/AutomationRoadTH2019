/*
 *  QA automation course
 *  Homework task MATH
 * 4. В переменной n хранится натуральное трёхзначное число.
 * Создайте программу, вычисляющую и выводящую на экран сумму цифр числа n.
 */

package homework1;

public class DigitsSum3 {

    public static void main(String[] args) {
        int n = 598;

        System.out.println("Number N given: " + n + ", its digits sum: " + (n / 100 + n / 10 - n / 100 * 10 + n % 10));
    }
}