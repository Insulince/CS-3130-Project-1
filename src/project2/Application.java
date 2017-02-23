package project2;

import java.util.Arrays;

public class Application {
    private static int linearComparisons;
    private static int linearAssignments;
    private static long linearDuration;
    private static int binaryComparisons;
    private static int binaryAssignments;
    private static long binaryDuration;
    
    public static void main(String[] args) throws RuntimeException {
        System.out.print("Generating data for Array #1...");
        int[] data1 = generateData();
        System.out.println("  done.");
        System.out.print("Creating copy of initial set of data for Array #2...");
        int[] data2 = Arrays.copyOf(data1, data1.length);
        System.out.println("  done.");
        System.out.print("Checking that Array #1 matches Array #2 before sorting...");
        if (!Arrays.equals(data1, data2)) {
            throw new RuntimeException("Internal Error: Could not duplicate Array #1 for Array #2, thus a valid performance comparison cannot be made! This is unusual behavior!");
        }
        System.out.println("  done.");

        System.out.println("\nContents of Array #1 before the sort:");
        displayData(data1);
        System.out.println("Beginning Linear Insertion Sort on Array #1...");
        data1 = linearInsertionSort(data1);
        System.out.println("Linear Insertion Sort on Array #1 complete.");
        System.out.println("\nContents of Array #1 after the sort:");
        displayData(data1);
        System.out.print("Checking that Array #1 is sorted in ascending order...");
        if (!sorted(data1)) {
            throw new RuntimeException("Linear Insertion Sort Error: Array #1 is not sorted!");
        }
        System.out.println("  done.");

        System.out.println("\nArray #1 sorted using:");
        System.out.println("Linear sort comparisons: " + linearComparisons);
        System.out.println("Linear sort assignments: " + linearAssignments);
        System.out.println("Linear sort duration (Milliseconds): " + (linearDuration / 1000000.0));

        System.out.println("\nContents of Array #2 before the sort:");
        displayData(data2);
        System.out.println("Beginning Binary Insertion Sort on Array #2...");
        data2 = binaryInsertionSort(data2);
        System.out.println("Binary Insertion Sort on Array #2 complete.");
        System.out.println("\nContents of Array #2 after the sort.");
        displayData(data2);
        System.out.print("Checking that Array #2 is sorted in ascending order...");
        if (!sorted(data2)) {
            throw new RuntimeException("Binary Insertion Sort Error: Array #2 is not sorted!");
        }
        System.out.println("  done.");

        System.out.println("\nArray #2 sorted using:");
        System.out.println("Binary sort comparisons: " + binaryComparisons);
        System.out.println("Binary sort assignments: " + binaryAssignments);
        System.out.println("Binary sort duration (Milliseconds): " + (binaryDuration / 1000000.0));

        System.out.print("\nChecking that Array #1 matches Array #2 after the sorting...");
        if (!Arrays.equals(data1, data2)) {
            throw new RuntimeException("Sorting Error: The two arrays do not match, one or both of the sorting algorithms failed to keep the data intact!");
        }
        System.out.println(" done.");

        System.out.println("\n---------- Final data recap ----------");
        System.out.println("Array #1 sorted using:");
        System.out.println("Linear sort comparisons: " + linearComparisons);
        System.out.println("Linear sort assignments: " + linearAssignments);
        System.out.println("Linear sort duration (Milliseconds): " + (linearDuration / 1000000.0));
        System.out.println("\nArray #2 sorted using:");
        System.out.println("Binary sort comparisons: " + binaryComparisons);
        System.out.println("Binary sort assignments: " + binaryAssignments);
        System.out.println("Binary sort duration (Milliseconds): " + (binaryDuration / 1000000.0));

        System.out.println("\n---------- Conclusion ----------");
        System.out.println("After running a few times, it appears that there is a slight time improvement using the binary insertion");
        System.out.println("sort versus the linear insertion sort, though there are rare exceptions depending on the data. In terms");
        System.out.println("of comparisons and assignments, the two do exactly the same in assignments every time, but comparisons");
        System.out.println("have been vastly reduced in the binary insertion sort.");
    }

    private static boolean sorted(final int[] data) {
        for (int i = 1; i < data.length; i++) {
            if (data[i - 1] > data[i]) {
                return false;
            }
        }

        return true;
    }

