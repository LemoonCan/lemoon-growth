package algorithm.bit;

/**
 * 2的幂
 * https://leetcode-cn.com/problems/power-of-two/
 * @author lee
 * @date 2021/11/3
 */
public class PowerOfTwo {
    public static void main(String[] args) {
        PowerOfTwo t = new PowerOfTwo();
        System.out.println(t.isPowerOfTwo(16));
    }
    public boolean isPowerOfTwo(int n) {
        return n>0 && (n & (n-1)) == 0;
    }

    public boolean isPowerOfTwo2(int n) {
        return (n & -n) == n;
    }
}
