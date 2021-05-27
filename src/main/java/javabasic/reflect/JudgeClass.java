package javabasic.reflect;

/**
 * @author lee
 * @date 5/27/21
 *
 * 运行时判断一个对象所属的类
 */
public class JudgeClass {
    public static void main(String[] args) {
        Lemoon lemoon = new Lemoon();
        System.out.println(lemoon.getClass());
    }
}
