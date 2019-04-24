package lesson6;

public class ArraysExample {
    public static void main(String[] args) {
            int[] a = {1,2,3};
            a[0] = 6;
            a[1] = 2;
            a[2] = 3;
            char b[] = {'a','b','1'};
            boolean bool[] = {true,true,false};
            for (int el : a) {
                System.out.println(el);
            }
    }
}
