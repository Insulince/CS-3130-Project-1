package project2.sorter;

public abstract class Sorter implements Sort {
    protected int comparisons;
    protected int assignments;

    public int getComparisons() {
        return comparisons;
    }

    public void setComparisons(int comparisons) {
        this.comparisons = comparisons;
    }

    public int getAssignments() {
        return assignments;
    }

    public void setAssignments(int assignments) {
        this.assignments = assignments;
    }
}
