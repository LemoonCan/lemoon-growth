package javabasic.dynamic.generics.construct;

/**
 * @author lee
 * @date 2020-09-17
 *
 * 场景？
 * 这样需要为类再创建一个工厂实现
 */
public class FactoryConstraint {
    public static void main(String[] args) {
        new Foo2<>(new IntegerFactory()).get();
        new Foo2<>(new Widget.Factory()).get();
    }
}

interface FactoryI<T>{
    T create();
}

class Foo2<T>{
    private T x;
    public <F extends FactoryI<T>> Foo2(F factory){
        x = factory.create();
    }

    T get(){
        System.out.println(x);
        return x;
    }
}

class IntegerFactory implements FactoryI<Integer>{
    @Override
    public Integer create() {
        return new Integer(0);
    }
}

class Widget{
    public static class Factory implements FactoryI<Widget>{
        @Override
        public Widget create() {
            return new Widget();
        }
    }

    @Override
    public String toString() {
        return "Widget";
    }
}
