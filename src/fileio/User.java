package fileio;

/**
 * IO Class for an user
 */
public class User {
    private UserCredentials credentials;

    /**
     * Empty constructor
     */
    public User() {
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
        return "User{" +
                "credentials=" + credentials +
                '}';
    }
}
