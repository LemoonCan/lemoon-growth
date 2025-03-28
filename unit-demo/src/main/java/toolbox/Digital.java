package toolbox;

/**
 * 数字工具
 * @author lee
 * @since 2022/2/9
 */
public class Digital {
    /**
     * 是否奇数
     * @param val
     * @return
     */
    public static boolean judgeOdd(int val) {
        return (val & 1) != 0;
    }
}
