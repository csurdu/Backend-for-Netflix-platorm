package bll.data;

import java.util.Comparator;

public class MovieComparatorByDurationAndRating extends MovieComparator implements Comparator<PlatformMovie> {
    private boolean durationIncreasing;
    private boolean ratingIncreasing;

    public MovieComparatorByDurationAndRating(boolean durationIncreasing, boolean ratingIncreasing) {
        this.durationIncreasing = durationIncreasing;
        this.ratingIncreasing = ratingIncreasing;
    }

    @Override
    public int compare(PlatformMovie o1, PlatformMovie o2) {
        int durationDiff = o1.getDuration() - o2.getDuration();
        if (durationDiff != 0) {
            return durationIncreasing ? durationDiff : -durationDiff;
        } else {
            return new MovieComparatorByRating(ratingIncreasing).compare(o1, o2);
        }
    }
}
