package bll.data;

public class PlatformNotification {
    private String movieName;
    private String message;

    public PlatformNotification() {
    }

    public PlatformNotification(String movieName, String message) {
        this.movieName = movieName;
        this.message = message;
    }

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "PlatformNotification{" +
                "movieName='" + movieName + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}
