package bll.pages;

import bll.actions.OnPageAction;

public class MoviesPage extends Page {
    @Override
    public boolean canChangeToPage(Page otherPage) {
        return ((otherPage instanceof AuthenticatedHomePage)
                || (otherPage instanceof SeeDetailsPage)
                || (otherPage instanceof LogoutPage)
                || (otherPage instanceof MoviesPage));
    }

    @Override
    public boolean canDoAction(OnPageAction action) {
        String feature = action.getAction().getFeature();
        return feature.equals(OnPageAction.SEARCH_ACTION) || feature.equals(OnPageAction.FILTER_ACTION);
    }
}
