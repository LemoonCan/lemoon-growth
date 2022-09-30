package cherry.controller;

import cherry.service.dubbo.spi.adaptive.clazz.WheelMaker;
import cherry.service.dubbo.spi.adaptive.method.Car;
import cherry.service.dubbo.spi.adaptive.method.CarMaker;
import cherry.service.dubbo.spi.common.Robot;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.dubbo.common.URL;
import org.apache.dubbo.common.extension.ExtensionLoader;
import org.apache.dubbo.common.url.component.ServiceConfigURL;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @author lee
 * @date 2022/6/22
 */
@RestController
@RequestMapping("/dubbo")
@Api(value = "dubbo-测试")
public class DubboDemo {

    @RequestMapping(method = RequestMethod.GET, value = "/commonspi")
    @ApiOperation(value = "普通SPI")
    public void commonSpi() {
        ExtensionLoader<Robot> extensionLoader =
                ExtensionLoader.getExtensionLoader(Robot.class);
        Robot optimusPrime = extensionLoader.getExtension("optimusPrime");
        optimusPrime.sayHello();
        Robot bumblebee = extensionLoader.getExtension("bumblebee");
        bumblebee.sayHello();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/adaptiveMethodSpi")
    @ApiOperation(value = "自适应SPI")
    public String adaptiveMethodSpi(String carBrand) {
        CarMaker carMaker = ExtensionLoader.getExtensionLoader(CarMaker.class).getAdaptiveExtension();
        Map<String, String> map = new HashMap<>();
        map.put("car.maker", carBrand);
        map.put("speed", "bmw");
        URL url = new ServiceConfigURL("p1", "1.2.3.4", 1010, "path1", map);

        Car car = carMaker.makeCar(url);
        String speed = carMaker.speed(url);
        return car.getName() +"\n" + speed;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/adaptiveClazzSpi")
    @ApiOperation(value = "自适应SPI")
    public String adaptiveClazzSpi() {
        WheelMaker wheelMaker = ExtensionLoader.getExtensionLoader(WheelMaker.class).getAdaptiveExtension();

        return wheelMaker.makeWheel();
    }
}
