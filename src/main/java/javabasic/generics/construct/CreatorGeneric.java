package javabasic.generics.construct;

/**
 * @author lee
 * @date 2020-09-17
 */
public class CreatorGeneric {
    public static void main(String[] args) {
        Creator c = new Creator();
        c.f();
    }
}

abstract class GenericWithCreate<T>{
    final T element;

    public GenericWithCreate() {
        this.element = create();
    }

    abstract T create();
}

class X{}

class Creator extends GenericWithCreate<X>{
    @Override
    X create() {
        return new X();
    }

    void f(){
        System.out.println(element.getClass().getSimpleName());
    }
}