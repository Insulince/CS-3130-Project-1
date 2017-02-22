package project2.sorter.insertion;

public class InsertionSorter {
    public int linearComparisons;
    public int linearAssignments;
    public int binaryComparisons = 0;
    public int binaryAssignments = 0;

    public int[] linearInsertionSort(final int[] data) {
        this.linearComparisons = 0;
        this.linearAssignments = 0;
        for (int currentIntegerIndex = 1; currentIntegerIndex < data.length; currentIntegerIndex++) {
            //Select next int.
            int currentInteger = data[currentIntegerIndex];
            this.linearAssignments++;

            //Compare with previous Integers.
            int candidateInsertionIntegerIndex = currentIntegerIndex - 1;
            while (candidateInsertionIntegerIndex >= 0 && currentInteger < data[candidateInsertionIntegerIndex]) {
                this.linearComparisons++;
                candidateInsertionIntegerIndex--;
            }
            int insertionIndex = candidateInsertionIntegerIndex + 1;

            //Shift affected Integers.
            for (int currentIndexToBeShiftedInto = currentIntegerIndex; currentIndexToBeShiftedInto > insertionIndex; currentIndexToBeShiftedInto--) {
                data[currentIndexToBeShiftedInto] = data[currentIndexToBeShiftedInto - 1];
                this.linearAssignments++;
            }

            //Insert current int.
            data[insertionIndex] = currentInteger;
            this.linearAssignments++;
        }

        return data;
    }

    public int[] binaryInsertionSort(final int[] data) {
        for (int currentIntegerIndex = 1; currentIntegerIndex < data.length; currentIntegerIndex++) {
            //Select next int.
            int currentInteger = data[currentIntegerIndex];
            this.binaryAssignments++;

            //Compare with previous Integers.
            int insertionIndex;
            int lower = 0;
            int upper = currentIntegerIndex;

            while (true) {
                int range = upper - lower;
                int focusIndex = range / 2 + lower;

                if (currentInteger < data[focusIndex]) {
                    this.binaryComparisons++;

                    if (range / 2 > 0) {
                        upper = focusIndex;
                    } else {
                        insertionIndex = focusIndex;
                        break;
                    }
                } else if (currentInteger > data[focusIndex]) {
                    this.binaryComparisons += 2;

                    if (range / 2 > 0) {
                        lower = focusIndex;
                    } else {
                        insertionIndex = focusIndex + 1;
                        break;
                    }
                } else {
                    this.binaryComparisons += 2;
                    insertionIndex = focusIndex + 1;
                    break;
                }
            }

            //Shift affected Integers.
            for (int currentIndexToBeShiftedInto = currentIntegerIndex; currentIndexToBeShiftedInto > insertionIndex; currentIndexToBeShiftedInto--) {
                data[currentIndexToBeShiftedInto] = data[currentIndexToBeShiftedInto - 1];
                this.binaryAssignments++;
            }

            //Insert current int.
            data[insertionIndex] = currentInteger;
            this.binaryAssignments++;
        }

        return data;
    }
}