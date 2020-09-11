package main.javabasic.generics.methods;

/**
 * @author lee
 * @date 2020-09-11
 */
public class GenericMethods {
    public <T> void f(T x) {
        System.out.println(x.getClass().getName());
    }

    public <T, G> void f(T x, G g) {
        System.out.println("T:" + x.getClass().getName());
        System.out.println("G:" + g.getClass().getName());
    }

    public static void main(String[] args) {
        GenericMethods gm = new GenericMethods();
        gm.f("");
        gm.f(1);
        gm.f(1.0);
        gm.f(1.0F);
        gm.f('c');
        gm.f(gm);
    }
}
