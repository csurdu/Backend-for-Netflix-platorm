package bll.pages;

import bll.actions.OnPageAction;

public class LoginPage extends Page {

    @Override
    public boolean canChangeToPage(Page otherPage) {
        return false;
    }

    @Override
    public boolean canDoAction(OnPageAction action) {
        return action.getAction().getFeature().equals(OnPageAction.LOGIN_ACTION);
    }
}
