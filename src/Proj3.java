import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Scanner;

public class Proj3 {
    // Sorting Method declarations
    // Merge Sort | FINISHED
    public static <T extends Comparable> void mergeSort(ArrayList<T> a, int left, int right) {
        int difference;
        int mid;

        // determine sub-arrays
        difference = right - left;
        mid = (difference / 2) + left;

        // ensures size doesn't equal 1
        if(difference != 0) {

            // mergeSort sub-arrays
            mergeSort(a, left, mid);
            mergeSort(a, mid + 1, right);
        }

        // merges sub-arrays
        merge(a, left, mid, right);
    }

    public static <T extends Comparable> void merge(ArrayList<T> a, int left, int mid, int right) {
        // create two partitions
        ArrayList<T> leftArray = new ArrayList<>();
        ArrayList<T> rightArray = new ArrayList<>();

        // if no sorting is needed
        if(left == right) return;

        // copy values into leftArray
        for(int i = left; i < mid + 1; i++) {
            leftArray.add(a.get(i));
        }

        // copy values into rightArray
        for(int i = mid + 1; i < right + 1; i++) {
            rightArray.add(a.get(i));
        }

        // compare values of leftArray and rightArray, insert into a
        for(int i = left; i < right + 1; i++) {

            // if there is no value in leftArray
            if(leftArray.isEmpty()) {
                a.set(i, rightArray.remove(0));

            // if there is no value in rightArray
            } else if(rightArray.isEmpty()) {
                a.set(i, leftArray.remove(0));

            // normal comparison
            } else {
                if(rightArray.get(0).compareTo(leftArray.get(0)) >= 0) {
                    a.set(i, rightArray.remove(0));
                } else {
                    a.set(i, leftArray.remove(0));
                }
            }
        }
    }

    // Quick Sort | FINISHED
    public static <T extends Comparable> void quickSort(ArrayList<T> a, int left, int right) {
        // definitions
        int partition;

        // ensures the method ends
        if(right - left > 0) {

            // call partition, get pivot from return
            partition = partition(a, left, right);

            // call quickSort on left, pivot - 1 and on pivot + 1, right
            if(partition != left) quickSort(a, left, partition - 1);
            if(partition != right) quickSort(a, partition + 1, right);
        }
    }

    public static <T extends Comparable> int partition (ArrayList<T> a, int left, int right) {
        // definitions
        int pivot;
        int rightIndex;
        int leftIndex;

        // choose "random" pivot (center is chosen since largest value will likely not be in the center for our cases
        pivot = ((right - left ) / 2) + left;
        rightIndex = right;
        leftIndex = left;

        if(right == left) return pivot;

        // sort into two partitions
        swap(a, pivot, right);
        pivot = right;

        /*
         *  Strategy: send pivot to back of the segment
         *            if a value is greater than the pivot, move on to next value
         *            if a value is less than the pivot, do three things:
         *                  first:  swap pivot with the item before the pivot
         *                  second: swap item that was before the pivot with the item compared previously
         *                  third:  stay on the same index (since the value has changed)
         */
        for(int i = left; i < pivot; i++) {
            // if value is greater
            if(a.get(i).compareTo(a.get(pivot)) >= 0) {
                leftIndex++;
            } else {
                // if value is less
                swap(a, pivot, pivot - 1);

                // ensures the pivot isn't swapped again
                if(pivot != leftIndex + 1) {
                    swap(a, i, rightIndex);
                }

                rightIndex--;
                i--;
                pivot--;

            }
        }

        return pivot;
    }

    static <T> void swap(ArrayList<T> a, int i, int j) {
        T temp = a.get(i);
        a.set(i, a.get(j));
        a.set(j, temp);
    }

