package bll.pages;

import bll.actions.OnPageAction;

public abstract class Page {
    public abstract boolean canChangeToPage(Page otherPage);
    public abstract boolean canDoAction(OnPageAction action);
}
