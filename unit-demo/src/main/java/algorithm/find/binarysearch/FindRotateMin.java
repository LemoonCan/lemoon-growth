package algorithm.find.binarysearch;

/**
 * 寻找旋转排序数组中的最小值
 * https://leetcode-cn.com/problems/find-minimum-in-rotated-sorted-array/
 *
 * @author lee
 * @since 2021/11/6
 */
public class FindRotateMin {
    /**
     * l<=m<=r l
     * l>m r=m
     * r<m l=m+1
     *
     * @param nums
     * @return
     */
    public int findMin(int[] nums) {
        int left = 0, right = nums.length - 1, middle;
        while (left <= right) {
            middle = (left + right) / 2;
            if (nums[left] <= nums[middle] && nums[middle] <= nums[right]) {
                return nums[left];
            }
            if (nums[left] > nums[middle]) {
                right = middle;
            } else {
                left = middle + 1;
            }
        }
        return -1;
    }
}
