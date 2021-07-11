package algorithm.string;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * @author lee
 * @date 2020-08-02
 * 分割回文串，字符串被分割后的所有子串都是回文串
 */
public class PartitionPalindrome {
    public static void main(String[] args) {
        List<List<String>> list = partition("abacdc");
        System.out.println(list);
    }

    /**
     * 参考题解
     * @param s
     * @return
     */
    public static List<List<String>> partition(String s) {
        int len = s.length();
        List<List<String>> res = new ArrayList<>();
        if (len == 0) {
            return res;
        }

        Deque<String> stack = new ArrayDeque<>();
        backtracking(s, 0, len, stack, res);
        return res;
    }

    /**
     * @param s
     * @param start 起始字符的索引
     * @param len   字符串 s 的长度，可以设置为全局变量
     * @param path  记录从根结点到叶子结点的路径
     * @param res   记录所有的结果
     */
    private static void backtracking(String s, int start, int len, Deque<String> path, List<List<String>> res) {
        if (start == len) {
            res.add(new ArrayList<>(path));
            return;
        }

        for (int i = start; i < len; i++) {
            if (!isPalindrome(s, start, i)) {
                continue;
            }

            path.addLast(s.substring(start, i + 1));
            backtracking(s, i + 1, len, path, res);
            path.removeLast();
        }
    }

    public static boolean isPalindrome(String s, int begin, int end) {
        if (begin > end) throw new IllegalArgumentException();
        while (begin < end) {
            if (s.charAt(begin) != s.charAt(end)) return false;
            begin++;
            end--;
        }
        return true;
    }
}
