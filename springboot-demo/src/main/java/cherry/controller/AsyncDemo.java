package cherry.controller;

import cherry.service.async.DependentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author lee
 * @date 2022/3/16
 */
@RestController
@RequestMapping("/async")
@Api(value = "async-测试")
public class AsyncDemo {
    @Autowired
    private DependentService async;

    @RequestMapping(method = RequestMethod.POST, value = "/hello")
    @ApiOperation(value = "打招呼")
    public void hello() {
        async.asyncReply();
    }
}
