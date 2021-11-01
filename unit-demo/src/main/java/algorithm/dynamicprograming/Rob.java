package algorithm.dynamicprograming;

/**
 * 打家劫舍
 * https://leetcode-cn.com/problems/house-robber/
 * @author lee
 * @date 2021/11/1
 */
public class Rob {
    public static void main(String[] args) {
        int[] nums = {0, 0, 0};
        Rob r = new Rob();
        System.out.println(r.rob(nums));
    }

    public int rob(int[] nums) {
        Integer[] maxRecord = new Integer[nums.length];
        rob(nums, 0, maxRecord);
        return maxRecord[0];
    }

    void rob(int[] nums, int index, Integer[] maxRecord) {
        if (index >= nums.length) {
            return;
        }
        if (maxRecord[index] != null) {
            return;
        }
        if (index + 1 == nums.length) {
            maxRecord[index] = nums[index];
            return;
        }
        if (index + 2 == nums.length) {
            maxRecord[index + 1] = nums[index + 1];
            maxRecord[index] = Math.max(nums[index], maxRecord[index + 1]);
            return;
        }
        if (index + 3 == nums.length) {
            maxRecord[index + 2] = nums[index + 2];
            maxRecord[index + 1] = Math.max(nums[index+1], maxRecord[index + 2]);
            maxRecord[index] = Math.max(nums[index] + maxRecord[index + 2], maxRecord[index + 1]);
            return;
        }

        rob(nums, index + 3, maxRecord);
        rob(nums, index + 2, maxRecord);
        rob(nums, index + 1, maxRecord);
        maxRecord[index] = max(nums[index] + maxRecord[index + 2], nums[index] + maxRecord[index + 3], maxRecord[index + 1]);
    }

    int max(int a, int b, int c) {
        int max = a;
        if (b > max) {
            max = b;
        }
        if (c > max) {
            max = c;
        }
        return max;
    }
}
