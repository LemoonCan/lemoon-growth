package algorithm.backtracking;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 全排列2
 * https://leetcode-cn.com/problems/permutations-ii/
 * <p>
 * 相比于全排列1 增加一个重复数字的判断
 *
 * @author lee
 * @since 2021/12/3
 */
public class PermuteUnique {
    public static void main(String[] args) {
        int[] nums = {1, 1, 2};
        System.out.println(permuteUnique(nums));
        System.out.println(permuteUnique2(nums));
        System.out.println(permuteUnique3(nums));
    }

    public static List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> res = new LinkedList<>();
        dfs(nums, 0, res);
        return res;
    }

    static void dfs(int[] nums, int begin, List<List<Integer>> res) {
        if (nums.length == begin) {
            res.add(Arrays.stream(nums)
                    .boxed()
                    .collect(Collectors.toList()));
        }
        Set<Integer> selected = new HashSet<>();
        for (int i = begin; i < nums.length; i++) {
            if (!selected.contains(nums[i])) {
                selected.add(nums[i]);
                swap(nums, begin, i);
                dfs(nums, begin + 1, res);
                swap(nums, begin, i);
            }
        }
    }

    static void swap(int[] nums, int i, int j) {
        int temp = nums[j];
        nums[j] = nums[i];
        nums[i] = temp;
    }

    public static List<List<Integer>> permuteUnique2(int[] nums) {
        List<List<Integer>> res = new LinkedList<>();
        dfs2(nums, 0, res);
        return res;
    }

    static void dfs2(int[] nums, int begin, List<List<Integer>> res) {
        if (nums.length == begin) {
            res.add(Arrays.stream(nums)
                    .boxed()
                    .collect(Collectors.toList()));
        }
        for (int i = begin; i < nums.length; i++) {
            if (canSelect(nums, begin, i)) {
                swap(nums, begin, i);
                dfs2(nums, begin + 1, res);
                swap(nums, begin, i);
            }
        }
    }

    static Boolean canSelect(int[] nums, int start, int end) {
        for (int i = start; i < end; i++) {
            if (nums[i] == nums[end]) {
                return false;
            }
        }
        return true;
    }

    /**
     * 先排序再剪枝
     *
     * @param nums
     * @return
     */
    public static List<List<Integer>> permuteUnique3(int[] nums) {
        Arrays.sort(nums);
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            list.add(nums[i]);
        }
        List<List<Integer>> res = new ArrayList<>();
        dfs3(list, res, new ArrayList<>());
        return res;
    }

    static void dfs3(List<Integer> nums, List<List<Integer>> res, List<Integer> item) {
        if (nums.size() == 1) {
            item.add(nums.get(0));
            res.add(new ArrayList<>(item));
            item.remove(item.size()-1);
            return;
        }

        for (int i = 0; i < nums.size(); i++) {
            if (i == 0 || !nums.get(i).equals(nums.get(i - 1))) {
                item.add(nums.get(i));
                List<Integer> truncate = new ArrayList<>(nums);
                truncate.remove(i);
                dfs3(truncate, res, item);
                item.remove(item.size()-1);
            }
        }
    }
}

