package lesson12;

import java.util.Set;
import java.util.TreeSet;

public class TreeSetExample {
    public static void main(String[] args) {

    Set<String> countrySet = new TreeSet<String>();
    countrySet.add("Россия");
    countrySet.add("Франция");
    countrySet.add("Гондурас");
    countrySet.add("Кот-Д'Ивуар"); // любимая страна всех котов
    System.out.println(countrySet);
    }
}
