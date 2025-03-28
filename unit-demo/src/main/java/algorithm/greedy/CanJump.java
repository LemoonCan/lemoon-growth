package algorithm.greedy;

/**
 * 跳跃游戏
 * https://leetcode-cn.com/problems/jump-game/
 *
 * @author lee
 * @since 2021/12/12
 */
public class CanJump {
    public static void main(String[] args) {
        int[] nums = {2, 3, 1, 1, 4};
        CanJump demo = new CanJump();
        System.out.println(demo.canJump(nums));
        int[] nums1 = {3, 2, 1, 0, 4};
        int[] nums2 = {2, 3, 1, 1, 4};
        System.out.println(demo.canJump2(nums1));
        System.out.println(demo.canJump2(nums2));
        System.out.println(demo.canJump3(nums1));
        System.out.println(demo.canJump3(nums2));
    }

    /**
     * 一半的动态规划解法
     *
     * @param nums
     * @return
     */
    public boolean canJump(int[] nums) {
        return dfs(nums, 0);
    }

    public boolean dfs(int[] nums, int index) {
        if (index >= nums.length - 1) {
            return true;
        }
        if (nums[index] == 0) {
            return false;
        }
        int step = nums[index];
        while (step > 0) {
            if (dfs(nums, index + step)) {
                return true;
            }
            step--;
        }
        nums[index] = 0;
        return false;
    }

    /**
     * 动态规划
     *
     * @param nums
     * @return
     */
    public boolean canJump2(int[] nums) {
        return dfs(nums, 0, new int[nums.length]);
    }

    public boolean dfs(int[] nums, int index, int[] his) {
        if (index >= nums.length - 1) {
            return true;
        }
        if (his[index] != 0) {
            return his[index] == 1;
        }
        int step = nums[index];
        while (step > 0) {
            if (dfs(nums, index + step, his)) {
                his[index] = 1;
                return true;
            }
            step--;
        }
        his[index] = 2;
        return false;
    }

    /**
     * 每次跳最长路径，最长前的都是可以到的
     * 最长路径内的遍历，也每次跳跃最长路径，与原最长路径比较取大的值
     * 最长路径大于数组末边界则可到达末边界
     *
     * @param nums
     * @return
     */
    public boolean canJump3(int[] nums) {
        int rightMax = 0;
        for (int i = 0; i < nums.length; i++) {
            if(i<=rightMax){
                rightMax = Math.max(rightMax,i+nums[i]);
                if(rightMax>=nums.length-1){
                    return true;
                }
            }
        }
        return false;
    }
}
