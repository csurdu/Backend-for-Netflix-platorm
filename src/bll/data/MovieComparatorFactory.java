package bll.data;

import fileio.SortIO;

public class MovieComparatorFactory {

    private static boolean getIncreasingAsBooleanValue(String value) {
        if (value.equals("increasing")) {
            return true;
        } else if (value.equals("decreasing")) {
            return false;
        } else {
            return true;
        }
    }
    public static MovieComparator getComparatorForSortFilter(SortIO sortIO) {
        if (sortIO.getDuration() != null) {
            if (sortIO.getRating() != null) {
                return new MovieComparatorByDurationAndRating(getIncreasingAsBooleanValue(sortIO.getDuration()), getIncreasingAsBooleanValue(sortIO.getRating()));
            } else {
                return new MovieComparatorByDuration(getIncreasingAsBooleanValue(sortIO.getDuration()));
            }
        } else {
            if (sortIO.getRating() != null) {
                return new MovieComparatorByRating(getIncreasingAsBooleanValue(sortIO.getRating()));
            } else {
                return null;
            }
        }
    }
}
