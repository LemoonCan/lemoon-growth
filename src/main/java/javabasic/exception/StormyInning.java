package javabasic.exception;

/**
 * @author lee
 * @date 2020-09-04
 */
public class StormyInning extends Inning implements Stormy {
    public static void main(String[] args) {
        try {
            StormyInning si = new StormyInning();
            si.atBat();
        } catch (PopFoul e) {
            System.out.println("Pop foul");
        } catch (RainedOut e) {
            System.out.println("Rained Out");
        } catch (BaseBallException e) {
            System.out.println("Generic BaseBallException");
        }

        try {
            Inning i = new StormyInning();
            i.atBat();
        } catch (Strike e) {
            System.out.println("Strike");
        } catch (Foul e) {
            System.out.println("Foul");
        } catch (RainedOut e) {
            System.out.println("RainedOut");
        } catch (BaseBallException e) {
            System.out.println("Generic BaseBallException");
        }

    }

    //异常限制对构造器不起作用
    public StormyInning() throws RainedOut, BaseBallException {
    }

    public StormyInning(String s) throws Foul, BaseBallException {
    }

    //重写方法不能抛出基类方法未声明的异常
//    @Override
//    public void event() throws RainedOut{
//    }

//    @Override
//    public void walk() throws PopFoul{
//        super.walk();
//    }

    //重写方法抛出基类指定异常的子类
    @Override
    public void atBat() throws PopFoul {
    }

    //重写方法抛出基类指定异常
    @Override
    public void rainHard() throws RainedOut {
    }

    //若【父类Inning event()】实现及异常满足【接口Stormy event()】的要求，则【父类Inning event()】可作为【接口Stormy event()】，否则需重写，重写方法需满足接口及父类方法的异常要求
    //故重写方法不抛出异常
    @Override
    public void event() {
    }

    //重写方法可不抛出异常
    @Override
    public void walk() {
        super.walk();
    }
}

class BaseBallException extends Exception {
}

class Foul extends BaseBallException {
}

class Strike extends BaseBallException {
}

abstract class Inning {
    public Inning() throws BaseBallException {
    }

    public void event() throws BaseBallException {
    }

    public abstract void atBat() throws Strike, Foul;

    public void walk() {
    }
}

class StormyException extends Exception {

}

class RainedOut extends StormyException {

}

class PopFoul extends Foul {

}

interface Stormy {
    void event() throws RainedOut;

    void rainHard() throws RainedOut;
}
