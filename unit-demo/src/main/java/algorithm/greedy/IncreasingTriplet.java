package algorithm.greedy;

/**
 * 递增的三元子序列
 * https://leetcode-cn.com/problems/increasing-triplet-subsequence/
 *
 * @author lee
 * @date 2022/1/7
 */
public class IncreasingTriplet {
    public static void main(String[] args) {
        int[] nums = {1,2,3,4,5};
        IncreasingTriplet demo = new IncreasingTriplet();
        System.out.println(demo.increasingTriplet(nums));

        int[] nums1 = {4,3,5,1,2,0,3};
        System.out.println(demo.increasingTriplet(nums1));

        int[] nums2 = {5,4,3,2,1};
        System.out.println(demo.increasingTriplet(nums2));

        int[] nums3 ={20,100,10,12,5,13};
        System.out.println(demo.increasingTriplet(nums3));

        int[] nums4 = {4,3,5,1,5,0};
        System.out.println(demo.increasingTriplet(nums4));

        int[] nums5 = {4,3,5,1,4,0};
        System.out.println(demo.increasingTriplet2(nums5));

    }

    public boolean increasingTriplet(int[] nums) {
        int[] tri = new int[3];
        tri[0] = nums[0];
        int begin = 1;

        boolean change = true;
        while (change) {
            change = false;
            int index = 0;
            tri[index] = nums[begin-1];
            for (int i = begin; i < nums.length; i++) {
                if (nums[i] > tri[index]) {
                    tri[++index] = nums[i];
                    if (index == 2) {
                        return true;
                    }
                } else {
                    if (index == 0) {
                        tri[index] = nums[i];
                    } else {
                        if (nums[i] > tri[0]) {
                            tri[index] = nums[i];
                        } else if (!change && nums[i] < tri[0]) {
                            begin = i+1;
                            change = true;
                        }
                    }
                }
            }
        }
        return false;
    }

    /**
     * 这是真的很贪心！
     *
     * @param nums
     * @return
     */
    public boolean increasingTriplet2(int[] nums) {
        int min=Integer.MAX_VALUE;
        int mid=Integer.MAX_VALUE;
        int n=nums.length;
        for (int i = 0; i < n; i++) {
            if (nums[i]<=min)
                min=nums[i];
            else  if(nums[i]<=mid)
                mid=nums[i];
            else if (nums[i]>mid)
            {
                return true;
            }

        }
        return false;
    }
}
