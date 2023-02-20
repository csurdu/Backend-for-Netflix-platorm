package bll.data;

import bll.actions.*;
import bll.pages.*;
import fileio.Movie;
import fileio.Output;
import fileio.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class PlatformManager {
    private StreamingPlatform platform;
    private PageBrowser pageBrowser;
    private PlatformUser currentUser;
    private Stack<ChangePageAction> backActionStack;

    public PlatformManager(StreamingPlatform platform, PageBrowser pageBrowser) {
        this.platform = platform;
        this.pageBrowser = pageBrowser;
        this.currentUser = null;
        this.backActionStack = new Stack<>();
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
                    backActionStack.clear();
                    pageBrowser.setCurrentPage(new UnauthenticatedHomePage());
                } else if (targetPage instanceof MoviesPage) {
                    Output result = new Output();
                    result.setError(null);
                    result.setCurrentUser(currentUser);
                    result.setCurrentMoviesList(platform.getAvailableMoviesForUser(currentUser));
                    backActionStack.push((ChangePageAction) action);
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
                    backActionStack.push((ChangePageAction) action);
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
            } else if (feature.equals(OnPageAction.SUBSCRIBE_ACTION)) {
                String moviePageName = ((SeeDetailsPage)pageBrowser.getCurrentPage()).getMovie();
                PlatformMovie movie = platform.getMovieByName(moviePageName);
                if (!platform.userSubscribesForGenre(currentUser, movie, ((OnPageAction) action).getAction().getSubscribedGenre())) {
                    return getErrorOutput();
                }
            }
        } else if (action instanceof BackAction) {
            Page currentPage = pageBrowser.getCurrentPage();
            if (currentUser == null || backActionStack.empty()) {
                return getErrorOutput();
            }
            // pop top cause it is the last changePage action
            backActionStack.pop();
            if (backActionStack.empty()) {
                return null;
            }
            ChangePageAction changePageAction = backActionStack.pop();
            Page targetPage = changePageAction.getTargetPage();
            pageBrowser.setCurrentPage(targetPage);
            if (targetPage instanceof MoviesPage) {
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
        } else if (action instanceof DatabaseAction) {
            String feature = ((DatabaseAction) action).getAction().getFeature();
            if (feature.equals(DatabaseAction.ADD_FEATURE)) {
                Movie movieToBeAdded = ((DatabaseAction) action).getAction().getAddedMovie();
                PlatformMovie movie = platform.getMovieByName(movieToBeAdded.getName());
                if (movie != null) {
                    return getErrorOutput();
                }
                platform.addMovieAndNotifyUsers(movieToBeAdded);
            } else if (feature.equals(DatabaseAction.DELETE_FEATURE)) {
                String movieToBeDeleted = ((DatabaseAction) action).getAction().getDeletedMovie();
                PlatformMovie searchedMovie = platform.getMovieByName(movieToBeDeleted);
                if (searchedMovie == null) {
                    return getErrorOutput();
                }
                platform.deleteMovieAndUpdate(movieToBeDeleted);
            }
        } else if (action instanceof FinalRecommendationAction) {
            if (currentUser == null) {
                return null;
            }
            if (currentUser.getCredentials().getAccountType().equals("premium")) {
                Output result = new Output();
                PlatformMovie recommendedMovie = platform.recommendMovieForUser(currentUser);
                if (recommendedMovie != null) {
                    currentUser.addNotification(recommendedMovie.getName(), "Recommendation");
                } else {
                    currentUser.addNotification("No recommendation", "Recommendation");
                }
                result.setError(null);
                result.setCurrentUser(currentUser);
                result.setCurrentMoviesList(null);

                return result;
            }
        }
        return null;
    }

}
