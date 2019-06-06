package lesson14;

import java.util.Comparator;

public class MovieYearComparator implements Comparator<Movie14> {
    @Override
    public int compare(Movie14 o1, Movie14 o2) {
        return (o1.year < o2.year ? -1 : (o1.year == o2.year ? 0 : 1));
    }
}
