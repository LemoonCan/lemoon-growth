package javabasic.container;

import java.util.*;

/**
 * https://leetcode-cn.com/problems/the-skyline-problem/
 * 天际线问题
 *
 * @author lee
 * @since 2022/3/14
 */
public class GetSkyline {
    public List<List<Integer>> getSkyline(int[][] buildings) {
        List<List<Integer>> res = new ArrayList<>();

        int i = 0;
        Deque<int[]> deque = new LinkedList<>();
        while (i < buildings.length) {
            if (deque.isEmpty()) {
                deque.addLast(buildings[i]);
                i++;
            } else if (buildings[i][0] <= deque.peekFirst()[1]) {
                //对队列内元素切割排序

            } else {
                int[] ele = deque.pop();
                if (res.isEmpty()) {
                    res.add(Arrays.asList(ele[0], ele[2]));
                } else {
                    if (res.get(res.size() - 1).get(1) != ele[2]) {
                        res.add(Arrays.asList(ele[0], ele[2]));
                    } else {
                        if (deque.isEmpty()) {
                            res.add(Arrays.asList(ele[1], ele[2]));
                            i++;
                        }
                    }
                }
            }
        }

        return res;
    }

    public List<List<Integer>> getSkyline2(int[][] buildings) {
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> b[1] - a[1]);
        List<Integer> boundaries = new ArrayList<>();
        for (int[] building : buildings) {
            boundaries.add(building[0]);
            boundaries.add(building[1]);
        }
        Collections.sort(boundaries);

        List<List<Integer>> ret = new ArrayList<>();
        int n = buildings.length, idx = 0;
        for (int boundary : boundaries) {
            while (idx < n && buildings[idx][0] <= boundary) {
                pq.offer(new int[]{buildings[idx][1], buildings[idx][2]});
                idx++;
            }
            while (!pq.isEmpty() && pq.peek()[0] <= boundary) {
                pq.poll();
            }

            int maxn = pq.isEmpty() ? 0 : pq.peek()[1];
            if (ret.size() == 0 || maxn != ret.get(ret.size() - 1).get(1)) {
                ret.add(Arrays.asList(boundary, maxn));
            }
        }
        return ret;
    }
}
