package cherry.service.dubbo.spi.adaptive.method;

import org.apache.dubbo.common.URL;

/**
 * @author lee
 * @since 2022/6/25
 */
public class BmwCarMaker implements CarMaker{
    @Override
    public Car makeCar(URL url) {
        return new Car("Bmw");
    }

    @Override
    public String speed(URL url) {
        return "Bmw-100";
    }
}
