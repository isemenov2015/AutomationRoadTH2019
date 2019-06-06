package lesson14;

import java.util.Comparator;

public class MovieTitlesComparator implements Comparator<Movie14> {
    @Override
    public int compare(Movie14 o1, Movie14 o2) {
        return o1.title.compareTo(o2.title);
    }
}
