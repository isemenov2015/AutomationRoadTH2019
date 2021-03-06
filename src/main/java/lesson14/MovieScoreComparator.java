package lesson14;

import java.util.Comparator;

public class MovieScoreComparator implements Comparator<Movie14> {
    @Override
    public int compare(Movie14 o1, Movie14 o2) {
        return (o1.score < o2.score) ? -1 : ((o1.score == o2.score) ? 0 : 1);
    }
}
