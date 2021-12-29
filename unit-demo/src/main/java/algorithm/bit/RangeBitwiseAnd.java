package algorithm.bit;

/**
 * 数字范围按位与
 * https://leetcode-cn.com/problems/bitwise-and-of-numbers-range/
 *
 * @author lee
 * @date 2021/12/29
 */
public class RangeBitwiseAnd {
    public static void main(String[] args) {
        RangeBitwiseAnd demo = new RangeBitwiseAnd();
        System.out.println(demo.rangeBitwiseAnd(0, 0));
        System.out.println(demo.rangeBitwiseAnd(5, 7));

        //+1会变成负数
        System.out.println(demo.rangeBitwiseAnd(2147483647, 2147483647));
        System.out.println(demo.rangeBitwiseAnd2(2147483647, 2147483647));
        System.out.println(demo.rangeBitwiseAnd3(2147483647, 2147483647));
    }

    public int rangeBitwiseAnd(int left, int right) {
        int res = left;
        while (left <= right) {
            res = left & right & res;
            if (res == 0) {
                return res;
            }
            left++;
            right--;
        }
        return res;
    }

    /**
     * 找到对应二进制字符串的公共前缀
     *
     * @param left
     * @param right
     * @return
     */
    public int rangeBitwiseAnd2(int left, int right){
        while (left < right)
            right &= (right - 1);
        return right;
    }

    public int rangeBitwiseAnd3(int left, int right){
        int shift = 0;
        while (left != right){
            left = left>>1;
            right = right>>1;
            shift++;
        }
        return left<<shift;
    }
}
