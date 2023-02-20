package fileio;

import bll.data.PlatformMovie;
import bll.data.PlatformUser;

import java.util.ArrayList;
import java.util.List;

public class Output {
    private String error;
    private List<PlatformMovie> currentMoviesList;
    private PlatformUser currentUser;

    public Output() {
        currentMoviesList = new ArrayList<>();
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public List<PlatformMovie> getCurrentMoviesList() {
        return currentMoviesList;
    }

    public void setCurrentMoviesList(List<PlatformMovie> currentMoviesList) {
        this.currentMoviesList = currentMoviesList;
    }

    public PlatformUser getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(PlatformUser currentUser) {
        this.currentUser = currentUser;
    }

    @Override
    public String toString() {
        return "Output{" +
                "error='" + error + '\'' +
                ", currentMoviesList=" + currentMoviesList +
                ", currentUser=" + currentUser +
                '}';
    }
}
