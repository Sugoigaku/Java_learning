/*
 * @lc app=leetcode id=875 lang=java
 * @lcpr version=30100
 *
 * [875] Koko Eating Bananas
 */

package leetcode.editor.en;

import java.util.*;
import leetcode.editor.common.*;

public class KokoEatingBananas {

    // @lc code=start
    class Solution {
        public int minEatingSpeed(int[] piles, int h) {
            // target=h, x=最小k
            // 吃完所有香蕉的时间
            int left = 1;
            int right = 1000000000;
            int mid;

            while (left <= right) {
                mid = left + (right - left) / 2;
                if (timeToEatBanana(piles, mid) > h) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }

            return left;
        }

        // x=1时，piles[i]*piles.length = 10^4 * 10^9 = 10^13,可能超出int范围
        public long timeToEatBanana(int[] piles, int x) {
            long hours = 0;
            for (int i = 0; i < piles.length; i++) {
                hours += piles[i] / x;
                if (piles[i] % x > 0) {
                    hours++;
                }
            }
            return hours;
        }
    }
    // @lc code=end

    public static void main(String[] args) {
        Solution solution = new KokoEatingBananas().new Solution();
        // put your test code here
        int[] piles = {2,2};
        int h = 2;
        System.out.println(solution.minEatingSpeed(piles, h));
    }
}

/*
 * // @lcpr case=start
 * // [3,6,7,11]\n8\n
 * // @lcpr case=end
 * 
 * // @lcpr case=start
 * // [30,11,23,4,20]\n5\n
 * // @lcpr case=end
 * 
 * // @lcpr case=start
 * // [30,11,23,4,20]\n6\n
 * // @lcpr case=end
 * 
 */
