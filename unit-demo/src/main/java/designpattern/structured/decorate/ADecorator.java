package designpattern.structured.decorate;

import designpattern.structured.IA;

/**
 * @author lee
 * @since 2022/10/9
 */
public class ADecorator implements IA {
    private IA a;

    public ADecorator(IA a) {
        this.a = a;
    }

    @Override
    public void f() {
        // 功能增强代码
        a.f();
        // 功能增强代码
    }
}
