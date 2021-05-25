package javabasic.generics.tclass;

/**
 * @author lee
 * @date 5/24/21
 */
public class Basic<T> {
    T element;

    public T getElement(){
        return element;
    }

    public void setElement(Class<T> element) throws IllegalAccessException, InstantiationException {
        this.element = element.newInstance();
    }

}
