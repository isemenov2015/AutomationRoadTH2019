/* Десять котов
Создать класс кот – Cat, с полем «имя» (String).
Создать словарь Map(<String, Cat>) и добавить туда 10 котов в виде «Имя»-«Кот».
Получить из Map множество(Set) всех имен и вывести его на экран.
*/

package homework6_cats;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Solution {
    public static void main(String[] args)
    {
        Map<String, Cat> map = createMap();
        Set<Cat> set = convertMapToSet(map);
        printCatSet(set);
    }

    private static Map<String, Cat> createMap() {
        Map<String, Cat> catsMap = new HashMap<String, Cat>();

        for (int i = 1; i < 11; i++) {
            String catName = "Cat" + i;
            catsMap.put(catName, new Cat(catName));
        }
        return catsMap;
    }

    private static Set<Cat> convertMapToSet(Map<String, Cat> map) {
        return new HashSet<Cat>(map.values());
    }

    private static void printCatSet(Set<Cat> set) {
        for (Cat cat : set)
            System.out.println(cat);
    }

    public static class Cat
    {
        private String name;

        public Cat(String name)
        {
            this.name = name;
        }

        public String toString()
        {
            return "Cat "+this.name;
        }
    }
}
