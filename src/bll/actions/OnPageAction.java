package bll.actions;

import fileio.Action;

public class OnPageAction extends GenericAction {
    public static final String LOGIN_ACTION = "login";
    public static final String REGISTER_ACTION = "register";
    public static final String SEARCH_ACTION = "search";
    public static final String FILTER_ACTION = "filter";
    public static final String BUY_TOKENS_ACTION = "buy tokens";
    public static final String BUY_PREMIUM_ACCOUNT_ACTION = "buy premium account";
    public static final String PURCHASE_ACTION = "purchase";
    public static final String WATCH_ACTION = "watch";
    public static final String LIKE_ACTION = "like";
    public static final String RATE_ACTION = "rate";

    private Action action;

    public OnPageAction(Action action) {
        this.action = action;
    }

    public Action getAction() {
        return action;
    }

    public void setAction(Action action) {
        this.action = action;
    }

    @Override
    public String toString() {
        return "OnPageAction{" +
                "action=" + action +
                '}';
    }
}
