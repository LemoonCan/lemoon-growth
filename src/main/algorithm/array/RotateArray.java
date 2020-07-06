package main.algorithm.array;

/**
 * @author lee
 * @date 2020-07-06
 */
public class RotateArray {
    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 4, 5, 6, 7};
        rotate(nums, 5);
        for (int cur : nums) {
            System.out.println(cur);
        }
    }

    public static void rotate(int[] nums, int k) {
        k = k % nums.length;
        if (k == 0) return;

        int move = k;
        int length = nums.length;
        while ((length-move) != 0) {
            int maxMultiple = length / move;
            for (int i = 1; i <= maxMultiple; i++) {
                for (int j = 0; j < move && i * move + j < length; j++) {
                    int temp = nums[i * move + j];
                    nums[i * move + j] = nums[j];
                    nums[j] = temp;
                }
            }
            int temp = length;
            length = move;
            move = length - temp % move;
        }
    }
}
