package javabasic.generics.holder;

/**
 * @author lee
 * @date 2020-09-10
 *
 * 容器可存储多种类型的对象，取某个值时需下溯
 * 但实际上希望容器可容纳多种类型对象，但一个容器只容纳一种类型的对象
 * 而下述方式一个容器可能会容纳多种类型的对象
 */
public class Holder2 {
    private Object a;

    public Holder2(Object a) {
        this.a = a;
    }

    public void set(Object a) {
        this.a = a;
    }

    public Object get() {
        return a;
    }

    public static void main(String[] args) {
        Holder2 h2 = new Holder2(new Automobile());
        Automobile a = (Automobile) h2.get();
        h2.set("Not an Automobile");
        String s = (String)h2.get();
        h2.set(1);
        Integer x = (Integer)h2.get();
    }
}
