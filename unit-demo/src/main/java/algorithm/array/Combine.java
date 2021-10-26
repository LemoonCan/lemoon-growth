package algorithm.array;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode-cn.com/problems/combinations/
 * 组合
 * @author lee
 * @date 2021/10/26
 */
public class Combine {
    public static void main(String[] args) {
        System.out.println(combine(5, 4));
    }

    public static List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> combine = new ArrayList<>();
        for (int i = 1; i <= n - k + 1; i++) {
            if(k==1){
                List<Integer> item = new ArrayList<>();
                item.add(i);
                combine.add(item);
                continue;
            }
            if(k==2){
                for (int j = i + 1; j <= n; j++){
                    List<Integer> item = new ArrayList<>();
                    item.add(i);
                    item.add(j);
                    combine.add(item);
                }
                continue;
            }

            for (int j = i + 1; j <= n - k + 2; j++) {
                for (int l = j + k - 2; l <= n; l++) {
                    List<Integer> item = new ArrayList<>();
                    item.add(i);
                    for (int m = j; m < j + k - 2; m++) {
                        item.add(m);
                    }
                    item.add(l);
                    combine.add(item);
                }
            }

        }
        return combine;
    }
}
