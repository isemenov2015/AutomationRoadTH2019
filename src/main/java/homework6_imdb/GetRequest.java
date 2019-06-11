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
import java.util.ArrayList;
import java.util.List;

public class GetRequest {
    private static final int NUMBER_OF_MOVIES_TO_PROCESS = 250;

    @Test
    public void imdbRequest() throws IOException {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url("https://www.imdb.com/chart/top").build();
        Response response = client.newCall(request).execute();
        String html = response.body().string();
        List<Movie> movies = new ArrayList<Movie>();
        Document document = Jsoup.parse(html);
        Elements elements = document.select(".lister-list tr");
        int counter = 0;
        for (Element element : elements) {
            String title = element.select(".titleColumn a").text();
            double rating = Double.parseDouble(element.select(".imdbRating strong").text());
            int year = Integer.parseInt(element.select(".secondaryInfo")
                    .text()
                    .replaceAll("\\(", "")
                    .replaceAll("\\)", ""));

            movies.add(new Movie(rating, title, year));
            counter++;
            if (counter > NUMBER_OF_MOVIES_TO_PROCESS)
                break;
        }
        System.out.println("Total movies parsed: " + movies.size());
        for (Movie movie : movies)
            System.out.println(movie.title);
    }
}
