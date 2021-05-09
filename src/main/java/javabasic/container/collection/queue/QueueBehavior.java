package javabasic.container.collection.queue;

import javabasic.generics.Generator;

import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.PriorityBlockingQueue;

/**
 * @author lee
 * @date 2020-09-23
 */
public class QueueBehavior {
    private static int count = 10;

    static <T> void test(String msg, Queue<T> queue, Generator<T> gen) {
        System.out.println("========= " + msg + " =========");
        for (int i = 0; i < count; i++) {
            queue.offer(gen.next());
        }
        while (queue.peek() != null) {
            System.out.println(queue.remove() + " ");
        }
        System.out.println();
    }

    static class Gen implements Generator<String> {
        String[] s = ("one two three four five six seven eight nine ten").split(" ");
        int i;

        @Override
        public String next() {
            return s[i++];
        }
    }

    public static void main(String[] args) {
        test("LinkedList", new LinkedList<>(), new Gen());
        test("PriorityQueue", new PriorityQueue<>(), new Gen());
        test("ArrayBlockingQueue", new ArrayBlockingQueue<>(count), new Gen());
        test("ConcurrentLinkedDeque", new ConcurrentLinkedDeque<>(), new Gen());
        test("LinkedBlockingDeque", new LinkedBlockingDeque<>(), new Gen());
        test("PriorityBlockingQueue", new PriorityBlockingQueue<>(), new Gen());
    }
}
