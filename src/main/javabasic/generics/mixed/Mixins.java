package main.javabasic.generics.mixed;

/**
 * @author lee
 * @date 2020-09-22
 */
public class Mixins {
    public static void main(String[] args) {

    }
}

interface TimeStamped{
    long getStamp();
}
class TimeStampedImp implements TimeStamped{
    private final long timeStamp;

    public TimeStampedImp() {
        this.timeStamp = System.currentTimeMillis();
    }

    @Override
    public long getStamp(){
        return timeStamp;
    }
}

interface SerialNumbered{
    long getSerialNumber();
}
class SerialNumberedImp implements SerialNumbered{
    private static long counter = 1;
    private final long serialNumber = counter++;

    @Override
    public long getSerialNumber(){
        return serialNumber;
    }
}

interface Basic{
    void set(String val);
    String get();
}
class BasicImp implements Basic{
    private String value;

    @Override
    public void set(String val) {
        value = val;
    }

    @Override
    public String get() {
        return value;
    }
}

/**
 * 组合
 */
class Mixin extends BasicImp implements TimeStamped,SerialNumbered{
    private TimeStamped timeStamp = new TimeStampedImp();
    private SerialNumbered serialNumber = new SerialNumberedImp();

    @Override
    public long getSerialNumber() {
        return serialNumber.getSerialNumber();
    }


    @Override
    public long getStamp() {
        return timeStamp.getStamp();
    }
}
