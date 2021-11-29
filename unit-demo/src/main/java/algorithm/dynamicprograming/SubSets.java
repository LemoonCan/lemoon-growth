package algorithm.dynamicprograming;

import java.util.ArrayList;
import java.util.List;

/**
 * 子集
 * https://leetcode-cn.com/problems/subsets/
 *
 * @author lee
 * @date 2021/11/28
 */
public class SubSets {
    public static void main(String[] args) {
        int[]  nums = {1,2,3};
        System.out.println(subsets(nums));
    }

    public static List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        res.add(new ArrayList<>());

        for (int i = 0; i < nums.length; i++) {
            int size = res.size();
            for (int j = 0; j < size; j++) {
                List<Integer> item = new ArrayList<>(res.get(j));
                item.add(nums[i]);
                res.add(item);
            }
        }

        return res;
    }
}
