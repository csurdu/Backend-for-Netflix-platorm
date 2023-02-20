package bll.pages;

import bll.actions.OnPageAction;

public class SeeDetailsPage extends Page {
    private String movie;

    public SeeDetailsPage() {
    }

    public String getMovie() {
        return movie;
    }

    public void setMovie(String movie) {
        this.movie = movie;
    }

    @Override
    public boolean canChangeToPage(Page otherPage) {
        return ((otherPage instanceof AuthenticatedHomePage)
                || (otherPage instanceof MoviesPage)
                || (otherPage instanceof UpgradesPage)
                || (otherPage instanceof LogoutPage));
    }

    @Override
    public boolean canDoAction(OnPageAction action) {
        String feature = action.getAction().getFeature();
        return feature.equals(OnPageAction.PURCHASE_ACTION) || feature.equals(OnPageAction.WATCH_ACTION) || feature.equals(OnPageAction.LIKE_ACTION) || feature.equals(OnPageAction.RATE_ACTION)
                || feature.equals(OnPageAction.SUBSCRIBE_ACTION);
    }
}
