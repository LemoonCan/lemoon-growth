package cherry.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.aop.framework.AopContext;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author lee
 * @since  2021/11/19
 */
@RestController
@RequestMapping("/aop")
@Tag(name = "aop-测试")
@RequiredArgsConstructor
public class AopDemo {
    @RequestMapping(method = RequestMethod.POST, value = "/coming")
    @Operation(description = "光临")
    public String coming(@RequestParam @Parameter(name = "name", description = "谁", required = true) String name) {
        return name + " is coming";
    }

    @RequestMapping(method = RequestMethod.POST, value = "/exception")
    @Operation(description = "异常了")
    public void exception() {
        throw new RuntimeException("Oh...这是个错误");
    }

    @RequestMapping(method = RequestMethod.POST, value = "/nestTrigger")
    @Operation(description = "嵌套调用")
    public void nestTrigger(){
        System.out.println("nestTrigger 执行中");

        //嵌套调用当前类的方法，实际调用的是目标对象方法，所以嵌套的方法未被拦截
        //nest();

        //取得代理对象进行调用，嵌套方法才可被拦截
        ((AopDemo)AopContext.currentProxy()).nest();
    }

    @RequestMapping(method = RequestMethod.POST, value = "/nest")
    @Operation(description = "嵌套方法")
    public void nest(){
        System.out.println("nest 执行中");
    }
}
