package algorithm.backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 组合总和
 * https://leetcode-cn.com/problems/combination-sum/
 *
 * @author lee
 * @date 2021/12/6
 */
public class CombinationSum {
    public static void main(String[] args) {
        int[] candidates = {2,3,6,7};
        System.out.println(combinationSum(candidates,7));
    }

    public static List<List<Integer>> combinationSum(int[] candidates, int target) {
        Arrays.sort(candidates);
        List<List<Integer>> res = new ArrayList<>();
        dfs(candidates,target,0,res,new ArrayList<>());
        return res;
    }

    public static void dfs(int[] candidates, int target, int index, List<List<Integer>> res, List<Integer> item) {
        if (target == 0) {
            res.add(new ArrayList<>(item));
            return;
        }

        for (int i = index; i < candidates.length; i++) {
            if(target-candidates[i]<0){
                return;
            }
            item.add(candidates[i]);
            dfs(candidates, target - candidates[i], i, res, item);
            item.remove(item.size() - 1);
        }
    }
}
