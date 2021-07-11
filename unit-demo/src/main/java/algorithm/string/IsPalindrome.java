package algorithm.string;

/**
 * @author lee
 * @date 2020-06-29
 * 是否回文字符(只有数字和字母为有效字符)
 */
public class IsPalindrome {
    public static void main(String[] args) {
        String s = ",,,,,,,,,,,,acva";
        isPalindrome(s);
        System.out.println(isPalindrome2(s));
    }

    /**
     * A~Z 65-90
     * a~z 97-122
     *
     * A man, a plan, a canal: Panama
     * 回文字符串，推断从0开始到中间位置的字符都能按序找到从末位开始到中间位置的字符
     * 一头一尾比较，遇到非有效字符则跳过，同步更新中间位置；
     * 只要遇到字符不等的情况则证明不是
     *
     * @param s
     * @return
     */
    public static boolean isPalindrome(String s) {
        char[] chars = s.toCharArray();
        //1,,32 262731
        int end=chars.length;
        float middle=chars.length/2;
        for (int i = 0; i < middle && middle<chars.length; i++) {
            if(Character.isLetter(chars[i])||Character.isDigit(chars[i])){
                end--;
                while (end>=middle){
                    if(Character.isLetter(chars[end])||Character.isDigit(chars[end])){
                        if(Character.toLowerCase(chars[i])!=Character.toLowerCase(chars[end])){
                            return false;
                        }
                        break;
                    }else{
                        end--;
                        middle-=0.5;
                    }
                }
            }else{
                middle+=0.5;
            }
        }
        return true;
    }

    /**
     * 使用双指针。初始时，左右指针分别指向s的两侧，
     * 随后我们不断地将这两个指针相向移动，每次移动一步，并判断这两个指针指向的字符是否相同。
     * 当这两个指针相遇时，就说明s是回文串。
     */
    public static boolean isPalindrome2(String s) {
        char[] chars = s.toCharArray();
        int left=0,right=chars.length-1;
        while(left<right){
            while(!isLetterOrNum(chars[left])&&left<right){
                left++;
            }
            while (!isLetterOrNum(chars[right])&&left<right){
                right--;
            }
            if(Character.toLowerCase(chars[left])!=Character.toLowerCase(chars[right])){
                return false;
            }
            if(left<right){
                left++;
                right--;
            }
        }
        return true;
    }

    private static boolean isLetterOrNum(char c){
        return Character.isLetter(c)||Character.isDigit(c);
    }
}
