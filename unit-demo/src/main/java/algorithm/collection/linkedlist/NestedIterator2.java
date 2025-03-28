package algorithm.collection.linkedlist;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * 扁平化嵌套列表迭代器
 * https://leetcode-cn.com/problems/flatten-nested-list-iterator/
 *
 * @author lee
 * @since 2022/1/20
 */
public class NestedIterator2 implements Iterator<Integer> {
    List<Integer> list;
    Iterator<Integer> iterator;

    public NestedIterator2(List<NestedInteger> nestedList) {
        list = new ArrayList<>();
        dfs(nestedList);
        iterator = list.iterator();
    }

    @Override
    public Integer next() {
        return iterator.next();
    }

    @Override
    public boolean hasNext() {
        return iterator.hasNext();
    }

    public void dfs(List<NestedInteger> nestedList) {
        for (int i = 0; i < nestedList.size(); i++) {
            if (nestedList.get(i).isInteger()) {
                list.add(nestedList.get(i).getInteger());
            } else {
                dfs(nestedList.get(i).getList());
            }
        }
    }
}
