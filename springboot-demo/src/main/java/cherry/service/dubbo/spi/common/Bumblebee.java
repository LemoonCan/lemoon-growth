package cherry.service.dubbo.spi.common;

/**
 * @author lee
 * @since 2022/6/22
 */
public class Bumblebee implements Robot {
    @Override
    public void sayHello() {
        System.out.println("Hello, I am Bumblebee.");
    }
}
