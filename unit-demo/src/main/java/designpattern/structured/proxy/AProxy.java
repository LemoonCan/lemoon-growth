package designpattern.structured.proxy;

import designpattern.structured.IA;

/**
 * @author lee
 * @date 2022/10/9
 */
public class AProxy implements IA {
    private IA a;

    public AProxy(IA a) {
        this.a = a;
    }

    @Override
    public void f() {
        // 新添加的代理逻辑
        a.f();
        // 新添加的代理逻辑
    }
}
