package bll.pages;

import bll.actions.OnPageAction;

public class UpgradesPage extends Page {
    @Override
    public boolean canChangeToPage(Page otherPage) {
        return ((otherPage instanceof AuthenticatedHomePage)
                || (otherPage instanceof MoviesPage)
                || (otherPage instanceof LogoutPage));
    }

    @Override
    public boolean canDoAction(OnPageAction action) {
        String feature = action.getAction().getFeature();
        return feature.equals(OnPageAction.BUY_TOKENS_ACTION) || feature.equals(OnPageAction.BUY_PREMIUM_ACCOUNT_ACTION);
    }
}
