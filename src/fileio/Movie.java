package fileio;

import java.util.List;

/**
 * IO-Class for a movie
 */
public class Movie {
    private String name;
    private int year;
    private int duration;
    private List<String> genres;
    private List<String> actors;
    private List<String> countriesBanned;

    /**
     * Empty constructor
     */
    public Movie() {
    }

    /**
     * Getter for name
     * @return String for name
     */
    public String getName() {
        return name;
    }

    /**
     * Setter for name
     * @param name String for name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Getter for year
     * @return int value
     */
    public int getYear() {
        return year;
    }

    /**
     * Setter for year
     * @param year int value
     */
    public void setYear(int year) {
        this.year = year;
    }

    /**
     * Getter for duration
     * @return int value
     */
    public int getDuration() {
        return duration;
    }

    /**
     * Setter for duration
     * @param duration int value
     */
    public void setDuration(int duration) {
        this.duration = duration;
    }

    /**
     * Getter for genres
     * @return List of strings
     */
    public List<String> getGenres() {
        return genres;
    }

    /**
     * Setter for genres
     * @param genres List of strings
     */
    public void setGenres(List<String> genres) {
        this.genres = genres;
    }

    /**
     * Getter for actors
     * @return List of strings
     */
    public List<String> getActors() {
        return actors;
    }

    /**
     * Setter for actors
     * @param actors List of strings
     */
    public void setActors(List<String> actors) {
        this.actors = actors;
    }

    /**
     * Getter for countries banned
     * @return List of strings
     */
    public List<String> getCountriesBanned() {
        return countriesBanned;
    }

    /**
     * Setter for countries banend
     * @param countriesBanned List of strings
     */
    public void setCountriesBanned(List<String> countriesBanned) {
        this.countriesBanned = countriesBanned;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "name='" + name + '\'' +
                ", year=" + year +
                ", duration=" + duration +
                ", genres=" + genres +
                ", actors=" + actors +
                ", countriesBanned=" + countriesBanned +
                '}';
    }
}
