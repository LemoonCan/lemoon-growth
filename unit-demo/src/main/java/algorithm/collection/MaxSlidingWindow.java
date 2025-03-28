package algorithm.collection;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * 滑动窗口最大值
 * https://leetcode-cn.com/problems/sliding-window-maximum/
 *
 * @author lee
 * @since 2022/1/16
 */
public class MaxSlidingWindow {
    public static void main(String[] args) {
        int[] nums = {1, 3, -1, -3, 5, 3, 6, 7};
        int k = 3;
        MaxSlidingWindow demo = new MaxSlidingWindow();
        System.out.println(Arrays.toString(demo.maxSlidingWindow(nums, k)));

        int[] nums1 = {1, -1};
        k = 1;
        System.out.println(Arrays.toString(demo.maxSlidingWindow(nums1, k)));

        System.out.println(Arrays.toString(demo.maxSlidingWindow2(nums, 3)));
        System.out.println(Arrays.toString(demo.maxSlidingWindow2(nums1, 1)));
    }

    /**
     * 超出时间限制
     *
     * @param nums
     * @param k
     * @return
     */
    public int[] maxSlidingWindow(int[] nums, int k) {
        int[] max = new int[]{-1, nums[0]};
        int[] res = new int[nums.length - k + 1];
        for (int i = 0; i <= nums.length - k; i++) {
            if (max[0] < i) {
                max = new int[]{i, nums[i]};
                for (int j = i + 1; j < i + k; j++) {
                    if (nums[j] > max[1]) {
                        max = new int[]{j, nums[j]};
                    }
                }
            } else {
                int end = i + k - 1;
                if (max[1] <= nums[end]) {
                    max = new int[]{end, nums[end]};
                }
            }
            res[i] = max[1];
        }

        return res;
    }

    /**
     * 维护大顶堆
     * @param nums
     * @param k
     * @return
     */
    public int[] maxSlidingWindow2(int[] nums, int k) {
        PriorityQueue<int[]> heap = new PriorityQueue<>(k, (x1, x2) -> x2[1] - x1[1]);
        int[] res = new int[nums.length - k + 1];
        for (int i = 0; i <= nums.length - k; i++) {
            if (heap.isEmpty()) {
                for (int j = i; j < i + k; j++) {
                    heap.offer(new int[]{j, nums[j]});
                }
            } else {
                while (!heap.isEmpty()&&heap.peek()[0] < i) {
                    heap.poll();
                }
                int end = i + k - 1;
                heap.offer(new int[]{end, nums[end]});
            }
            res[i]=heap.peek()[1];
        }
        return res;
    }

    /**
     * 维护单调递减队列
     */
}
