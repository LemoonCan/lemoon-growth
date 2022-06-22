package cherry.service.dubbo.spi;

/**
 * @author lee
 * @date 2022/6/22
 */
public class OptimusPrime implements Robot {
    @Override
    public void sayHello() {
        System.out.println("Hello, I am Optimus Prime.");
    }
}
