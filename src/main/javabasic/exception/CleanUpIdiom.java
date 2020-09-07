package main.javabasic.exception;

/**
 * @author lee
 * @date 2020-09-07
 */
public class CleanUpIdiom {
    public static void main(String[] args) {
        NeedsCleanup nc1 = new NeedsCleanup();
        try {
            System.out.println("nc1 action");
        } finally {
            nc1.dispose();
        }

        //无异常抛出的需要清理的对象，可合并处理
        NeedsCleanup nc2 = new NeedsCleanup();
        NeedsCleanup nc3 = new NeedsCleanup();
        try {
            System.out.println("nc2 action");
            System.out.println("nc3 action");
        } finally {
            nc3.dispose();
            nc2.dispose();
        }

        //可以失败的抛出不同异常的构造器，需分别处理
        //需要在finally清理的对象抛出异常，需分别处理
        try {
            NeedsCleanup2 nc4 = new NeedsCleanup2();
            NeedsCleanup2 nc5 = new NeedsCleanup2();
            try {
                System.out.println("nc4 action");
                try {
                    System.out.println("nc5 action");
                } finally {
                    nc5.dispose();
                }
            } finally {
                nc4.dispose();
            }
        } catch (ConstructException e) {
            System.out.println("nc4 or nc5 fail");
        }
    }
}

class NeedsCleanup {
    private static long counter = 1;
    private final long id = counter++;

    public void dispose() {
        System.out.println("NeedsCleanup " + id + " disposed");
    }
}

class ConstructException extends Exception {
}

class NeedsCleanup2 extends NeedsCleanup {
    public NeedsCleanup2() throws ConstructException {
        throw new ConstructException();
    }
}
