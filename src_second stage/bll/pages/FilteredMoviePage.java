package bll.pages;

import bll.actions.OnPageAction;
import bll.data.PlatformMovie;

import java.util.List;

public class FilteredMoviePage extends Page {
    private List<PlatformMovie> filteredMovies;

    public FilteredMoviePage(List<PlatformMovie> filteredMovies) {
        this.filteredMovies = filteredMovies;
    }

    public boolean containsMovie(PlatformMovie movie) {
        return filteredMovies.contains(movie);
    }

    @Override
    public boolean canChangeToPage(Page otherPage) {
        return new MoviesPage().canChangeToPage(otherPage);
    }

    @Override
    public boolean canDoAction(OnPageAction action) {
        return false;
    }
}
