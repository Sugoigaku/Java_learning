/*
 * @lc app=leetcode id=704 lang=java
 * @lcpr version=30100
 *
 * [704] Binary Search
 */

package leetcode.editor.en;

import java.util.*;
import leetcode.editor.common.*;

public class BinarySearch {

    // @lc code=start
    class Solution {
        public int search(int[] nums, int target) {
            int left = 0;
            int right = nums.length -1;            
            
            //此时循环结束的条件为 left = rihgt+1
            //所以left=right的情况也能考虑到
            while (left <= right) {
                //防止left和right相加过大，溢出int范围
                int mid = left + (right-left)/2;
                if (nums[mid] == target) {
                    return mid;
                } else if(nums[mid] < target){
                    left = mid + 1;
                } else if(nums[mid] > target){
                    right = mid - 1;
                }
            }

            return -1;
        }
    }
    // @lc code=end
    
    public static void main(String[] args) {
        Solution solution = new BinarySearch().new Solution();
        // put your test code here
        int[] nums = {-1,0,3,5,9,12};
        int target = 5;

        System.out.println(solution.search(nums, target));
    }
}



/*
// @lcpr case=start
// [-1,0,3,5,9,12]\n9\n
// @lcpr case=end

// @lcpr case=start
// [-1,0,3,5,9,12]\n2\n
// @lcpr case=end

 */

