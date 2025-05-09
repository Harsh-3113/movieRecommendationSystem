import java.util.*;

public class User {
    private int id;
    private String name;
    private Map<Integer, Double> ratedMovies; // MovieID -> Rating

    public User(int id, String name) {
        this.id = id;
        this.name = name;
        this.ratedMovies = new HashMap<>();
    }

    public int getId() { return id; }
    public String getName() { return name; }

    public void rateMovie(int movieId, double rating) {
        ratedMovies.put(movieId, rating);
    }

    public Map<Integer, Double> getRatedMovies() {
        return ratedMovies;
    }

    @Override
    public String toString() {
        return id + " - " + name;
    }
}
