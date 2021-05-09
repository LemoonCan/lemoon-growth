package algorithm.collection;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @author lee
 * @date 2020-09-07
 */
public class StackTest {
}

class MinStack {
    private List<Integer> elements;

    /** initialize your data structure here. */
    public MinStack() {
        elements = new ArrayList<>();
    }

    public void push(int x) {
        elements.add(x);
    }

    public void pop() {
        elements.remove(elements.size()-1);
    }

    public int top() {
        return elements.get(elements.size()-1);
    }

    public int getMin() {
        int min = elements.get(0);
        for (int item:elements) {
            if(item<min){
                min=item;
            }
        }
        return min;
    }
}

/**
 * 不会存在最小的数先出栈，最小栈里未保存栈中其他数的情况
 * 因为栈中所有的数都与最小栈已存数比较过，并保留了最小的数；
 * 所以在最小栈出栈的元素出栈前，在该元素之后入栈的元素会先出栈，也就不存在上述情况。
 *
 * 最小栈栈顶保留的是栈中现有元素的最小值
 */
class MinStack2 {
    private Stack<Integer> stack;
    /**
     * 比栈顶元素小，则压入栈
     */
    private Stack<Integer> minStack;
    public MinStack2() {
        stack = new Stack<>();
        minStack = new Stack<>();
    }
    public void push(int x) {
        stack.push(x);
        if(minStack.isEmpty() || x <= minStack.peek())
            minStack.push(x);
    }
    public void pop() {
        if(stack.pop().equals(minStack.peek()))
            minStack.pop();
    }
    public int top() {
        return stack.peek();
    }
    public int getMin() {
        return minStack.peek();
    }
}

/**
 * Your MinStack object will be instantiated and called as such:
 * MinStack obj = new MinStack();
 * obj.push(x);
 * obj.pop();
 * int param_3 = obj.top();
 * int param_4 = obj.getMin();
 */
