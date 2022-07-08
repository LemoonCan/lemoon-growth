package algorithm.dynamicprograming;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * 最长连续序列
 * https://leetcode.cn/problems/longest-consecutive-sequence/
 *
 * @author lee
 * @date 2022/7/7
 */
public class LongestConsecutive {
    public static void main(String[] args) {
        LongestConsecutive demo = new LongestConsecutive();
        int[] nums = {0,3,7,2,5,8,4,6,0,1};
        System.out.println(demo.longestConsecutive(nums));
    }

    /**
     * 先排序后检索
     * @param nums
     * @return
     */
    public int longestConsecutive(int[] nums) {
        if (nums.length < 1) {
            return 0;
        }
        Arrays.sort(nums);
        int max = 1;
        int count = 1;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] == nums[i - 1] + 1) {
                count++;
            } else if (nums[i] != nums[i - 1]) {
                max = Math.max(count, max);
                count = 1;
            }
        }
        max = Math.max(count, max);
        return max;
    }

    /**
     * 找到连续序列中最小的数据，再向后查找一共多少数据连续
     * @param nums
     * @return
     */
    public int longestConsecutive2(int[] nums) {
        Set<Integer> numsSet = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            numsSet.add(nums[i]);
        }

        int max = 0;
        for (Integer item : numsSet) {
            //找到连续序列中最小的数据
            if(!numsSet.contains(item-1)){
                int count = 1;
                int curNumber = item;
                while (numsSet.contains(++curNumber)){
                    count++;
                }
                max = Math.max(count,max);
            }
        }
        return max;
    }

    /**
     * 都是正数可以这么做
     *
     * @param nums
     * @return
     */
    public int longestConsecutiveSpecial(int[] nums) {
        int[] count = new int[10 ^ 9 * 2];
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < nums.length; i++) {
            count[nums[i]]++;
            if(nums[i]<min){
                min = nums[i];
            }
            if(nums[i]>max){
                max = nums[i];
            }
        }

        int countmax = 0;
        int temp = 0;
        for (int i = min; i <= max; i++) {
            if (count[i] > 0) {
                temp++;
            } else {
                countmax = Math.max(temp, countmax);
                temp = 0;
            }
        }
        countmax = Math.max(temp,countmax);
        return countmax;
    }
}
