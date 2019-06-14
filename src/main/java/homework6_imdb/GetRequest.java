package homework6_imdb;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.*;

public class GetRequest {
    private static final int NUMBER_OF_MOVIES_TO_PROCESS = 10;

    @Test
    public void imdbRequest() throws IOException {
        String imdbURL = "https://www.imdb.com/chart/top";
        String movieDomain = "https://www.imdb.com";

        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(imdbURL).build();
        Response response = client.newCall(request).execute();
        String html = response.body().string();
        List<Movie> movies = new ArrayList<Movie>();
        Document document = Jsoup.parse(html);
        Elements elements = document.select(".lister-list tr");
        int counter = 0;
        for (Element element : elements) {
            //title
            String title = element.select(".titleColumn a").text();
            //rating
            double rating = Double.parseDouble(element.select(".imdbRating strong").text());
            //year
            int year = Integer.parseInt(element.select(".secondaryInfo")
                    .text()
                    .replaceAll("\\(", "")
                    .replaceAll("\\)", ""));
            //pull movie page
            String movieURL = movieDomain + element.select(".titleColumn a").attr("href");
            request = new Request.Builder().url(movieURL).build();
            response = client.newCall(request).execute();
            html = response.body().string();
            document = Jsoup.parse(html);
            //metascore
            String tmpString = document.select(".metacriticScore").text();
            double metascore = 0;
            if (tmpString.length() > 0)
                metascore = Double.parseDouble(tmpString);
            //time
            tmpString = document.select(".title_wrapper div").select("time").text();
            if (tmpString.indexOf("h") < 0)
                tmpString = "0h " + tmpString;
            if (tmpString.indexOf("min") < 0)
                tmpString += " 0min";
            String hours = tmpString.split(" ")[0].replaceAll("h", "");
            String minutes = tmpString.split(" ")[1].replaceAll("min", "");
            int duration = Integer.parseInt(hours) * 60 + Integer.parseInt(minutes);
            //director
            String[] directorActorsString = document.select(".plot_summary_wrapper a").toString().split("\n");
            String director = Jsoup.parse(directorActorsString[0]).text();
            List<String> actors = new ArrayList<String>();

            for (String person : directorActorsString) {
                if (person.indexOf("tt_ov_st_sm") > -1 && person.indexOf("full cast") == -1) {
                    actors.add(Jsoup.parse(person).text());
                }
            }
            movies.add(new Movie(rating, title, year, metascore, director, actors, duration));
            counter++;
            System.out.println("Movies processed: " + counter + " of " + NUMBER_OF_MOVIES_TO_PROCESS);
            if (counter >= NUMBER_OF_MOVIES_TO_PROCESS)
                break;
        }
        System.out.println("Total movies parsed: " + movies.size());

        System.out.println("==============================================");
        System.out.println("Movies sorted by release year:");
        Comparator<Movie> yearComparator = new Comparator<Movie>() {
            @Override
            public int compare(Movie o1, Movie o2) {
                return o1.getYear() > o2.getYear() ? 1 : -1;
            }
        };
        printSortedList(movies, yearComparator);

        System.out.println("==============================================");
        System.out.println("Movies sorted by metascore:");
        Comparator<Movie> metascoreComparator = new Comparator<Movie>() {
            @Override
            public int compare(Movie o1, Movie o2) {
                return o1.getMetascore() > o2.getMetascore() ? 1 : -1;
            }
        };
        printSortedList(movies, metascoreComparator);

        System.out.println("==============================================");
        System.out.println("Shortest movie title:");
        Comparator<Movie> durationComparator = new Comparator<Movie>() {
            @Override
            public int compare(Movie o1, Movie o2) {
                return o1.getDuration() > o2.getDuration() ? 1 : -1;
            }
        };
        Collections.sort(movies, durationComparator);
        System.out.println(movies.get(0).getTitle());

        Map<String, Integer> directorsMap = new HashMap<String, Integer>();
        for (Movie movie : movies) {
            if (directorsMap.keySet().contains(movie.getDirector()))
                directorsMap.put(movie.getDirector(), directorsMap.get(movie.getDirector()) + 1);
            else
                directorsMap.put(movie.getDirector(), 1);
        }
        System.out.println("==============================================");
        System.out.println("Film directors with number of films from Top " + NUMBER_OF_MOVIES_TO_PROCESS + ":");
        for (String director : directorsMap.keySet())
            System.out.println(director + ": " + directorsMap.get(director) + " film(s)");

    }

    private static void printSortedList(List<Movie> movies, Comparator<Movie> comparator) {
        Collections.sort(movies, comparator);
        System.out.println("Title, Release year, Metascore, Duration in minutes");
        for (Movie movie : movies)
            System.out.println(movie.getTitle() +
                    ", " + movie.getYear() +
                    ", " + movie.getMetascore() +
                    ", " + movie.getDuration());
    }
}
