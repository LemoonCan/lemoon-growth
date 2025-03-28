package algorithm.collection.heap;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * @author lee
 * @since 2020-11-09
 * 数据流中的中位数
 */
public class MedianFinder {
    /**
     * minHeap 维护小顶堆，维护较大的一半
     * maxHeap 维护大顶堆，维护较小的一半
     *
     * 假设总共有N个数，A的数据量为m，B的数据量为n；有N=m+n
     * 若N为奇数，m=(N+1)/2，n=(N-1)/2；则有m=n+1
     * 若N为偶数，m=N/2，n=N/2
     * 所以若m=n，插入A；m!=n插入B
     */
    Queue<Integer> minHeap, maxHeap;

    public MedianFinder() {
        minHeap = new PriorityQueue<>();
        maxHeap = new PriorityQueue<>(Comparator.reverseOrder());
    }

    public void addNum(int num) {
        if (minHeap.size() == maxHeap.size()) {
            maxHeap.add(num);
            minHeap.add(maxHeap.poll());
        } else {
            minHeap.add(num);
            maxHeap.add(minHeap.poll());
        }
    }

    public double findMedian() {
        if (minHeap.size() == maxHeap.size()) {
            return (minHeap.peek() + maxHeap.peek()) / 2.0d;
        } else {
            return minHeap.peek();
        }
    }
}
