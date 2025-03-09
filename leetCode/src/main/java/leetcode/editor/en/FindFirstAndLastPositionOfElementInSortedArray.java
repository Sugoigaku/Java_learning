/*
 * @lc app=leetcode id=34 lang=java
 * @lcpr version=30100
 *
 * [34] Find First and Last Position of Element in Sorted Array
 */

package leetcode.editor.en;

import java.util.*;
import leetcode.editor.common.*;

public class FindFirstAndLastPositionOfElementInSortedArray {

    // @lc code=start
    class Solution {
        public int[] searchRange(int[] nums, int target) {
            int leftBound = leftBound(nums, target);
            int rightBound = rightBound(nums, target);
            int[] result = {leftBound,rightBound};
            return result;
        }

        public int leftBound(int[] nums, int target){
            int left=0, right = nums.length -1;
            int mid;
            
            while (left <= right) {
                mid = left + (right - left)/2;
                if (nums[mid] == target) {
                    right = mid - 1 ;
                } else if(nums[mid] < target){
                    left = mid + 1;
                } else if (nums[mid] > target) {
                    right = mid - 1;
                }
            }

            if (left < 0 || left >= nums.length ) {
                return -1;
            }

            return nums[left] == target?left:-1;
        }

        public int rightBound(int[] nums, int target){
            int left=0, right = nums.length -1;
            int mid;
            
            while (left <= right) {
                mid = left + (right - left)/2;
                if (nums[mid] == target) {
                    left = mid + 1 ;
                } else if(nums[mid] < target){
                    left = mid + 1;
                } else if (nums[mid] > target) {
                    right = mid - 1;
                }
            }

            if (left -1 < 0 || left - 1 >= nums.length ) {
                return -1;
            }

            return nums[left -1] == target?(left -1):-1;
        }
    }
    // @lc code=end
    
    public static void main(String[] args) {
        Solution solution = new FindFirstAndLastPositionOfElementInSortedArray().new Solution();
        // put your test code here
        int[] nums = {1};
        int target = 0;
        for (int i : solution.searchRange(nums, target)) {
            System.out.println(i);
        }
    }
}



/*
// @lcpr case=start
// [5,7,7,8,8,10]\n8\n
// @lcpr case=end

// @lcpr case=start
// [5,7,7,8,8,10]\n6\n
// @lcpr case=end

// @lcpr case=start
// []\n0\n
// @lcpr case=end

 */

