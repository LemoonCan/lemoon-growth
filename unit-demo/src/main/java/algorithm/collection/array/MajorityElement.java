package algorithm.collection.array;

import java.util.Arrays;

/**
 * 多数元素
 * https://leetcode-cn.com/problems/majority-element/
 *
 * @author lee
 * @date 2020-07-01
 */
public class MajorityElement {
    public static void main(String[] args) {
        int[] nums = {3,2,3};
        System.out.println(majorityElement(nums));
    }

    /**
     * 摩尔投票
     * 投票法是遇到相同的则票数 + 1，遇到不同的则票数 - 1。
     * 且多数元素的个数> ⌊ n/2 ⌋，其余元素的个数总和<= ⌊ n/2 ⌋
     * 1213112222
     * @param nums
     * @return
     */
    public static int majorityElement(int[] nums) {
        int res = nums[0], count = 1;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] == res) {
                count++;
            } else {
                count--;
                if (count == 0) {
                    res = nums[i];
                    count = 1;
                }
            }
        }
        return res;
    }

    public static int majorityElement2(int[] nums) {
        Arrays.sort(nums);
        return nums[nums.length/2];
    }
}
