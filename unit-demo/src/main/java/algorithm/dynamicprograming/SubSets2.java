package algorithm.dynamicprograming;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 子集2
 * https://leetcode-cn.com/problems/subsets-ii/
 *
 * @author lee
 * @since 2021/11/29
 */
public class SubSets2 {
    public static void main(String[] args) {
        int[] nums ={1,2,2};
        System.out.println(subsetsWithDup(nums));
    }

    public static List<List<Integer>> subsetsWithDup(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> res = new ArrayList<>();
        res.add(new ArrayList<>());
        if (nums.length >= 1) {
            res.add(Arrays.asList(nums[0]));
        }

        int count=0,add=0;
        for (int i = 1; i < nums.length; i++) {
            int size = res.size();

            if (nums[i - 1] == nums[i]) {
                count++;
                if(count==1){
                    add = size/2;
                }
                for (int j = size-1; j >= size-add; j--) {
                    List<Integer> item = new ArrayList<>(res.get(j));
                    item.add(nums[i]);
                    res.add(item);
                }
            } else {
                count=0;
                for (int j = 0; j < size; j++) {
                    List<Integer> item = new ArrayList<>(res.get(j));
                    item.add(nums[i]);
                    res.add(item);
                }
            }
        }

        return res;
    }
}
