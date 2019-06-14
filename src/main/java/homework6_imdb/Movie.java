package homework6_imdb;

import java.util.List;

public class Movie {
    private double score;
    private double metascore;
    private String director;
    private List<String> actors;
    private String title;
    private int year;
    private int duration;

    Movie(double score, String title, int year) {
        this.score = score;
        this.title = title;
        this.year = year;
    }

    public Movie(double score, String title, int year, double metascore, String director, List<String> actors, int duration) {
        this.score = score;
        this.title = title;
        this.year = year;
        this.metascore = metascore;
        this.director = director;
        this.actors = actors;
        this.duration = duration;
    }

    public double getScore() {
        return score;
    }

    public double getMetascore() {
        return metascore;
    }

    public String getDirector() {
        return director;
    }

    public List<String> getActors() {
        return actors;
    }

    public String getTitle() {
        return title;
    }

    public int getYear() {
        return year;
    }

    public int getDuration() {
        return duration;
    }
}
