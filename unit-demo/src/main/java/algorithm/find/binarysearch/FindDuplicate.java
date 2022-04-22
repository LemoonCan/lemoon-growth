package algorithm.find.binarysearch;

/**
 * 寻找重复数
 * https://leetcode-cn.com/problems/find-the-duplicate-number/
 *
 * @author lee
 * @date 2022/4/22
 */
public class FindDuplicate {
    public static void main(String[] args) {
        FindDuplicate demo = new FindDuplicate();
        System.out.println(demo.findDuplicate(new int[]{2,1,3,4,5,3}));
    }

    /**
     * 只有一个重复数的情况下
     * 假设 z 是重复数，满足数组中>=z的数x，<=x数的数量 >x；满足数组中<z的数y,<=y数的数量 =y
     * 2,1,3,4,5,3
     * 数             1 2 3 4 5
     * <=目标值的数量   1 2 4 5 6
     *
     * 通过二分法，先计算<=mid 数的数量c，
     * 若c<=mid，则重复数在 mid 的右侧；
     * 若c>mid，则重复数在 mid 的左侧或mid。
     * 结束条件是left>right
     *
     * @param nums
     * @return
     */
    public int findDuplicate(int[] nums) {
        int n = nums.length;
        int l = 1, r = n - 1, ans = -1;
        while (l <= r) {
            int mid = (l + r) >> 1;
            int cnt = 0;
            for (int i = 0; i < n; ++i) {
                if (nums[i] <= mid) {
                    cnt++;
                }
            }
            if (cnt <= mid) {
                l = mid + 1;
            } else {
                r = mid - 1;
                ans = mid;
            }
        }
        return ans;
    }
}
