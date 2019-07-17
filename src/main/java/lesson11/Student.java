package lesson11;

import java.util.Arrays;

public class Student {
    public String surname;
    public String name;
    public int birthYear;
    public int course;
    public int group;
    public int[] marks;

    Student(String name, String surname, int birthYear, int course, int group, int[] marks) {
        this.name = name;
        this.surname = surname;
        this.birthYear = birthYear;
        this.course = course;
        this.group = group;
        this.marks = marks;
    }

    public String toString() {
        return surname + " " + name + " " + birthYear + " " + course + " " + group + " " + Arrays.toString(marks);
    }
}
