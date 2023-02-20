package bll.pages;

import bll.actions.OnPageAction;

public class UnauthenticatedHomePage extends Page {
    @Override
    public boolean canChangeToPage(Page otherPage) {
        return ((otherPage instanceof LoginPage) || (otherPage instanceof RegisterPage));
    }

    @Override
    public boolean canDoAction(OnPageAction action) {
        return false;
    }
}
