package algorithm.collection;

import java.util.*;

/**
 * 前K个高频元素
 * https://leetcode-cn.com/problems/top-k-frequent-elements/
 * @author lee
 * @date 2022/1/14
 */
public class TopKFrequent {
    public static void main(String[] args) {
        int[] nums = {1, 1, 1, 2, 2, 3};
        int k = 2;
        TopKFrequent demo = new TopKFrequent();
        System.out.println(Arrays.toString(demo.topKFrequent(nums, k)));
    }

    /**
     * 哈希加最小堆实现
     *
     * @param nums
     * @param k
     * @return
     */
    public int[] topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>(nums.length * 2);
        PriorityQueue<int[]> heap = new PriorityQueue<>(k, Comparator.comparingInt(x -> x[1]));
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(nums[i])) {
                int count = map.get(nums[i]) + 1;
                map.put(nums[i], count);
            } else {
                map.put(nums[i], 1);
            }
        }

        map.forEach((key, value) -> {
            if (heap.size() < k) {
                heap.offer(new int[]{key, value});
            } else {
                if (value > heap.peek()[1]) {
                    heap.poll();
                    heap.offer(new int[]{key, value});
                }
            }
        });

        int[] res = new int[heap.size()];
        for (int i = 0; i < res.length; i++) {
            res[i] = heap.poll()[0];
        }
        return res;
    }
}
