package algorithm.dynamicprograming;

/**
 * 等差数列划分
 * https://leetcode-cn.com/problems/arithmetic-slices/
 *
 * @author lee
 * @since 2021/12/16
 */
public class NumberOfArithmeticSlices {
    public static void main(String[] args) {
        int[] nums = {1,2,3,4};
        NumberOfArithmeticSlices demo = new NumberOfArithmeticSlices();
        System.out.println(demo.numberOfArithmeticSlices(nums));
    }

    /**
     * 1.如果连续3个数是等差数列，继续查找其后数字是否满足当前等差条件；(3个数字有1个子串；每增加1个数字相比于[前序增加一个数字增加的子串数]+1)
     * 举例：
     * 3个数字 ([1,2,3])
     * 4个数字 ([1,2,3,4] [2,3,4])
     * 5个数字 ([1,2,3,4,5] [2,3,4,5] [3,4,5])
     * 6个数字 ([1,2,3,4,5,6] [2,3,4,5,6] [3,4,5,6] [4,5,6])
     * 下一个检测起始数为满足当前满足等差数列数据的最后一个数
     *
     * 2.如果连续3个数不是等差数列，下一个检测起始数为当前检测起始数+1
     *
     * @param nums
     * @return
     */
    public int numberOfArithmeticSlices(int[] nums) {
        int origin = 0;
        int count = 0;
        while ((origin + 2) < nums.length) {
            int difference = nums[origin + 1] - nums[origin];
            int rm = origin + 2;
            int temp = 0;
            while (rm < nums.length && nums[rm] - nums[rm - 1] == difference) {
                temp++;
                count += temp;
                rm++;
            }
            origin = rm-1;
        }
        return count;
    }
}
