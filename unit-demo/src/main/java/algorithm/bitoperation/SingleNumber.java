package algorithm.bitoperation;

/**
 * @author lee
 * @date 2020-06-23
 *
 * 异或：相同为0，不同为1
 */
public class SingleNumber {
    /**
     * 给定一个数组，除某个元素只出现一次之外，其余元素都会出现两次，请找出只出现一次的元素
     * @param nums
     * @return
     */
    public int singleNumber(int[] nums) {
        int single = 0;
        for (int num : nums) {
            single ^= num;
        }
        return single;
    }

    /**
     * 给定一个数组，除两个元素只出现一次之外，其余元素都会出现两次，请找出只出现一次的两个元素
     */
    public int[] twoSingleNumber(int[] nums){
        //取数组所有值异或后得到的值
        int xor = 0;
        for (int num:nums) {
            xor^=num;
        }
        //xor中为1的位一定是只出现一次的两个元素的不同位，假设是第x位；
        //对数组分组，与第x位按位与为0的为一组，为1的为另一组；则两个不同元素一定会被分到不同组；
        //再对两组分别异或得到的值就是只出现一次的两个元素。
        int xorHighestOneBit = Integer.highestOneBit(xor);
        int[] single = {0,0};
        for (int num:nums) {
            if((xorHighestOneBit&num)==0){
                single[0]^=num;
            }else{
                single[1]^=num;
            }
        }
        return single;
    }
}
