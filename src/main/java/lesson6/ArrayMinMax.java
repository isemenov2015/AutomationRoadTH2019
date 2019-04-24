package lesson6;

public class ArrayMinMax {
    public static void main(String[] args) {
        int[] ar = {0, 5, 1999, -907, 46, 0, 88, 854, 88, 77, 734837438, -377, 88735, 999};
        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;

        for (int el : ar) {
            if (max < el) {
                max = el;
            }
            if (min > el) {
                min = el;
            }
        }
        System.out.println("Minimum value: " + min);
        System.out.println("Maximum value: " + max);
    }
}
