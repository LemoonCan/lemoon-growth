package algorithm.doublepointer;

/**
 * 比较含退格的字符串
 * https://leetcode-cn.com/problems/backspace-string-compare/
 *
 * @author lee
 * @since 2021/11/8
 */
public class BackspaceCompare {
    public static void main(String[] args) {
        String s = "ab#c", t = "ad#c";
        System.out.println(backspaceCompare(s, t));

        s = "ab##";
        t = "c#d#";
        System.out.println(backspaceCompare(s, t));

        s = "a##c";
        t = "#a#c";
        System.out.println(backspaceCompare(s, t));

        s = "a#c";
        t = "b";
        System.out.println(backspaceCompare(s, t));

        s = "y#fo##f";
        t = "y#f#o##f";
        System.out.println(backspaceCompare(s, t));

//        System.out.println(backspaceCompare2("y#fo##f", "y#f#o##f"));
        System.out.println(backspaceCompare2("a##c", "#a#c"));
        System.out.println(backspaceCompare3("a##c", "#a#c"));
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

    public static String backCompelete(String s) {
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
        return new String(cs, 0, size);
    }

    //逆序遍历
    public static boolean backspaceCompare2(String s, String t) {
        int sSkip = 0, tSkip = 0;
        int p1 = s.length() - 1, p2 = t.length() - 1;
        while (p1 >= 0 && p2 >= 0) {
            if (s.charAt(p1) == '#') {
                sSkip++;
                p1--;
                continue;
            }
            if (t.charAt(p2) == '#') {
                tSkip++;
                p2--;
                continue;
            }
            if (sSkip == 0 && tSkip == 0) {
                if (s.charAt(p1) != t.charAt(p2)) {
                    return false;
                }
                p1--;
                p2--;
            } else {
                if (sSkip > 0) {
                    sSkip--;
                    p1--;
                }
                if (tSkip > 0) {
                    tSkip--;
                    p2--;
                }
            }
        }

        while (p1 >= 0) {
            if (s.charAt(p1) == '#') {
                sSkip++;
                p1--;
                continue;
            }
            if (sSkip > 0) {
                p1--;
                sSkip--;
            } else {
                return false;
            }
        }

        while (p2 >= 0) {
            if (t.charAt(p2) == '#') {
                tSkip++;
                p2--;
                continue;
            }
            if (tSkip > 0) {
                p2--;
                sSkip--;
            } else {
                return false;
            }
        }

        return true;
    }

    public static boolean backspaceCompare3(String s, String t) {
        int sSkip = 0, tSkip = 0;
        int sp = s.length() - 1, tp = t.length() - 1;

        while (sp >= 0 || tp >= 0) {
            while (sp >= 0) {
                if (s.charAt(sp) == '#') {
                    sSkip++;
                    sp--;
                } else if (sSkip > 0) {
                    sSkip--;
                    sp--;
                } else {
                    break;
                }
            }

            while (tp >= 0) {
                if (t.charAt(tp) == '#') {
                    tSkip++;
                    tp--;
                } else if (tSkip > 0) {
                    tSkip--;
                    tp--;
                } else {
                    break;
                }
            }


            if (sp >= 0 && tp >= 0) {
                if (s.charAt(sp) != t.charAt(tp)) {
                    return false;
                }
            } else {
                if (sp >= 0 || tp >= 0) {
                    return false;
                }
            }
            sp--;
            tp--;
        }
        return true;
    }
}
