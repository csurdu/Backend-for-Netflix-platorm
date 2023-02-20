package bll.data;

import bll.pages.Page;
import bll.pages.UnauthenticatedHomePage;

public class PageBrowser {
    private Page currentPage;

    public PageBrowser() {
        currentPage = new UnauthenticatedHomePage();
    }

    public Page getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Page currentPage) {
        this.currentPage = currentPage;
    }
}
