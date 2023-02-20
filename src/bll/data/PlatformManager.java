package bll.data;

import bll.actions.ChangePageAction;
import bll.actions.GenericAction;
import bll.actions.OnPageAction;
import bll.pages.*;
import fileio.Output;
import fileio.User;

import java.util.ArrayList;
import java.util.List;

public class PlatformManager {
    private StreamingPlatform platform;
    private PageBrowser pageBrowser;
    private PlatformUser currentUser;

    public PlatformManager(StreamingPlatform platform, PageBrowser pageBrowser) {
        this.platform = platform;
        this.pageBrowser = pageBrowser;
        this.currentUser = null;
    }

    public StreamingPlatform getPlatform() {
        return platform;
    }

    public PageBrowser getPageBrowser() {
        return pageBrowser;
    }

    private Output getErrorOutput() {
        Output result = new Output();
        result.setError("Error");
        result.setCurrentUser(null);
        return result;
    }
    public Output doAction(GenericAction action) {
        if (action instanceof ChangePageAction) {
            Page currentPage = pageBrowser.getCurrentPage();
            Page targetPage = ((ChangePageAction) action).getTargetPage();
            if (pageBrowser.getCurrentPage().canChangeToPage(targetPage)) {
                pageBrowser.setCurrentPage(targetPage);
                if (targetPage instanceof LogoutPage) {
                    currentUser = null;
                    pageBrowser.setCurrentPage(new UnauthenticatedHomePage());
                } else if (targetPage instanceof MoviesPage) {
                    Output result = new Output();
                    result.setError(null);
                    result.setCurrentUser(currentUser);
                    result.setCurrentMoviesList(platform.getAvailableMoviesForUser(currentUser));
                    return result;
                } else if (targetPage instanceof SeeDetailsPage) {
                    PlatformMovie movie = platform.getMovieByName(((SeeDetailsPage) targetPage).getMovie());
                    if (movie == null || platform.isMovieBannedForUser(currentUser, movie) || (currentPage instanceof FilteredMoviePage && !((FilteredMoviePage) currentPage).containsMovie(movie))) {
                        pageBrowser.setCurrentPage(new MoviesPage());
                        return getErrorOutput();
                    }
                    Output result = new Output();
                    result.setError(null);
                    result.setCurrentUser(currentUser);
                    List<PlatformMovie> movieList = new ArrayList<>();
                    movieList.add(movie);
                    result.setCurrentMoviesList(movieList);
                    return result;
                }
                return null;
            } else {
                return getErrorOutput();
            }
        } else if (action instanceof OnPageAction) {
            if (!pageBrowser.getCurrentPage().canDoAction((OnPageAction) action)) {
                return getErrorOutput();
            }
            String feature = ((OnPageAction) action).getAction().getFeature();
            if (feature.equals(OnPageAction.LOGIN_ACTION)) {
                String name = ((OnPageAction) action).getAction().getCredentials().getName();
                String password = ((OnPageAction) action).getAction().getCredentials().getPassword();
                PlatformUser user = platform.getUserByName(name);
                if (user == null || !user.getCredentials().getPassword().equals(password)) {
                    pageBrowser.setCurrentPage(new UnauthenticatedHomePage());
                    return getErrorOutput();
                }
                Output result = new Output();
                result.setError(null);
                result.setCurrentUser(user);
                pageBrowser.setCurrentPage(new AuthenticatedHomePage());
                currentUser = user;
                return result;
            } else if (feature.equals(OnPageAction.REGISTER_ACTION)) {
                String name = ((OnPageAction) action).getAction().getCredentials().getName();
                String password = ((OnPageAction) action).getAction().getCredentials().getPassword();
                PlatformUser existingUser = platform.getUserByName(name);
                if (existingUser != null) {
                    pageBrowser.setCurrentPage(new UnauthenticatedHomePage());
                    return getErrorOutput();
                }
                User newUser = new User();
                newUser.setCredentials(((OnPageAction) action).getAction().getCredentials());
                platform.addUser(newUser);
                existingUser = platform.getUserByName(name);
                Output result = new Output();
                result.setError(null);
                result.setCurrentUser(existingUser);
                pageBrowser.setCurrentPage(new AuthenticatedHomePage());
                currentUser = existingUser;
                return result;
            } else if (feature.equals(OnPageAction.SEARCH_ACTION)) {
                Output result = new Output();
                result.setError(null);
                result.setCurrentUser(currentUser);
                result.setCurrentMoviesList(platform.getAvailableMoviesThatStartWith(currentUser, ((OnPageAction) action).getAction().getStartsWith()));
                return result;
            } else if (feature.equals(OnPageAction.FILTER_ACTION)) {
                Output result = new Output();
                result.setError(null);
                result.setCurrentUser(currentUser);
                List<PlatformMovie> filteredMovies = platform.getFilteredMovies(currentUser, ((OnPageAction) action).getAction().getFilters());
                result.setCurrentMoviesList(filteredMovies);
                pageBrowser.setCurrentPage(new FilteredMoviePage(filteredMovies));
                return result;
            } else if (feature.equals(OnPageAction.BUY_TOKENS_ACTION)) {
                boolean success = platform.userBuysTokens(currentUser, ((OnPageAction) action).getAction().getCount());
                if (!success) {
                    return getErrorOutput();
                }
            } else if (feature.equals(OnPageAction.BUY_PREMIUM_ACCOUNT_ACTION)) {
                boolean success = platform.userBuysPremiumAccount(currentUser);
                if (!success) {
                    return getErrorOutput();
                }
            } else if (feature.equals(OnPageAction.PURCHASE_ACTION)) {
                boolean success = platform.userPurchasesMovie(currentUser, ((SeeDetailsPage)pageBrowser.getCurrentPage()).getMovie());
                if (!success) {
                    return getErrorOutput();
                }
                PlatformMovie movie = platform.getMovieByName(((SeeDetailsPage)pageBrowser.getCurrentPage()).getMovie());
                Output result = new Output();
                result.setError(null);
                result.setCurrentUser(currentUser);
                List<PlatformMovie> movieList = new ArrayList<>();
                movieList.add(movie);
                result.setCurrentMoviesList(movieList);
                return result;
            } else if (feature.equals(OnPageAction.WATCH_ACTION)) {
                String moviePageName = ((SeeDetailsPage)pageBrowser.getCurrentPage()).getMovie();
                PlatformMovie movie = platform.getMovieByName(moviePageName);
                boolean success = platform.userWatchesMovie(currentUser, moviePageName);
                if (!success) {
                    return getErrorOutput();
                }
                Output result = new Output();
                result.setError(null);
                result.setCurrentUser(currentUser);
                List<PlatformMovie> movieList = new ArrayList<>();
                movieList.add(movie);
                result.setCurrentMoviesList(movieList);
                return result;
            } else if (feature.equals(OnPageAction.LIKE_ACTION)) {
                String moviePageName = ((SeeDetailsPage)pageBrowser.getCurrentPage()).getMovie();
                PlatformMovie movie = platform.getMovieByName(moviePageName);
                boolean success = platform.userLikesMovie(currentUser, moviePageName);
                if (!success) {
                    return getErrorOutput();
                }
                Output result = new Output();
                result.setError(null);
                result.setCurrentUser(currentUser);
                List<PlatformMovie> movieList = new ArrayList<>();
                movieList.add(movie);
                result.setCurrentMoviesList(movieList);
                return result;
            } else if (feature.equals(OnPageAction.RATE_ACTION)) {
                String moviePageName = ((SeeDetailsPage)pageBrowser.getCurrentPage()).getMovie();
                PlatformMovie movie = platform.getMovieByName(moviePageName);
                int rating = ((OnPageAction) action).getAction().getRate();
                boolean success = platform.userRatesMovie(currentUser, moviePageName, rating);
                if (!success) {
                    return getErrorOutput();
                }
                Output result = new Output();
                result.setError(null);
                result.setCurrentUser(currentUser);
                List<PlatformMovie> movieList = new ArrayList<>();
                movieList.add(movie);
                result.setCurrentMoviesList(movieList);
                return result;

            }
        }
        return null;
    }

}
