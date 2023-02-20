package bll.actions;

import bll.pages.Page;

public class ChangePageAction extends GenericAction {
    private Page targetPage;

    public ChangePageAction(Page targetPage) {
        this.targetPage = targetPage;
    }

    public Page getTargetPage() {
        return targetPage;
    }
}
