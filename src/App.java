public class App {
    public static void main(String[] args) throws Exception {
        int[] nums1 = {-1, 0, 3, 5, 9, 12};
        int target1 = 9;

    System.out.println((search(nums1, target1)));

    }

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

        /* //RECURSIVE
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
        return -1; */
    } 
}
