package bll.data;

import fileio.User;
import fileio.UserCredentials;

import java.util.ArrayList;
import java.util.List;

public class PlatformUser {
    private UserCredentials credentials;
    private int tokensCount;
    private int numFreePremiumMovies;
    private List<PlatformMovie> purchasedMovies;
    private List<PlatformMovie> watchedMovies;
    private List<PlatformMovie> likedMovies;
    private List<PlatformMovie> ratedMovies;

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
                '}';
    }
}
