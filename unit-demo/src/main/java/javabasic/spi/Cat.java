package javabasic.spi;

/**
 * @author lee
 * @since 2021/12/16
 */
public class Cat implements IShout{
    @Override
    public void shout() {
        System.out.println("喵");
    }
}
