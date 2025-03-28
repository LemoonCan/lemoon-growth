package cherry.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author lee
 * @since 2024/10/14
 */
@RestController
@RequestMapping("/memoryleak")
@Tag(name = "oom-测试")
public class MemoryLeakDemo {
    @RequestMapping(method = RequestMethod.POST, value = "/test0")
    @Operation(description = "测试")
    public String test0(HttpServletRequest request) {
        ThreadLocal<Byte[]> localVariable = new ThreadLocal<>();
        localVariable.set(new Byte[4096*1024]);// 为线程添加变量
        return "success";
    }
}
