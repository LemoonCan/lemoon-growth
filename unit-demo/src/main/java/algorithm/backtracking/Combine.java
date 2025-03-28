package algorithm.backtracking;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * https://leetcode-cn.com/problems/combinations/
 * 组合
 *
 * @author lee
 * @since 2021/10/26
 */
public class Combine {
    public static void main(String[] args) {
        System.out.println(combine(5, 4));
        System.out.println(combine2(5, 4));
    }

    public static List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> combine = new ArrayList<>();
        for (int i = 1; i <= n - k + 1; i++) {
            if (k == 1) {
                List<Integer> item = new ArrayList<>();
                item.add(i);
                combine.add(item);
                continue;
            }
            if (k == 2) {
                for (int j = i + 1; j <= n; j++) {
                    List<Integer> item = new ArrayList<>();
                    item.add(i);
                    item.add(j);
                    combine.add(item);
                }
                continue;
            }

            for (int j = i + 1; j <= n - k + 2; j++) {
                for (int l = j + k - 2; l <= n; l++) {
                    List<Integer> item = new ArrayList<>();
                    item.add(i);
                    for (int m = j; m < j + k - 2; m++) {
                        item.add(m);
                    }
                    item.add(l);
                    combine.add(item);
                }
            }

        }
        return combine;
    }

    public static List<List<Integer>> combine2(int n, int k) {
        List<List<Integer>> res = new ArrayList<>();
        dfs(n, k, 1, new LinkedList<>(), res);
        return res;
    }

    /**
     * 1 2 3 4 5   (3)
     *选        1                       2         3
     *      (2,3,4,5)                (3,4,5)    (4,5)
     *选    2     3     4
     *  (3,4,5) (4,5)  5
     */
    static void dfs(int n, int k, int begin, Deque<Integer> path, List<List<Integer>> res) {
        if (path.size() == k) {
            res.add(new ArrayList<>(path));
            return;
        }

        //5 3 path.size=1 5-2=3 4
        for (int i = begin; i <= n - (k - path.size()) + 1; i++) {
            path.add(i);
            dfs(n, k, i + 1, path, res);
            path.removeLast();
        }
    }
}
