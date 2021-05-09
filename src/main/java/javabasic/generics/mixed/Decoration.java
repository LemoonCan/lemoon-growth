package javabasic.generics.mixed;

/**
 * @author lee
 * @date 2020-09-22
 *
 * 需要组合的类，都继承Decorator 就可通过构造方法传入其他继承了Decorator的类
 * 从而实现组合的效果
 */
public class Decoration {
    public static void main(String[] args) {
        TimeStamped2 t1 = new TimeStamped2(new Basic2());
        TimeStamped2 t2 = new TimeStamped2(new SerialNumbered2(new Basic2()));

        SerialNumbered2 s1 = new SerialNumbered2(new Basic2());
        SerialNumbered2 s2 = new SerialNumbered2(new SerialNumbered2(new Basic2()));
    }
}
class Basic2{
    private String value;
    public void set(String val){
        value=val;
    }
    public String get(){
        return value;
    }
}
class Decorator extends Basic2{
    protected Basic2 basic;

    public Decorator(Basic2 basic) {
        this.basic = basic;
    }

    @Override
    public void set(String val) {
        basic.set(val);
    }

    @Override
    public String get() {
        return basic.get();
    }
}

class TimeStamped2 extends Decorator{
    private final long timeStamp;

    public TimeStamped2(Basic2 basic) {
        super(basic);
        timeStamp = System.currentTimeMillis();
    }

    public long getStamp(){
        return timeStamp;
    }
}
class SerialNumbered2 extends Decorator{
    private static long counter = 1;
    private final long serialNumber = counter++;
    public SerialNumbered2(Basic2 basic) {
        super(basic);
    }

    public long getSerialNumber(){
        return serialNumber;
    }
}