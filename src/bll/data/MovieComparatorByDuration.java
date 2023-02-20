package bll.data;

import java.util.Comparator;

public class MovieComparatorByDuration extends MovieComparator implements Comparator<PlatformMovie> {

    public MovieComparatorByDuration(boolean increasing) {
        super(increasing);
    }

    @Override
    public int compare(PlatformMovie o1, PlatformMovie o2) {
        int diff =  o1.getDuration() - o2.getDuration();
        return isIncreasing() ? diff : -diff;
    }
}
