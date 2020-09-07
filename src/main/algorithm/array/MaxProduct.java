package main.algorithm.array;

/**
 * @author lee
 * @date 2020-07-01
 * <p>
 * 找出数组中乘积最大的连续子数组（该子数组中至少包含一个数字），并返回该子数组所对应的乘积
 */
public class MaxProduct {
    public static void main(String[] args) {
        int[] nums = {2, -1, 3, 2, -4, 6, -5};
        System.out.println(maxProduct(nums));
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
     *
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
}
