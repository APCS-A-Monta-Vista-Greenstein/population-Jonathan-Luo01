import java.util.ArrayList;
import java.util.List;
/**
 *	SortMethods - Sorts the City objects in specified order.
 *
 *	@author Jonathan Luo
 *	@since	December 5, 2022
 */
public class SortMethods {

    private List<City> tempArr; // MergeSort temporary array

    /**
     *	Bubble Sort algorithm - in ascending order
     *	@param arr		list of City objects to sort
     */
    public void bubbleSort(List<City> arr) {
        for(int outer = arr.size()-1; outer > 0; outer--) {
            for(int inner = 0; inner < outer; inner++) {
                if(arr.get(inner).compareTo(arr.get(inner+1)) > 0) swap(arr, inner, inner+1);
            }
        }
    }

    /**
     *	Swaps two City objects in array arr
     *	@param arr		list of City objects
     *	@param x		index of first object to swap
     *	@param y		index of second object to swap
     */
    private void swap(List<City> arr, int x, int y) {
        City temp = arr.get(x);
        arr.set(x, arr.get(y));
        arr.set(y, temp);
    }

    /**
     *	Selection Sort algorithm - in ascending order (you implement)
     *	@param arr		list of City objects to sort
     */
    public void selectionSort(List<City> arr) {
        for(int outer = arr.size()-1; outer > 0; outer--) {
            City main = arr.get(0);
            int index = 0;
            for(int inner = 0; inner < outer; inner++) {
                if(arr.get(inner+1).compareTo(main) > 0) {
                    main = arr.get(inner+1);
                    index = inner+1;
                }
            }
            swap(arr, index, outer);
        }
    }

    /**
     *	Insertion Sort algorithm - in ascending order (you implement)
     *	@param arr		list of City objects to sort
     */
    public void insertionSort(List<City> arr) {
        for(int i = 1; i < arr.size(); i++) {
            int index = i;
            City temp = arr.get(i);
            while(index > 0 && temp.compareTo(arr.get(index-1)) < 0) {
                arr.set(index, arr.get(index-1));
                index--;
            }
            arr.set(index, temp);
        }
    }

    /**
     *	Merge Sort algorithm - in ascending order (you implement)
     *	@param arr		list of City objects to sort
     */
    public void mergeSort(List<City> arr) {
        tempArr = new ArrayList<City>();
        recursiveSort(arr, 0, arr.size()-1);
    }

    /**
     * Merge sort recursive helper method - sorts from first to last
     * @param arr		list of City objects to sort
     * @param first		the first element to sort from
     * @param last		the last element to sort to
     */
    private void recursiveSort(List<City> arr, int first, int last) {
        if(last - first < 2) {
            if(last > first && arr.get(last).compareTo(arr.get(first)) < 0) swap(arr, first, last);
        }
        else {
            int middle = (first+last)/2;
            recursiveSort(arr, first, middle);
            recursiveSort(arr, middle+1, last);
            merge(arr, first, middle, last);
        }
    }

    /**
     * Merge sort recursive helper method - merges the arrays
     * @param arr		list of City objects to merge
     * @param first		the first element to merge from
     * @param middle	middle between the unmerged sections
     * @param last		the last element to merge to
     */
    private void merge(List<City> arr, int first, int middle, int last) {
        int i = middle+1;
        int a = first; //Retain index of first
        int index = first;
        while(first <= middle && i <= last) {
            if(arr.get(first).compareTo(arr.get(i)) < 0) {
                tempArr.set(index, arr.get(first));
                first++;
            }
            else {
                tempArr.set(index, arr.get(i));
                i++;
            }
            index++;
        }
        while(first <= middle) {
            tempArr.set(index, arr.get(first));
            first++;
            index++;
        }
        while(i <= last) {
            tempArr.set(index, arr.get(i));
            i++;
            index++;
        }
        for(int j = a; j <= last; j++) {
            arr.set(j, tempArr.get(j));
        }
    }

