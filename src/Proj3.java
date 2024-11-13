import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Proj3 {
    // Sorting Method declarations
    // Merge Sort
    public static <T extends Comparable> void mergeSort(ArrayList<T> a, int left, int right) {
        // Finish Me
    }

    public static <T extends Comparable> void merge(ArrayList<T> a, int left, int mid, int right) {
        // Finish Me
    }

    // Quick Sort
    public static <T extends Comparable> void quickSort(ArrayList<T> a, int left, int right) {
        // Finish Me
    }

    public static <T extends Comparable> int partition (ArrayList<T> a, int left, int right) {
        // Finish Me

        return 0;
    }

    static <T> void swap(ArrayList<T> a, int i, int j) {
        T temp = a.get(i);
        a.set(i, a.get(j));
        a.set(j, temp);
    }

    // Heap Sort
    public static <T extends Comparable> void heapSort(ArrayList<T> a, int left, int right) {
        // Finish Me
    }

    public static <T extends Comparable> void heapify (ArrayList<T> a, int left, int right) {
        // Finish Me
    }

    // Bubble Sort
    public static <T extends Comparable> int bubbleSort(ArrayList<T> a, int size) {
        // Finish Me

        return 0;
    }

    // Odd-Even Transposition Sort
    public static <T extends Comparable> int transpositionSort(ArrayList<T> a, int size) {
        // Finish Me

        return 0;
    }

    public static void main(String [] args)  throws IOException {
        // TODO: change from Integer to custom type

        // TODO: define arraylist, starttime, endtime, printers, readers, and args
        ArrayList<Catcher> listToSort;
        long startTime;
        long endTime;
        String inputFile;
        String algorithmType;
        int linesToRead;

        listToSort  = new ArrayList<>();


        // TODO: check for correct args count
        if (args.length != 3) {
            System.err.println("Usage: java TestAvl <input file> <number of lines>");
            System.exit(1);
        }

        inputFile = args[0];
        algorithmType = args[1];
        linesToRead = Integer.parseInt(args[2]);

        // TODO: create fileIO
        FileInputStream inputFileNameStream;
        Scanner inputFileNameScanner = null;

        inputFileNameStream = new FileInputStream(inputFile);
        inputFileNameScanner = new Scanner(inputFileNameStream);

        // ignore first line
        inputFileNameScanner.nextLine();
        // FINISH ME

        for(int i = 0; i < linesToRead; i++) {
            listToSort.add(new Catcher(inputFileNameScanner.nextLine()));
        }

        System.out.println(listToSort.toString());
    }
}
