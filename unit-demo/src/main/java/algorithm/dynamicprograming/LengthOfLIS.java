package algorithm.dynamicprograming;

/**
 * 最长递增子序列
 * https://leetcode-cn.com/problems/longest-increasing-subsequence/
 *
 * @author lee
 * @date 2021/12/22
 */
public class LengthOfLIS {
    public static void main(String[] args) {
        LengthOfLIS demo = new LengthOfLIS();
        System.out.println(demo.lengthOfLIS(new int[]{10,9,2,5,3,7,101,18}));
        System.out.println(demo.lengthOfLIS(new int[]{0,1,0,3,2,3}));
        System.out.println(demo.lengthOfLIS(new int[]{7,7,7,7,7,7,7}));
    }

    public int lengthOfLIS(int[] nums) {
        int[] count = new int[nums.length];
        count[0] = 1;
        int max = 1;

        for (int i = 1; i < nums.length; i++) {
            count[i] = 1;
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    if (count[j] + 1 > count[i]) {
                        count[i] = count[j] + 1;
                        if(count[i]>max){
                            max = count[i];
                        }
                    }
                }
            }
        }

        return max;
    }
}
