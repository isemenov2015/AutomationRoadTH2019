package lesson14;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SortExample {
    public static void main(String[] args) {
        List<Movie14> movies = new ArrayList<Movie14>();

        movies.add(new Movie14(4.5, "FF"));
        movies.add(new Movie14(2.5, "JJJ"));
        movies.add(new Movie14(1.5, "LLL"));
        movies.add(new Movie14(0.5, "AAAAA"));

        for (Movie14 movie : movies) {
            System.out.println(movie.title);
        }

        Collections.sort(movies);
        for (Movie14 movie14 : movies) {
            System.out.println(movie14.title);
        }

        Collections.sort(movies, new MovieScoreComparator());
        for (Movie14 movie14 : movies) {
            System.out.println(movie14.title);
        }
        Collections.sort(movies, new MovieTitlesComparator());
        for (Movie14 movie14 : movies) {
            System.out.println(movie14.title);
        }
    }
}
