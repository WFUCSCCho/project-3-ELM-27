import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Proj3 {
    // Sorting Method declarations
    // Merge Sort
    public static <T extends Comparable> void mergeSort(ArrayList<T> a, int left, int right) {
        // TODO: Finish Me
    }

    public static <T extends Comparable> void merge(ArrayList<T> a, int left, int mid, int right) {
        // TODO: Finish Me
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
        heapify(a, left, right);

        // TODO: deleteMax until array is sorted, somehow make duplicate array do deal with this
    }

    public static <T extends Comparable> void heapify (ArrayList<T> a, int left, int right) {
        // TODO: Finish Me
        int startIndex = left;
        int index;
        int lChild;
        int rChild;
        int largestChildIndex;

        // find index to start
        while((startIndex * 2) + 1 < (right)) {
            startIndex = (startIndex * 2) + 1;
        }

        while(startIndex * 2 > right) {
            startIndex--;
        }

        // repeated percolate down
        for(int i = startIndex; i > left - 1; i--) {
            index = i;
            lChild = index * 2;

            // while has children
            while(lChild < right) {
                lChild = index * 2;
                rChild = (index * 2) + 1;

                // find largest child
                if(rChild < right) {
                    if(a.get(lChild).compareTo(a.get(rChild)) > 0) {
                        largestChildIndex = lChild;
                    } else {
                        largestChildIndex = rChild;
                    }
                } else {
                    largestChildIndex = lChild;
                }

                // compare largest child and parent
                if(a.get(largestChildIndex).compareTo(a.get(index)) > 0) {
                    // swap if necessary
                    swap(a, index, largestChildIndex);
                    index = largestChildIndex;

                    // if swap not necessary, break
                } else {
                    break;
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

        // test line
        System.out.println(listToSort.toString());

        Collections.shuffle(listToSort);

        // insert test sorting algorithm below
        swapCount = bubbleSort(listToSort, linesToRead);

        System.out.print(swapCount + " swaps, ");
        System.out.println(listToSort.toString());

        swapCount = transpositionSort(listToSort, linesToRead);

        System.out.print(swapCount + " swaps, ");
        System.out.println(listToSort.toString());
    }
}
