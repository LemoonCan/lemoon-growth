package algorithm.find;

/**
 * 在排序数组中查找元素的第一个和最后一个位置
 * https://leetcode-cn.com/problems/find-first-and-last-position-of-element-in-sorted-array/
 * @author lee
 * @date 2021/11/5
 */
public class SearchRange {
    public static void main(String[] args) {

    }

    public int[] searchRange(int[] nums, int target) {
        int left = 0, right = nums.length - 1,
                middle;
        int[] result = new int[]{-1,-1};
        while (left <= right) {
            middle = (left + right) / 2;
            if (nums[middle] == target) {
                result[0] = middle;
                result[1] = middle;
                int lm = middle-1;
                while (lm >= 0 && nums[lm] == target) {
                    result[0] = lm--;
                }
                int rm = middle+1;
                while (rm <nums.length && nums[rm] == target){
                    result[1] = rm++;
                }
                break;
            }
            if (nums[middle] < target) {
                left = middle + 1;
            } else {
                right = middle - 1;
            }
        }

        return result;
    }
}
