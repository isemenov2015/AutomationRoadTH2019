package homework6_collections_hand_input;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Solution {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        List<String> list = new ArrayList<String>();
        String input;
        //заполните список строками при помощи reader.readLine();
        while (!(input = reader.readLine()).equals("")) {
            list.add(input);
        }

        sort(list); //метод для сортировки списка

        //вывести список в отсортированном порядке
        System.out.println("List in alphabetically sorted order:");
        for (String s : list)
            System.out.println(s);
    }

    public static void sort(List<String> list) {
        //реализуйте свой алгоритм сортировки списка при помощи  метода isGreaterThan(String a, String b)
        Collections.sort(list, new Comparator<String>() {
            public int compare(String s1, String s2) {
                if (isGreaterThan(s1, s2))
                    return 1;
                else
                    return -1;
            }
        });
    }

    //Метод для сравнения строк: 'а' больше чем 'b'
    public static boolean isGreaterThan(String a, String b)
    {
        return a.compareTo(b) > 0;
    }
}
