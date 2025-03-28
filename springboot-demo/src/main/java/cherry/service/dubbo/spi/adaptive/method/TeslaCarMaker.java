package cherry.service.dubbo.spi.adaptive.method;

import org.apache.dubbo.common.URL;

/**
 * @author lee
 * @since 2022/6/25
 */
public class TeslaCarMaker implements CarMaker{
    @Override
    public Car makeCar(URL url) {
        return new Car("Tesla");
    }

    @Override
    public String speed(URL url) {
        return "Tesla-80";
    }
}
