/*
 *  QA automation course
 *  Homework task MATH
 *  1. В переменных q и w хранятся два натуральных числа.
 *  Создайте программу, выводящую на экран результат деления q на w с остатком.
 *  Пример вывода программы (для случая, когда в q хранится 21, а в w хранится 8):
 *  21 / 8 = 2 и 5 в остатке
 */

package homework1;

public class DivisionRemainder {
    private static int[] divisionWithRemainder(int q, int w) {
        int[] lst = new int[2];

        lst[0] = q / w;
        lst[1] = q % w;
        return lst;
    }

    public static void main(String[] args) {
        int q = 21;
        int w = 2;

        if (w == 0) {
            System.out.println("Ошибка: Деление на ноль");
        }
        else {
            int[] result = divisionWithRemainder(q, w);
            System.out.println(result[0] + " и " + result[1] + " в остатке");
        }
    }
}