package main.javabasic.multithread.test;

import main.javabasic.container.array.CountingIntegerList;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author lee
 * @date 12/30/20
 * 比较SynchronizedArrayList与CopyOnWriteList性能
 */
public class ListComparisons {
    public static void main(String[] args) {
        Tester.initMain(args);
        new SynchronizedArrayListTest(10,0);
        new SynchronizedArrayListTest(9,1);
        new SynchronizedArrayListTest(5,5);
        new CopyOnwriteListTest(10,0);
        new CopyOnwriteListTest(9,5);
        new CopyOnwriteListTest(5,5);
        Tester.exec.shutdown();
    }
}

abstract class ListTest extends Tester<List<Integer>> {

    public ListTest(String testId, int nReaders, int nWriters) {
        super(testId, nReaders, nWriters);
    }

    class Reader extends TestTask {
        long result = 0;

        void test() {
            for (int i = 0; i < testCycles; i++) {
                for (int index = 0; index < containerSize; index++) {
                    result += testContainer.get(index);
                }
            }
        }

        @Override
        void putResults() {
            readResult += result;
            readTime += duration;
        }
    }

    class Writer extends TestTask{
        void test(){
            for (int i = 0; i < testCycles; i++) {
                for (int index = 0; index < containerSize; index++) {
                    testContainer.set(index, writeData[index]);
                }
            }
        }
        @Override
        void putResults() {
            writeTime+=duration;
        }
    }

    @Override
    void startReaderAndWriters(){
        for (int i = 0; i < nReaders; i++) {
            exec.execute(new Reader());
        }
        for (int i = 0; i < nWriters; i++) {
            exec.execute(new Writer());
        }
    }
}

class SynchronizedArrayListTest extends ListTest{
    public SynchronizedArrayListTest(int nReaders, int nWriters) {
        super("Synched ArrayList", nReaders, nWriters);
    }

    @Override
    List<Integer> containerInitializer() {
        return Collections.synchronizedList(new ArrayList<>(new CountingIntegerList(containerSize)));
    }
}

class CopyOnwriteListTest extends ListTest{

    public CopyOnwriteListTest(int nReaders, int nWriters) {
        super("CopyOnwriteListTest", nReaders, nWriters);
    }

    @Override
    List<Integer> containerInitializer() {
        return new CopyOnWriteArrayList<>(new CountingIntegerList(containerSize));
    }
}