    private static int[] linearInsertionSort(final int[] data) {
        linearComparisons = 0;
        linearAssignments = 0;
        long linearEndTime;
        long linearStartTime = System.nanoTime();

        for (int currentIntegerIndex = 1; currentIntegerIndex < data.length; currentIntegerIndex++) {
            //Select next int.
            int currentInteger = data[currentIntegerIndex];
            linearAssignments++;

            //Compare with previous Integers.
            int candidateInsertionIntegerIndex = currentIntegerIndex - 1;
            while (candidateInsertionIntegerIndex >= 0 && currentInteger < data[candidateInsertionIntegerIndex]) {
                linearComparisons++;
                candidateInsertionIntegerIndex--;
            }
            int insertionIndex = candidateInsertionIntegerIndex + 1;

            //Shift affected Integers.
            for (int currentIndexToBeShiftedInto = currentIntegerIndex; currentIndexToBeShiftedInto > insertionIndex; currentIndexToBeShiftedInto--) {
                data[currentIndexToBeShiftedInto] = data[currentIndexToBeShiftedInto - 1];
                linearAssignments++;
            }

            //Insert current int.
            data[insertionIndex] = currentInteger;
            linearAssignments++;
        }

        linearEndTime = System.nanoTime();
        linearDuration = linearEndTime - linearStartTime;

        return data;
    }

    private static int[] binaryInsertionSort(final int[] data) {
        binaryComparisons = 0;
        binaryAssignments = 0;
        long binaryEndTime;
        long binaryStartTime = System.nanoTime();

        for (int currentIntegerIndex = 1; currentIntegerIndex < data.length; currentIntegerIndex++) {
            //Select next int.
            int currentInteger = data[currentIntegerIndex];
            binaryAssignments++;

            //Compare with previous Integers.
            int insertionIndex;
            int lower = 0;
            int upper = currentIntegerIndex;

            while (true) {
                int range = upper - lower;
                int focusIndex = range / 2 + lower;

                if (currentInteger < data[focusIndex]) {
                    binaryComparisons++;

                    if (range / 2 > 0) {
                        upper = focusIndex;
                    } else {
                        insertionIndex = focusIndex;
                        break;
                    }
                } else {
                    binaryComparisons++;

                    if (range / 2 > 0) {
                        lower = focusIndex;
                    } else {
                        insertionIndex = focusIndex + 1;
                        break;
                    }
                }
            }

            //Shift affected Integers.
            for (int currentIndexToBeShiftedInto = currentIntegerIndex; currentIndexToBeShiftedInto > insertionIndex; currentIndexToBeShiftedInto--) {
                data[currentIndexToBeShiftedInto] = data[currentIndexToBeShiftedInto - 1];
                binaryAssignments++;
            }

            //Insert current int.
            data[insertionIndex] = currentInteger;
            binaryAssignments++;
        }

        binaryEndTime = System.nanoTime();
        binaryDuration = binaryEndTime - binaryStartTime;

        return data;
    }

    private static int[] generateData() {
        int[] ret = new int[1000];

        for (int i = 0; i < 1000; i++) {
            ret[i] = (int) (1 + Math.random() * 1000);
//            ret[i] = i + 1;
//            ret[i] = 1000 - i;
        }

        return ret;
    }

    private static void displayData(final int[] data) {
        System.out.println(formatData(data));
    }

    private static String formatData(final int[] data) {
        int rowCounter = 0;
        StringBuilder output;

        output = new StringBuilder("================================================= ARRAY =================================================\n");
        output.append("INDEX {00} {01} {02} {03} {04} {05} {06} {07} {08} {09} {10} {11} {12} {13} {14} {15} {16} {17} {18} {19}\n");
        for (int rowNumber = 0; rowNumber < 50; rowNumber++) {
            StringBuilder rowCounterAsString = new StringBuilder(String.valueOf(rowCounter));
            int lengthOfRowCounterString = rowCounterAsString.length();

            for (int paddingCharacterIndex = 0; paddingCharacterIndex < "###".length() - lengthOfRowCounterString; paddingCharacterIndex++) {
                rowCounterAsString.insert(0, "0");
            }

            output.append("{").append(rowCounterAsString).append("} ");

            rowCounter += 20;

            for (int columnNumber = 0; columnNumber < 20; columnNumber++) {
                StringBuilder cellValueAsString = new StringBuilder(String.valueOf(data[20 * rowNumber + columnNumber]));
                int lengthOfCellValueString = cellValueAsString.length();

                for (int paddingCharacterIndex = 0; paddingCharacterIndex < "####".length() - lengthOfCellValueString; paddingCharacterIndex++) {
                    cellValueAsString.insert(0, " ");
                }

                output.append(cellValueAsString).append(" ");
            }

            output.append("\n");
        }

        output.append("=========================================================================================================");

        return output.toString();
    }
}
