package algorithm.greedy;

/**
 * 跳跃游戏2
 * https://leetcode-cn.com/problems/jump-game-ii/
 * @author lee
 * @date 2021/12/13
 */
public class Jump {
    /**
     * 每跳一步能达到的最远距离
     *
     * @param nums
     * @return
     */
    public int jump(int[] nums) {
        int length = nums.length;
        int end = 0;
        int maxPosition = 0;
        int steps = 0;
        for (int i = 0; i < length - 1; i++) {
            maxPosition = Math.max(maxPosition, i + nums[i]);
            if (i == end) {
                end = maxPosition;
                steps++;
            }
        }
        return steps;
    }
}
