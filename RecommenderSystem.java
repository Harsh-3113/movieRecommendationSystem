import java.util.*;
import java.util.stream.Collectors;

public class RecommenderSystem {
    private Map<Integer, Movie> movies;
    private Map<Integer, User> users;

    public RecommenderSystem() {
        movies = new HashMap<>();
        users = new HashMap<>();
    }

    public void addMovie(Movie movie) {
        movies.put(movie.getId(), movie);
    }

    public void addUser(User user) {
        users.put(user.getId(), user);
    }

    public void rateMovie(int userId, int movieId, double rating) {
        if (!movies.containsKey(movieId) || !users.containsKey(userId)) return;
        movies.get(movieId).addRating(rating);
        users.get(userId).rateMovie(movieId, rating);
    }

    public List<Movie> searchByGenre(String genre) {
        return movies.values().stream()
                .filter(m -> m.getGenre().equalsIgnoreCase(genre))
                .collect(Collectors.toList());
    }

    public List<Movie> topRatedMovies(int topN) {
        return movies.values().stream()
                .sorted((a, b) -> Double.compare(b.getAverageRating(), a.getAverageRating()))
                .limit(topN)
                .collect(Collectors.toList());
    }

    public List<Movie> recommendMovies(int userId) {
        if (!users.containsKey(userId)) return Collections.emptyList();

        User currentUser = users.get(userId);
        Set<Integer> seenMovies = currentUser.getRatedMovies().keySet();

        // Find similar users
        List<User> similarUsers = users.values().stream()
            .filter(u -> u.getId() != userId)
            .sorted((u1, u2) -> Double.compare(
                similarity(currentUser, u2),
                similarity(currentUser, u1)))
            .limit(3)
            .collect(Collectors.toList());

        // Recommend movies rated by similar users that current user hasn't seen
        Set<Movie> recommended = new HashSet<>();
        for (User simUser : similarUsers) {
            for (Map.Entry<Integer, Double> entry : simUser.getRatedMovies().entrySet()) {
                if (!seenMovies.contains(entry.getKey()) && entry.getValue() >= 3.5) {
                    recommended.add(movies.get(entry.getKey()));
                }
            }
        }

        return new ArrayList<>(recommended);
    }

    private double similarity(User u1, User u2) {
        Map<Integer, Double> r1 = u1.getRatedMovies();
        Map<Integer, Double> r2 = u2.getRatedMovies();

        Set<Integer> common = new HashSet<>(r1.keySet());
        common.retainAll(r2.keySet());

        if (common.isEmpty()) return 0;

        double sum = 0;
        for (int movieId : common) {
            sum += (r1.get(movieId) - r2.get(movieId)) * (r1.get(movieId) - r2.get(movieId));
        }
        return 1.0 / (1.0 + Math.sqrt(sum)); // Lower distance = higher similarity
    }

    public void showAllMovies() {
        movies.values().forEach(System.out::println);
    }

    public void showAllUsers() {
        users.values().forEach(System.out::println);
    }
}
