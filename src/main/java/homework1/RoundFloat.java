/*
 *  QA automation course
 *  Homework task MATH
 * 3. В переменной n хранится вещественное число с ненулевой дробной частью.
 * Создайте программу, округляющую число n до ближайшего целого и выводящую результат на экран.
 */

package homework1;

public class RoundFloat {
    public static void main(String[] args) {
        double n = 1.51;

        System.out.println("Number N given: " + n + ", N rounded to the nearest int: " + Math.round(n));
    }
}