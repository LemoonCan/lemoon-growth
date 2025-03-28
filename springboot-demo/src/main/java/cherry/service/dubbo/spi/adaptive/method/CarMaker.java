package cherry.service.dubbo.spi.adaptive.method;

import org.apache.dubbo.common.URL;
import org.apache.dubbo.common.extension.Adaptive;
import org.apache.dubbo.common.extension.SPI;

/**
 * @author lee
 * @since 2022/6/25
 */
@SPI("carMaker")
public interface CarMaker {
    @Adaptive
    Car makeCar(URL url);

    @Adaptive({"speed"})
    String speed(URL url);
}
