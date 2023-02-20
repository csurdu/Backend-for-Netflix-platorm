package bll.actions;

import bll.pages.SeeDetailsPage;
import fileio.Action;

public class ActionFactory {
    private static final String CHANGE_PAGE_TYPE = "change page";
    private static final String ON_PAGE_TYPE = "on page";
    private static final String BACK_TYPE = "back";
    private static final String DATABASE_TYPE = "database";
    private static final String FINAL_RECOMMENDATION_TYPE = "FINAL_RECOMMENDATION";

    public static GenericAction buildActionFromInput(Action action) throws Exception {
        if (action.getType().equals(CHANGE_PAGE_TYPE)) {
            ChangePageAction pageChangeAction = new ChangePageAction(PageFactory.buildPageFromString(action.getPage()));
            if (pageChangeAction.getTargetPage() instanceof SeeDetailsPage) {
                ((SeeDetailsPage) pageChangeAction.getTargetPage()).setMovie(action.getMovie());
            }
            return pageChangeAction;
        } else if (action.getType().equals(ON_PAGE_TYPE)) {
            return new OnPageAction(action);
        } else if (action.getType().equals(BACK_TYPE)) {
            return new BackAction();
        } else if (action.getType().equals(DATABASE_TYPE)) {
            return new DatabaseAction(action);
        } else if (action.getType().equals(FINAL_RECOMMENDATION_TYPE)) {
            return new FinalRecommendationAction();
        } else {
            throw new InvalidActionException("Invalid action type");
        }
    }
}
