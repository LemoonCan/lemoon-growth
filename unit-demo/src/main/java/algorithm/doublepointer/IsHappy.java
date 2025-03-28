package algorithm.doublepointer;

import java.util.HashSet;
import java.util.Set;

/**
 * 快乐数
 * https://leetcode-cn.com/problems/happy-number/
 *
 * @author lee
 * @since 2021/12/31
 */
public class IsHappy {
    public static void main(String[] args) {
        IsHappy happy = new IsHappy();
        System.out.println(happy.isHappy(7));
        System.out.println(happy.isHappy2(19));
        System.out.println(happy.isHappy2(2));
    }

    public boolean isHappy(int n) {
        Set<Integer> set = new HashSet<>();
        int sum = n;
        while (sum != 1 && !set.contains(sum)) {
            set.add(sum);
            sum = totalSum(sum);
        }
        return sum == 1;
    }

    public int totalSum(int n) {
        int sum = 0;
        while (n != 0) {
            int remainder = n % 10;
            sum += remainder * remainder;
            n = n / 10;
        }
        return sum;
    }

    /**
     * 快慢指针
     * @param n
     * @return
     */
    public boolean isHappy2(int n) {
        int slow = n, quick = totalSum(n);
        while (slow != 1 && slow != quick) {
            slow = totalSum(slow);
            quick = totalSum(totalSum(quick));
        }
        return slow==1;
    }

}
