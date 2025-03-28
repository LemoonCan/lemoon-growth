package cherry.service.dubbo.spi.adaptive.clazz;

/**
 * @author lee
 * @since 2022/6/25
 */
public class SquareMaker implements WheelMaker{
    @Override
    public String makeWheel() {
        return "square";
    }
}
