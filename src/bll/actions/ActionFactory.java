package bll.actions;

import bll.pages.SeeDetailsPage;
import fileio.Action;

public class ActionFactory {
    private static final String CHANGE_PAGE_TYPE = "change page";
    private static final String ON_PAGE_TYPE = "on page";
    public static GenericAction buildActionFromInput(Action action) throws Exception {
        if (action.getType().equals(CHANGE_PAGE_TYPE)) {
            ChangePageAction pageChangeAction = new ChangePageAction(PageFactory.buildPageFromString(action.getPage()));
            if (pageChangeAction.getTargetPage() instanceof SeeDetailsPage) {
                ((SeeDetailsPage) pageChangeAction.getTargetPage()).setMovie(action.getMovie());
            }
            return pageChangeAction;
        } else if (action.getType().equals(ON_PAGE_TYPE)) {
            return new OnPageAction(action);
        } else {
            throw new InvalidActionException("Invalid action type");
        }
    }
}
