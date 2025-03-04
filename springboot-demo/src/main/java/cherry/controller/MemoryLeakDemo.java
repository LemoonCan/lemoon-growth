package cherry.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @author lee
 * @since 2024/10/14
 */
@RestController
@RequestMapping("/memoryleak")
@Api(value = "oom-测试")
public class MemoryLeakDemo {
    @RequestMapping(method = RequestMethod.POST, value = "/test0")
    @ApiOperation(value = "测试")
    public String test0(HttpServletRequest request) {
        ThreadLocal<Byte[]> localVariable = new ThreadLocal<>();
        localVariable.set(new Byte[4096*1024]);// 为线程添加变量
        return "success";
    }
}