    /**
     *	Selection Sort algorithm - in ascending order (you implement)
     *  Sorts by ascending order.
     *	@param arr		list of City objects to sort
     */
    public void sortAscendPopulation(List<City> arr) {
        for(int outer = arr.size()-1; outer > 0; outer--) {
            City main = arr.get(0);
            int index = 0;
            for(int inner = 0; inner < outer; inner++) {
                if(arr.get(inner+1).getPopulation()>main.getPopulation()) {
                    main = arr.get(inner+1);
                    index = inner+1;
                }
            }
            swap(arr, index, outer);
        }
    }

    /**
     *	Merge Sort algorithm - in descending order (you implement)
     *	@param arr		list of Integer objects to sort
     */
    public void sortDescendPopulation(List<City> arr) {
        recursivePopulationSort(arr, 0, arr.size()-1);
    }

    /**
     * Merge sort recursive helper method - sorts from first to last
     * Sorts in descending order.
     * @param arr		list of City objects to sort
     * @param first		the first element to sort from
     * @param last		the last element to sort to
     */
    private void recursivePopulationSort(List<City> arr, int first, int last) {
        if(last - first < 2) {
            if(last > first && arr.get(last).compareTo(arr.get(first)) > 0) swap(arr, first, last);
        }
        else {
            int middle = (first+last)/2;
            recursivePopulationSort(arr, first, middle);
            recursivePopulationSort(arr, middle+1, last);
            populationMerge(arr, first, middle, last);
        }
    }

    /**
     * Merge sort recursive helper method - merges the arrays
     * @param arr		list of City objects to merge
     * @param first		the first element to merge from
     * @param middle	middle between the unmerged sections
     * @param last		the last element to merge to
     */
    private void populationMerge(List<City> arr, int first, int middle, int last) {
        tempArr = new ArrayList<City>();
        int i = middle+1;
        int a = first; //Retain index of first
        int index = first;
        while(first <= middle && i <= last) {
            if(arr.get(first).compareTo(arr.get(i)) > 0) {
                tempArr.add(arr.get(first));
                first++;
            }
            else {
                tempArr.add(arr.get(i));
                i++;
            }
            index++;
        }
        while(first <= middle) {
            tempArr.add(arr.get(first));
            first++;
            index++;
        }
        while(i <= last) {
            tempArr.add(arr.get(i));
            i++;
            index++;
        }
        int j = 0;
        while(j < tempArr.size()) {
            arr.set(a, tempArr.get(j++));
            a++;
        }
    }

    /**
     *	Insertion Sort algorithm - in ascending name order
     *	@param arr		list of City objects to sort
     */
    public void sortAscendName(List<City> arr) {
        for(int i = 1; i < arr.size(); i++) {
            int index = i;
            City temp = arr.get(i);
            while(index > 0 && temp.getName().compareTo(arr.get(index-1).getName()) < 0) {
                arr.set(index, arr.get(index-1));
                index--;
            }
            arr.set(index, temp);
        }
    }

    /**
     *	Merge Sort algorithm - sorts names in descending order
     *	@param arr		list of Integer objects to sort
     */
    public void sortDescendName(List<City> arr) {
        recursiveNameSort(arr, 0, arr.size()-1);
    }

    /**
     * Merge sort recursive helper method - sorts from first to last
     * Sorts names in descending order.
     * @param arr		list of City objects to sort
     * @param first		the first element to sort from
     * @param last		the last element to sort to
     */
    private void recursiveNameSort(List<City> arr, int first, int last) {
        if(last - first < 2) {
            if(last > first && (arr.get(last).getName().compareTo(arr.get(first).getName())) > 0) swap(arr, first, last);
        }
        else {
            int middle = (first+last)/2;
            recursiveNameSort(arr, first, middle);
            recursiveNameSort(arr, middle+1, last);
            nameMerge(arr, first, middle, last);
        }
    }

    /**
     * Merge sort recursive helper method - merges the arrays
     * @param arr		list of City objects to merge
     * @param first		the first element to merge from
     * @param middle	middle between the unmerged sections
     * @param last		the last element to merge to
     */
    private void nameMerge(List<City> arr, int first, int middle, int last) {
        tempArr = new ArrayList<City>();
        int i = middle+1;
        int a = first; //Retain index of first
        int index = first;
        while(first <= middle && i <= last) {
            if((arr.get(first).getName()).compareTo(arr.get(i).getName()) > 0) {
                tempArr.add(arr.get(first));
                first++;
            }
            else {
                tempArr.add(arr.get(i));
                i++;
            }
            index++;
        }
        while(first <= middle) {
            tempArr.add(arr.get(first));
            first++;
            index++;
        }
        while(i <= last) {
            tempArr.add(arr.get(i));
            i++;
            index++;
        }
        int j = 0;
        while(j < tempArr.size()) {
            arr.set(a, tempArr.get(j++));
            a++;
        }
    }

