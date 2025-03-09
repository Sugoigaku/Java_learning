/*
 * @lc app=leetcode id=1011 lang=java
 * @lcpr version=30100
 *
 * [1011] Capacity To Ship Packages Within D Days
 */

package leetcode.editor.en;

import java.util.*;
import leetcode.editor.common.*;

public class CapacityToShipPackagesWithinDDays {

    // @lc code=start
    class Solution {
        public int shipWithinDays(int[] weights, int days) {
            // x=装货量，target=days
            int left = 0, right = 0;

            for (int w : weights) {
                left = Math.max(left, w);
                right += w;
            }

            while (left <= right) {
                int mid = left + (right - left)/2;
                if (shipDays(weights, mid) <= days) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            }

            return left;
        }

        public int shipDays(int[] weights, int x) {
            int days = 0;
            for (int i = 0; i < weights.length;) {
                int cap = x;
                while (i < weights.length && cap >= weights[i]) {
                    cap -= weights[i];
                    i++;
                }
                days++;
            }
            return days;
        }
    }
    // @lc code=end
    
    public static void main(String[] args) {
        Solution solution = new CapacityToShipPackagesWithinDDays().new Solution();
        // put your test code here
        int[] weights ={1,2,3,4,5,6,7,8,9,10};
        int days = 5;
        // System.out.println(solution.shipDays(weights, days));
        System.out.println(solution.shipWithinDays(weights, days));
    }
}



/*
// @lcpr case=start
// [1,2,3,4,5,6,7,8,9,10]\n5\n
// @lcpr case=end

// @lcpr case=start
// [3,2,2,4,1,4]\n3\n
// @lcpr case=end

// @lcpr case=start
// [1,2,3,1,1]\n4\n
// @lcpr case=end

 */

