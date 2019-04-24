package lesson7;

public class StringIsImmutable {
    public static void main(String[] args) {
        String myString = "JACK";
        myString.concat(" SPARROW"); // "JACK" + " SPARROW"
        System.out.println(myString); // JACK
//метод concat возвращает новую строку - ОН НЕ ИЗМЕНЯЕТ ОБЪЕКТ
        myString = myString.concat(" SPARROW"); // myString = "JACK" + " SPARROW"
        System.out.println(myString); // JACK SPARROW
    }
}
