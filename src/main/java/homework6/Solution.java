package homework6;

/* Множество всех животных
1. Внутри класса Solution создать public static классы Cat, Dog.
2. Реализовать метод createCats, котороый должен возвращать множество с 4 котами.
3. Реализовать метод createDogs, котороый должен возвращать множество с 3 собаками.
4. Реализовать метод join, котороый должен возвращать объединенное множество всех животных - всех котов и собак.
5. Реализовать метод removeCats, котороый должен удалять из множества pets всех котов, которые есть в множестве cats.
6. Реализовать метод printPets, котороый должен выводить на экран всех животных, которые в нем есть. Каждое животное с новой строки
*/

import java.util.HashSet;
import java.util.Set;

public class Solution {
    public static void main(String[] args) {
        Set<Cat> cats = createCats();
        Set<Dog> dogs = createDogs();

        Set<Object> pets = join(cats, dogs);
        System.out.println("All pets, CATS and DOGS together:");
        printPets(pets);

        removeCats(pets, cats);
        System.out.println("----------------------");
        System.out.println("With CATS removed:");
        printPets(pets);
    }

    public static Set<Cat> createCats() {
        //Ваш код
        Set<Cat> catsSet = new HashSet<Cat>();
        for (int i = 0; i < 4; i++)
            catsSet.add(new Cat());
        return catsSet;
    }

    public static Set<Dog> createDogs() {
        //Ваш код
        Set<Dog> dogsSet = new HashSet<Dog>();
        for (int i = 0; i < 3; i++)
                dogsSet.add(new Dog());
        return dogsSet;
    }

    public static Set<Object> join(Set<Cat> cats, Set<Dog> dogs) {
        //Ваш код
        Set<Object> animalsSet = new HashSet<Object>();
        animalsSet.addAll(cats);
        animalsSet.addAll(dogs);
        return animalsSet;
    }

    public static void removeCats(Set<Object> pets, Set<Cat> cats) {
        //Ваш код
        pets.removeAll(cats);
    }

    public static void printPets(Set<?> pets) {
        //Ваш код
        for (Object pet : pets)
            System.out.println(pet);
    }

    public static class Cat {}

    public static class Dog {}
}
