package algorithm.shuffle;

import java.util.Random;

/**
 * 打乱数组
 * https://leetcode-cn.com/problems/shuffle-an-array/
 *
 * @author lee
 * @date 2021/12/30
 */
public class Solution {
    int[] nums;
    int[] origin;

    public Solution(int[] nums) {
        this.nums = nums;
        int[] origin = new int[nums.length];
        System.arraycopy(nums, 0, origin, 0, nums.length);
    }

    public int[] reset() {
        System.arraycopy(origin, 0, nums, 0, origin.length);
        return nums;
    }

    /**
     * 第一个数从n个数里筛选 概率是1/n
     * 第二个数是除去第一个数后，从n-1个数里筛选 概率是 n-1/n * 1/n-1 = 1/n
     * 第三个数是除去第一、二个数后，从n-2个数里筛选 概率是 n-1/n * n-2/n-1 * 1/n-2 = 1/n
     * ...
     *
     * 所以每个位置的选取数的概率都是1/n
     *
     * @return
     */
    public int[] shuffle() {
        Random random = new Random();
        for (int i = 0; i < nums.length; i++) {
            int j = i + random.nextInt(nums.length - i);
            int temp = nums[j];
            nums[j] = nums[i];
            nums[i] = temp;
        }
        return nums;
    }

}
