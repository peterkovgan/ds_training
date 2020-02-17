package com.company;

/**
 *
 * Approach 2: One pass
 * Instead of two passes, all this could be done in one pass. Kudos for this solution go to @haoyangfan.
 *
 * Algorithm
 *
 * Initiate start to be equal to 0, and end to be equal to n - 1.
 *
 * Perform standard binary search. While start <= end:
 *
 * Take an index in the middle mid as a pivot.
 *
 * If nums[mid] == target, the job is done, return mid.
 *
 * Now there could be two situations:
 *
 * Pivot element is larger than the first element in the array,
 * i.e. the part of array from the first element to the pivot one is non-rotated.
 *
 * If the target is in that non-rotated part as well: go left: end = mid - 1.
 *
 * Otherwise: go right: start = mid + 1.
 *
 * Pivot element is smaller than the first element of the array, i.e. the rotation index
 * is somewhere between 0 and mid. That means that the part of array from the pivot element to the last one is non-rotated.
 *
 * If target is in that non-rotated part as well: go right: end = mid + 1.
 *
 * Otherwise: go left: start = mid - 1.
 *
 * We're here because the target is not found. Return -1.
 *
 *
 */
public class SearchInRotatedSortedArrayOnePass {

    public int search(int[] nums, int target) {
        int start = 0, end = nums.length - 1;

        while (start <= end) {

            int mid = start + (end - start) / 2;

            if (nums[mid] == target) return mid;

            else if (nums[mid] >= nums[start]) {
                if (target >= nums[start] && target < nums[mid]) end = mid - 1;
                else start = mid + 1;
            }
            else {
                if (target <= nums[end] && target > nums[mid]) start = mid + 1;
                else end = mid - 1;
            }
        }
        return -1;
    }

}
