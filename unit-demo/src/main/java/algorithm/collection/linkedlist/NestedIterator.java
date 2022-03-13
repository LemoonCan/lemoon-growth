package algorithm.collection.linkedlist;

import java.util.*;

/**
 * 扁平化嵌套列表迭代器
 * https://leetcode-cn.com/problems/flatten-nested-list-iterator/
 *
 * @author lee
 * @date 2022/1/19
 */
public class NestedIterator implements Iterator<Integer> {
    Stack<Iterator<NestedInteger>> stack;

    public NestedIterator(List<NestedInteger> nestedList) {
        stack = new Stack<>();
        stack.push(nestedList.iterator());
    }

    @Override
    public Integer next() {
        return stack.pop().next().getInteger();
    }

    @Override
    public boolean hasNext() {
        while (!stack.empty()){
            Iterator<NestedInteger> iterator = stack.peek();
            if(!iterator.hasNext()){
                stack.pop();
                continue;
            }
            NestedInteger item = iterator.next();
            if (item.isInteger()){
                List<NestedInteger> list = Arrays.asList(item);
                stack.push(list.iterator());
                return true;
            }
            stack.push(item.getList().iterator());
        }
        return false;
    }
}

interface NestedInteger {
    // @return true if this NestedInteger holds a single integer, rather than a nested list.
    boolean isInteger();

    // @return the single integer that this NestedInteger holds, if it holds a single integer
    // Return null if this NestedInteger holds a nested list
    Integer getInteger();

    // @return the nested list that this NestedInteger holds, if it holds a nested list
    // Return empty list if this NestedInteger holds a single integer
    List<NestedInteger> getList();
}

