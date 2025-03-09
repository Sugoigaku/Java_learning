/*
 * @lc app=leetcode id=410 lang=java
 * @lcpr version=30100
 *
 * [410] Split Array Largest Sum
 */

package leetcode.editor.en;

import java.util.*;
import leetcode.editor.common.*;

public class SplitArrayLargestSum {

    // @lc code=start
    class Solution {
        public int splitArray(int[] nums, int k) {
            // x = largest sum
            // target = k
            // f(x): base on x, how many subarrays can be splited
            // left = largest num of array
            // right = sum(nums) 
            int left = 0, right = 0;
            for (int num : nums) {
                left = Math.max(left, num);
                right += num;
            }

            while (left <= right) {
                int mid = left + (right - left)/2;
                if (numOfSubarrays(nums, mid) <= k) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            }

            return left;
        }

        public int numOfSubarrays(int[] nums, int x) {
            int i = 0;
            int numOfSubarrays = 0;
            
            while (i < nums.length) {
                int cap = x;
                while (i < nums.length && cap >= nums[i]) {
                    cap -= nums[i];
                    i++;
                }
                numOfSubarrays++;
            }

            return numOfSubarrays;
        }
    }
    // @lc code=end
    
    public static void main(String[] args) {
        Solution solution = new SplitArrayLargestSum().new Solution();
        // put your test code here
        int[] nums = {1,2,3,4,5};
        // int x = 9;
        int k = 2;
        // System.out.println(solution.numOfSubarrays(nums, x));
        System.out.println(solution.splitArray(nums, k));
    }
}



/*
// @lcpr case=start
// [7,2,5,10,8]\n2\n
// @lcpr case=end

// @lcpr case=start
// [1,2,3,4,5]\n2\n
// @lcpr case=end

 */

