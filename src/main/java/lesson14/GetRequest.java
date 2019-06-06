package lesson14;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GetRequest {

    @Test
    public void tryIt() throws IOException {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url("https://www.imdb.com/chart/top").build();
        Response response = client.newCall(request).execute();
        String html = response.body().string();

        List<Movie14> movies = new ArrayList<Movie14>();

        Document document = Jsoup.parse(html);
        Elements elements = document.select(".lister-list tr");
        for (Element element : elements) {
            String title = element.select(".titleColumn a").text();
            Double rating = Double.parseDouble(element.select(".imdbRating strong").text());
            int year = Integer.parseInt(element.select(".secondaryInfo")
                                                .text()
                                                .replaceAll("\\(", "")
                                                .replaceAll("\\)", ""));

            movies.add(new Movie14(rating, title, year));
        }

//        Collections.sort(movies, new MovieTitlesComparator());
//        for (Movie14 movie14 : movies) {
//            System.out.println(movie14.title + " " + movie14.score + " " + movie14.year);
//        }

        Collections.sort(movies, new MovieYearComparator());
        for (Movie14 movie14 : movies) {
            System.out.println(movie14.title + " " + movie14.score + " " + movie14.year);
        }
    }
}
