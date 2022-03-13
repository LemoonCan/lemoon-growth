package algorithm.collection.heap;

/**
 * @author lee
 * @date 2022/1/25
 */
public class Heap {
    private int[] a;
    private int n;
    private int size;

    public Heap(int capacity) {
        a = new int[capacity + 1];
        n = capacity;
        size = 0;
    }

    public void offer(int ele) {
        //扩容
        if (size >= n) {
        }
        a[++size] = ele;
        int cur = size;
        //从下至上堆化
        while (cur > 1) {
            if (a[cur / 2] < a[cur]) {
                int temp = a[cur];
                a[cur] = a[cur / 2];
                a[cur / 2] = temp;
                cur = cur / 2;
            } else {
                break;
            }
        }
    }

    public int pop() {
        if (size < 1) throw new IllegalStateException();
        int ele = a[0];
        size--;
        a[1] = a[size + 1];
        int cur = 1;
        while (true) {
            int left = cur * 2;
            int right = left + 1;
            int maxPos = cur;
            if (left <= size && a[left] > a[cur]) maxPos = left;
            if (right <= size && a[right] > a[cur]) maxPos = right;
            if (maxPos == cur) break;
            swap(cur, maxPos);
            cur = maxPos;
        }
        return ele;
    }

    private void swap(int index1, int index2) {
        int temp = a[index1];
        a[index1] = a[index2];
        a[index2] = temp;
    }
}
