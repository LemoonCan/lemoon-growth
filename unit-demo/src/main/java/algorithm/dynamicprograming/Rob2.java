package algorithm.dynamicprograming;

/**
 * 打家劫舍2
 * https://leetcode-cn.com/problems/house-robber-ii/
 * (官方答案是从前往后偷)
 *
 * @author lee
 * @date 2021/12/11
 */
public class Rob2 {
    public static void main(String[] args) {
        int[] nums = {1,2,3,1};
        Rob2 demo = new Rob2();
        System.out.println(demo.rob(nums));
    }

    /**
     * 从后往前偷
     * 比较 0-length-1范围内 和 1-length内的最大值
     *
     * @param nums
     * @return
     */
    public int rob(int[] nums) {
        if(nums.length==1){
            return nums[0];
        }
        Integer[] maxRecord0 = new Integer[nums.length];
        Integer[] maxRecord1 = new Integer[nums.length];
        rob(nums, 0, nums.length - 1, maxRecord0);
        rob(nums, 1, nums.length, maxRecord1);
        return maxRecord0[0] > maxRecord1[1] ? maxRecord0[0] : maxRecord1[1];
    }

    void rob(int[] nums, int index, int max, Integer[] maxRecord) {
        if (index >= max) {
            return;
        }
        if (maxRecord[index] != null) {
            return;
        }
        if (index + 1 == max) {
            maxRecord[index] = nums[index];
            return;
        }
        if (index + 2 == max) {
            maxRecord[index + 1] = nums[index + 1];
            maxRecord[index] = Math.max(nums[index], maxRecord[index + 1]);
            return;
        }
        if (index + 3 == max) {
            maxRecord[index + 2] = nums[index + 2];
            maxRecord[index + 1] = Math.max(nums[index + 1], maxRecord[index + 2]);
            maxRecord[index] = Math.max(nums[index] + maxRecord[index + 2], maxRecord[index + 1]);
            return;
        }

        rob(nums, index + 3, max, maxRecord);
        rob(nums, index + 2, max, maxRecord);
        rob(nums, index + 1, max, maxRecord);
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