    /**
     *	Insertion Sort algorithm - in descending population order
     *  Fifty most populous cities in a state.
     *	@param arr		    list of City objects to sort
     *  @return tempArr     sorted list of City objects
     */
    public List<City> sortInState(List<City> arr, String state) {
        List<City> tempArr = new ArrayList<City>();
        for(int i = 0; i < arr.size(); i++) {
            if(arr.get(i).getState().equals(state)) tempArr.add(arr.get(i));
        }
        for(int i = 1; i < tempArr.size(); i++) {
            int index = i;
            City temp = tempArr.get(i);
            while(index > 0 && temp.compareTo(tempArr.get(index-1)) > 0) {
                tempArr.set(index, tempArr.get(index-1));
                index--;
            }
            tempArr.set(index, temp);
        }
        return tempArr;
    }

    /**
     *	Insertion Sort algorithm - in descending population order
     *  Fifty most populous cities in a state.
     *	@param arr		    list of City objects to sort
     *  @return tempArr     sorted list of City objects
     */
    public List<City> matchCity(List<City> arr, String name) {
        List<City> tempArr = new ArrayList<City>();
        for(int i = 0; i < arr.size(); i++) {
            if(arr.get(i).getName().equals(name)) tempArr.add(arr.get(i));
        }
        for(int i = 1; i < tempArr.size(); i++) {
            int index = i;
            City temp = tempArr.get(i);
            while(index > 0 && temp.compareTo(tempArr.get(index-1)) > 0) {
                tempArr.set(index, tempArr.get(index-1));
                index--;
            }
            tempArr.set(index, temp);
        }
        return tempArr;
    }




    /*****************************************************************/
    /************************* For Testing ***************************/
    /*****************************************************************/

    /**
     *	Print an array of Integers to the screen
     *	@param arr		the array of Integers
     */
    public void printArray(Integer[] arr) {
        if (arr.length == 0) System.out.print("(");
        else System.out.printf("( %4d", arr[0]);
        for (int a = 1; a < arr.length; a++) {
            if (a % 10 == 0) System.out.printf(",\n  %4d", arr[a]);
            else System.out.printf(", %4d", arr[a]);
        }
        System.out.println(" )");
    }

    public static void main(String[] args) {
        SortMethods se = new SortMethods();
        // se.run();
    }
/**
 public void run() {
 Integer[] arr = new Integer[10];
 // Fill arr with random numbers
 for (int a = 0; a < 10; a++)
 arr[a] = (int)(Math.random() * 100) + 1;
 System.out.println("\nBubble Sort");
 System.out.println("Array before sort:");
 printArray(arr);
 System.out.println();
 bubbleSort(arr);
 System.out.println("Array after sort:");
 printArray(arr);
 System.out.println();

 for (int a = 0; a < 10; a++)
 arr[a] = (int)(Math.random() * 100) + 1;
 System.out.println("\nSelection Sort");
 System.out.println("Array before sort:");
 printArray(arr);
 System.out.println();
 selectionSort(arr);
 System.out.println("Array after sort:");
 printArray(arr);
 System.out.println();


 for (int a = 0; a < 10; a++)
 arr[a] = (int)(Math.random() * 100) + 1;
 System.out.println("\nInsertion Sort");
 System.out.println("Array before sort:");
 printArray(arr);
 System.out.println();
 insertionSort(arr);
 System.out.println("Array after sort:");
 printArray(arr);
 System.out.println();



 for (int a = 0; a < 10; a++)
 arr[a] = (int)(Math.random() * 100) + 1;
 System.out.println("\nMerge Sort");
 System.out.println("Array before sort:");
 printArray(arr);
 System.out.println();
 mergeSort(arr);
 System.out.println("Array after sort:");
 printArray(arr);
 System.out.println();

 }
 */
}
