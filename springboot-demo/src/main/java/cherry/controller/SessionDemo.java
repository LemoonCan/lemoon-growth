package cherry.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.io.Serializable;

/**
 * @author lee
 * @date 8/16/21
 */
@RestController
@RequestMapping("/session")
@Api(value = "会话测试")
public class SessionDemo {
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    @ApiOperation(value = "登录",httpMethod = "GET")
    public String login(@RequestParam @ApiParam(name = "name", value = "名称", required = true) String name,
                        @RequestParam @ApiParam(name = "password", value = "密码", required = true) String password,
                        HttpSession session) {
        session.setAttribute(session.getId(), new User(name, password));
        return "Login success!😈";
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    @ApiOperation(value = "登出",httpMethod = "GET")
    public String logout(HttpSession session) {
        session.removeAttribute(session.getId());
        return "Logout success!😴";
    }

    @AllArgsConstructor
    @Data
    public static class User implements Serializable {
        private String name;
        private String password;
    }
}
