package fileio;

/**
 * IO-Class for filters input
 */
public class FiltersIO {
    private SortIO sort;
    private ContainsIO contains;

    public FiltersIO() {
    }

    public SortIO getSort() {
        return sort;
    }

    public void setSort(SortIO sort) {
        this.sort = sort;
    }

    public ContainsIO getContains() {
        return contains;
    }

    public void setContains(ContainsIO contains) {
        this.contains = contains;
    }

    @Override
    public String toString() {
        return "FiltersIO{" +
                "sort=" + sort +
                ", contains=" + contains +
                '}';
    }
}
