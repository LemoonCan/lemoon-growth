package designpattern.behavior.callback.async;

/**
 * @author lee
 * @since 2022/10/13
 */
public class AClass {
    public static void main(String[] args) {
        BClass bClass = new BClass();
        bClass.process(() -> System.out.println("callback..."));
    }
}
