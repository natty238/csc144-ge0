
package searchandsort;


import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SearchAndSort {
    /**
     * This code will randomly generate a list of numbers to use the sorting algorithms on.
     * 
     */
    
    private static Random rng = new Random();
    public static List<Integer> makelist(int size){
        List<Integer> result = new ArrayList<>();
        for (int i =0; i< size; i++){
            int n = 10 + rng.nextInt(90);
            result.add(n);
        }
        return result;
    
    }
    
    /**
     * This code will print the values of the list in order as they are randomly generated.
     * 
     */
    
    public static void printList(List<Integer> values) {
        int SIZE_THRESHOLD = 0;
        if (values.size() < SIZE_THRESHOLD) {
            for (int n : values) {
                System.out.printf("%4d", n);
            } // for
            System.out.println();
        } // if
        else {
            for (int n : values) {
                System.out.printf("%4d\n", n);
            } 
        } 
    } 
    
    
    // TO-DO: Define a method that determines
    // the index of the first integer in a list
    // of integers that matches a given integer.
    // The method should return -1 if no match is found.
    // Use the sequential search algorithm.
    
    /**
     * 
     *  Goes number by number to look for a match. If it isn't the number that is looked for, it will add 1 to the index so that the index is the number of the match.
     * 
     *  
     */
    public static int linearSearch(List<Integer> values, int target){
        int result = -1;
        int index = 0;
        while (index< values.size() && result < 0){
            if (target == values.get(index)){
                result = index;
            }
            index = index + 1;
        }
        return result;
    }
    
    
    
    // TO-DO: Define a method that determines
    // the index of the first integer in a list
    // of integers that matches a given integer.
    // The method should return -1 if no match is found.
    // Use the binary search algorithm.
    
    /**
     * this algorithm will pick a point in the middle of the list and if
     * the number is too high or too low it will search the half of the list 
     * that is above or below the middle number
     * 
     */
    
    public static int binarySearch(List<Integer> values,
            int target) {
        int result = -1;

        int lo = 0;
        int hi = values.size() - 1;

        while (lo < hi && result < 0) {
            int mid = (lo + hi) / 2;
            if (target == values.get(lo)) {
                result = lo;
            } 
            else if (target == values.get(mid)) {
                result = mid;
            } 
            else if (target == values.get(hi)) {
                result = hi;
            } 
            else if (target < values.get(mid)) {
                hi = mid - 1;
            } 
            else {
                lo = mid + 1;
            } 
        } 

        return result;
    } 
    
    
    // TO-DO: Define a method that sorts a list
    // of integers using the selection sort algorithm.
    
    /**
     * 
     * the algorithm finds the smallest integer and puts it at
     * the first spot and keeps doing it for every spot in the list
     * so that the list is ordered from smallest to largest.
     * 
     * 
     * 
     */
    
     public static void swap(List<Integer> values, int i, int j) {
        int temp = values.get(i);
        values.set(i, values.get(j));
        values.set(j, temp);
    } 

    public static int findPosMin(List<Integer> values, int start) {
        int bestGuessSoFar = start;
        for (int i = start + 1; i < values.size(); i++) {
            if (values.get(i) < values.get(bestGuessSoFar)) {
                bestGuessSoFar = i;
            } 
        } 
        return bestGuessSoFar;
    } 

    public static void selectionSort(List<Integer> values) {
        for (int i = 0; i < values.size(); i++) {
            int j = findPosMin(values, i);
            swap(values, i, j);
        } 
    }

    
    
    // TO-DO: Define a method that sorts a list
    // of integers using the insertion sort algorithm.
    
    
    /**
     * the algorithm goes down the list one number at  a time and 
     * puts it after the next smallest number
     *
     */
    
    public static void insert(List<Integer> values, int next) {

        int i = next;
        while (i > 0 && values.get(i) < values.get(i - 1)) {
            swap(values, i, i - 1);
            i = i - 1;
        } 

    } 

    public static void insertionSort(List<Integer> values) {
        for (int i = 1; i < values.size(); i++) {
            insert(values, i);
        } 
    } 
    
    // TO-DO: Define a method that sorts a list
    // of integers using the merge sort algorithm.
    
    /**
     * 
     * 
     */
    
    public static void merge(List<Integer> values, int prefixStart,
            int suffixStart, int suffixEnd) {
        List<Integer> temp = new ArrayList<>();

        int i = prefixStart;
        int j = suffixStart;

        while (i < suffixStart && j < suffixEnd) {
            if (values.get(i) < values.get(j)) {
                temp.add(values.get(i));
                i++;
            } 
            else {
                temp.add(values.get(j));
                j++;
            } 
        } 

        while (i < suffixStart) {
            temp.add(values.get(i));
            i++;
        } 

        while (j < suffixEnd) {
            temp.add(values.get(j));
            j++;
        } 

        i = prefixStart;
        for (int index = 0; index < temp.size(); index++) {
            values.set(i, temp.get(index));
            i++;
        } 
    } 

    public static void mergeSort(List<Integer> values) {
        for (int stepSize = 2; stepSize < values.size(); stepSize *= 2) {
            for (int i = 0; i < values.size(); i += stepSize) {
                int prefixStart = i;
                int suffixStart = i + stepSize / 2;
                int suffixEnd = Math.min(values.size(), i + stepSize);
                merge(values, prefixStart, suffixStart, suffixEnd);
            } 
            if (stepSize > values.size() / 2) {
                int prefixStart = 0;
                int suffixStart = stepSize;
                int suffixEnd = values.size();
                merge(values, prefixStart, suffixStart, suffixEnd);
            } 
            
        } 
    } 
    
    public static void main( String [] args ) {
        System.out.println( "Searching and sorting algorithms" );
        
        // TO-DO: Add code that tests the searching and sorting
        // methods.
        
        System.out.println("Selection sort.");
        List<Integer> data = makeList(12);
        printList(data);
        System.out.println(" **** ");
        selectionSort(data);
        printList(data);

        System.out.println(" **** ");

        System.out.println("Insertion sort.");
        data = makeList(12);
        printList(data);
        System.out.println(" **** ");
        insertionSort(data);
        printList(data);

        System.out.println(" **** ");

        System.out.println("Merge sort.");
        data = makeList(12);
        printList(data);
        System.out.println(" **** ");
        mergeSort(data);
        printList(data);
        
        
        
    } // main( String [] )
} // SearchAndSort
