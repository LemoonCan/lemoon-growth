package cherry.controller;

import cherry.service.dubbo.spi.Robot;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.dubbo.common.extension.ExtensionLoader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author lee
 * @date 2022/6/22
 */
@RestController
@RequestMapping("/dubbo")
@Api(value = "dubbo-测试")
public class DubboDemo {

    @RequestMapping(method = RequestMethod.POST, value = "/spiTest")
    @ApiOperation(value = "SPI测试")
    public void spiTest() {
        ExtensionLoader<Robot> extensionLoader =
                ExtensionLoader.getExtensionLoader(Robot.class);
        Robot optimusPrime = extensionLoader.getExtension("optimusPrime");
        optimusPrime.sayHello();
        Robot bumblebee = extensionLoader.getExtension("bumblebee");
        bumblebee.sayHello();
    }
}
