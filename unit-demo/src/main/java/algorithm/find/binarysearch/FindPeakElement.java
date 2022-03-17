package algorithm.find.binarysearch;

/**
 * 找到峰值元素
 * https://leetcode-cn.com/problems/find-peak-element/
 *
 * @author lee
 * @date 2021/11/6
 */
public class FindPeakElement {
    public static void main(String[] args) {
        int[] nums = {1};
        System.out.println(findPeakElement(nums));

        int[] nums2 = {2,3,1,0,3,4,4};
        System.out.println(findPeakElement2(nums2));
    }

    public static int findPeakElement(int[] nums) {
        int i = 0;
        while (i < nums.length) {
            if (((i - 1) == -1 || nums[i] > nums[i - 1])
                    && ((i + 1) == nums.length || nums[i] > nums[i + 1])) {
                return i;
            }

            if (nums[i] < nums[i + 1]) {
                i++;
            } else {
                i = i + 2;
            }
        }
        return -1;
    }

    /**
     * 1.相邻值不相等
     * 2.对于任意数组而言，一定存在峰值
     * 3.二分决定的向左或向右不会错过峰值
     */
    public static int findPeakElement2(int[] nums) {
        int left = 0, right = nums.length - 1, mid;
        while (left <= right) {
            mid = (left + right) / 2;
            int ln = mid - 1;
            int rn = mid + 1;
            if ((ln == -1 || nums[ln] < nums[mid])
                    && (rn == nums.length || nums[mid] > nums[rn])) {
                return mid;
            }
            if (nums[rn] > nums[mid]) {
                left = rn;
            } else {
                right = ln;
            }
        }
        return -1;
    }
}
