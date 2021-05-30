package javabasic.reflect;

/**
 * @author lee
 * @date 5/27/21
 *
 * 运行时判断一个对象所属的类
 */
public class JudgeClass {
    public static void main(String[] args) {
        //1.实例获取 class
        System.out.println(new Lemoon().getClass());

        //2.类获取 class
        System.out.println(Lemoon.class);

        //3.完全限定名获取 class
        try {
            System.out.println(Class.forName("javabasic.reflect.Lemoon"));
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        System.out.println(LittleLemoon.class);
        System.out.println(LittleLemoon.class.getSuperclass());
    }
}
