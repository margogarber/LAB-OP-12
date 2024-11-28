import java.util.HashSet;
import java.util.Set;

public class Actor implements Comparable<Actor> {
    private String name;
    private Set<Film> films;

    public Actor(String name) {
        this.name = name;
        this.films = new HashSet<>(); // Simplified instantiation
    }

    public String getName() {
        return name;
    }

    public Set<Film> getFilms() {
        return films;
    }

    public void addFilm(Film film) {
        if (film != null && !films.contains(film)) { // Added null check
            films.add(film);
            film.addActor(this); // Ensure bi-directional reference (if Film has addActor method)
        }
    }

    @Override
    public int compareTo(Actor other) {
        return this.name.compareTo(other.name);
    }

    @Override
    public String toString() {
        return name;
    }
}