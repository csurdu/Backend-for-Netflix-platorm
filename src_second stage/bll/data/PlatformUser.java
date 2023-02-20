package bll.data;

import fileio.User;
import fileio.UserCredentials;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.function.Predicate;

public class PlatformUser {
    private UserCredentials credentials;
    private int tokensCount;
    private int numFreePremiumMovies;
    private List<PlatformMovie> purchasedMovies;
    private List<PlatformMovie> watchedMovies;
    private List<PlatformMovie> likedMovies;
    private List<PlatformMovie> ratedMovies;
    private HashMap<PlatformMovie, Integer> moviesRating;
    private List<PlatformNotification> notifications;
    private List<String> subscribedGenres;

    public PlatformUser() {
    }

    public PlatformUser(User user) {
        this.credentials = user.getCredentials();
        this.tokensCount = 0;
        this.numFreePremiumMovies = 15;
        this.purchasedMovies = new ArrayList<>();
        this.watchedMovies = new ArrayList<>();
        this.likedMovies = new ArrayList<>();
        this.ratedMovies = new ArrayList<>();
        this.notifications = new ArrayList<>();
        this.subscribedGenres = new ArrayList<>();
        this.moviesRating = new HashMap<>();
    }

    public UserCredentials getCredentials() {
        return credentials;
    }

    public void setCredentials(UserCredentials credentials) {
        this.credentials = credentials;
    }

    public int getTokensCount() {
        return tokensCount;
    }

    public void setTokensCount(int tokensCount) {
        this.tokensCount = tokensCount;
    }

    public int getNumFreePremiumMovies() {
        return numFreePremiumMovies;
    }

    public void setNumFreePremiumMovies(int numFreePremiumMovies) {
        this.numFreePremiumMovies = numFreePremiumMovies;
    }

    public List<PlatformMovie> getPurchasedMovies() {
        return purchasedMovies;
    }

    public void setPurchasedMovies(List<PlatformMovie> purchasedMovies) {
        this.purchasedMovies = purchasedMovies;
    }

    public List<PlatformMovie> getWatchedMovies() {
        return watchedMovies;
    }

    public void setWatchedMovies(List<PlatformMovie> watchedMovies) {
        this.watchedMovies = watchedMovies;
    }

    public List<PlatformMovie> getLikedMovies() {
        return likedMovies;
    }

    public void setLikedMovies(List<PlatformMovie> likedMovies) {
        this.likedMovies = likedMovies;
    }

    public List<PlatformMovie> getRatedMovies() {
        return ratedMovies;
    }

    public void setRatedMovies(List<PlatformMovie> ratedMovies) {
        this.ratedMovies = ratedMovies;
    }

    public List<PlatformNotification> getNotifications() {
        return notifications;
    }

    public void setNotifications(List<PlatformNotification> notifications) {
        this.notifications = notifications;
    }

    public void addSubscribedGenre(String subscribedGenre) {
        if (!subscribedGenres.contains(subscribedGenre)) {
            subscribedGenres.add(subscribedGenre);
        }
    }

    public boolean isSubscribedToGenre(String genre) {
        return subscribedGenres.contains(genre);
    }

    public void addNotification(String movieName, String message) {
        notifications.add(new PlatformNotification(movieName, message));
    }

    @Override
    public String toString() {
        return "PlatformUser{" +
                "credentials=" + credentials +
                ", tokensCount=" + tokensCount +
                ", numFreePremiumMovies=" + numFreePremiumMovies +
                ", purchasedMovies=" + purchasedMovies +
                ", watchedMovies=" + watchedMovies +
                ", likedMovies=" + likedMovies +
                ", ratedMovies=" + ratedMovies +
                ", notifications=" + notifications +
                '}';
    }

    public void updateAfterDeleteMovie(PlatformMovie deletedMovie) {
        if (purchasedMovies.contains(deletedMovie)) {
            purchasedMovies.remove(deletedMovie);
            if (credentials.getAccountType().equals("premium")) {
                numFreePremiumMovies++;
            } else if (credentials.getAccountType().equals("standard")) {
                tokensCount += 2;
            }
        }
        watchedMovies.removeIf(new Predicate<PlatformMovie>() {
            @Override
            public boolean test(PlatformMovie movie) {
                return movie.getName().equals(deletedMovie.getName());
            }
        });
        //watchedMovies.remove(deletedMovie);
        likedMovies.remove(deletedMovie);
        ratedMovies.remove(deletedMovie);
    }

    public int hasUserRatedMovie(PlatformMovie movie) {
        if (moviesRating.containsKey(movie)) {
            return moviesRating.get(movie);
        }
        return -1;
    }

    public void setRatingForMovie(PlatformMovie movie, int rating) {
        moviesRating.put(movie, rating);
    }

    public void subscribesForGenre(String subscribedGenre) {
        subscribedGenres.add(subscribedGenre);
    }
}
