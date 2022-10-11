package designpattern.structured.flyweight;

/**
 * Integer 缓存了-128~127的数据，应用了享元模式
 * @author lee
 * @date 2022/10/11
 */
public class Demo {
    public static void main(String[] args) {
        Integer i1 = 56;
        Integer i2 = 56;
        Integer j1 = 128;
        Integer j2 = 128;
        System.out.println(i1==i2);
        System.out.println(j1==j2);
    }
}
