package fileio;

import java.util.List;

/**
 * IO-Class for contains input parameters
 */
public class ContainsIO {
    private List<String> actors;
    private List<String> genre;

    public ContainsIO() {
    }

    public List<String> getActors() {
        return actors;
    }

    public void setActors(List<String> actors) {
        this.actors = actors;
    }

    public List<String> getGenre() {
        return genre;
    }

    public void setGenre(List<String> genre) {
        this.genre = genre;
    }

    @Override
    public String toString() {
        return "ContainsIO{" +
                "actors=" + actors +
                ", genre=" + genre +
                '}';
    }
}
