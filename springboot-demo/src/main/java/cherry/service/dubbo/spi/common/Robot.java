package cherry.service.dubbo.spi.common;

import org.apache.dubbo.common.extension.SPI;

/**
 * @author lee
 * @since 2022/6/22
 */
@SPI
public interface Robot {
    void sayHello();
}
