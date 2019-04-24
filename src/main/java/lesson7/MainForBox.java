package lesson7;

public class MainForBox {
    public static void main(String[] args) {
        Box mybox = new Box();
        Box cube = new Box();
        Box parallelepiped = new Box();

        cube.setDim(10, 10, 10);
        parallelepiped.setDim(10, 20, 15);

        cube.volume();
        parallelepiped.volume();
    }
}
