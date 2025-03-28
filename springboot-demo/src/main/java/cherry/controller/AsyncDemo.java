package cherry.controller;

import cherry.service.async.DependentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author lee
 * @since  2022/3/16
 */
@RestController
@RequestMapping("/async")
@Tag(name = "async-测试")
public class AsyncDemo {
    @Autowired
    private DependentService async;

    @RequestMapping(method = RequestMethod.POST, value = "/hello")
    @Operation(description = "打招呼")
    public void hello() {
        async.asyncReply();
    }
}
