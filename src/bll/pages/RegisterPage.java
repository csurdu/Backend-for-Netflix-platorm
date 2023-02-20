package bll.pages;

import bll.actions.OnPageAction;

public class RegisterPage extends Page {
    @Override
    public boolean canChangeToPage(Page otherPage) {
        return false;
    }

    @Override
    public boolean canDoAction(OnPageAction action) {
        return action.getAction().getFeature().equals(OnPageAction.REGISTER_ACTION);
    }
}
