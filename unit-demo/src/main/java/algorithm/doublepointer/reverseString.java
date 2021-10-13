package algorithm.doublepointer;

/**
 * 反转字符串
 * 输入：s = ["h","e","l","l","o"]
 * 输出：["o","l","l","e","h"]
 * @author lee
 * @date 2021/10/13
 */
public class reverseString {
    public void reverseString(char[] s) {
        int left =0,right=s.length-1;
        while (left<right){
            char temp = s[left];
            s[left] = s[right];
            s[right] = temp;
            left++;
            right--;
        }
    }
}
