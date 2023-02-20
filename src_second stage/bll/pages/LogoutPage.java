package bll.pages;

import bll.actions.OnPageAction;

public class LogoutPage extends Page {
    @Override
    public boolean canChangeToPage(Page otherPage) {
        return false;
    }

    @Override
    public boolean canDoAction(OnPageAction action) {
        return false;
    }
}
