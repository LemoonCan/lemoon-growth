package main.algorithm.array;

/**
 * @author lee
 * @date 2020-06-29
 *
 * 合并两个有序数组nums1、nums2；
 * nums1足够大，合并后的数组存储在nums1。
 */
public class MergeSort {
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int i = 0;
        while (i < m && n>0) {
            if (nums1[i] > nums2[0]) {
                int temp = nums1[i];
                nums1[i] = nums2[0];
                nums2[0] = temp;

                for (int j = 1; j < n; j++) {
                    if (nums2[j] >= nums2[j - 1]) {
                        break;
                    } else {
                        temp = nums2[j - 1];
                        nums2[j - 1] = nums2[j];
                        nums2[j] = temp;
                    }
                }
            }
            i++;
        }
        for (int j = m; j < n + m; j++) {
            nums1[j] = nums2[j - m];
        }
    }

    //从后开始
}
