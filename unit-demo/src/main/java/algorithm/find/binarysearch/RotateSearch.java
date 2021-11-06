package algorithm.find.binarysearch;

/**
 * 搜索旋转排序数组
 * https://leetcode-cn.com/problems/search-in-rotated-sorted-array/submissions/
 * @author lee
 * @date 2021/11/5
 */
public class RotateSearch {
    /**
     * 必有一段有序
     *
     * @param nums
     * @param target
     * @return
     */
    public int search(int[] nums, int target) {
        int left = 0, right = nums.length - 1, middle;
        while (left <= right) {
            middle = (left + right) / 2;
            if (nums[middle] == target) {
                return middle;
            }
            //需考虑值等于 right或者 left的情况
            if (nums[middle] >= nums[0]) {
                //左边有序
                if (target >= nums[left] && target<nums[middle]) {
                    right = middle - 1;
                } else {
                    left = middle + 1;
                }
            } else {
                //右边有序
                if (target>nums[middle] && target <= nums[right]) {
                    left = middle + 1;
                } else {
                    right = middle - 1;
                }
            }
        }
        return -1;
    }
}