    // Heap Sort
    public static <T extends Comparable> void heapSort(ArrayList<T> a, int left, int right) {
        // declarations
        ArrayList<T> tempArray = new ArrayList<>();
        int tempNode;
        int lastIndex = right;

        // copy values into tempArray
        tempArray = (ArrayList<T>) a.clone();
        a.clear();
        tempArray.add(0, null);
        tempNode = left + 1;

        // call heapify on tempArray
        heapify(tempArray, left + 1, right + 1);

        while(left <= right) {
            tempNode = left + 1;
            // percolate top node down to bottom, then delete
            while ((tempNode * 2) + 1 <= right) {
                // case for if both children exist
                if ((tempNode * 2) + 1 <= right) {
                    if (tempArray.get(tempNode * 2).compareTo(tempArray.get((tempNode * 2) + 1)) >= 0) {
                        swap(tempArray, tempNode, tempNode * 2);
                        tempNode = tempNode * 2;
                    } else {
                        swap(tempArray, tempNode, (tempNode * 2) + 1);
                        tempNode = (tempNode * 2) + 1;
                    }


                    // case for if only left child exists
                } else {
                    swap(tempArray, tempNode, tempNode * 2);
                    tempNode = tempNode * 2;
                }
            }

            // swap tempNode with last node
            swap(tempArray, tempNode, right);
            tempNode = right;

            // percolate last node up
            while(tempNode / 2 != 0 && tempArray.get(tempNode).compareTo(tempArray.get(tempNode / 2)) < 0) {
                swap(tempArray, tempNode, tempNode / 2);
                tempNode = tempNode / 2;
            }

            a.add(tempArray.remove(right));
            right--;
        }

        // I literally need this to fix the code, idk why
        while(true) {
            if(lastIndex > 0 && a.get(lastIndex).compareTo(a.get(lastIndex - 1)) > 0) {
                swap(a, lastIndex, lastIndex - 1);
                lastIndex--;
            } else {
                break;
            }
        }
    }

    public static <T extends Comparable> void heapify (ArrayList<T> a, int left, int right) {
        // declarations
        int mid;
        int tempNode;

        // find "middle" value
        mid = right / 2;

        // from "middle", percolate down
        for(int i = mid + 1; i > left - 1; i--) {
            tempNode = i;
            while((tempNode * 2) + 1 <= right) {
                // case for if both children exist
                if((tempNode * 2) + 1 <= right) {
                    if(a.get(tempNode * 2).compareTo(a.get((tempNode * 2) + 1)) >= 0) {
                        if(a.get(tempNode * 2).compareTo(a.get(tempNode)) > 0) {
                            swap(a, tempNode, tempNode * 2);
                        }
                        tempNode = tempNode * 2;
                    } else {
                        if(a.get((tempNode * 2) + 1).compareTo(a.get(tempNode)) > 0) {
                            swap(a, tempNode, (tempNode * 2) + 1);
                        }
                        tempNode = (tempNode * 2) + 1;
                    }


                // case for if only left child exists
                } else {
                    if(a.get(tempNode * 2).compareTo(a.get(tempNode)) > 0) {
                        swap(a, tempNode, tempNode * 2);
                    }
                    tempNode = tempNode * 2;
                }
            }
        }
    }

    // Bubble Sort | FINISHED
    public static <T extends Comparable> int bubbleSort(ArrayList<T> a, int size) {
        boolean hasSwapped = true;
        int swapCounter = 0;

        // outer loop
        while(hasSwapped) {
            hasSwapped = false;

            // inner loop
            for(int i = 0; i < size - 1; i++) {

                // swap if necessary
                if(a.get(i).compareTo(a.get(i + 1)) < 0) {
                    hasSwapped = true;
                    swap(a, i, i + 1);
                    swapCounter++;
                }
            }
        }

        return swapCounter;
    }

    // Odd-Even Transposition Sort | FINISHED
    public static <T extends Comparable> int transpositionSort(ArrayList<T> a, int size) {
        boolean hasSwapped = true;
        int swapCounter = 0;

        // outer loop
        while(hasSwapped) {
            hasSwapped = false;

            // inner loop for odd elements
            for(int i = 0; i < size - 1; i += 2) {

                // swap if necessary
                if(a.get(i).compareTo(a.get(i + 1)) < 0) {
                    hasSwapped = true;
                    swap(a, i, i + 1);
                }
            }

            swapCounter++;

            // inner loop for even elements
            for(int i = 1; i < size - 1; i += 2) {

                // swap if necessary
                if(a.get(i).compareTo(a.get(i + 1)) < 0) {
                    hasSwapped = true;
                    swap(a, i, i + 1);
                }
            }

            swapCounter++;
        }

        return swapCounter;
    }

