package project2;

import java.util.Arrays;

public class InsertionSort {
    private static int linearComparisons; //Tracks linear insertion sort comparisons.
    private static int linearAssignments; //Tracks linear insertion sort assignments.
    private static long linearDuration; //Tracks linear insertion sort's duration.
    private static int binaryComparisons; //Tracks binary insertion sort comparisons.
    private static int binaryAssignments; //Tracks binary insertion sort assignments.
    private static long binaryDuration; //Tracks binary insertion sort's duration

    //Main Method.
    public static void main(String[] args) throws RuntimeException {
        System.out.print("Generating data for Array #1...");
        int[] array1 = generateArrayData(); //Generate the data for the first int array.
        System.out.println("  done.");
        System.out.print("Creating copy of initial set of data for Array #2...");
        int[] array2 = Arrays.copyOf(array1, array1.length); //Duplicate the data for the second int array.
        System.out.println("  done.");
        System.out.print("Checking that Array #1 matches Array #2 before sorting...");
        if (!Arrays.equals(array1, array2)) { //Ensure that the two int arrays are exactly equal to make sure that the performance comparison is fair.
            throw new RuntimeException("Internal Error: Could not duplicate Array #1 for Array #2, thus a valid performance comparison cannot be made! This is unusual behavior!");
        }
        System.out.println("  done.");

        System.out.println("\nContents of Array #1 before the sort:");
        displayArrayData(array1); //Display the contents of the first int array.
        System.out.println("Beginning Linear Insertion Sort on Array #1...");
        array1 = linearInsertionSort(array1); //Apply the linear insertion sort algorithm to the first int array.
        System.out.println("Linear Insertion Sort on Array #1 complete.");

        System.out.println("\nContents of Array #1 after the sort:");
        displayArrayData(array1); //Display the contents of the now-arrayIsSortedInAscendingOrder first int array.
        System.out.print("Checking that Array #1 is arrayIsSortedInAscendingOrder in ascending order...");
        if (!arrayIsSortedInAscendingOrder(array1)) { //Check that the first int array is, in fact, sorted.
            throw new RuntimeException("Linear Insertion Sort Error: Array #1 is not arrayIsSortedInAscendingOrder!");
        }
        System.out.println("  done.");

        System.out.println("\nArray #1 arrayIsSortedInAscendingOrder using:");
        System.out.println("Linear sort comparisons: " + linearComparisons);
        System.out.println("Linear sort assignments: " + linearAssignments);
        System.out.println("Linear sort duration (Milliseconds): " + (linearDuration / 1000000.0)); //Divide the duration by the double value 1000000.0 to get milliseconds (currently in nanoseconds).

        System.out.println("\nContents of Array #2 before the sort:");
        displayArrayData(array2); //Display the contents of the second int array.
        System.out.println("Beginning Binary Insertion Sort on Array #2...");
        array2 = binaryInsertionSort(array2); //Apply the binary insertion sort algorithm to the second int array.
        System.out.println("Binary Insertion Sort on Array #2 complete.");

        System.out.println("\nContents of Array #2 after the sort.");
        displayArrayData(array2); //Display the contents of the now-arrayIsSortedInAscendingOrder second int array.
        System.out.print("Checking that Array #2 is arrayIsSortedInAscendingOrder in ascending order...");
        if (!arrayIsSortedInAscendingOrder(array2)) { //Check that the second int array is, in fact, sorted.
            throw new RuntimeException("Binary Insertion Sort Error: Array #2 is not arrayIsSortedInAscendingOrder!");
        }
        System.out.println("  done.");

        System.out.println("\nArray #2 arrayIsSortedInAscendingOrder using:");
        System.out.println("Binary sort comparisons: " + binaryComparisons);
        System.out.println("Binary sort assignments: " + binaryAssignments);
        System.out.println("Binary sort duration (Milliseconds): " + (binaryDuration / 1000000.0));

        System.out.print("\nChecking that Array #1 matches Array #2 after the sorting...");
        if (!Arrays.equals(array1, array2)) { //Check that tje two int arrays are exactly the same after the sort to ensure that no indices were lost or overwritten during the sort.
            throw new RuntimeException("Sorting Error: The two arrays do not match, one or both of the sorting algorithms failed to keep the data intact!");
        }
        System.out.println(" done.");

        System.out.println("\n---------- Final data recap ----------");
        System.out.println("Array #1 arrayIsSortedInAscendingOrder using:");
        System.out.println("Linear sort comparisons: " + linearComparisons);
        System.out.println("Linear sort assignments: " + linearAssignments);
        System.out.println("Linear sort duration (Milliseconds): " + (linearDuration / 1000000.0));

        System.out.println("\nArray #2 arrayIsSortedInAscendingOrder using:");
        System.out.println("Binary sort comparisons: " + binaryComparisons);
        System.out.println("Binary sort assignments: " + binaryAssignments);
        System.out.println("Binary sort duration (Milliseconds): " + (binaryDuration / 1000000.0));

        System.out.println("\n---------- Conclusion ----------");
        System.out.println("After running a few times, it appears that there is a slight time improvement using the binary insertion");
        System.out.println("sort versus the linear insertion sort, though there are rare exceptions depending on the data. In terms");
        System.out.println("of comparisons and assignments, the two do exactly the same in assignments every time, but comparisons");
        System.out.println("have been vastly reduced in the binary insertion sort.");
    }

