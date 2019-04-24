/*
 *  QA automation course
 *  Homework task MATH
 * 2. В переменной n хранится натуральное двузначное число.
 * Создайте программу, вычисляющую и выводящую на экран сумму цифр числа n.
 */

package homework1;

public class DigitsSum2 {
    public static void main(String[] args) {
        int n = 30;

        System.out.println("Number N given: " + n + ", its digits sum is: " + (n / 10 + n % 10));
    }
}