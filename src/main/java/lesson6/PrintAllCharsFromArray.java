package lesson6;

import java.util.Arrays;

public class PrintAllCharsFromArray {
    public static void main(String[] args) {
        char[] chArray = new char[255];

        for (int i = 0; i < 255; i++) {
            chArray[i] = (char) i;
        }
        System.out.println(Arrays.toString(chArray));
    }
}
