package algorithm.backtracking;

import java.util.*;
import java.util.stream.Collectors;

/**
 * https://leetcode-cn.com/problems/permutations/submissions/
 * 全排列
 * @author lee
 * @date 2021/10/27
 */
public class Permute {
    public static void main(String[] args) {
        System.out.println(permute(new int[]{1,2,3}));
        System.out.println(permute2(new int[]{1,2,3}));
    }

    public static List<List<Integer>> permute(int[] nums) {
        Deque<Integer> set = new LinkedList<>();
        for (int num : nums) {
            set.add(num);
        }
        List<List<Integer>> res = new ArrayList<>();
        permute(res,set,new LinkedList<>());
        return res;
    }

    static void permute(List<List<Integer>> res,Deque<Integer> nums,Deque<Integer> item){
        if(nums.size()==1){
            item.add(nums.poll());
            res.add(new LinkedList<>(item));
            item.removeLast();
            return;
        }
        for (Integer num : nums) {
            item.add(num);
            LinkedList<Integer> truncateNum = new LinkedList<>(nums);
            truncateNum.remove(num);
            permute(res,truncateNum,item);
            item.removeLast();
        }
    }

    public static List<List<Integer>> permute2(int[] nums) {
        List<List<Integer>> res = new LinkedList<>();
        dfs(nums,0,res);
        return res;
    }

    static void dfs(int[] nums,int begin,List<List<Integer>> res){
        if(nums.length==begin){
            res.add(Arrays.stream(nums)
                    .boxed()
                    .collect(Collectors.toList()));
        }
        for (int i = begin; i < nums.length; i++) {
            swap(nums,begin,i);
            dfs(nums,begin+1,res);
            swap(nums,begin,i);
        }
    }
    static void swap(int[] nums,int i,int j){
        int temp = nums[j];
        nums[j] = nums[i];
        nums[i] = temp;
    }
}
