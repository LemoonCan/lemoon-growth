package designpattern.structured.adapter.clazz;

import designpattern.structured.adapter.Adaptee;
import designpattern.structured.adapter.ITarget;

/**
 * @author lee
 * @since 2022/10/9
 */
public class Adaptor extends Adaptee implements ITarget {
    @Override
    public void f1() {
        super.fa();
    }

    @Override
    public void f2() {
        super.fb();
    }
}
