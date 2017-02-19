package project2.sorter.insertion;

public class InsertionSorter {
    public Integer[] linearInsertionSort(final Integer[] data) {
        for (int currentIntegerIndex = 1; currentIntegerIndex < data.length; currentIntegerIndex++) {
            //Select next Integer.
            Integer currentInteger = data[currentIntegerIndex];

            //Compare with previous Integers.
            int insertionIndex = 0;
            for (int candidateInsertionIntegerIndex = currentIntegerIndex - 1; candidateInsertionIntegerIndex >= 0; candidateInsertionIntegerIndex--) {
                Integer candidateInsertionInteger = data[candidateInsertionIntegerIndex];
                if (currentInteger > candidateInsertionInteger) {
                    insertionIndex = candidateInsertionIntegerIndex + 1;
                    break;
                }
            }

            //Shift affected Integers.
            for (int indexToBeShiftedInto = currentIntegerIndex; indexToBeShiftedInto > insertionIndex; indexToBeShiftedInto--) {
                int indexToBeShiftedFrom = indexToBeShiftedInto - 1;
                Integer currentlyShiftingInteger = data[indexToBeShiftedFrom];
                data[indexToBeShiftedInto] = currentlyShiftingInteger;
            }

            //Insert current Integer.
            data[insertionIndex] = currentInteger;
        }

        return data;
    }

    public Integer[] binaryInsertionSort(final Integer[] data) {
        for (int currentIntegerIndex = 1; currentIntegerIndex < data.length; currentIntegerIndex++) {
            //Select next Integer.
            Integer currentInteger = data[currentIntegerIndex];

            //Compare with previous Integers.
            int insertionIndex = 0;
            for (int candidateInsertionIntegerIndex = currentIntegerIndex - 1; candidateInsertionIntegerIndex >= 0; candidateInsertionIntegerIndex--) {
                Integer candidateInsertionInteger = data[candidateInsertionIntegerIndex];
                if (currentInteger > candidateInsertionInteger) {
                    insertionIndex = candidateInsertionIntegerIndex + 1;
                    break;
                }
            }

            //Shift affected Integers.
            //implement

            //Insert current Integer.
            data[insertionIndex] = currentInteger;
        }

        return data;
    }
}