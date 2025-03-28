package algorithm.find.binarysearch;

/**
 * @author lee
 * @since 2021/10/9
 *
 * 二分查找
 */
public class BinarySearch {
    public static void main(String[] args) {
        int[] nums = {-1, 0, 3, 5, 9, 12};
        System.out.println(search(nums, 9));
        System.out.println(search(nums, 2));
    }

    public static int search(int[] data, int target) {
        int left = 0, right = data.length - 1, mid;
        while (left <= right) {
            mid = (right + left) / 2;
            if (target == data[mid]) {
                return mid;
            } else if (target > data[mid]) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return -1;
    }
}