    //This is my implementation of the Linear Insertion Sort algorithm.
    private static int[] linearInsertionSort(final int[] array) {
        linearComparisons = 0; //Initialize linearComparisons to 0 to prepare it for incrementation.
        linearAssignments = 0; //Initialize linearAssignments to 0 to prepare it for incrementation.

        long linearStartTime = System.nanoTime(); //Record the current time to start timing the operation. This is a reasonable starting position because prior to this statement was all variable setup.

        for (int currentIntIndex = 1; currentIntIndex < array.length; currentIntIndex++) { //For every int in the int array starting from the second int (assume first is arrayIsSortedInAscendingOrder)...
            //1. Select next int.
            int currentInt = array[currentIntIndex]; //This step is required because when we preform the shift step, this will be overwritten. This is the O(1) extra memory slot, or the tmp variable in most implementations.
            linearAssignments++; //The previous operation classifies as an assignment since it changes a value directly in the array.

            //2. Compare with previous ints.
            int candidateInsertionIntIndex = currentIntIndex - 1; //Start by retrieving the index directly before the current int's index and store it in a variable referred to as the candidate insertion index.
            while (candidateInsertionIntIndex >= 0 && currentInt < array[candidateInsertionIntIndex]) { //While that index is greater than or equal to the first index (0), and the current int is less than the int in the int array at the candidate insertion index...
                linearComparisons++; //The previous operation classifies as a comparison because it directly checks the value of an int in the array.
                candidateInsertionIntIndex--; //Shift the candidate insertion index backwards one.
            }
            int insertionIndex = candidateInsertionIntIndex + 1; //The index to insert into is 1 plus where we stopped, since we only stop when we find the very first int in the int array which is LESS than the current int, or when the index is less than 0.

            //3. Shift affected Ints.
            for (int currentIndexToBeShiftedInto = currentIntIndex; currentIndexToBeShiftedInto > insertionIndex; currentIndexToBeShiftedInto--) { //For every index in the int array starting at the index that the current int was residing at up until the index which the current int is going to be inserted into...
                array[currentIndexToBeShiftedInto] = array[currentIndexToBeShiftedInto - 1]; //Put the int array's previous index's value in the next index.
                linearAssignments++; //The previous operation classifies as an assignment since it changes a value directly in the array.
            }

            //4. Insert current int.
            array[insertionIndex] = currentInt; //Put the current int in the int array at the designated insertion index.
            linearAssignments++; //The previous operation classifies as an assignment since it changes a value directly in the array.
        }

        linearDuration = System.nanoTime() - linearStartTime; //Record the current time and subtract out the starting time to get the operation duration.

        return array; //Return the now-arrayIsSortedInAscendingOrder int array.
    }

