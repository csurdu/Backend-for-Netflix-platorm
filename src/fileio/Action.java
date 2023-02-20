package fileio;

/**
 * IO-Class for Action
 */
public class Action {
    private String type;
    private String page;
    private String feature;
    private String count;
    private String movie;
    private String startsWith;
    private FiltersIO filters;
    private int rate;
    private UserCredentials credentials;

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public String getMovie() {
        return movie;
    }

    public void setMovie(String movie) {
        this.movie = movie;
    }

    public String getStartsWith() {
        return startsWith;
    }

    public void setStartsWith(String startsWith) {
        this.startsWith = startsWith;
    }

    public FiltersIO getFilters() {
        return filters;
    }

    public void setFilters(FiltersIO filters) {
        this.filters = filters;
    }

    /**
     * Getter for feature
     * @return
     */
    public String getFeature() {
        return feature;
    }

    /**
     * Setter for feature
     * @param feature
     */
    public void setFeature(String feature) {
        this.feature = feature;
    }

    /**
     * Empty constructor
     */
    public Action() {
    }

    /**
     * Getter for type
     * @return String object
     */
    public String getType() {
        return type;
    }

    /**
     * Setter for type
     * @param type String object
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * Getter for page
     * @return String object
     */
    public String getPage() {
        return page;
    }

    /**
     * Setter for page
     * @param page String object
     */
    public void setPage(String page) {
        this.page = page;
    }

    /**
     * Getter for user credentials
     * @return UserCredentials object
     */
    public UserCredentials getCredentials() {
        return credentials;
    }

    /**
     * Setter for user credentials
     * @param credentials UserCredentials object
     */
    public void setCredentials(UserCredentials credentials) {
        this.credentials = credentials;
    }

    @Override
    public String toString() {
        return "Action{" +
                "type='" + type + '\'' +
                ", page='" + page + '\'' +
                ", feature='" + feature + '\'' +
                ", count='" + count + '\'' +
                ", movie='" + movie + '\'' +
                ", startsWith='" + startsWith + '\'' +
                ", filters=" + filters +
                ", rate=" + rate +
                ", credentials=" + credentials +
                '}';
    }
}
