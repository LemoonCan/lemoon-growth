package javabasic.container.array;

import java.util.Arrays;
import java.util.Random;

/**
 * @author lee
 * @date 2020-09-09
 */
public class MultiDimensionalPrimitiveArray {
    public static void main(String[] args) {
        int[][] a = {
                {1, 2, 3},
                {4, 5, 6}
        };
        System.out.println("==============相同长度二维数组聚集初始化==============");
        System.out.println(Arrays.deepToString(a));
        int[][] d = {
                {1, 2, 3},
                {4, 5},
                {6, 7, 8, 9}
        };
        System.out.println("==============不同长度二维数组聚集初始化==============");
        System.out.println(Arrays.deepToString(a));

        int[][][] b = new int[2][2][4];
        System.out.println("==============三维数组初始化==============");
        System.out.println(Arrays.deepToString(b));

        Random random = new Random();
        int[][][] c = new int[random.nextInt(7)][][];
        for (int i = 0; i < c.length; i++) {
            c[i] = new int[random.nextInt(5)][];
            for (int j = 0; j < c[i].length; j++) {
                c[i][j] = new int[random.nextInt(5)];
            }
        }
        System.out.println("==============不同长度三维数组赋值==============");
        System.out.println(Arrays.deepToString(c));
    }
}
