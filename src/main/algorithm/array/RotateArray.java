package main.algorithm.array;

/**
 * @author lee
 * @date 2020-07-06
 *
 * 给定一个数组，将数组中的元素向右移动 k 个位置，其中 k 是非负数。
 * 输入: [1,2,3,4,5,6,7] 和 k = 3
 * 输出: [5,6,7,1,2,3,4]
 * 解释:
 * 向右旋转 1 步: [7,1,2,3,4,5,6]
 * 向右旋转 2 步: [6,7,1,2,3,4,5]
 * 向右旋转 3 步: [5,6,7,1,2,3,4]
 *
 */
public class RotateArray {
    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 4, 5, 6, 7};
        rotate(nums, 5);
        for (int cur : nums) {
            System.out.println(cur);
        }
    }

    /**
     * 按照移动数的倍数分组交换
     * 若操作的数组长度➗移动位置=倍数...余数
     * 余数不等于0，继续分组交换，移动数=原数组长度-原数组长度%原移动数；操作数组长度=原移动数；
     * 余数等于0，结束循环。
     * @param nums
     * @param k
     */
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
