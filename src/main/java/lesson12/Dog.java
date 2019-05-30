package lesson12;

public class Dog extends Pet {
    @Override
    public void voice() {
        System.out.println("BUFF - BUFF");
    }

    @Override
    public void move() {
        System.out.println("Ran 10 ft");
    }
}
