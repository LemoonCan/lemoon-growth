package algorithm.hashandmapping;

import java.util.*;

/**
 * 常数时间插入、删除和随机获取元素
 * https://leetcode-cn.com/problems/insert-delete-getrandom-o1/
 *
 * @author lee
 * @date 2022/2/12
 */
public class RandomizedSet {
    Map<Integer, Integer> dict;
    List<Integer> list;
    Random rand = new Random();

    public RandomizedSet() {
        dict = new HashMap();
        list = new ArrayList();
    }

    public boolean insert(int val) {
        if (dict.containsKey(val)) return false;

        //值、list中的序号
        dict.put(val, list.size());
        list.add(list.size(), val);
        return true;
    }

    public boolean remove(int val) {
        if (! dict.containsKey(val)) return false;

        //移动最后一个元素 到 删除元素位置
        int lastElement = list.get(list.size() - 1);
        int idx = dict.get(val);
        list.set(idx, lastElement);
        dict.put(lastElement, idx);
        //删除最后一个元素
        list.remove(list.size() - 1);
        dict.remove(val);
        return true;
    }

    public int getRandom() {
        return list.get(rand.nextInt(list.size()));
    }
}
