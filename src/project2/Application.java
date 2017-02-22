package project2;

import project2.sorter.insertion.InsertionSorter;

import java.util.Arrays;

public class Application {
    public static void main(String[] args) throws RuntimeException {
        int[] data1 = generateData();
        int[] data2 = Arrays.copyOf(data1, data1.length);

        if (!Arrays.equals(data1, data2)) {
            throw new RuntimeException("Internal Error: Could not duplicate integer array, thus a valid performance comparison cannot be made!");
        }

        displayData(data1);

        InsertionSorter insertionSorter = new InsertionSorter();

        data1 = insertionSorter.linearInsertionSort(data1);

        displayData(data1);

        displayData(data2);

        insertionSorter.binaryInsertionSort(data2);

        displayData(data2);

        System.out.println(Arrays.toString(data1).equals(Arrays.toString(data2)));

        System.out.println(insertionSorter.linearAssignments);
        System.out.println(insertionSorter.linearComparisons);
        System.out.println("");
        System.out.println(insertionSorter.binaryAssignments);
        System.out.println(insertionSorter.binaryComparisons);
    }

    private static int[] generateData() {
        int[] ret = new int[1000];

        for (int i = 0; i < 1000; i++) {
//            ret[i] = (int) (1 + Math.random() * 1000);
//            ret[i] = i + 1;
            ret[i] = 1000 - i;
        }

        return ret;
    }

    private static void displayData(final int[] data) {
        System.out.println(formatData(data));
    }

    private static String formatData(final int[] data) {
        final int NUM_ROWS = 50;
        final int NUM_COLUIMNS = 20;

        int rowCounter = 0;
        String output;

        output = "================================================= ARRAY =================================================\n";
        output += "INDEX {00} {01} {02} {03} {04} {05} {06} {07} {08} {09} {10} {11} {12} {13} {14} {15} {16} {17} {18} {19}\n";
        for (int rowNumber = 0; rowNumber < NUM_ROWS; rowNumber++) {
            String rowCounterAsString = String.valueOf(rowCounter);
            int lengthOfRowCounterString = rowCounterAsString.length();

            for (int paddingCharacterIndex = 0; paddingCharacterIndex < "###".length() - lengthOfRowCounterString; paddingCharacterIndex++) {
                rowCounterAsString = "0" + rowCounterAsString;
            }

            output += "{" + rowCounterAsString + "} ";

            rowCounter += NUM_COLUIMNS;

            for (int columnNumber = 0; columnNumber < NUM_COLUIMNS; columnNumber++) {
                String cellValueAsString = String.valueOf(data[NUM_COLUIMNS * rowNumber + columnNumber]);
                int lengthOfCellValueString = cellValueAsString.length();

                for (int paddingCharacterIndex = 0; paddingCharacterIndex < "####".length() - lengthOfCellValueString; paddingCharacterIndex++) {
                    cellValueAsString = " " + cellValueAsString;
                }

                output += cellValueAsString + " ";
            }

            output += "\n";
        }

        output += "=========================================================================================================\n";

        return output;
    }
}
