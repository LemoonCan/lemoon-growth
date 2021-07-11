package algorithm.hashandmapping;

/**
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
     * <p>
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
     * 再加一个字母的情况，对于所有已计数的，每个都有26种组合方式，再加上字母的位置，即为所得
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
