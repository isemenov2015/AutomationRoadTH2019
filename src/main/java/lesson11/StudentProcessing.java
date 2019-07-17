package lesson11;

import java.util.*;

public class StudentProcessing {

    public static void main(String[] args) {
        int[] marks1 = {3, 2, 5, 1, 9};
        int[] marks2 = {7, 1, 1, 9, 9};
        int[] marks3 = {6, 2, 8, 1, 0};
        Student stud1 = new Student("Peter", "Johnson", 2000, 5, 2, marks1);
        Student stud2 = new Student("Jane", "Stewart", 1998, 3, 1, marks2);
        Student stud3 = new Student("Julie", "Harris", 2001, 2, 3, marks3);
        Student stud4 = new Student("Philip", "Aaron", 1998, 3, 1, marks2);
        List<Student> studList = new ArrayList<Student>();

        studList.add(stud1);
        studList.add(stud2);
        studList.add(stud3);
        studList.add(stud4);

        Collections.sort(studList, new Comparator<Student>() {
            public int compare(Student s1, Student s2) {
                if (s1.course < s2.course)
                    return 1;
                else if (s1.course > s2.course)
                    return -1;
                else
                    if (s1.surname.compareTo(s2.surname) > 0)
                        return 1;
                    else
                        return -1;
            }
        });

        // Print sorted students list
        for (Student stud : studList)
            System.out.println(stud.toString());

        Map<Integer, Integer[]> averageScore = new HashMap<Integer, Integer[]>();
        int maxAge = Integer.MAX_VALUE;
        Student studMaxAge = stud1;
        int minAge = Integer.MIN_VALUE;
        Student studMinAge = stud1;
        int bestScoreSum = Integer.MIN_VALUE;
        Student studBestScore = stud1;

        for (Student stud : studList) {
            if (stud.birthYear < maxAge) {
                studMaxAge = stud;
                maxAge = stud.birthYear;
            }
            if (stud.birthYear > minAge) {
                studMinAge = stud;
                minAge = stud.birthYear;
            }
        }

        System.out.println("Oldest student: " + studMaxAge.toString());
        System.out.println("Youngest student: " + studMinAge.toString());
    }
}