    //This is my implementation of the Binary Insertion Sort algorithm.
    private static int[] binaryInsertionSort(final int[] array) {
        binaryComparisons = 0; //Initialize binaryComparisons to 0 to prepare it for incrementation.
        binaryAssignments = 0; //Initialize binaryAssignments to 0 to prepare it for incrementation.

        long binaryStartTime = System.nanoTime(); //Record the current time to start timing the operation. This is a reasonable starting position because prior to this statement was all variable setup.

        for (int currentIntIndex = 1; currentIntIndex < array.length; currentIntIndex++) { //For every int in the int array starting from the second int (assume first is arrayIsSortedInAscendingOrder)...
            //1. Select next int.
            int currentInt = array[currentIntIndex]; //This step is required because when we preform the shift step, this will be overwritten. This is the O(1) extra memory slot, or the tmp variable in most implementations.
            linearAssignments++; //The previous operation classifies as an assignment since it changes a value directly in the array.

            //2. Compare with previous ints.
            int lowerBoundIndex = 0; //This variable is used to keep track of the lowest index that could possibly be the insertion index. Initialized to 0 because this is the very start of the sort, and all indices must be considered.
            int upperBoundIndex = currentIntIndex; //This variable is used to keep track of the greatest index that could possibly be the insertion index. Initialized to the current int's index because this is the very start of the sort, and all indices must be considered.
            int candidateIndexRange; //This variable will hold the size of the range of indices that could possibly hold the insertion index.
            int currentFocusIndex; //This variable will be the "pivot" point of the binary search, right in the middle of the upper and lower index bounds.

            while (true) { //Loop (technically indefinitely) through the following process until the candidateIndexRange is 1 or less (which it inevitably will).
                candidateIndexRange = upperBoundIndex - lowerBoundIndex; //The candidate index range is the current upper index bound minus the current lower index bound.
                currentFocusIndex = candidateIndexRange / 2 + lowerBoundIndex; //The current focus index is the candidate index range cut in half (the middle of the range), plus the lower index bound.

                if (currentInt < array[currentFocusIndex]) { //If the current int is less than the int in the array at the focus index...
                    binaryComparisons++; //The previous operation classifies as a comparison because it directly checks the value of an int in the array.

                    if (candidateIndexRange > 1) { //If the candidate index range is greater than 1...
                        upperBoundIndex = currentFocusIndex; //The insertion index is somewhere less than the focus index, so the new upper bound is the current focus index. The lower bound is unaffected.
                    } else { //If the candidate index range is not greater than 1...
                        break; //The current focus index is exactly where the current int should go, so break from this loop.
                    }
                } else { //If the current int is not less than the int in the array at the focus index...
                    binaryComparisons++; //The previous operation does not classify as a comparison since it is simply "else"; however, the initial "if" classifies as a comparison because it directly checks the value of an int in the array.

                    if (candidateIndexRange > 1) { //If the candidate index range is greater than 1...
                        lowerBoundIndex = currentFocusIndex; //The insertion index is somewhere greater than the focus index, so the new lower bound is the current focus index. The upper bound is unaffected.
                    } else { //If the candidate index range is not greater than 1...
                        currentFocusIndex++; //The current focus index is the value directly before where the current int should go, so increment it once.
                        break; //Then break from this loop.
                    }
                }
            }
            int insertionIndex = currentFocusIndex; //The insertion index is the current focus index.

            //3. Shift affected Ints.
            for (int currentIndexToBeShiftedInto = currentIntIndex; currentIndexToBeShiftedInto > insertionIndex; currentIndexToBeShiftedInto--) { //For every index in the int array starting at the index that the current int was residing at up until the index which the current int is going to be inserted into...
                array[currentIndexToBeShiftedInto] = array[currentIndexToBeShiftedInto - 1]; //Put the int array's previous index's value in the next index.
                linearAssignments++; //The previous operation classifies as an assignment since it changes a value directly in the array.
            }

            //4. Insert current int.
            array[insertionIndex] = currentInt; //Put the current int in the int array at the designated insertion index.
            linearAssignments++; //The previous operation classifies as an assignment since it changes a value directly in the array.
        }

        binaryDuration = System.nanoTime() - binaryStartTime; //Record the current time and subtract out the starting time to get the operation duration.

        return array; //Return the now-arrayIsSortedInAscendingOrder int array.
    }

