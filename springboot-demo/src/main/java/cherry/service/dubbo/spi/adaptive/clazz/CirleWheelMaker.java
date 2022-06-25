package cherry.service.dubbo.spi.adaptive.clazz;

import org.apache.dubbo.common.extension.Adaptive;

/**
 * @author lee
 * @date 2022/6/25
 */
@Adaptive
public class CirleWheelMaker implements WheelMaker{
    @Override
    public String makeWheel() {
        return "circle";
    }
}
