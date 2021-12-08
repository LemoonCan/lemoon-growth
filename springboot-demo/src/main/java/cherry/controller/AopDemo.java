package cherry.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.aop.framework.AopContext;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author lee
 * @date 2021/11/19
 */
@RestController
@RequestMapping("/aop")
@Api(value = "aop-测试")
public class AopDemo {
    @RequestMapping(method = RequestMethod.POST, value = "/coming")
    @ApiOperation(value = "观临")
    public String coming(@RequestParam @ApiParam(name = "name", value = "谁", required = true) String name) {
        return name + " is coming";
    }

    @RequestMapping(method = RequestMethod.POST, value = "/exception")
    @ApiOperation(value = "异常了")
    public void exception() {
        throw new RuntimeException("Oh...这是个错误");
    }

    @RequestMapping(method = RequestMethod.POST, value = "/nestTrigger")
    @ApiOperation(value = "嵌套调用")
    public void nestTrigger(){
        System.out.println("nestTrigger 执行中");

        //嵌套调用当前类的方法，实际调用的是目标对象方法，所以嵌套的方法未被拦截
        //nest();

        //取得代理对象进行调用，嵌套方法才可被拦截
        ((AopDemo)AopContext.currentProxy()).nest();
    }

    @RequestMapping(method = RequestMethod.POST, value = "/nest")
    @ApiOperation(value = "嵌套方法")
    public void nest(){
        System.out.println("nest 执行中");
    }
}
