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
            //tmpString = document.select(".plot_summary_wrapper div").select("href").text();
            System.out.println("Director: " + document.select(".plot_summary_wrapper a"));

            movies.add(new Movie(rating, title, year));
            counter++;
            if (counter > NUMBER_OF_MOVIES_TO_PROCESS)
                break;
        }
        System.out.println("Total movies parsed: " + movies.size());
        for (Movie movie : movies)
            System.out.println(movie.getTitle());
    }
}
