package algorithm.dynamicprograming;

/**
 * 解码方法
 * https://leetcode-cn.com/problems/decode-ways/
 *
 * @author lee
 * @date 2021/12/17
 * <p>
 * 1020312
 * 10 20 3 1 2
 * 10 20 3 12
 */
public class NumDecodings {
    public static void main(String[] args) {
        NumDecodings demo = new NumDecodings();
        System.out.println(demo.numDecodings("226"));
        System.out.println(demo.numDecodings("12"));
        System.out.println(demo.numDecodings("06"));
        System.out.println(demo.numDecodings("12006"));
        System.out.println(demo.numDecodings("1201061626"));
        System.out.println(demo.numDecodings("0"));
        System.out.println(demo.numDecodings("2611055971756562"));
        System.out.println(demo.numDecodings2("2611055971756562"));
    }

    /**
     * f(x+2)=f(x+1)+f(x)
     * @param s
     * @return
     */
    public int numDecodings(String s) {
        if (s.length() < 1) return 0;
        if (s.charAt(0) == '0') return 0;
        if (s.length() == 1) return 1;

        int[] count = new int[s.length() + 2];
        for (int i = 1; i < s.length(); i++) {
            if (s.charAt(i) == '0') {
                if (s.charAt(i - 1) == '1' || s.charAt(i - 1) == '2') {
                    count[i + 1] = -1;
                } else {
                    return 0;
                }
            }
        }

        count[0] = 0;
        count[1] = 1;

        for (int i = 0; i < s.length(); i++) {
            int temp = i + 2;
            while (count[temp] == -1) {
                count[temp] = count[temp - 1];
                if (++temp < s.length() + 2) {
                    count[temp] = count[temp - 1];
                }
                ++temp;
            }
            if ((temp - 2) != i) {
                count[temp] = count[temp - 1];
                i = temp - 2;
                continue;
            }
            if (i == 0 || judgeRange(Character.getNumericValue(s.charAt(i - 1)), Character.getNumericValue(s.charAt(i)))) {
                count[i + 2] = count[i] + count[i + 1];
            } else {
                count[i + 2] = count[i + 1];
            }

        }
        return count[s.length() + 1];
    }

    public boolean judgeRange(int tens, int digits) {
        if (tens == 1 || tens == 2) {
            if (tens * 10 + digits <= 26) {
                return true;
            }
        }
        return false;
    }

    public int numDecodings2(String s) {
        int n = s.length();
        int[] f = new int[n + 1];
        f[0] = 1;
        for (int i = 1; i <= n; ++i) {
            if (s.charAt(i - 1) != '0') {
                f[i] += f[i - 1];
            }
            if (i > 1 && s.charAt(i - 2) != '0' && ((s.charAt(i - 2) - '0') * 10 + (s.charAt(i - 1) - '0') <= 26)) {
                f[i] += f[i - 2];
            }
        }
        return f[n];
    }
}
