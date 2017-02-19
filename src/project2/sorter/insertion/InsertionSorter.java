package project2.sorter.insertion;

public class InsertionSorter {
    public int[] linearInsertionSort(final int[] data) {
        for (int currentIntegerIndex = 1; currentIntegerIndex < data.length; currentIntegerIndex++) {
            //Select next int.
            int currentInteger = data[currentIntegerIndex];

            //Compare with previous Integers.
            int insertionIndex = 0;
            for (int candidateInsertionIntegerIndex = currentIntegerIndex - 1; candidateInsertionIntegerIndex >= 0; candidateInsertionIntegerIndex--) {
                int candidateInsertionInteger = data[candidateInsertionIntegerIndex];
                if (currentInteger > candidateInsertionInteger) {
                    insertionIndex = candidateInsertionIntegerIndex + 1;
                    break;
                }
            }

            //Shift affected Integers.
            for (int indexToBeShiftedInto = currentIntegerIndex; indexToBeShiftedInto > insertionIndex; indexToBeShiftedInto--) {
                int indexToBeShiftedFrom = indexToBeShiftedInto - 1;
                int currentlyShiftingInteger = data[indexToBeShiftedFrom];
                data[indexToBeShiftedInto] = currentlyShiftingInteger;
            }

            //Insert current int.
            data[insertionIndex] = currentInteger;
        }

        return data;
    }

    public int[] binaryInsertionSort(final int[] data) {
        for (int currentIntegerIndex = 1; currentIntegerIndex < data.length; currentIntegerIndex++) {
            //Select next int.
            int currentInteger = data[currentIntegerIndex];
            
            //Compare with previous Integers.
            int insertionIndex;
            int lower = 0;
            int upper = currentIntegerIndex;
            int range = upper - lower;
            int focusIndex = range / 2 + lower;

            while (upper > lower) {
                if (currentInteger == data[focusIndex]) {
                    break;
                } else if (currentInteger < data[focusIndex]) {
                    lower = lower;
                    upper = focusIndex - 1;
                    range = upper - lower;
                    focusIndex = range / 2 + lower;
                } else if (currentInteger > data[focusIndex]) {
                    lower = focusIndex + 1;
                    upper = upper;
                    range = upper - lower;
                    focusIndex = range / 2 + lower;
                }
            }

            if (currentInteger >= data[focusIndex]) {
                insertionIndex = focusIndex + 1;
            } else {
                insertionIndex = focusIndex;
            }

            //Shift affected Integers.
            for (int indexToBeShiftedInto = currentIntegerIndex; indexToBeShiftedInto > insertionIndex; indexToBeShiftedInto--) {
                int indexToBeShiftedFrom = indexToBeShiftedInto - 1;
                int currentlyShiftingInteger = data[indexToBeShiftedFrom];
                data[indexToBeShiftedInto] = currentlyShiftingInteger;
            }

            //Insert current int.
            data[insertionIndex] = currentInteger;
        }

        return data;
    }
}