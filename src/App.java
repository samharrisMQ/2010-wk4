public class App {
    public static void main(String[] args) throws Exception {
        
        // Q1
        int[] nums1 = {-1, 0, 3, 5, 9, 12};
        int target1 = 9;
        System.out.println((search(nums1, target1)));

        //Q2
        System.out.println(intsqrt(10));

        //Q4
        int[] nums4 = {27, 29, 35, 42, 5, 15};
        int k = 4;
        System.out.println(swift(nums4, k));

        //Q5
        int[] nums5 = {27, 29, 35, 42, 5, 105, 15, 17, 22, 50, 200};
        int element = 105;
        System.out.println(findIndex(nums5, element));
    }
/* Q1 

// Given sorted array of ints, and a target value
// Implement binary search algorithm, to find the index of the target value in the array
// Return -1 if target not found
// Don't iterate over all elements to find [O(n) solution]
// Implement a O(logn) solution
// Array is sorted, so don't need to iterate over its entirety

//binary search
//arr = {-1, 0, 3, 5, 9, 12}
//key = 9

//1. find mid = (low+high)/2 = (0 + 5) / 2 = 3
//2. key greater than mid, move search space to right
//      new ss = {5,9,12}
//3. key = mid, stop algorithm

//Implementing iteratively:
//base case: if(target = mid), return mid
//mid = (low + high)/2 = (0 + arr.length-1)/2  
//right side of mid = arr[mid] +1
//left side of mid = arr[mid] -1
*/
     public static int search(int[] arr, int target) {
        // ITERATIVE
        int mid = 0;
        int low = 0;
        int high = arr.length-1;
// a
        while(low <= high) {
            // find mid
            mid = (low+high)/2;

            //check mid = target
            if(arr[mid] == target)
                return mid;

            //if key greater, search right
            if(arr[mid] < target)
                low = mid + 1;

            //if key less, search left
            else
                high = mid - 1;
        }

        return -1;
    } 
/* 
         //RECURSIVE
        int mid;
        int low = 0;
        int high = arr.length-1;

        mid = (low+high)/2;

        //base case: key = mid
        if(target == mid) {
            return mid;
        }


        //guaranteed: key != mid
        //check key > mid, if so, move search space to right
        if(target>mid) {
            search(arr, target); // how to modify search space for next recursive call?
        }

        //guaranteed: key < mid
        else {
            search(arr,target); // how to modify search space for next recursive call?
        }

        //guaranteed: search space exhausted
        return -1; 
    } 
*/

/* Q2
The integer square root of an integer x is defined to be the integer m such
that , m^2 ≤ x < (m + 1)^2
I.e. the integer closest to the actual square root.
Write a function to find the integer square root of an integer x ≥ 0 using a
binary search strategy.

Positive int x
square root of int x, is the int m thats closest to the square root
take square root of x, remove decimal
e.g. 1
    x = 10
    3^2 = 9
    4^2 = 16
    9 <= 10 < 16
    m = 3
    Integer square root of 10 is 3

e.g. 2
    x = 25
    5^2 = 25
    6^2 = 36
    25 <= 25 < 36
    m = 5
    Integer square root of 25 is 5

Find the largest number m thats square is <= x
*/
public static int intsqrt(int x) {
    int low = 0;
    int high = x;
    int mid = 0;
    int square = 0;
    int m = 0;

    while(low <= high) {
        mid = (low+high)/2;
        square = mid*mid;

        if(square == x)
            return mid;
        if(square < x) {
            low = mid + 1;
            m = mid;
        } else {
            high = mid - 1;
        }

    }
    return m;
}

/* Q4
Find largest number in A using
O(1)
O(n)
O(log n)

Array is originally in ascending order
Each shift changes the split between largest and smallest
k=0 5, 15, 27, 29, 35, 42 - smallest = arr[0] largest = arr[5]
k=2 35, 42, 5, 15, 27, 29 - smallest = arr[2] largest = arr[1]
k=4 27, 29, 35, 42, 5, 15 - smallest = arr[4] largest = arr[3]
smallest is always 1 to the right, or 5 to the left of largest
so, largest number = k-1

e.g
    {35, 42, 5, 15, 27, 29}
    k = 2
    k-1 = 1
    arr[1] = 42, largest

    {27, 29, 35, 42, 5, 15}
    k=4
    k-1 = 3
    arr[3] = 42, largest
*/

public static int swift(int[] arr, int k) {
    /* O(1)
    int largest = arr[k-1];
    return largest;
    */

    /* O(n) 
    int largest = 0;

    for(int i = 0; i<arr.length; i++) {
        if(arr[i] > largest)
            largest = arr[i];
    }
    return largest;
    */

    /* O(log n) */
    int low = 0;
    int high = arr.length - 1;
    int mid = 0;

    while(low <= high) {
        mid = (low+high)/2;

        if(arr[mid] > arr[high]) // largest is in right half
            low = mid;

        else { // largest is in left half
            high = mid - 1;
        }
    }

    //low == high
    return arr[low]; 
    
}   

/* Q5
Given a vector with n integers 

Write a divide-and-conquer algorithm that
returns the position i in which a given element x is

Assume x appears at most
once in the vector, i.e. return -1 if x doesn’t appear

Find index i of element x
Return -1 if x not found
Assumed unsorted (use recursion) 
*/

// Wrapper function: user calls this
    public static int findIndex(int[] arr, int x) {
        return findIndexRec(arr, x, 0, arr.length - 1);
    }

    // Recursive divide-and-conquer function
    private static int findIndexRec(int[] arr, int x, int low, int high) {
        // Base case: empty subarray
        if (low > high) {
            return -1;
        }

        // Base case: single element
        if (low == high) {
            if (arr[low] == x) {
                return low;
            } else {
                return -1;
            }
        }

        // Divide the array
        int mid = (low + high) / 2;

        // Recursively search the left half
        int leftResult = findIndexRec(arr, x, low, mid);
        if (leftResult != -1) {
            return leftResult; // found in left half
        }

        // Recursively search the right half
        return findIndexRec(arr, x, mid + 1, high);
    }
}
