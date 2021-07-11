package algorithm;

import java.util.Arrays;

/**
 * @author lee
 * @date 2019/9/12
 */
public class Sort {
    public static void main(String[] args) {
        int[] nums = {-2, 3, -5};
        Sort sort = new Sort();
        sort.mergeSort(nums);
        System.out.println(Arrays.toString(nums));

        int[] nums2 ={5,2,3,1};
        sort.selectSort(nums2);
        System.out.println(Arrays.toString(nums2));
    }

    /**
     * 冒泡排序
     * 最好情况时间复杂度：n^2
     * 最坏情况时间复杂度：n
     *
     * @param nums
     * @return
     */
    public int[] bubbleSort(int[] nums) {
        if (nums.length <= 1) return nums;
        for (int i = 0; i < nums.length; i++) {
            boolean flag = true;
            for (int j = 1; j < nums.length - i; j++) {
                if (nums[j] < nums[j - 1]) {
                    int temp = nums[j - 1];
                    nums[j - 1] = nums[j];
                    nums[j] = temp;
                    flag = false;
                }
            }
            if (flag) {
                break;
            }
        }
        return nums;
    }

    /**
     * 插入排序
     * 最坏情况时间复杂度：n^2
     * 最好情况时间复杂度：n
     * 空间复杂度：O(1)
     * 原地排序算法
     * (冒泡和插入对比，会选择插入，因为赋值操作更少)
     *
     * @param nums
     * @return
     */
    public int[] insertionSort(int[] nums) {
        if (nums.length <= 1) return nums;

        for (int i = 1; i < nums.length; i++) {
            int value = nums[i];
            int j = i - 1;
            for (; j >= 0; j--) {
                if (nums[j] > value) {
                    nums[j + 1] = nums[j];
                } else {
                    break;
                }
            }
            nums[j + 1] = value;
        }

        return nums;
    }

    /**
     * 选择排序
     * 最坏情况时间复杂度：n^2
     * 最好情况时间复杂度：n^2
     *
     * 不稳定排序算法
     */
    public int[] selectSort(int[] nums) {
        if (nums.length <= 1) return nums;

        int minIndex;
        for (int i = 0; i < nums.length - 1; i++) {
            minIndex = i;
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[j] < nums[minIndex]) {
                    minIndex = j;
                }
            }
            if (minIndex != i) {
                int temp = nums[i];
                nums[i] = nums[minIndex];
                nums[minIndex] = temp;
            }
        }
        return nums;
    }

    /**
     * 归并排序
     * 时间复杂度：
     */
    public int[] mergeSort(int[] nums) {
        mergeSortC(nums, 0, nums.length - 1);
        return nums;
    }

    void mergeSortC(int[] nums, int p, int r) {
        if (p >= r) return;

        int q = (p + r) / 2;
        mergeSortC(nums, p, q);
        mergeSortC(nums, q + 1, r);

        merge(nums, p, q, r);
    }

    void merge(int[] all, int p, int q, int r) {
        int[] tmp = new int[all.length];
        int k = 0, i = p, j = q + 1;
        for (; i <= q && j <= r; ) {
            if (all[i] <= all[j]) {
                tmp[k++] = all[i++];
            } else {
                tmp[k++] = all[j++];
            }
        }

        if (i <= q) {
            for (; i <= q; ) {
                tmp[k++] = all[i++];
            }
        }
        if (j <= r) {
            for (; j <= r; ) {
                tmp[k++] = all[j++];
            }
        }

        for (int index = 0; index < k; index++) {
            all[p + index] = tmp[index];
        }
    }

    /**
     * 快速排序
     */
    public int[] quickSort(int[] nums) {
        quickSortC(nums, 0, nums.length - 1);
        return nums;
    }

    void quickSortC(int[] nums, int begin, int end) {
        if (begin >= end) return;

        int middle = partition(nums, begin, end);
        quickSortC(nums, begin, middle - 1);
        quickSortC(nums, middle + 1, end);
    }

    int partition(int[] nums, int begin, int end) {
        int partion = begin, index = begin;
        for (; index < end; index++) {
            if (nums[index] <= nums[end]) {
                int tmp = nums[partion];
                nums[partion] = nums[index];
                nums[index] = tmp;
                partion++;
            }
        }
        int tmp = nums[end];
        nums[end] = nums[partion];
        nums[partion] = tmp;
        return partion;
    }

    /**
     * 桶排序
     */

    /**
     * 计数排序
     */

    /**
     * 基数排序
     */

    /**
     * 堆排序
     */

}

