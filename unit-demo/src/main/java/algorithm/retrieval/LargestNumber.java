package algorithm.retrieval;

import java.util.Arrays;

/**
 * https://leetcode-cn.com/problems/largest-number/
 * 最大数
 *
 * @author lee
 * @date 2022/3/15
 */
public class LargestNumber {
    private int aIndex;
    private int bIndex;

    public static void main(String[] args) {
        LargestNumber demo = new LargestNumber();
        int[] nums = {10, 2};
        System.out.println(demo.largestNumber2(nums));

        int[] nums1 = {3, 30, 34, 5, 9};
        System.out.println(demo.largestNumber2(nums1));

        int[] nums2 = {8308, 8308, 830};
        System.out.println(demo.largestNumber2(nums2));

        int[] nums3 = {111311, 1113};
        System.out.println(demo.largestNumber2(nums3));

        int[] nums4 = {10, 2, 9, 39, 17};
        System.out.println(demo.largestNumber2(nums4));

        int[] nums5 = {432, 43243};
        System.out.println(demo.largestNumber2(nums5));

        int[] nums6 = {999999991, 9};
        System.out.println(demo.largestNumber2(nums6));

        int[] nums7 = {0, 0};
        System.out.println(demo.largestNumber2(nums7));
    }

    public String largestNumber(int[] nums) {
        if (nums.length <= 1) {
            return String.valueOf(nums[0]);
        }

        String[] numsString = new String[nums.length];
        for (int i = 0; i < nums.length; i++) {
            numsString[i] = String.valueOf(nums[i]);
        }

        for (int i = 1; i < numsString.length; i++) {
            String temp = numsString[i];
            int j = i;
            for (; j > 0; j--) {
                if (moreThan(temp, numsString[j - 1])) {
                    numsString[j] = numsString[j - 1];
                } else {
                    break;
                }
            }
            numsString[j] = temp;
        }

        if (numsString[0].equals("0")) {
            return "0";
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < numsString.length; i++) {
            sb.append(numsString[i]);
        }
        return sb.toString();
    }

    public boolean moreThan(String a, String b) {
        aIndex = 0;
        bIndex = 0;
        if (nestMoreThan(a, b)) return true;

        while ((aIndex >= a.length() && bIndex < b.length()) ||
                (aIndex < a.length() && bIndex >= b.length())) {
            if (aIndex >= a.length() && bIndex < b.length()) {
                aIndex = 0;
                if (nestMoreThan(a, b)) return true;
            }
            if (aIndex < a.length() && bIndex >= b.length()) {
                bIndex = 0;
                if (nestMoreThan(a, b)) return true;
            }
        }

        return false;
    }

    private Boolean nestMoreThan(String a, String b) {
        while (aIndex < a.length() && bIndex < b.length()) {
            if (a.charAt(aIndex) > b.charAt(bIndex)) {
                return true;
            } else if (a.charAt(aIndex) < b.charAt(bIndex)) {
                break;
            }
            aIndex++;
            bIndex++;
        }
        return false;
    }

    public String largestNumber2(int[] nums) {
        if (nums.length <= 1) {
            return String.valueOf(nums[0]);
        }

        Integer[] numsArr = new Integer[nums.length];
        for (int i = 0; i < numsArr.length; i++) {
            numsArr[i] = nums[i];
        }

        Arrays.sort(numsArr, (x, y) -> {
            int sx = 10, sy = 10;
            while (sx <= x) {
                sx *= 10;
            }
            while (sy <= y) {
                sy *= 10;
            }
            return x * sy - y * sx;
        });

        if (nums[0] == 0) {
            return "0";
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < numsArr.length; i++) {
            sb.append(numsArr[i]);
        }
        return sb.toString();
    }
}
