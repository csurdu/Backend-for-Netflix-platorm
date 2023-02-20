package bll.data;

import java.util.Comparator;

public class MovieComparatorByRating extends MovieComparator implements Comparator<PlatformMovie> {

    public MovieComparatorByRating() {
        super();
    }

    public MovieComparatorByRating(boolean increasing) {
        super(increasing);
    }

    @Override
    public int compare(PlatformMovie o1, PlatformMovie o2) {
        if (o1.getRating() < o2.getRating()) {
            return isIncreasing() ? -1 : 1;
        } else {
            if (o1.getRating() == o2.getRating()) {
                return 0;
            } else {
                return isIncreasing() ? 1 : -1;
            }
        }
    }
}
