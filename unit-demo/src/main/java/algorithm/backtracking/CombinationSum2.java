package algorithm.backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 组合总和2
 * https://leetcode-cn.com/problems/combination-sum-ii/
 *
 * @author lee
 * @since 2021/12/7
 */
public class CombinationSum2 {
    public static void main(String[] args) {
        int[] candidates = {10, 1, 2, 7, 6, 1, 5};
        CombinationSum2 cs = new CombinationSum2();
        System.out.println(cs.combinationSum2(candidates, 8));
    }

    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        Arrays.sort(candidates);
        List<List<Integer>> res = new ArrayList<>();
        dfs(candidates, target, 0, res, new ArrayList<>());
        return res;
    }

    public void dfs(int[] candidates, int target, int index, List<List<Integer>> res, List<Integer> item) {
        if (target == 0) {
            res.add(new ArrayList<>(item));
            return;
        }
        for (int i = index; i < candidates.length; i++) {
            if (target - candidates[i] < 0) {
                break;
            }

            if (i == index || candidates[i] != candidates[i - 1]) {
                item.add(candidates[i]);
                dfs(candidates, target - candidates[i], i + 1, res, item);
                item.remove(item.size() - 1);
            }
        }
    }
}
