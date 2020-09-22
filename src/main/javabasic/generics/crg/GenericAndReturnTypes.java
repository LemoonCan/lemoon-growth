package main.javabasic.generics.crg;

/**
 * @author lee
 * @date 2020-09-22
 */
public class GenericAndReturnTypes {
    void test(Getter g){
        Getter result = g.get();
        GenericGetter gg = g.get();
    }
}

interface GenericGetter<T extends GenericGetter<T>>{
    T get();
}

interface Getter extends GenericGetter<Getter>{
}