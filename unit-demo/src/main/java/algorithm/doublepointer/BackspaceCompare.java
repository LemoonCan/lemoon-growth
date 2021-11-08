package algorithm.doublepointer;

/**
 * 比较含退格的字符串
 * https://leetcode-cn.com/problems/backspace-string-compare/
 * @author lee
 * @date 2021/11/8
 */
public class BackspaceCompare {
    public static void main(String[] args) {
        String s = "ab#c", t = "ad#c";
        System.out.println(backspaceCompare(s, t));

        s = "ab##"; t = "c#d#";
        System.out.println(backspaceCompare(s, t));

        s = "a##c"; t = "#a#c";
        System.out.println(backspaceCompare(s, t));

        s = "a#c"; t = "b";
        System.out.println(backspaceCompare(s, t));

        s="y#fo##f";t="y#f#o##f";
        System.out.println(backspaceCompare(s, t));
    }

    public static boolean backspaceCompare(String s, String t) {
        String rs = backCompelete(s);
        String rt = backCompelete(t);

        if (rs.length() != rt.length()) {
            return false;
        }

        int i = 0;
        while (i < rs.length()) {
            if (rs.charAt(i) != rt.charAt(i)) {
                return false;
            }
            i++;
        }

        return true;
    }

    public static String backCompelete(String s){
        char[] cs = s.toCharArray();
        int size = cs.length;
        int i = 0;
        while (i < size) {
            if (cs[i] == '#') {
                if (i - 1 < 0) {
                    for (int j = 1; j < size; j++) {
                        cs[j - 1] = cs[j];
                    }
                    size--;
                } else {
                    for (int j = i + 1; j < size; j++) {
                        cs[j - 2] = cs[j];
                    }
                    size = size - 2;
                    i--;
                }
            } else {
                i++;
            }
        }
        return new String(cs,0,size);
    }

    //逆序遍历
}
