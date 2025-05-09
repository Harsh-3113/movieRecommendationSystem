import java.util.*;

public class Main {
    public static void main(String[] args) {
        RecommenderSystem system = new RecommenderSystem();
        Scanner sc = new Scanner(System.in);

        // Sample Data
        system.addMovie(new Movie(1, "Chup Chup ke", "Comedy"));
        system.addMovie(new Movie(2, "Bhoot Police", "Crime"));
        system.addMovie(new Movie(3, "Toy Story", "Animation"));

        system.addUser(new User(1, "Harsh"));
        system.addUser(new User(2, "Rishabh"));
        system.addUser(new User(3, "Gyan"));

        system.rateMovie(1, 1, 4.5);
        system.rateMovie(1, 2, 5.0);
        system.rateMovie(2, 1, 4.0);
        system.rateMovie(2, 3, 3.0);
        system.rateMovie(3, 2, 4.5);
        system.rateMovie(3, 3, 4.0);

        while (true) {
            System.out.println("\n--- Movie Recommender ---");
            System.out.println("1. Show All Movies");
            System.out.println("2. Show Top Rated Movies");
            System.out.println("3. Search by Genre");
            System.out.println("4. Recommend Movies");
            System.out.println("5. Exit");

            int choice = sc.nextInt();
            // sc.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    system.showAllMovies();
                    break;
                case 2:
                    system.topRatedMovies(3).forEach(System.out::println);
                    break;
                case 3:
                    sc.nextLine(); // <-- consume leftover newline from previous nextInt()
                    System.out.print("Enter genre: ");
                    String genre = sc.nextLine(); // now it waits for user input
                    system.searchByGenre(genre).forEach(System.out::println);
                    break;
                case 4:
                    System.out.print("Enter user ID: ");
                    int uid = sc.nextInt();
                    system.recommendMovies(uid).forEach(System.out::println);
                    break;
                case 5:
                    return;
            }
        }
    }
}

