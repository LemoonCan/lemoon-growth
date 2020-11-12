package main.javabasic.multithread.share;

/**
 * @author lee
 * @date 2020-11-12
 */
public class EvenGenerator extends IntGenerator {
    private int currentEvenValue = 0;
    @Override
    public int next() {
        ++currentEvenValue;
        ++currentEvenValue;
        return currentEvenValue;
    }

    public static void main(String[] args) {
        EventChecker.test(new EvenGenerator());
    }
}
