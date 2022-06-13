package algorithm.retrieval;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 计算右侧小于当前元素的个数
 * https://leetcode.cn/problems/count-of-smaller-numbers-after-self/
 *
 * @author lee
 * @date 2022/6/8
 */
public class CountSmaller {
    public static void main(String[] args) {
        CountSmaller demo = new CountSmaller();
//      {5, 2, 6, 1};
//      {2,0,1};
        int[] nums = {1,9,7,8,5};
        System.out.println(demo.countSmaller(nums));
    }

    class Pair {
        int val;
        int id;

        public Pair(int val, int id) {
            this.val = val;
            this.id = id;
        }
    }

    /**
     * 右侧小于当前元素的数量
     */
    int[] count;
    /**
     * 归并排序使用的辅助数组
     */
    Pair[] temp;

    public List<Integer> countSmaller(int[] nums) {
        Pair[] pairs = new Pair[nums.length];
        temp = new Pair[nums.length];
        for (int i = 0; i < nums.length; i++) {
            pairs[i] = new Pair(nums[i], i);
        }
        count = new int[nums.length];
        mergeSort(pairs, 0, nums.length - 1);

        return Arrays.stream(count).boxed().collect(Collectors.toList());
    }

    public void mergeSort(Pair[] pairs, int begin, int end) {
        if (begin >= end) {
            return;
        }
        int mid = begin + (end - begin) / 2;
        mergeSort(pairs, begin, mid);
        mergeSort(pairs, mid + 1, end);

        for (int i = begin; i <= end; i++) {
            temp[i] = pairs[i];
        }
        //i表示左侧当前数据，j表示右侧当前数据
        int i = begin, j = mid + 1;
        int index = begin;
        //轮到左侧数据填入最终数组时，说明右侧当前数据大于左侧当前数据；
        //所以[mid+1,j) 都小于左侧当前数据
        //所以 左侧当前数据 计数+(j-mid-1)
        while (index <= end) {
            if (i > mid) {
                pairs[index++] = temp[j++];
            } else if (j > end) {
                count[temp[i].id] += j - mid - 1;
                pairs[index++] = temp[i++];
            } else if (temp[i].val > temp[j].val) {
                pairs[index++] = temp[j++];
            } else {
                count[temp[i].id] += j - mid - 1;
                pairs[index++] = temp[i++];
            }
        }
    }
}
