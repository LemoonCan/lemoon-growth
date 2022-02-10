package algorithm.hashandmapping;

/**
 * Excel表列序号
 * https://leetcode-cn.com/problems/excel-sheet-column-number/
 *
 * @author lee
 * @date 2020-07-09
 * 给定一个excel表的列名称，返回相应的列序号
 * A -> 1
 * B -> 2
 * C -> 3
 * ...
 * Z -> 26
 * AA -> 27
 * AB -> 28
 * ...
 */
public class TitleToNumber {
    public static void main(String[] args) {
        System.out.println(titleToNumber("A"));
        System.out.println(titleToNumber("AB"));
        System.out.println(titleToNumber("ZY"));
        System.out.println(titleToNumber2("A"));
        System.out.println(titleToNumber2("AB"));
        System.out.println(titleToNumber2("ZY"));
    }

    /**
     * A~Z 65-90
     * a~z 97-122
     *
     * result=(C1-'A')*26^(length-1)+(C2-'A')*26^(length-2)+...(直到length-x=0)
     *
     * @param s
     * @return
     */
    public static int titleToNumber(String s) {
        if (s.length() == 0) {
            return 0;
        }
        char[] array = s.toCharArray();

        int num = 0;
        for (int i = 0; i < array.length; i++) {
            num += (array[i] - 64) * pow(26, array.length - i - 1);
        }
        return num;
    }

    private static int pow(int x, int pow) {
        int result = 1;
        for (int i = 0; i < pow; i++) {
            result *= x;
        }
        return result;
    }

    /**
     * “FXSHRXW” 中的每个字母对应的序号分别是：[6,24,19,8,18,24,23][6,24,19,8,18,24,23]
     * 23*26^0 + 24*26^1 + 18*26^2 + 8*26^3 + 19*26^4 + 24*26^5 + 6*26^6 = 2147483647
     * = (6*26+24)*26^5 + 19*26^4 + 8*26^3 + 18*26^2 + 24*26^1 + 23*26^0
     * = [(6*26+24)*26+19]*26^4 + 8*26^3 + 18*26^2 + 24*26^1 + 23*26^0
     * = {[(6*26+24)*26+19]*26+8]}*26^3 + 18*26^2 + 24*26^1 + 23*26^0
     *
     * @param s
     * @return
     */
    public static int titleToNumber2(String s) {
        if (s.length() == 0) {
            return 0;
        }

        int num = 0;
        for (int i = 0; i < s.length(); i++) {
            num = num * 26 + s.charAt(i) - 64;
        }
        return num;
    }
}
