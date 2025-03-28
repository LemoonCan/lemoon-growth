package algorithm.doublepointer;

import java.util.Arrays;

/**
 * 轮转数组
 * https://leetcode-cn.com/problems/rotate-array/
 * @author lee
 * @since 2020-07-06
 *
 * 给定一个数组，将数组中的元素向右移动 k 个位置，其中 k 是非负数。
 * 输入: [1,2,3,4,5,6,7] 和 k = 3
 * 输出: [5,6,7,1,2,3,4]
 * 解释:
 * 向右旋转 1 步: [7,1,2,3,4,5,6]
 * 向右旋转 2 步: [6,7,1,2,3,4,5]
 * 向右旋转 3 步: [5,6,7,1,2,3,4]
 */
public class RotateArray {
    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15};
        rotate(nums, 8);
        System.out.println(Arrays.toString(nums));

        int[] nums2 = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27};
        rotate2(nums2, 12);
        System.out.println(Arrays.toString(nums2));

        int[] numsx1 = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27};
        rotatex1(numsx1, 8);
        System.out.println(Arrays.toString(numsx1));

        int[] numsx2 = {1, 2, 3, 4, 5, 6, 7};
        rotatex2(numsx2, 4);
        System.out.println(Arrays.toString(numsx2));

    }

    /**
     * 按照移动数的倍数分组交换
     * 若操作的数组长度➗移动位置=倍数...余数
     * 余数不等于0，继续分组交换，移动数=原数组长度-原数组长度%原移动数；操作数组长度=原移动数；
     * 余数等于0，结束循环。
     * <p>
     * 替换完最后一位数后另做考虑
     * <p>
     * 举例 7位数，向右移动3位
     * 1,2,3,4,5,6,7
     * 1.每3位替换
     * 4,2,3,1,5,6,7
     * 4,5,3,1,2,6,7
     * 4,5,6,1,2,3,7
     * 7,5,6,1,2,3,4
     * <p>
     * 2.前3位，向右移动2位 2=3-7%3
     * 6,5,7,1,2,3,4
     * <p>
     * 3.前2位，向右移动1位 1=2-3%2
     * 5,6,7,1,2,3,4
     *
     * @param nums
     * @param k
     */
    public static void rotate(int[] nums, int k) {
        k = k % nums.length;
        if (k == 0) return;

        int move = k;
        int length = nums.length;
        while ((length - move) != 0) {
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

    /**
     * 环状替换
     *
     * 从0出发需要回到0，需要a圈；每次走k步，走过了b个元素。
     * 需要满足下述公式：
     * a*n = b*k
     *
     * 每次出发能遍历k个元素
     * 需要多少次能遍历完所有元素？
     * count = n/b
     *
     * b=lcm(n,k)/k
     * count=n*k/lcm(n,k)=gcd(n,k)
     *
     * @param nums
     * @param k
     */
    public static void rotate2(int[] nums, int k) {
        int step = k % nums.length;
        if (step <= 0) return;

        int commonDivisor = maxCommonDivisor(nums.length, step);
        for (int i = 0; i < commonDivisor; i++) {
            int movePosition = i;
            int temp = nums[i];
            while ((movePosition = (movePosition + step) % nums.length) != i) {
                int next = nums[movePosition];
                nums[movePosition] = temp;
                temp = next;
            }
            nums[i] = temp;
        }
    }

    /**
     * 环状替换
     * @param nums
     * @param k
     */
    public static void rotate4(int[] nums, int k) {
        boolean[] moved = new boolean[nums.length];
        for (int i = 0; i < nums.length; i++) {
            if (!moved[i]) {
                int change = (i+ k) % nums.length;
                int cur = nums[i];
                while (change != i) {
                    int temp = nums[change];
                    nums[change] = cur;
                    moved[change] = true;
                    cur = temp;
                    change = (change + k) % nums.length;
                }
                nums[i] = cur;
                moved[i] = true;
            }
        }
    }

    /**
     * 求最大公约数
     *
     * @param m
     * @param n
     * @return
     */
    public static int maxCommonDivisor(int m, int n) {
        // 保证被除数大于除数
        if (m < n) {
            int temp = m;
            m = n;
            n = temp;
        }
        // 在余数不能为0时,进行循环
        while (m % n != 0) {
            int temp = m % n;
            m = n;
            n = temp;
        }
        // 返回最大公约数
        return n;
    }

    /**
     * 数组翻转
     *
     * @param nums
     * @param k
     */
    public void rotate3(int[] nums, int k) {
        k %= nums.length;
        reverse(nums, 0, nums.length - 1);
        reverse(nums, 0, k - 1);
        reverse(nums, k, nums.length - 1);
    }

    public void reverse(int[] nums, int start, int end) {
        while (start < end) {
            int temp = nums[start];
            nums[start] = nums[end];
            nums[end] = temp;
            start += 1;
            end -= 1;
        }
    }

    /**
     * @param nums
     * @param k
     */
    public static void rotatex1(int[] nums, int k) {
        int step = k % nums.length;
        if (step <= 0) return;
        int i = 0;
        int base;
        while ((base = i * step) < nums.length) {
            for (int j = 0; j < step; j++) {
                int movePosition = (base + j) % nums.length;
                int temp = nums[j];
                nums[j] = nums[movePosition];
                nums[movePosition] = temp;
            }
            i++;
        }
    }

    /**
     * 27➗6=4...3 6➗3=0 无需再做移动
     * 27➗7=3...6 7➗3=2...1 后6位(前面的余数)向右移动1(后面的余数)位
     * 27➗8=3...3 8➗3=2...2 后3位向右移动2位
     * 27➗9=3 无需再做移动
     * 27➗10=2...7 10➗7=1...3 后7位向右移动3位
     * 27➗11=2...5 11➗5=2...1 后5位向右移动1位
     *
     * @param nums
     * @param k
     */
    public static void rotatex2(int[] nums, int k) {
        int step = k % nums.length;
        int first = 0;
        int length = nums.length;
        while (step > 0) {
            int base;
            int i = 1;
            while ((base = i * step) < length) {
                for (int j = 0; j < step; j++) {
                    int movePosition = first + (base + j) % length;
                    int temp = nums[movePosition];
                    nums[movePosition] = nums[first + j];
                    nums[first + j] = temp;
                }
                i++;
            }

            int remainder = length % step;
            first = first + step - remainder;
            length = remainder;
            if (remainder == 0) {
                step = 0;
            } else {
                step = step % remainder;
            }
        }
    }
}
