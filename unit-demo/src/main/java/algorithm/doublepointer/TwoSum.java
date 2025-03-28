package algorithm.doublepointer;

/**
 * 递增数组，从数组中找出两个数满足相加之和等于target
 * 对应唯一答案
 * <p>
 * 输入：numbers = [2,7,11,15], target = 9
 * 输出：[1,2]
 *
 * @author lee
 * @since 2021/10/12
 */
public class TwoSum {
    public int[] twoSum(int[] numbers, int target) {
        int left = 0;
        int right = numbers.length - 1;

        while (left < right) {
            int sum = numbers[left] + numbers[right];
            if (sum == target) {
                return new int[]{left + 1, right + 1};
            }
            if (sum > target) {
                right--;
            } else {
                left++;
            }
        }

        return null;
    }
}
