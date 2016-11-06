# difference-pairs
[Java] Implementation of merge sort and binary search algorithms to efficiently find all pairs of numbers in a given input array which share the input difference.

* Files:
DifferencePairs.java : Application/driver, algorithm utiliing both a mergesort and binary search for efficiency
Pair.java : Simple Pair object definition class
README.md : Big-Oh algorithm efficiency analysis

* Big-Oh Analysis of findPairs method

Pseudo Code Explaining findPairs Method:

array = mergeSort(array); ←Merge Sort and re-store passed in array  O(nlogn)

for (each element in sorted array)
{
	binarySearch(element + difference); ← n (loop) * logn(binary search) : nlogn
}


I have found that my algorithm for finding the difference pairs will be roughly that of merge sort + binary search. Since I implemented the standard merge sort provided to us in lecture, it will run at O(nlogn) time efficiency. The next step in my algorithm involves executing a binary search n times, where n is the number of elements in the entered array. A binary search typically runs at O(logn) time efficiency, however since the number of times my binary search is executed is dependent on the for loop iterating the number of elements in my array this portion becomes O(n * logn) or O(nlogn). This gives me a final time complexity of:

O(nlogn + nlogn) = O(2nlogn) time efficiency
