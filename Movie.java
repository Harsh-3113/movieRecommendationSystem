import java.util.*;

public class Movie {
    private int id;
    private String title;
    private String genre;
    private List<Double> ratings;

    public Movie(int id, String title, String genre) {
        this.id = id;
        this.title = title;
        this.genre = genre;
        this.ratings = new ArrayList<>();
    }

    public int getId() { return id; }
    public String getTitle() { return title; }
    public String getGenre() { return genre; }

    public void addRating(double rating) {
        ratings.add(rating);
    }

    public double getAverageRating() {
        if (ratings.isEmpty()) return 0;
        return ratings.stream().mapToDouble(r -> r).average().orElse(0.0);
    }

    @Override
    public String toString() {
        return id + " - " + title + " [" + genre + "] Avg Rating: " + getAverageRating();
    }
}
