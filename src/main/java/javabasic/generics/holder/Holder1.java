package javabasic.generics.holder;

/**
 * @author lee
 * @date 2020-09-10
 *
 * 无法持有任何其他类型的对象
 */
public class Holder1 {
    private Automobile automobile;

    public Holder1(Automobile automobile) {
        this.automobile = automobile;
    }

    Automobile get(){
        return automobile;
    }

}

class Automobile{}
