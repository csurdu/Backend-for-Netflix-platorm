package fileio;

import java.util.List;

/**
 * IO-Class for a JSON input file
 */
public class Input {
    private List<User> users;
    private List<Movie> movies;
    private List<Action> actions;

    /**
     * Empty constructor
     */
    public Input() {
    }

    /**
     * Getter for users
     * @return List of users
     */
    public List<User> getUsers() {
        return users;
    }

    /**
     * Setter for users
     * @param users List of users
     */
    public void setUsers(List<User> users) {
        this.users = users;
    }

    /**
     * Getter for movies
     * @return List of movies
     */
    public List<Movie> getMovies() {
        return movies;
    }

    /**
     * Setter for movies
     * @param movies List of movies
     */
    public void setMovies(List<Movie> movies) {
        this.movies = movies;
    }

    /**
     * Getter for actions
     * @return List of actions
     */
    public List<Action> getActions() {
        return actions;
    }

    /**
     * Setter for actions
     * @param actions List of actions
     */
    public void setActions(List<Action> actions) {
        this.actions = actions;
    }

    @Override
    public String toString() {
        return "Input{" +
                "users=" + users +
                ", movies=" + movies +
                ", actions=" + actions +
                '}';
    }
}
