package algorithm.doublepointer;

import java.util.Arrays;

/**
 * 两个数组的交集2
 * https://leetcode-cn.com/problems/intersection-of-two-arrays-ii/
 *
 * @author lee
 * @since 2022/1/7
 */
public class Intersect {
    public static void main(String[] args) {

    }

    public int[] intersect(int[] nums1, int[] nums2) {
        Arrays.sort(nums1);
        Arrays.sort(nums2);

        int size=Math.min(nums1.length,nums2.length);
        int[] res = new int[size];
        int index = 0;

        int l1=0,l2=0;
        while (l1<nums1.length&&l2<nums2.length){
            if(nums1[l1]<nums2[l2]){
                l1++;
            }else if(nums1[l1]==nums2[l2]){
                res[index] = nums1[l1];
                l1++;
                l2++;
                index++;
            }else{
                l2++;
            }
        }

        return Arrays.copyOfRange(res,0,index);
    }
}
