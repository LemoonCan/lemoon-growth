package javabasic.generics.crg;

/**
 * @author lee
 * @date 2020-09-22
 */
public class OrdinaryArguments {
    public static void main(String[] args) {
        Base base = new Base();
        Derived derived = new Derived();
        DerivedSetter ds = new DerivedSetter();
        ds.set(derived);
        ds.set(base);
    }
}

class OrdinarySetter{
    void set(Base base){
        System.out.println("OrdinarySetter.set(Base)");
    }
}

class DerivedSetter extends OrdinarySetter{
    /**
     * 重载
     * @param derived
     */
    void set(Derived derived){
        System.out.println("DerivedSetter.set(Derived)");
    }
}
