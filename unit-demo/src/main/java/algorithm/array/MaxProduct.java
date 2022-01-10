package algorithm.array;

/**
 * https://leetcode-cn.com/problems/maximum-product-subarray/
 * 找出数组中乘积最大的连续子数组（该子数组中至少包含一个数字），并返回该子数组所对应的乘积
 * @author lee
 * @date 2020-07-01
 *
 */
public class MaxProduct {
    public static void main(String[] args) {
        int[] nums = {2, -1, 3, 2, 0, -4, 6, -5};
        System.out.println(maxProduct(nums));
        System.out.println(maxProduct2(nums));
        System.out.println(maxProduct3(nums));
    }

    /**
     * 正数、负数、0
     * 2,-1,3,2,-4,6,-5
     * 2,-1,3,2,-4,6,-5,-2
     * 2,-1,3
     * 2,-1,0,3
     * -2,0,-1
     * -2,0,0,-1
     * 假如n个数
     * 1.全是正数，全部相乘
     * 2.存在负数
     * 2.1负数总个数为双，全部相乘；
     * 2.2负数总个数为单，max(所有数的乘积/左侧第一个负数相乘的数,所有数的乘积/右侧第一个负数相乘的数)
     * (①对负数计数，记录下第一个负数和最后一个负数的位置)
     * 3.存在0，左、右、0比较取最大值
     * ...0...0...
     * 由于2.2特性，取max([前到后遍历相乘的最大值],[后到前遍历相乘的最大值])
     * <p>
     * 方法：
     * 1.从前往后遍历相乘，取最大值；
     * 遇0，max=1。
     * 2.从后往前遍历相乘，取最大值；
     * 遇0，max=1。
     */
    public static int maxProduct(int[] nums) {
        int max = 1;
        int res = nums[0];
        int i = 0;
        for (; i < nums.length; i++) {
            max *= nums[i];
            res = Math.max(max, res);
            if (max == 0) {
                max = 1;
            }
        }
        max = 1;
        for (i = nums.length - 1; i >= 0; i--) {
            max *= nums[i];
            res = Math.max(max, res);
            if (max == 0) {
                max = 1;
            }
        }
        return res;
    }

    /**
     * -1 2 -3 -4 0 8
     * -1 2  6  24
     * -1 -2 -6 -24
     *
     *
     * 1 2 3 -2 -4  0 4 -4
     * 1 2 6 -2  48 0 4 -4
     * 1 2 3 -12 -4 0 0 -16
     *
     *
     * 2, -1, 3, 2, -4, 6, -5
     * 2  -1  3  6   48  288  -5
     * 2  -2  -6 -12 -24 -144 144*5=720
     *
     * 动态规划
     * 维护连续子数组包含当前值的最大值和最小值
     * 正 x 负 = 负
     * 负 x 负 = 正
     * 正 x 正 = 正
     *
     * 负数遇到负数是可能翻身的
     */
    public static int maxProduct2(int[] nums) {
        int res = nums[0];
        int max = nums[0];
        int min = nums[0];
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] > 0) {
                if (max <= 0) {
                    max = nums[i];
                } else {
                    max *= nums[i];
                }

                if (min <= 0) {
                    min = min * nums[i];
                } else {
                    min = nums[i];
                }
            } else {
                int temp = max;
                if (min <= 0) {
                    max = min * nums[i];
                } else {
                    max = nums[i];
                }
                if (temp > 0) {
                    min = temp * nums[i];
                } else {
                    min = nums[i];
                }
            }
            res = Math.max(max, res);
        }
        return res;
    }

    /**
     * 动态规划
     * 维护连续子数组包含当前值的最大值和最小值
     *
     * @param nums
     * @return
     */
    public static int maxProduct3(int[] nums) {
        int maxF = nums[0], minF = nums[0], ans = nums[0];
        int length = nums.length;
        for (int i = 1; i < length; ++i) {
            int mx = maxF, mn = minF;
            maxF = Math.max(mx * nums[i], Math.max(nums[i], mn * nums[i]));
            minF = Math.min(mn * nums[i], Math.min(nums[i], mx * nums[i]));
            ans = Math.max(maxF, ans);
        }
        return ans;
    }
}