    //This function is responsible for randomly generating the data. All hard coded values are per the project specification.
    private static int[] generateArrayData() {
        final int[] array = new int[1000]; //Create a new int array of size 1000.

        for (int index = 0; index < 1000; index++) { //For each index in the int array...
            array[index] = (int) (1 + Math.random() * 1000); //Generate a random number between 1 and 1000 inclusive and store it in the int array at the current index.
        }

        return array; //Return the now randomly populated int array.
    }

    //This function is responsible for outputting the data to the user in a meaningful way. Hardcoded values are per the project specification.
    private static void displayArrayData(final int[] array) {
        StringBuilder output; //This is the object responsible for creating the String that will ultimately be displayed to the user.
        int rowCounter = 0; //This variable is used to display the multiples of 20 along the rows.

        output = new StringBuilder("================================================= ARRAY =================================================\n"); //Attach an upper border.
        output.append("INDEX {00} {01} {02} {03} {04} {05} {06} {07} {08} {09} {10} {11} {12} {13} {14} {15} {16} {17} {18} {19}\n"); //Append a basic index track along the top to keep track of the column number.

        for (int rowNumber = 0; rowNumber < 50; rowNumber++) { //For every row to be displayed (50 rows)...
            StringBuilder rowCounterAsString = new StringBuilder(String.valueOf(rowCounter)); //Create a new StringBuilder around the string value of the rowCounter variable (will be used for padding to keep things square).
            int lengthOfRowCounterString = rowCounterAsString.length(); //Record the initial length of the rowCounterString (because it changes as we pad it, and that messes with the for loop).

            for (int paddingCharacterIndex = 0; paddingCharacterIndex < "###".length() - lengthOfRowCounterString; paddingCharacterIndex++) { //For every character in the rowCounterAsString variable LESS than the length of a String with 3 characters...
                rowCounterAsString.insert(0, "0"); //Pad the front of the String with a 0 (This makes "0" -> "000", "80" -> "080", and "200" -> "200" to keep everything the same length).
            }

            output.append("{").append(rowCounterAsString).append("} "); //Attach the row counter String to the front of the current row.

            rowCounter += 20; //Increment the rowCounter variable by 20 (since we have moved on to the next row).

            for (int columnNumber = 0; columnNumber < 20; columnNumber++) { //For every column to be displayed (20 columns)...
                StringBuilder cellValueAsString = new StringBuilder(String.valueOf(array[20 * rowNumber + columnNumber])); //Create a new StringBuilder around the String value of the current int to be displayed (will be used for padding to keep things square).
                int lengthOfCellValueString = cellValueAsString.length(); //Record the initial length of the cellValueString (because it changes as we pad it, and that messes with the for loop).

                for (int paddingCharacterIndex = 0; paddingCharacterIndex < "####".length() - lengthOfCellValueString; paddingCharacterIndex++) { //For every character in the cellValueAsString variable LES than the length of a String with 4 characters...
                    cellValueAsString.insert(0, " "); //Pad the front of the Sting with a " " (This makes "0" -> "   0", "378" -> " 378", and "1000" -> "1000" to keep everything the same length).
                }

                output.append(cellValueAsString).append(" "); //Attach the cellValue to the current String.
            }

            output.append("\n"); //Add a new line at the end of this row.
        }

        output.append("========================================================================================================="); //Attach a lower border.

        System.out.println(output.toString()); //Output the completed String to the user.
    }

    //This is an extra function I added to help ensure data integrity. It checks that the passed int array is arrayIsSortedInAscendingOrder in ascending order.
    private static boolean arrayIsSortedInAscendingOrder(final int[] array) {
        for (int index = 1; index < array.length; index++) { //For every pair of indices in the int array (sequentially)...
            if (array[index - 1] > array[index]) { //If the first int in this pair is greater than the second int in this pair...
                return false; //Then this array is not arrayIsSortedInAscendingOrder in ascending order. Return false.
            }
        }

        //If we made it here...
        return true; //Then this array is arrayIsSortedInAscendingOrder in ascending order. Return true.
    }
}
