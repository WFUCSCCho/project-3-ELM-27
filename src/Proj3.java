import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Scanner;

public class Proj3 {
    // Sorting Method declarations
    // Merge Sort
    public static <T extends Comparable> void mergeSort(ArrayList<T> a, int left, int right) {
        // TODO: Finish Me
        int difference;
        int mid;

        difference = right - left;
        mid = (difference / 2) + left;

        if(difference != 0) {
            mergeSort(a, left, mid);
            mergeSort(a, mid + 1, right);
        }

        merge(a, left, mid, right);
    }

    public static <T extends Comparable> void merge(ArrayList<T> a, int left, int mid, int right) {
        // TODO: Finish Me
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

    // Quick Sort
    public static <T extends Comparable> void quickSort(ArrayList<T> a, int left, int right) {
        // TODO: Finish Me
    }

    public static <T extends Comparable> int partition (ArrayList<T> a, int left, int right) {
        // TODO: Finish Me

        return 0;
    }

    static <T> void swap(ArrayList<T> a, int i, int j) {
        T temp = a.get(i);
        a.set(i, a.get(j));
        a.set(j, temp);
    }

    // Heap Sort
    public static <T extends Comparable> void heapSort(ArrayList<T> a, int left, int right) {
        // TODO: Finish Me

    }

    public static <T extends Comparable> void heapify (ArrayList<T> a, int left, int right) {
        // TODO: Finish Me


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

    // Odd-Even Transposition Sort
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

                swapCounter++;
            }

            // inner loop for even elements
            for(int i = 1; i < size - 1; i += 2) {

                // swap if necessary
                if(a.get(i).compareTo(a.get(i + 1)) < 0) {
                    hasSwapped = true;
                    swap(a, i, i + 1);
                }

                swapCounter++;
            }
        }

        return swapCounter;
    }

    public static void main(String [] args)  throws IOException {
        // TODO: remove test code

        ArrayList<Catcher> listToSort;
        long startTime;
        long endTime;
        String inputFile;
        String algorithmType;
        int linesToRead;
        int swapCount;

        boolean testBoolean = true;
        ArrayList<Catcher> testList;

        listToSort  = new ArrayList<>();

        testList = new ArrayList<>();


        // check for correct args count
        if (args.length != 3) {
            System.err.println("Usage: java TestAvl <input file> <number of lines>");
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

        testList = (ArrayList<Catcher>) listToSort.clone();

        // test line
        Collections.sort(testList, Collections.reverseOrder());
        System.out.println(listToSort.toString());

        Collections.shuffle(listToSort);

        // insert test sorting algorithm below
        swapCount = bubbleSort(listToSort, linesToRead);

        System.out.print(swapCount + " swaps, ");
        System.out.println(listToSort.toString());

        Collections.shuffle(listToSort);

        swapCount = transpositionSort(listToSort, linesToRead);

        System.out.print(swapCount + " swaps, ");
        System.out.println(listToSort.toString());

        Collections.shuffle(listToSort);

        mergeSort(listToSort, 0, listToSort.size() - 1);

        System.out.println(listToSort.toString());

        for(int i = 0; i < listToSort.size(); i++) {
            if(listToSort.get(i).compareTo(testList.get(i)) != 0) {
                testBoolean = false;
                System.out.println("Breaks at " + listToSort.get(i).toString() + ", index " + i);
            }
        }
        System.out.println("Sort works: " + testBoolean);
    }
}
