package algorithm.find.binarysearch;

/**
 * 搜索二维矩阵
 * https://leetcode-cn.com/problems/search-a-2d-matrix/
 * @author lee
 * @since 2021/11/5
 */
public class SearchMatrix {
    public boolean searchMatrix(int[][] matrix, int target) {
        int xSize = matrix.length, ySize = matrix[0].length;
        int left = 0, right = xSize * ySize - 1;
        int[] middle = new int[2];
        while (left <= right) {
            int middleN = (right + left) / 2;
            middle[0] = middleN / ySize;
            middle[1] = middleN % ySize;
            if (matrix[middle[0]][middle[1]] == target) {
                return true;
            }
            if (matrix[middle[0]][middle[1]] < target) {
                left = middleN + 1;
            } else {
                right = middleN - 1;
            }
        }
        return false;
    }

    /**
     * 先纵向找行，再横向找列
     * @param matrix
     * @param target
     * @return
     */
    public boolean searchMatrix2(int[][] matrix, int target) {
        int up = 0, down = matrix.length - 1, mid;
        int row = 0;
        while (up <= down) {
            mid = (up + down) / 2;
            if (matrix[mid][0] == target) {
                return true;
            }
            if (matrix[mid][0] > target) {
                down = mid - 1;
                row = down;
            } else {
                row = mid;
                up = mid + 1;
            }
        }

        if (row < 0) {
            return false;
        }
        int left = 0, right = matrix[row].length - 1;
        while (left <= right) {
            mid = (left+right)/2;
            if(matrix[row][mid]==target){
                return true;
            }
            if(matrix[row][mid]>target){
                right = mid-1;
            }else{
                left = mid+1;
            }
        }
        return false;
    }
}
