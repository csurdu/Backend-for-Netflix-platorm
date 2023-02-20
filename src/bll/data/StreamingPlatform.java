package bll.data;

import fileio.*;

import java.util.ArrayList;
import java.util.List;

public class StreamingPlatform {
    private List<PlatformUser> users;
    private List<PlatformMovie> movies;

    public StreamingPlatform(List<User> users, List<Movie> movies) {
        this.users = new ArrayList<>();
        for (User user : users) {
            this.users.add(new PlatformUser(user));
        }
        this.movies = new ArrayList<>();
        for (Movie movie : movies) {
            this.movies.add(new PlatformMovie(movie));
        }
    }

    public List<PlatformUser> getUsers() {
        return users;
    }

    public void setUsers(List<PlatformUser> users) {
        this.users = users;
    }

    public List<PlatformMovie> getMovies() {
        return movies;
    }

    public void setMovies(List<PlatformMovie> movies) {
        this.movies = movies;
    }

    public PlatformUser getUserByName(String username) {
        for (PlatformUser user : users) {
            if (user.getCredentials().getName().equals(username)) {
                return user;
            }
        }
        return null;
    }

    public PlatformMovie getMovieByName(String name) {
        for (PlatformMovie movie : movies) {
            if (movie.getName().equals(name)) {
                return movie;
            }
        }
        return null;
    }
    public void addUser(User user) {
        this.users.add(new PlatformUser(user));
    }

    public boolean isMovieBannedForUser(PlatformUser user, PlatformMovie movie) {
        for (String bannedCountry : movie.getCountriesBanned()) {
            if (bannedCountry.equals(user.getCredentials().getCountry())) {
                return true;
            }
        }
        return false;
    }

    public List<PlatformMovie> getAvailableMoviesThatStartWith(PlatformUser user, String prefix) {
        List<PlatformMovie>  filteredMovies = new ArrayList<>();
        for (PlatformMovie movie : getAvailableMoviesForUser(user)) {

            if (movie.getName().startsWith(prefix)) {
                filteredMovies.add(movie);
            }
        }
        return filteredMovies;
    }

    public List<PlatformMovie> getAvailableMoviesForUser(PlatformUser user) {
        List<PlatformMovie>  filteredMovies = new ArrayList<>();
        for (PlatformMovie movie : movies) {
            if (!isMovieBannedForUser(user, movie)) {
                filteredMovies.add(movie);
            }
        }
        return filteredMovies;
    }

    private boolean applyContainsFilter(PlatformMovie movie, ContainsIO containsFilter) {
        if (containsFilter.getActors() != null) {
            for (String actor : containsFilter.getActors()) {
                if (!movie.containsActor(actor)) {
                    return false;
                }
            }
        }
        if (containsFilter.getGenre() != null) {
            for (String genre : containsFilter.getGenre()) {
                if (!movie.containsGenre(genre)) {
                    return false;
                }
            }
        }
        return true;
    }

    public List<PlatformMovie> getFilteredMovies(PlatformUser currentUser, FiltersIO filters) {
        List<PlatformMovie> availableMoviesForUser = getAvailableMoviesForUser(currentUser);
        if (availableMoviesForUser.isEmpty()) {
            return availableMoviesForUser;
        }
        List<PlatformMovie> moviesToBeDeleted = new ArrayList<>();
        if (filters.getContains() != null) {
            for (PlatformMovie movie : availableMoviesForUser) {
                if (!applyContainsFilter(movie, filters.getContains())) {
                    moviesToBeDeleted.add(movie);
                }
            }
        }
        availableMoviesForUser.removeAll(moviesToBeDeleted);
        if (filters.getSort() != null) {
            availableMoviesForUser.sort(MovieComparatorFactory.getComparatorForSortFilter(filters.getSort()));
        }
        return availableMoviesForUser;
    }

    public boolean userBuysTokens(PlatformUser user, String count) {
        int tokens = Integer.parseInt(count);
        int balance = Integer.parseInt(user.getCredentials().getBalance());
        if (balance < tokens) {
            return false;
        }
        user.setTokensCount(user.getTokensCount() + tokens);
        int newBalance = balance - tokens;
        user.getCredentials().setBalance(String.valueOf(newBalance));
        return true;
    }

    public boolean userBuysPremiumAccount(PlatformUser currentUser) {
        int tokens = currentUser.getTokensCount();
        if (tokens < 10) {
            return false;
        }
        tokens -= 10;
        currentUser.setTokensCount(tokens);
        currentUser.getCredentials().setAccountType("premium");
        return true;
    }

    public boolean userPurchasesMovie(PlatformUser currentUser, String movieName) {
        PlatformMovie movie = getMovieByName(movieName);
        if (movie == null) {
            return false;
        }
        if (hasUserPurchasedMovie(currentUser, movie)) {
            return false;
        }
        int tokens = currentUser.getTokensCount();
        if (currentUser.getCredentials().getAccountType().equals("premium")) {
            if (currentUser.getNumFreePremiumMovies() < 1) {
                if (tokens < 2) {
                    return false;
                } else {
                    currentUser.setTokensCount(tokens - 2);
                    currentUser.getPurchasedMovies().add(movie);
                    return true;
                }
            }
            currentUser.setNumFreePremiumMovies(currentUser.getNumFreePremiumMovies() - 1);
            currentUser.getPurchasedMovies().add(movie);
        } else {
            if (tokens < 2) {
                return false;
            } else {
                currentUser.setTokensCount(tokens - 2);
                currentUser.getPurchasedMovies().add(movie);
            }
        }
        return true;
    }

    private boolean hasUserPurchasedMovie(PlatformUser user, PlatformMovie movie) {
        for (PlatformMovie platformMovie : user.getPurchasedMovies()) {
            if (platformMovie == movie) {
                return true;
            }
        }
        return false;
    }

    private boolean hasUserWatchedMovie(PlatformUser user, PlatformMovie movie) {
        for (PlatformMovie platformMovie : user.getWatchedMovies()) {
            if (platformMovie == movie) {
                return true;
            }
        }
        return false;
    }

    public boolean userWatchesMovie(PlatformUser currentUser, String moviePageName) {
        PlatformMovie movie = getMovieByName(moviePageName);
        if (movie == null) {
            return false;
        }
        if (!hasUserPurchasedMovie(currentUser, movie)) {
            return false;
        }
        currentUser.getWatchedMovies().add(movie);
        return true;
    }

    public boolean userLikesMovie(PlatformUser currentUser, String moviePageName) {
        PlatformMovie movie = getMovieByName(moviePageName);
        if (movie == null) {
            return false;
        }
        if (!hasUserWatchedMovie(currentUser, movie)) {
            return false;
        }
        movie.setNumLikes(movie.getNumLikes() + 1);
        currentUser.getLikedMovies().add(movie);
        return true;
    }

    public boolean userRatesMovie(PlatformUser user, String moviePageName, int rating) {
        PlatformMovie movie = getMovieByName(moviePageName);
        if (movie == null) {
            return false;
        }
        if (rating < 1 || rating > 5) {
            return false;
        }
        if (!hasUserWatchedMovie(user, movie)) {
            return false;
        }
        int numRatingsNew = movie.getNumRatings() + 1;
        double newRating = (movie.getRating() * movie.getNumRatings() + rating) / numRatingsNew;
        movie.setNumRatings(numRatingsNew);
        movie.setRating(newRating);
        user.getRatedMovies().add(movie);
        return true;
    }
}
