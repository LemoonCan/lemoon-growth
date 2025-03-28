package algorithm.bit;

/**
 * 颠倒二进制位
 * https://leetcode-cn.com/problems/reverse-bits/
 *
 * @author lee
 * @since 2021/11/3
 */
public class ReverseBits {
    public static void main(String[] args) {
        int n = -3;
        System.out.println(reverseBits(n));
    }

    /**
     * 交换对称位置
     * @param n
     * @return
     */
    public static int reverseBits(int n) {
        int result = 0;
        for (int i = 0; i < 16; i++) {
            result |= result | ((1 << i & n) << (31 - i * 2));
            result |= (1 << (31 - i) & n) >>> (31 - i * 2);
            System.out.println(Integer.toBinaryString(result));
        }
        return result;
    }

    /**
     * 位分治解法
     * 原数据为:12345678
     * 第一轮 奇偶位交换 21436587
     * 第二轮 每两位交换 43218765
     * 第三轮 每四位交换 87654321
     *
     * @param n
     * @return
     */
    public static int reverseBits2(int n) {
        // 01010101010101010101010101010101
        final int M1 = 0x55555555;
        // 00110011001100110011001100110011
        final int M2 = 0x33333333;
        // 00001111000011110000111100001111
        final int M4 = 0x0f0f0f0f;
        // 00000000111111110000000011111111
        final int M8 = 0x00ff00ff;
        n = n >>> 1 & M1 | (n & M1) << 1;
        n = n >>> 2 & M2 | (n & M2) << 2;
        n = n >>> 4 & M4 | (n & M4) << 4;
        n = n >>> 8 & M8 | (n & M8) << 8;
        return n >>> 16 | n << 16;
    }

    /**
     * 循环移动
     */
    public int reverseBits3(int n) {
        int rev = 0;
        for (int i = 0; i < 32 && n != 0; ++i) {
            rev |= (n & 1) << (31 - i);
            n >>>= 1;
        }
        return rev;
    }
}
