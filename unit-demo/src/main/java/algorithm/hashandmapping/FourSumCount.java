package algorithm.hashandmapping;

import java.util.HashMap;
import java.util.Map;

/**
 * 四数相加
 * https://leetcode-cn.com/problems/4sum-ii/
 *
 * @author lee
 * @date 2022/2/11
 */
public class FourSumCount {
    public int fourSumCount(int[] nums1, int[] nums2, int[] nums3, int[] nums4) {
        Map<Integer, Integer> sum1 = new HashMap<>();
        for (int i = 0; i < nums1.length; i++) {
            if (sum1.containsKey(nums1[i])) {
                sum1.put(nums1[i], sum1.get(nums1[i]) + 1);
            } else {
                sum1.put(nums1[i], 1);
            }
        }
        Map<Integer, Integer> sum2 = new HashMap<>();
        add(sum1, nums2, sum2);
        Map<Integer,Integer> sum3 = new HashMap<>();
        add(sum2, nums3, sum3);

        int count = 0;
        for (int i = 0; i < nums4.length; i++) {
            int cal = 0 - nums4[i];
            if (sum3.containsKey(cal)) {
                count = count+sum3.get(cal);
            }
        }
        return count;
    }

    public void add(Map<Integer, Integer> add1, int[] add2, Map<Integer, Integer> sum) {
        for (Map.Entry<Integer, Integer> item : add1.entrySet()) {
            for (int i = 0; i < add2.length; i++) {
                int cal = item.getKey() + add2[i];
                if (sum.containsKey(cal)) {
                    sum.put(cal, sum.get(cal) + item.getValue());
                } else {
                    sum.put(cal, item.getValue());
                }
            }
        }
    }
}
