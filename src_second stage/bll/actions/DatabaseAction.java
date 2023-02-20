package bll.actions;

import fileio.Action;

public class DatabaseAction extends GenericAction {
    public static final String ADD_FEATURE = "add";
    public static final String DELETE_FEATURE = "delete";

    private Action action;

    public DatabaseAction(Action action) {
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
        return "DatabaseAction{" +
                "action=" + action +
                '}';
    }
}
