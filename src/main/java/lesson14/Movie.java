package lesson14;

public class Movie implements Comparable {
    public double score;
    public String title;
    public int year;

    @Override
    public int compareTo(Object o) {
        Movie m = (Movie)o;
        return (score < m.score) ? -1 : ((score == m.score) ? 0 : 1);    }
}
