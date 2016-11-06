/**
 *	DifferencePairs class - Finds all pairs of entered integers which share
 *      the input difference.
 *	
 * @author Jason Schenck
 *
 */
import java.util.ArrayList;
import java.util.Scanner;


public class DifferencePairs {

// USER INTERFACE START// 
    public static void main(String[] args) {
                System.out.println("Difference Pair Finder");
		System.out.println("Please select an option from the options below");
		System.out.println("");
                ArrayList<Integer> listNumbers = new ArrayList<Integer>();
                
                Scanner input = new Scanner(System.in);
		while (true) {
			System.out.println();
                        // Give the user a list of their options
			System.out.println("1: Enter list of integers");
			System.out.println("2: Find Difference Pairs");
			System.out.println("0: Exit");

			// Get the user input
			int userChoice = input.nextInt();

			switch (userChoice) {
			case 1:
				//method call enter integers here
                            enterIntegers(listNumbers);
				break;
			case 2:
				//method call find difference pairs here
                            Scanner scan2 = new Scanner(System.in);
                            System.out.println("Please enter the difference to find pairs: ");
                            int diff = scan2.nextInt();
                            
                            // copy array list to regular array
                            int[] numbersArray = new int[listNumbers.size()];
                            int i = 0;
                            for(int number:listNumbers){
                                numbersArray[i] = listNumbers.get(i);
                                i++;
                            }
                            
                            ArrayList<Pair> differencePairs = findPairs(numbersArray,diff);
                            
                            // FOR TESTING, PRINT OUT PAIRS ARRAY LIST!
                            if (differencePairs != null){
                            System.out.println();
                            for(Pair pair:differencePairs){
                                System.out.print(pair.getFirst() + " , " + pair.getLast());
                                System.out.println();
                                }                            
                            }
                            else{
                                System.out.println("NULL RETURNED, NO PAIRS FOUND.");
                            }
                            
				break;
			case 0:
				System.out.println("Goodbye!");
				System.exit(0);
			}
		}                
    }
    
    public static void enterIntegers(ArrayList<Integer> listNumbers){
        Scanner scan = new Scanner(System.in);
        
        System.out.println("Enter a list of integers separated by spaces: ");
        String listLine = scan.nextLine();
        String[] numbersStr = listLine.split(" "); // converts string entered to array of strings
        
        listNumbers.clear();
        int i = 0;
        for(String str:numbersStr){
            try{
                listNumbers.add(Integer.parseInt(str));
                i++;
            } catch (NumberFormatException e){
                throw new IllegalArgumentException(str + " at index " + i + " is not a number!",e);
            }
        }
        
        // for testing -- out put of integer array 
        System.out.println();
        System.out.println("You entered the following list of integers: ");
        System.out.println();
        for(int number:listNumbers){
            System.out.print(number + " ");
            
        }
        System.out.println();
    }
    // USER INTERFACE END //
    
    // findPairs ALGORITHM START //
    public static ArrayList<Pair> findPairs(int[] array, int diff){
        // 1) merge sort data
        // 2) binary search data for pairs
        // 3) generate arraylist of Pairs objects
        // 4) return arraylist of pairs
       
        // 1) SORT THE DATA USING MERGE SORT 
        array = mergeSort(array); // Store sorted array 
        
        
        // 2) FOR EACH ELEMENT, BINARY SEARCH FOR ELEMENT + DIFFERENCE
        ArrayList<Pair> differencePairs = new ArrayList<Pair>();
        
        int count = 0;
        for (int index = 0; index < array.length; index++)
        {
            if (binarySearch(array, array[index], diff) != -1)
            {
                // 3) GENERATE ARRAYLIST OF PAIRS
                differencePairs.add(new Pair(binarySearch(array, array[index], diff), array[index]));
                count++;
            }
        }
        
        // COUNT FOR TESTING 
        System.out.println("PAIRS COUNT IS: " + count);
        
        // 4) RETURN ARRAY LIST OF PAIRS IF PAIRS FOUND
        if ( differencePairs.size() > 0)
        {
            return differencePairs; // pairs found, return pairs arraylist
        }
        else
        {
            return null; // pairs not found, return NULL
        }
       
    }
    
    private static void merge(int[] a, int[] tempArray, int first, int mid, int last){
            int beginHalf1 = first; // first entry in left part
            int endHalf1 = mid; // last entry in left part
            int beginHalf2 = mid + 1; // first entry right part
            int endHalf2 = last; // last entry right part
            int index = beginHalf1; // next location in tempArray
            
            // while both subarrays are not empty, copy the smaller entry to tempArray
            while((beginHalf1 <= endHalf1) && (beginHalf2 <= endHalf2))
            {
                if(a[beginHalf1] < a[beginHalf2]) // if beginHalf1 < beginHalf2
                {
                    tempArray[index] = a[beginHalf1]; // place beginHalf1 in next index of tempArray
                    beginHalf1++; // increment beginHalf1
                }
                else // beginHalf1 >= beginHalf2
                {
                    tempArray[index] = a[beginHalf2]; // place beginHalf2 in next index of tempArray
                    beginHalf2++; //increment beginHalf2
                } // end if
                
                index++; //increment tempArray index each time a value is placed
                
            } // end while
            
            // copy rest of nonempty subarray to the temp array
            if(beginHalf1 > endHalf1) // indicates the left half is empty, copy the right half
            {
                System.arraycopy(a, beginHalf2, tempArray, index, endHalf2 - beginHalf2 + 1);
            }
            else // otherwise left half is not empty, copy left half
            {
                System.arraycopy(a, beginHalf1, tempArray, index, endHalf1 - beginHalf1 + 1);
            } // end if
            
            // finally copy the result back into the orignal array
            System.arraycopy(tempArray, first, a, first, last - first + 1);
            
        } // end merge
        
        public static int[] mergeSort(int[] a){
            // first method call, create tempArray, begin recursion with tempArray for 2nd constructor
           @SuppressWarnings("unchecked")
            int[] tempArray = new int[a.length];
            mergeSort(a, tempArray, 0, a.length - 1);
            return a;
        }
        
        private static void mergeSort(int[] a, int[] tempArray, int first, int last){
            if(first < last)
            {
                // sort each half
                int mid = (first + last) / 2; //index of midpoint
                
                mergeSort(a, tempArray, first, mid); // sort left
                mergeSort(a, tempArray, mid+1, last); // sort right
                
                merge(a, tempArray, first, mid, last); // merge halves
            }// end if
                     
        }// end mergeSort
    
        public static int binarySearch(int[] array, int element, int diff){
            int low = 0;
            int high = array.length-1;
            int size = array.length;
            
            // Binary search target is current array (element + diff)
            while(high>=low)
            {
                int mid = (high+low)/2;
                
                if(array[mid] == element + diff)
                {
                    return array[mid]; // target found, return mid
                }
                else if ((element + diff) < array[mid])
                {
                    high = mid - 1; // readjust mid to left half 
                }
                else
                {
                    low = mid + 1; // readjust mid to right half
                }
                          
            }
            return -1; // NOT FOUND
        }
}
// findPairs ALGORITHM END //