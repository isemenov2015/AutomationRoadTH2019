package lesson2;

public class TypeConversion {

    public static void main(String[] args) {
        int i = 80;
        float d = 1.09f;
        double d1 = 1.098e-10;

        char chr = (char) i;
        System.out.println("Int 80 to Char '80': " + chr);
        chr = (char) d;
        System.out.println("Float 1.09f to Char: " + chr);
        i = (int) chr;
        System.out.println("Float 1.09f -> Char -> Int: " + i);
    }
}