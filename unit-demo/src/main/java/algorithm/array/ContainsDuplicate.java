package algorithm.array;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * 存在重复元素
 * https://leetcode-cn.com/problems/contains-duplicate/
 *
 * @author lee
 * @date 2022/1/6
 *
 */
public class ContainsDuplicate {
    /**
     * 哈希
     * @param nums
     * @return
     */
    public boolean containsDuplicate(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            if(set.contains(nums[i])){
                return true;
            }
            set.add(nums[i]);
        }
        return false;
    }

    /**
     * 排序
     * @param nums
     * @return
     */
    public boolean containsDuplicate2(int[] nums) {
        Arrays.sort(nums);
        for (int i = 1; i < nums.length; i++) {
            if(nums[i]==nums[i-1]){
                return true;
            }
        }
        return false;
    }
}
