import java.util.*;

public class Database {
    private HashSet<Film> films;
    private HashSet<Actor> actors;
    private HashMap<Film, Double> filmRatings; // Added field for film ratings

    public Database() {
        films = new HashSet<Film>();
        actors = new HashSet<Actor>();
        filmRatings = new HashMap<>(); // Initialize filmRatings
    }

    public HashSet<Film> getFilms() {
        return films;
    }

    public HashSet<Actor> getActors() {
        return actors;
    }

    public void addRating(Film film, double rating) {
        if (films.contains(film)) {
            filmRatings.put(film, rating);
        } else {
            System.out.println("Film not found in the database.");
        }
    }

    public Film getFilmWithHighestRating() {
        Film topRatedFilm = null;
        double highestRating = -1;
        for (Map.Entry<Film, Double> entry : filmRatings.entrySet()) {
            if (entry.getValue() > highestRating) {
                highestRating = entry.getValue();
                topRatedFilm = entry.getKey();
            }
        }
        return topRatedFilm;
    }

    public TreeMap<Film, Double> getFilmsSortedByRating() {
        TreeMap<Film, Double> sortedFilms = new TreeMap<>(
                (f1, f2) -> Double.compare(filmRatings.get(f2), filmRatings.get(f1)) // Comparator for descending order
        );
        sortedFilms.putAll(filmRatings);
        return sortedFilms;
    }

    public void addFilm(Film film) {
        if (!films.contains(film)) {
            films.add(film);
        }
    }

    public void addActor(Actor actor) {
        if (!actors.contains(actor)) {
            actors.add(actor);
        }
    }

    // Task 1:
    public HashSet<Actor> getActorsWithNoFilms() {
        HashSet<Actor> actorsWithNoFilms = new HashSet<Actor>();
        // Typed iterator
        Iterator<Actor> iterator = actors.iterator();
        while (iterator.hasNext()) {
            Actor actor = iterator.next();
            if (actor.getFilms().isEmpty()) {
                actorsWithNoFilms.add(actor);
            }
        }
        return actorsWithNoFilms;
    }

    // Task 2:
    public HashSet<Actor> getCoActors(Actor givenActor) {
        HashSet<Actor> coActors = new HashSet<Actor>();
        // Typed for-each loop
        for (Film film : givenActor.getFilms()) {
            for (Actor actor : film.getActors()) {
                if (!actor.equals(givenActor) && !coActors.contains(actor)) {
                    coActors.add(actor);
                }
            }
        }
        return coActors;
    }

    // Task 3:
    public Film getFilmWithMostActors() {
        // Untyped iterator
        Iterator iterator = films.iterator();
        Film maxFilm = null;
        int maxActors = 0;
        while (iterator.hasNext()) {
            Film film = (Film) iterator.next();
            int numActors = film.getActors().size();
            if (numActors > maxActors) {
                maxActors = numActors;
                maxFilm = film;
            }
        }
        return maxFilm;
    }
}
