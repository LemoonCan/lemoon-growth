package algorithm.doublepointer;

/**
 * 盛最多水的容器
 * https://leetcode-cn.com/problems/container-with-most-water/
 *
 * @author lee
 * @since 2021/11/10
 */
public class MaxArea {
    public int maxArea(int[] height) {
        if (height.length < 2) {
            return 0;
        }
        int max = 0;
        int left = 0, right = height.length;
        while (left < right) {
            max = Math.max(max, (right - left) * Math.min(height[left], height[right]));
            if (height[left] < height[right]) {
                left++;
            } else {
                right--;
            }
        }
        return max;
    }
}
