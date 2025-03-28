package algorithm.doublepointer;

import java.util.Arrays;

/**
 * @author lee
 * @since 2021/10/10
 *
 * 升序排序的整数数组nums，
 * 平方后仍按按递增排序。
 *
 * 输入：nums = [-4,-1,0,3,10]
 * 输出：[0,1,9,16,100]
 */
public class SortedSquares {
    public static void main(String[] args) {
        int[] nums = {-4, -1, 0, 3};
        System.out.println(Arrays.toString(insertionSortedSquares(nums)));
        int[] nums2 = {-7,-3,2,3,11};
        System.out.println(Arrays.toString(insertionSortedSquares2(nums2)));
        int[] nums3 = {-4, -1, 0, 3};
        System.out.println(Arrays.toString(bothEndsSortedSquares(nums3)));
    }

    public static int[] insertionSortedSquares(int[] nums) {
        nums[0] *= nums[0];
        int square = nums.length;

        while (--square > 0) {
            nums[square] *= nums[square];
            int temp = nums[square];
            if (nums[0] > nums[square]) {
                temp = nums[0];
                nums[0] = nums[square];
                nums[square] = temp;
            }
            int i = square + 1;
            while (i < nums.length && nums[square] > nums[i]) {
                i++;
            }
            --i;
            for (int j = square; j < i; j++) {
                nums[j] = nums[j + 1];
            }
            nums[i] = temp;
        }

        return nums;
    }

    /**
     * =======================最快=======================
     * 时间复杂度 O(n)
     * 空间复杂度 O(2n)
     * @param nums
     * @return
     */
    public static int[] bothEndsSortedSquares(int[] nums) {
        int[] result = new int[nums.length];
        int left = 0, right = nums.length - 1;
        int index = right;
        while (left <= right) {
            int leftProduct = nums[left] * nums[left];
            int rightProduct = nums[right] * nums[right];
            if (leftProduct > rightProduct) {
                result[index--] = leftProduct;
                left++;
            } else {
                result[index--] = rightProduct;
                right--;
            }
        }

        return result;
    }

    public static int[] insertionSortedSquares2(int[] nums) {
        int sortedSquare = nums.length;
        while (--sortedSquare >= 0) {
            int left = nums[0] * nums[0];
            int right = nums[sortedSquare] * nums[sortedSquare];
            if (left > right) {
                for (int i = 0; i < sortedSquare; i++) {
                    nums[i] = nums[i + 1];
                }
                nums[sortedSquare] = left;
            } else {
                nums[sortedSquare] = right;
            }
        }
        return nums;
    }
}
