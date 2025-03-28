package cherry.service.dubbo.spi.adaptive.clazz;

import org.apache.dubbo.common.extension.SPI;

/**
 * @author lee
 * @since 2022/6/25
 */
@SPI
public interface WheelMaker {
    String makeWheel();
}
