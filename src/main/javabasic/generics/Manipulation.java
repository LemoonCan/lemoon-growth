package main.javabasic.generics;

/**
 * @author lee
 * @date 2020-09-17
 */
public class Manipulation {
    public static void main(String[] args) {
        HasF hf = new HasF();
        Manipulator<HasF> manipulator = new Manipulator<>(hf);
        manipulator.manipulate();

        Manipulator2<HasF> manipulator2 = new Manipulator2<>(hf);
        manipulator2.get().f();
    }
}

class Manipulator<T extends HasF>{
    private T obj;
    public Manipulator(T x){
        obj = x;
    }

    public void manipulate(){
        obj.f();
    }
}

class Manipulator2<T>{
    private T obj;

    public Manipulator2(T obj) {
        this.obj = obj;
    }

    public T get(){
        return obj;
    }
}

class HasF{
    void f(){
        System.out.println("HasF:f()");
    }
}
