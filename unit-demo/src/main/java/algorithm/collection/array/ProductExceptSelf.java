package algorithm.collection.array;

import java.util.Arrays;

/**
 * 除自身以外数组的乘积
 * https://leetcode-cn.com/problems/product-of-array-except-self/
 *
 * @author lee
 * @date 2022/1/9
 */
public class ProductExceptSelf {
    public static void main(String[] args) {
        ProductExceptSelf demo = new ProductExceptSelf();
        int[] nums = {1,2,3,4};
        System.out.println(Arrays.toString(demo.productExceptSelf(nums)));
    }

    /**
     * res[i]=Muti([0]~[i-1])*Muti([i+1]~[length-1])
     * i左边乘以i右边
     *
     * @param nums
     * @return
     */
    public int[] productExceptSelf(int[] nums) {
        int[] res = new int[nums.length];
        res[0] = 1;
        for (int i = 1; i < nums.length; i++) {
            res[i] = res[i-1]*nums[i-1];
        }

        int r = 1;
        for (int i = nums.length-1; i >=0 ; i--) {
            res[i] = res[i]*r;
            r*=nums[i];
        }

        return res;
    }
}
