package algorithm.doublepointer;

import java.util.LinkedList;
import java.util.List;

/**
 * 区间列表的交集
 * https://leetcode-cn.com/problems/interval-list-intersections/
 *
 * @author lee
 * @since 2021/11/10
 */
public class InterverIntersection {
    public int[][] intervalIntersection(int[][] firstList, int[][] secondList) {
        List<int[]> res = new LinkedList<>();
        int p1 = 0, p2 = 0;
        while (p1 < firstList.length && p2 < secondList.length) {
            if (firstList[p1][1] < secondList[p2][0]) {
                p1++;
            } else if (firstList[p1][0] > secondList[p2][1]) {
                p2++;
            } else {
                int[] item = {Math.max(firstList[p1][0], secondList[p2][0]), Math.min(firstList[p1][1], secondList[p2][1])};
                res.add(item);
                if (firstList[p1][1] > secondList[p2][1]) {
                    p2++;
                } else {
                    p1++;
                }
            }
        }
        return res.toArray(new int[res.size()][]);
    }

    public int[][] intervalIntersection2(int[][] firstList, int[][] secondList) {
        List<int[]> res = new LinkedList<>();
        int p1 = 0, p2 = 0;
        while (p1 < firstList.length && p2 < secondList.length) {
            int low = Math.max(firstList[p1][0], secondList[p2][0]);
            int high = Math.min(firstList[p1][1], secondList[p2][1]);
            if(low<=high){
                res.add(new int[]{low,high});
            }
            if(firstList[p1][1]>secondList[p2][1]){
                p2++;
            }else{
                p1++;
            }
        }
        return res.toArray(new int[res.size()][]);
    }
}
