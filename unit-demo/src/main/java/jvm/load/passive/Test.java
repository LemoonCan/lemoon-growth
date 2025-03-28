package jvm.load.passive;

/**
 * 被动引用测试
 *
 * @author lee
 * @since 2022/3/7
 */
public class Test {
    public static void main(String[] args) {
        SubClass subClass = new SubClass();
        // 通过子类引用父类的静态字段, 不会导致子类初始化，只初始化父类
        System.out.println(SubClass.value);
        // 通过数组定义来引用类，不会触发此类的初始化
        SuperClass[] sca = new SuperClass[10];
    }
}
