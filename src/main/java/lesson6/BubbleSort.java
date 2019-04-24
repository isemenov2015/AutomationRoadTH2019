package lesson6;

public class BubbleSort {

    public static int[] sortBubble(int[] array) {
        boolean sorted = true;

        for (int i = 0; i < array.length - 1; i++) {
            for (int j = 0; j < array.length - i - 1; j++) {
                if (array[j] > array[j+1]) sorted = false;
                if (array[j] > array[j+1]) {
                    int value = array[j];
                    array[j] = array[j+1];
                    array[j+1] = value;
                }
            }
            if (sorted) return array;
            sorted = true;
        }
        return array;
    }

    public static void main(String[] args) {
        int[] input = {89, 67, -9, 6, 0, 19, 998, 45, 88, 23, 762, 90756, -1008, 2, 9853, -367};

        input = sortBubble(input);
        for (int el : input)
            System.out.print(el + " ");
    }
}
