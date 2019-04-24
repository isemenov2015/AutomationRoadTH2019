package lesson7;

public class Box {
    public double width;
    public double height;
    public double depth;

    public void volume() {
        System.out.println("The volume is:");
        System.out.println(width*height*depth);
    }

    // устанавливаем размеры коробки
    public void setDim(int w, int h, int d) {
        width = w;
        height = h;
        depth = d;
    }
}
