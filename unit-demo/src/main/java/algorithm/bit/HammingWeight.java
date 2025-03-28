package algorithm.bit;

/**
 * 位1的个数
 * https://leetcode-cn.com/problems/number-of-1-bits/
 *
 * @author lee
 * @since 2021/11/3
 */
public class HammingWeight {
    public int hammingWeight(int n) {
        String bs = Integer.toBinaryString(n);
        int count = 0;
        for (int i = 0; i < bs.length(); i++) {
            if (bs.charAt(i) == '1') {
                count++;
            }
        }
        return count;
    }

    /**
     * 每执行一次 n&(n-1) 会把低位1变成0
     * @param n
     * @return
     */
    public int hammingWeight2(int n) {
        int count = 0;
        while (n != 0) {
            n = n & (n - 1);
            count++;
        }
        return count;
    }

    /**
     *
     * @param n
     * @return
     */
    public int hammingWeight3(int n) {
        int count = 0;
        for (int i = 0; i < 32; i++) {
            if((1<<i & n) !=0){
                count++;
            }
        }
        return count;
    }
}
