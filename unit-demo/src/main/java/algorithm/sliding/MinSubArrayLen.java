package algorithm.sliding;

/**
 * 长度最小的子数组
 * https://leetcode-cn.com/problems/minimum-size-subarray-sum/
 *
 * @author lee
 * @date 2021/11/12
 */
public class MinSubArrayLen {
    public int minSubArrayLen(int target, int[] nums) {
        int left = 0, right = 0, min = Integer.MAX_VALUE;
        int sum = 0;
        while (right < nums.length) {
            sum += nums[right];
            while (sum >= target) {
                min = Math.min(min, right - left + 1);
                sum -= nums[left];
                left++;
            }
            right++;
        }
        return min == Integer.MAX_VALUE ? 0 : min;
    }
}
