package designpattern.structured.adapter.interfaze;

import designpattern.structured.adapter.Adaptee;
import designpattern.structured.adapter.ITarget;

/**
 * @author lee
 * @date 2022/10/9
 */
public class Adaptor implements ITarget {
    private Adaptee adaptee;

    public Adaptor(Adaptee adaptee) {
        this.adaptee = adaptee;
    }

    @Override
    public void f1() {
        adaptee.fa();
    }

    @Override
    public void f2() {
        adaptee.fb();
    }

    @Override
    public void fc() {
        adaptee.fc();
    }
}
