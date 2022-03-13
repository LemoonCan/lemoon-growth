package algorithm.collection.array;

/**
 * @author lee
 * @date 2020-06-25
 *
 * 编写一个高效的算法来搜索 m x n 矩阵 matrix 中的一个目标值 target。该矩阵具有以下特性：
 * 每行的元素从左到右升序排列。
 * 每列的元素从上到下升序排列。
 */
public class SearchMatrix {
    public static void main(String[] args) {
        int[][] arr = {{1}, {3}, {5}};
        System.out.print(searchMatrix(arr, 2));
        System.out.print(searchMatrix2(arr, 2));
        System.out.print(searchMatrix3(arr, 2));
    }

    /**
     * 时间复杂度：O(m+2n)
     * 空间复杂度：O(1)
     * @param matrix
     * @param target
     * @return
     */
    public static boolean searchMatrix(int[][] matrix, int target) {
        if (matrix.length == 0) {
            return false;
        }
        int colBoundary = 0;
        for (; colBoundary < matrix[0].length; colBoundary++) {
            if (matrix[0][colBoundary] == target) {
                return true;
            }
            if (matrix[0][colBoundary] > target) {
                break;
            }
        }
        int i = 1, j = colBoundary - 1;
        while (i < matrix.length && j >= 0) {
            if (matrix[i][j] == target) {
                return true;
            }
            if (matrix[i][j] > target) {
                j--;
            } else {
                i++;
            }
        }
        return false;
    }

    /**
     * 选右上角：变成只有↓←两个方向遍历
     *
     * 题解补充：
     * 选左上角，往右走和往下走都增大，不能选
     * 选右下角，往上走和往左走都减小，不能选
     * 选左下角，往右走增大，往上走减小，可选
     * 选右上角，往下走增大，往左走减小，可选
     *
     * 时间复杂度：O(m+n)
     * 空间复杂度：O(1)
     * @param matrix
     * @param target
     * @return
     */
    public static boolean searchMatrix2(int[][] matrix, int target) {
        if (matrix.length == 0) {
            return false;
        }
        int colBoundary = matrix[0].length - 1;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = colBoundary; j >= 0; j--) {
                if(matrix[i][j]==target){
                    return true;
                }
                if(matrix[i][j]<target){
                    colBoundary=j;
                    break;
                }
            }
        }
        return false;
    }

    public static boolean searchMatrix3(int[][] matrix, int target) {
        if (matrix.length == 0) {
            return false;
        }
        int colBoundary = matrix[0].length - 1;
        for (int[] row:matrix) {
            for (int j = colBoundary; j >= 0; j--) {
                if(row[j]==target){
                    return true;
                }
                if(row[j]<target){
                    colBoundary=j;
                    break;
                }
            }
        }
        return false;
    }
}
