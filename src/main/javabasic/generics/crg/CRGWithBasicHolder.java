package main.javabasic.generics;

/**
 * @author lee
 * @date 2020-09-20
 */
public class CRGWithBasicHolder {
    public static void main(String[] args) {
        SubType st1 = new SubType(), st2 = new SubType();
        st1.set(st2);
        SubType st3 = st1.get();
        st1.f();
    }
}

class SubType extends BasicHolder<SubType> {
}
