package lesson6;

import java.util.Arrays;

public class ArraysBuiltinSort {
    public static void main(String[] args) {
        int someArray[] = {1, 5, 7, 2, 0};
        Arrays.sort(someArray);
        System.out.println("Sorted array:" + Arrays.toString(someArray));
    }
}
