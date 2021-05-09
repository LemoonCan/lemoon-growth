package javabasic.generics.crg;

/**
 * @author lee
 * @date 2020-09-20
 */
public class BasicHolder<T> {
    T element;
    void set(T arg){
        this.element = arg;
    }
    T get(){
        return element;
    }

    void f(){
        System.out.println(element.getClass().getSimpleName());
    }
}
