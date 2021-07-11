package javabasic.dynamic.generics.tclass;

/**
 * @author lee
 * @date 5/24/21
 */
public class Basic<T> {
    protected T element;

    public T getElement(){
        return element;
    }

    public void setElement(T element) {
        this.element = element;
    }

}
