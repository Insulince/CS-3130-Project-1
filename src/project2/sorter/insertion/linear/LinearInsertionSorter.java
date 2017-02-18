package project2.sorter.insertion.linear;

import project2.sorter.insertion.InsertionSorter;

public class LinearInsertionSorter extends InsertionSorter {
    @Override
    public Integer[] sort(final Integer[] data) {
        return super.sort(data);
    }

    @Override
    public int compareWithPreviousIntegers(final int currentIntegerIndex, final Integer currentInteger) {
        int index = 0;

        for (int i = currentIntegerIndex; i > 0; i--) {
            if (this.sortedData[i] > currentInteger) {
                index = i;
                break;
            }
        }

        return index;
    }
}
