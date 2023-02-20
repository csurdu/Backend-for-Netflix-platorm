package bll.actions;

import bll.pages.*;

public class PageFactory {
    private static final String LOGIN_PAGE = "login";
    private static final String LOGOUT_PAGE = "logout";
    private static final String REGISTER_PAGE = "register";
    private static final String MOVIES_PAGE = "movies";
    private static final String UPGRADES_PAGE = "upgrades";
    private static final String SEE_DETAILS_PAGE = "see details";
    public static final String AUTH_HOMEPAGE = "AUTH_HOMEPAGE";
    public static final String UNAUTH_HOMEPAGE = "UNAUTH_HOMEPAGE";

    public static Page buildPageFromString(String page) throws Exception {
        if (page.equals(LOGIN_PAGE)) {
            return new LoginPage();
        } else if (page.equals(LOGOUT_PAGE)) {
            return new LogoutPage();
        } else if (page.equals(REGISTER_PAGE)) {
            return new RegisterPage();
        } else if (page.equals(MOVIES_PAGE)) {
            return new MoviesPage();
        } else if (page.equals(UPGRADES_PAGE)) {
            return new UpgradesPage();
        } else if (page.equals(SEE_DETAILS_PAGE)) {
            return new SeeDetailsPage();
        } else if (page.equals(AUTH_HOMEPAGE)) {
            return new AuthenticatedHomePage();
        } else if (page.equals(UNAUTH_HOMEPAGE)) {
            return new UnauthenticatedHomePage();
        } else {
            throw new InvalidPageException("Invalid page");
        }
    }

}
