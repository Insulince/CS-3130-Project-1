package project2.sorter.insertion;

import project2.sorter.Sorter;

public abstract class InsertionSorter extends Sorter {
    protected Integer[] sortedData;

    public Integer[] getSortedData() {
        return sortedData;
    }

    public void setSortedData(Integer[] sortedData) {
        this.sortedData = sortedData;
    }

    @Override
    public Integer[] sort(final Integer[] data) {
        this.sortedData = data;

        for (int currentIntegerIndex = 1; currentIntegerIndex < this.sortedData.length; currentIntegerIndex++) {
            Integer currentInteger = this.sortedData[currentIntegerIndex];

            int insertionIndex = compareWithPreviousIntegers(currentIntegerIndex, currentInteger);

            shiftAffectedIntegers(insertionIndex, currentIntegerIndex);

            insertInteger(currentInteger, insertionIndex);
        }

        return this.sortedData;
    }

    public abstract int compareWithPreviousIntegers(final int currentIntegerIndex, final Integer currentInteger);

    public void shiftAffectedIntegers(final int shiftStartIndex, final int shiftEndIndex) {
        for (int i = shiftEndIndex; i > shiftStartIndex; i--) {
            this.sortedData[i] = this.sortedData[i - 1];
        }
    }

    public void insertInteger(final Integer integer, final int integerIndex) {
        this.sortedData[integerIndex] = integer;
    }
}
