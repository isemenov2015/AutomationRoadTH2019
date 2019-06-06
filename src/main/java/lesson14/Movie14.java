package lesson14;

public class Movie14 implements Comparable {
    public double score;
    public String title;
    public int year;

    public Movie14(double score, String title) {
        this.score = score;
        this.title = title;
    }

    public Movie14(double score, String title, int year) {
        this.score = score;
        this.title = title;
        this.year = year;
    }

    @Override
    public int compareTo(Object o) {
        Movie14 m = (Movie14)o;
        return (score < m.score) ? -1 : ((score == m.score) ? 0 : 1);    }
}
