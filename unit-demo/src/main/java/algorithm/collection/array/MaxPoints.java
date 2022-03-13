package algorithm.collection.array;

import java.util.HashMap;
import java.util.Map;

/**
 * 直线上最多的点数
 * https://leetcode-cn.com/problems/max-points-on-a-line/
 * @author lee
 * @date 2022/1/4
 */
public class MaxPoints {
    public static void main(String[] args) {
        int[][] points = {{1, 1}, {3, 2}, {5, 3}, {4, 1}, {2, 3}, {1, 4}};
        MaxPoints demo = new MaxPoints();
        System.out.println(demo.maxPoints(points));
    }

    public int maxPoints(int[][] points) {
        int max = 1;
        Map<String, Integer> linearEquation = new HashMap<>();
        for (int i = 0; i < points.length - max; i++) {
            for (int j = i + 1; j < points.length; j++) {
                int y = points[j][1] - points[i][1];
                int x = points[j][0] - points[i][0];
                String equation;
                if (y == 0) {
                    equation = "y=" + points[i][1];
                } else if (x == 0) {
                    equation = "x=" + points[i][0];
                } else {
                    float a = y / (float) x;
                    float b = points[i][1] - points[i][0] * a;
                    equation = "y=" + a + "*x+" + b;
                }
                Integer count;
                if (linearEquation.containsKey(equation)) {
                    count = linearEquation.get(equation);
                    count++;
                } else {
                    count = 2;
                }
                if (count > max) {
                    max = count;
                }
                linearEquation.put(equation, count);
            }
            linearEquation.clear();
        }
        return max;
    }

    public int maxPoints2(int[][] points) {
        int n = points.length;
        if (n < 3) {
            return n;
        }
        int res = 2;
        for (int i = 0; i < n - res; i++) {
            for (int j = i + 1; j < n - 1; j++) {
                int cur = 2;
                int k1 = points[i][0] - points[j][0];
                int k2 = points[i][1] - points[j][1];
                int s = points[i][1] * k1 - points[i][0] * k2;
                for (int k = j + 1; k < n; k++) {
                    if (points[k][1] * k1 - points[k][0] * k2 == s) {
                        cur++;
                    }
                }
                res = Math.max(res, cur);
            }
        }
        return res;
    }
}
