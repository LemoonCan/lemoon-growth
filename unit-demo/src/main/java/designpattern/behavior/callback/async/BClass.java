package designpattern.behavior.callback.async;

/**
 * @author lee
 * @since 2022/10/13
 */
public class BClass {
    public void process(ICallback callback){
        new Thread(()->doProcess(callback)).start();
    }

    private void doProcess(ICallback callback){
        callback.methodCallback();
    }
}
