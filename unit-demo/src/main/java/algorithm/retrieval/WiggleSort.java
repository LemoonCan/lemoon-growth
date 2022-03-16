package algorithm.retrieval;

import java.util.Arrays;

/**
 * https://leetcode-cn.com/problems/wiggle-sort-ii/
 * 摆动排序2
 *
 * @author lee
 * @date 2022/3/16
 */
public class WiggleSort {
    public static void main(String[] args) {
        WiggleSort demo = new WiggleSort();

        int[] nums = {1, 5, 1, 1, 6, 4};
        demo.wiggleSort(nums);
        System.out.println(Arrays.toString(nums));

        int[] nums2 = {1, 5, 1, 1, 6};
        demo.wiggleSort(nums2);
        System.out.println(Arrays.toString(nums2));

        int[] nums3 = {1, 3, 2, 2, 3, 1};
        demo.wiggleSort2(nums3);
        System.out.println(Arrays.toString(nums3));

        int[] nums4 = {1,1,2,2,3,3};
        demo.wiggleSort2(nums4);
        System.out.println(Arrays.toString(nums4));
    }

    /**
     * 快排分成左小于中，右大于中
     * 但对于相同数据无法处理？？？
     *
     * @param nums
     */
    public void wiggleSort(int[] nums) {
        int middle = (nums.length - 1) / 2;
        int pivot = nums.length - 1;
        int left = 0, right = nums.length;
        while (pivot != middle) {
            pivot = right - 1;
            int fMax = -1;
            for (int i = left; i < right - 1; i++) {
                if (nums[i] < nums[pivot]) {
                    if (fMax != -1) {
                        swap(nums, fMax, i);
                        fMax++;
                    }
                } else {
                    if (fMax == -1) {
                        fMax = i;
                    }
                }
            }
            //若所有数都<选的锚点 fMax=-1
            //若所有数都>=选的锚点 fMax=0
            if (fMax == -1) {
                pivot = right - 1;
            } else {
                swap(nums, pivot, fMax);
                pivot = fMax;
            }
            if (pivot < middle) {
                left = pivot + 1;
            } else {
                right = pivot;
            }
        }

        int j;
        //偶数 小(大)小大
        //奇数 小大(小)大小
        if ((nums.length & 1) == 0) {
            j = nums.length - 2;
        } else {
            j = nums.length - 1;
        }

        for (int i = 1; i <= middle && j > middle; i = i + 2, j = j - 2) {
            swap(nums, i, j);
        }
    }

    private void swap(int[] nums, int a, int b) {
        int temp = nums[a];
        nums[a] = nums[b];
        nums[b] = temp;
    }

    /**
     * 先桶排序，
     * 再从大到小，填充数组存放较大数的位置；(i=1开始)
     * 剩余的数字继续从大到小，填充数组存放较小数的位置 (i=0开始)
     *
     * 时间复杂度：O(n)
     * 空间复杂度：O(n)
     *
     * @param nums
     */
    public void wiggleSort2(int[] nums) {
        int[] bucket=new int[5001];
        for(int num:nums) bucket[num]++;
        int j=5000;
        int len=nums.length;

        for(int i=1;i<len;i+=2){
            while (bucket[j]==0)j--;
            nums[i]=j;
            bucket[j]--;
        }

        for(int i=0;i<len;i+=2){
            while (bucket[j]==0)j--;
            nums[i]=j;
            bucket[j]--;
        }
    }
}
