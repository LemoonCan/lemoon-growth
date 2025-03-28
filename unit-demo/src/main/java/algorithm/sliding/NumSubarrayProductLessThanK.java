package algorithm.sliding;

/**
 * 乘积小于K的子数组
 * https://leetcode-cn.com/problems/subarray-product-less-than-k/
 * @author lee
 * @since 2021/11/12
 */
public class NumSubarrayProductLessThanK {
    public static void main(String[] args) {
        int[] nums = {10, 5, 2, 6};
        int k = 100;
        System.out.println(numSubarrayProductLessThanK(nums, k));

        int[] nums2 = {57, 44, 92, 28, 66, 60, 37, 33, 52, 38, 29, 76, 8, 75, 22};
        k = 18;
        System.out.println(numSubarrayProductLessThanK(nums2, k));
    }

    public static int numSubarrayProductLessThanK(int[] nums, int k) {
        int left = 0, right = 0, multiple = 1;
        int count = 0;
        while (left < nums.length && right < nums.length) {
            while (right < nums.length && (multiple * nums[right]) < k) {
                for (int i = left; i <= right; i++) {
                    count++;
                }
                multiple *= nums[right];
                right++;
            }
            if (left != right) {
                multiple = multiple / nums[left];
            } else {
                right++;
            }
            left++;
        }
        return count;
    }

    /**
     * 一直相乘直到找到第一个>=K的右端
     * 再看左端相除之后是否仍>=K
     * 若小于，从 left 到 right的组合都是解
     *
     * 10、5、2、6、7
     * k=100
     * left=0,right=0 ans=1 (10)
     * left=0,rignt=1 ans=2 (10,5) -> (10、5，5)
     * left=0,right=2 ans=2 (5,2) -> (5、2，2)
     * left=1,right=3 ans=3 (5,2,6) -> (5、2、6，2、6，6)
     *
     * @param nums
     * @param k
     * @return
     */
    public static int numSubarrayProductLessThanK2(int[] nums, int k) {
        if (k <= 1) return 0;
        int prod = 1, ans = 0, left = 0;
        for (int right = 0; right < nums.length; right++) {
            prod *= nums[right];
            while (prod >= k) prod /= nums[left++];
            ans += right - left + 1;
        }
        return ans;
    }
}
