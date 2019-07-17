package lesson11;

public class Dog extends Animal {

    @Override
    void voice() {
        System.out.println("Bark-Bark-Bark");
    }

    void uselessLoop() {
        for (int i = 0; i < 1000; i++) {
            if (i > 500)
                System.out.println("Iteration No " + i);
            else
                continue;
        }
        System.out.println("No one will see iteration No 501, 502 etc.");
    }
}
