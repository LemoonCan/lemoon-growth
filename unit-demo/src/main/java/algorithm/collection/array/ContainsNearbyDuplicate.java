package algorithm.collection.array;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 存在重复元素2
 * https://leetcode-cn.com/problems/contains-duplicate-ii/
 *
 * @author lee
 * @since 2022/1/12
 */
public class ContainsNearbyDuplicate {
    public static void main(String[] args) {
        int[] nums = {1,2,3,1};
        ContainsNearbyDuplicate demo = new ContainsNearbyDuplicate();
        System.out.println(demo.containsNearbyDuplicate(nums,3));

        int[] nums1 = {1,0,1,1};
        System.out.println(demo.containsNearbyDuplicate(nums1,1));

        int[] nums2 = {1,2,3,1,2,3};
        System.out.println(demo.containsNearbyDuplicate(nums2,2));
    }

    public boolean containsNearbyDuplicate(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(nums[i])) {
                Integer index = map.get(nums[i]);
                if (i - index <= k) {
                    return true;
                }
            }
            map.put(nums[i], i);
        }
        return false;
    }

    /**
     * 把不在 i、j范围的都删除
     * 可减少空间
     *
     * @param nums
     * @param k
     * @return
     */
    public boolean containsNearbyDuplicate2(int[] nums, int k) {
        int i = 0, j = 0;
        Set<Integer> seen = new HashSet<>();
        while(i < nums.length){
            if(!seen.add(nums[i])){
                return true;
            }
            i++;
            while(i - j > k){
                seen.remove(nums[j]);
                j++;
            }
        }
        return false;
    }

}
