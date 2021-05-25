package javabasic.generics.wildcard;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 * @author lee
 * @date 5/25/21
 * T extends 指定边界只能置于泛型参数声明部分(方法置于返回值前、类置于当前类声明后)
 */
public class LetterMark {
    //T声明在方法上
    static <T> void aGenericMethod(T x){
        System.out.println(x);
    }

    //T extends 声明在方法上，置于方法前
    static <T extends Number> void aInheritGenericMethod(List<T> x){
        System.out.println(x);
    }

    //T extends 不能声明在形参上
    //static <T> void aInheritGenericMethod2(List<T extends Number> x){
    //}

    //T extends 声明在类上，置于当前类声明后
    class ListInherit<T extends Number> implements Iterable<T>{
        @Override
        public Iterator<T> iterator() {
            return null;
        }
    }

    //无 T super


    public static void main(String[] args) {
        aGenericMethod("666");
        aInheritGenericMethod(Arrays.asList(1,2,3));
        aInheritGenericMethod(Arrays.asList(1.0f,2.0f,3.0f));
    }
}
