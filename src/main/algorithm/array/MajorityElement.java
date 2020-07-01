package main.algorithm.array;

import java.util.Arrays;

/**
 * @author lee
 * @date 2020-07-01
 */
public class MajorityElement {
    public static void main(String[] args) {
        int[] nums = {3,2,3};
        System.out.println(majorityElement(nums));
    }

    /**
     * 摩尔投票
     * 投票法是遇到相同的则票数 + 1，遇到不同的则票数 - 1。
     * 且多数元素的个数> ⌊ n/2 ⌋，其余元素的个数总和<= ⌊ n/2 ⌋
     * @param nums
     * @return
     */
    public static int majorityElement(int[] nums) {
        int result = nums[0],count=1;
        for(int i=1;i<nums.length;i++){
            if(nums[i]==result){
                count++;
            }else{
                count--;
                if(count==0){
                    result = nums[i];
                    count=1;
                }
            }
        }
        return result;
    }

    public static int majorityElement2(int[] nums) {
        Arrays.sort(nums);
        return nums[nums.length/2];
    }
}
