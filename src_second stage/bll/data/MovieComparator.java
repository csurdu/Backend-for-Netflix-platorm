package bll.data;

import java.util.Comparator;

public abstract class MovieComparator implements Comparator<PlatformMovie> {
    private boolean increasing;

    public MovieComparator() {
        this.increasing = true;
    }

    public MovieComparator(boolean increasing) {
        this.increasing = increasing;
    }

    public boolean isIncreasing() {
        return increasing;
    }

    public void setIncreasing(boolean increasing) {
        this.increasing = increasing;
    }
}
