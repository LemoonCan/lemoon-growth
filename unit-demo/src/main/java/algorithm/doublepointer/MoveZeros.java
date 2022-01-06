package algorithm.doublepointer;

/**
 * 移动0
 * https://leetcode-cn.com/problems/move-zeroes/
 *
 * 给定一个数组 nums，编写一个函数将所有 0 移动到数组的末尾，同时保持非零元素的相对顺序。
 * 示例:
 * 输入: [0,1,0,3,12]
 * 输出: [1,3,12,0,0]
 *
 * @author lee
 * @date 2021/10/12
 */
public class MoveZeros {
    public void moveZeroes(int[] nums) {
        /**
         * 从左至右遍历非0,与第一个非0交换
         */
        int left = 0;
        /**
         * 右边第一个0
         */
        int right = 0;

        while (left < nums.length) {
            if (nums[left] != 0) {
                int temp = nums[left];
                nums[left] = nums[right];
                nums[right] = temp;
                right++;
            }
            left++;
        }
    }

    public void moveZeroes2(int[] nums) {
        int count = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0) {
                count++;
            } else {
                nums[i - count] = nums[i];
            }
        }
        for (int i = 1; i <= count; i++) {
            nums[nums.length - i] = 0;
        }
    }

    public void moveZeroes3(int[] nums) {
        int left = -1;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0) {
                if (left == -1) {
                    left = i;
                }
            } else {
                if (left != -1) {
                    int temp = nums[i];
                    nums[i] = nums[left];
                    nums[left++] = temp;
                }
            }
        }
    }
}
