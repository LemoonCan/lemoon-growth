package designpattern.behavior.callback.sync;

/**
 * @author lee
 * @date 2022/10/13
 */
public class BClass {
    public void process(ICallback callback){
        callback.methodCallback();
    }
}
