package algorithm.doublepointer;

/**
 * 反转字符串中的单词
 * 输入："Let's take LeetCode contest"
 * 输出："s'teL ekat edoCteeL tsetnoc"
 * @author lee
 * @date 2021/10/13
 */
public class ReverseWords {
    public String reverseWords(String s) {
        String[] words = s.split(" ");
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < words.length; i++) {
            for (int j = words[i].length() - 1; j >= 0; j--) {
                sb.append(words[i].charAt(j));
            }
            sb.append(' ');
        }
        return sb.substring(0, sb.length() - 1);
    }

    public String reverseWords2(String s) {
        int left = 0, right = 0;
        char[] chars = s.toCharArray();
        while (right <= chars.length) {
            if (right == chars.length || chars[right] == ' ') {
                int reverseRight = right - 1;
                while (left < reverseRight) {
                    char temp = chars[left];
                    chars[left++] = chars[reverseRight];
                    chars[reverseRight--] = temp;
                }
                right++;
                left = right;
            } else {
                right++;
            }
        }
        return new String(chars);
    }
}
