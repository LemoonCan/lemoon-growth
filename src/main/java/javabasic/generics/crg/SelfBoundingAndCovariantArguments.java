package javabasic.generics.crg;

/**
 * @author lee
 * @date 2020-09-22
 */
public class SelfBoundingAndCovariantArguments {
    void testA(Setter s1,Setter s2,SelfBoundSetter sbs1,SelfBoundSetter sbs2){
        s1.set(s2);
//        s1.set(sbs1);
        sbs1.set(s1);
        sbs1.set(sbs2);
    }

    public static void main(String[] args) {
        SelfBoundingAndCovariantArguments s = new SelfBoundingAndCovariantArguments();
        s.testA(new Setter(),new Setter(),new SelfBoundSetter(),new SelfBoundSetter());

        GenericSetter<Derived> gsd = new GenericSetter();
        GenericSetter<Base> gsb = new GenericSetter();

        DerivedGS dgs = new DerivedGS();
        Derived d = new Derived();
        Base b = new Base();
        dgs.set(d);
        dgs.set(b);

        gsd.set(d);
//        gsd.set(b);
        gsb.set(b);
        gsb.set(d);
    }
}

class SelfBoundSetter<T extends SelfBoundSetter<T>>{
    void set(T arg){
        System.out.println("SelfBoundSetter.set()"+arg.getClass().getSimpleName());
    }
}

/**
 * 重写了set方法
 */
class Setter extends SelfBoundSetter<Setter>{
}


class GenericSetter<T>{
    void set(T arg){
        System.out.println("GenericSetter.set(Base)");
    }
}

/**
 * GenericSetter<Base> 泛化的变量也不是Derived，
 * 所以void set(Derived arg) 当然是重载了
 */
class DerivedGS extends GenericSetter<Base>{
    void set(Derived arg){
        System.out.println("GenericSetter.set(Base)");
    }
}