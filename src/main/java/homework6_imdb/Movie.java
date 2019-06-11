package homework6_imdb;

import java.util.List;

public class Movie {
    public double score;
    public double metascore;
    public String director;
    public List<String> actors;
    public List<String> genres;
    public String title;
    public int year;
    public int duration;

    Movie(double score, String title, int year) {
        this.score = score;
        this.title = title;
        this.year = year;
    }

    public Movie(double score, String title, int year, double metascore, String director, List<String> actors, List<String> genres, int duration) {
        this.score = score;
        this.title = title;
        this.year = year;
        this.metascore = metascore;
        this.director = director;
        this.actors = actors;
        this.genres = genres;
        this.duration = duration;
    }
}