    public static void main(String [] args)  throws IOException {
        // TODO: finish proper implementation

        ArrayList<Catcher> listToSort;
        long startTime;
        long endTime;
        String inputFile;
        String algorithmType;
        int linesToRead;
        int swapCount;

        listToSort  = new ArrayList<>();


        // check for correct args count
        if (args.length != 3) {
            System.err.println("Usage: java Proj3 <database file> <algorithm type (as named in method names)> <number of lines>");
            System.exit(1);
        }

        // assign args
        inputFile = args[0];
        algorithmType = args[1];
        linesToRead = Integer.parseInt(args[2]);

        // create fileIO
        FileInputStream inputFileNameStream;
        Scanner inputFileNameScanner = null;

        inputFileNameStream = new FileInputStream(inputFile);
        inputFileNameScanner = new Scanner(inputFileNameStream);

        // ignore first line
        inputFileNameScanner.nextLine();

        // reads data from file
        for(int i = 0; i < linesToRead; i++) {
            listToSort.add(new Catcher(inputFileNameScanner.nextLine()));
        }

        clearOutFile("sorted.txt");

        if(algorithmType.equals("bubbleSort")) {
            writeToFile("bubbleSort", "analysis.txt");
            writeToFile(listToSort.size() + "", "analysis.txt");

            System.out.println("bubbleSort with " + listToSort.size() + " items.");

            // sorted
            Collections.sort(listToSort, Collections.reverseOrder());
            swapCount = bubbleSort(listToSort, listToSort.size());
            writeFullList(listToSort, "sorted.txt");
            writeToFile(swapCount + "", "analysis.txt");
            System.out.println("Sorted: " + swapCount + " swaps.");

            // shuffled
            Collections.shuffle(listToSort);
            swapCount = bubbleSort(listToSort, listToSort.size());
            writeFullList(listToSort, "sorted.txt");
            writeToFile(swapCount + "", "analysis.txt");
            System.out.println("Shuffled: " + swapCount + " swaps.");

            // reversed
            Collections.sort(listToSort);
            swapCount = bubbleSort(listToSort, listToSort.size());
            writeFullList(listToSort, "sorted.txt");
            fileNewLine(swapCount + "", "analysis.txt");
            System.out.println("Reversed: " + swapCount + " swaps.");

        } else if(algorithmType.equals("transpositionSort")) {
            writeToFile("transpositionSort", "analysis.txt");
            writeToFile(listToSort.size() + "", "analysis.txt");

            System.out.println("transpositionSort with " + listToSort.size() + " items.");

            // sorted
            Collections.sort(listToSort, Collections.reverseOrder());
            swapCount = transpositionSort(listToSort, listToSort.size());
            writeFullList(listToSort, "sorted.txt");
            writeToFile(swapCount + "", "analysis.txt");
            System.out.println("Sorted: " + swapCount + " swaps.");

            // shuffled
            Collections.shuffle(listToSort);
            swapCount = transpositionSort(listToSort, listToSort.size());
            writeFullList(listToSort, "sorted.txt");
            writeToFile(swapCount + "", "analysis.txt");
            System.out.println("Shuffled: " + swapCount + " swaps.");

            // reversed
            Collections.sort(listToSort);
            swapCount = transpositionSort(listToSort, listToSort.size());
            writeFullList(listToSort, "sorted.txt");
            fileNewLine(swapCount + "", "analysis.txt");
            System.out.println("Reversed: " + swapCount + " swaps.");
        } else if(algorithmType.equals("heapSort")) {
            writeToFile("heapSort", "analysis.txt");
            writeToFile(listToSort.size() + "", "analysis.txt");

            System.out.println("heapSort with " + listToSort.size() + " items.");

            // sorted
            Collections.sort(listToSort);
            startTime = System.nanoTime();
            heapSort(listToSort, 0, listToSort.size() - 1);
            endTime = System.nanoTime();
            writeFullList(listToSort, "sorted.txt");
            writeToFile((endTime - startTime) + "", "analysis.txt");
            System.out.println("Sorted: " + (endTime - startTime) + " nanoseconds.");

            // shuffled
            Collections.shuffle(listToSort);
            startTime = System.nanoTime();
            heapSort(listToSort, 0, listToSort.size() - 1);
            endTime = System.nanoTime();
            writeFullList(listToSort, "sorted.txt");
            writeToFile((endTime - startTime) + "", "analysis.txt");
            System.out.println("Sorted: " + (endTime - startTime) + " nanoseconds.");

            // reversed
            Collections.sort(listToSort, Collections.reverseOrder());
            startTime = System.nanoTime();
            heapSort(listToSort, 0, listToSort.size() - 1);
            endTime = System.nanoTime();
            writeFullList(listToSort, "sorted.txt");
            fileNewLine((endTime - startTime) + "", "analysis.txt");
            System.out.println("Sorted: " + (endTime - startTime) + " nanoseconds.");
        } else if(algorithmType.equals("mergeSort")) {
            writeToFile("mergeSort", "analysis.txt");
            writeToFile(listToSort.size() + "", "analysis.txt");

            System.out.println("mergeSort with " + listToSort.size() + " items.");

            // sorted
            Collections.sort(listToSort);
            startTime = System.nanoTime();
            mergeSort(listToSort, 0, listToSort.size() - 1);
            endTime = System.nanoTime();
            writeFullList(listToSort, "sorted.txt");
            writeToFile((endTime - startTime) + "", "analysis.txt");
            System.out.println("Sorted: " + (endTime - startTime) + " nanoseconds.");

            // shuffled
            Collections.shuffle(listToSort);
            startTime = System.nanoTime();
            mergeSort(listToSort, 0, listToSort.size() - 1);
            endTime = System.nanoTime();
            writeFullList(listToSort, "sorted.txt");
            writeToFile((endTime - startTime) + "", "analysis.txt");
            System.out.println("Sorted: " + (endTime - startTime) + " nanoseconds.");

            // reversed
            Collections.sort(listToSort, Collections.reverseOrder());
            startTime = System.nanoTime();
            mergeSort(listToSort, 0, listToSort.size() - 1);
            endTime = System.nanoTime();
            writeFullList(listToSort, "sorted.txt");
            fileNewLine((endTime - startTime) + "", "analysis.txt");
            System.out.println("Sorted: " + (endTime - startTime) + " nanoseconds.");
        } else if(algorithmType.equals("quickSort")) {
            writeToFile("quickSort", "analysis.txt");
            writeToFile(listToSort.size() + "", "analysis.txt");

            System.out.println("quickSort with " + listToSort.size() + " items.");

            // sorted
            Collections.sort(listToSort);
            startTime = System.nanoTime();
            quickSort(listToSort, 0, listToSort.size() - 1);
            endTime = System.nanoTime();
            writeFullList(listToSort, "sorted.txt");
            writeToFile((endTime - startTime) + "", "analysis.txt");
            System.out.println("Sorted: " + (endTime - startTime) + " nanoseconds.");

            // shuffled
            Collections.shuffle(listToSort);
            startTime = System.nanoTime();
            quickSort(listToSort, 0, listToSort.size() - 1);
            endTime = System.nanoTime();
            writeFullList(listToSort, "sorted.txt");
            writeToFile((endTime - startTime) + "", "analysis.txt");
            System.out.println("Sorted: " + (endTime - startTime) + " nanoseconds.");

            // reversed
            Collections.sort(listToSort, Collections.reverseOrder());
            startTime = System.nanoTime();
            quickSort(listToSort, 0, listToSort.size() - 1);
            endTime = System.nanoTime();
            writeFullList(listToSort, "sorted.txt");
            fileNewLine((endTime - startTime) + "", "analysis.txt");
            System.out.println("Sorted: " + (endTime - startTime) + " nanoseconds.");
        } else {
            System.out.println("not an algorithm type");
            System.exit(1);
        }
    }

    public static void writeToFile(String content, String filePath) throws IOException {
        FileWriter outFile = new FileWriter(filePath, true);  // navigates to end of file

        outFile.write(content + ",");

        outFile.close();
    }

    public static void clearOutFile(String filePath) throws IOException{
        FileWriter outFile = new FileWriter(filePath);

        outFile.write("");

        outFile.close();
    }

    public static <T extends Comparable> void writeFullList(ArrayList<T> a, String filePath) throws IOException {
        FileWriter outFile = new FileWriter(filePath, true);  // navigates to end of file

        outFile.write(a.toString() + "\n");

        outFile.close();
    }

    public static void fileNewLine(String content, String filePath) throws IOException {
        FileWriter outFile = new FileWriter(filePath, true);  // navigates to end of file

        outFile.write(content + "\n");

        outFile.close();
    }
}
