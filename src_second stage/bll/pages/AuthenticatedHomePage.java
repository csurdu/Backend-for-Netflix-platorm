package bll.pages;

import bll.actions.OnPageAction;

public class AuthenticatedHomePage extends Page {
    @Override
    public boolean canChangeToPage(Page otherPage) {
        return ((otherPage instanceof MoviesPage)
                || (otherPage instanceof UpgradesPage)
                || (otherPage instanceof LogoutPage));
    }

    @Override
    public boolean canDoAction(OnPageAction action) {
        return false;
    }
}
