package javabasic.generics.crg;

/**
 * @author lee
 * @date 2020-09-22
 */
public class CovaiantReturnTypes {
    void test(DerivedGetter d){
        Derived d2 = d.get();
    }
}

class Base{
}
class Derived extends Base{
}

interface OridinaryGetter {
    Base get();
}
interface DerivedGetter extends OridinaryGetter{
    @Override
    Derived get();
}