package cherry.controller;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.apache.dubbo.remoting.http12.rest.Operation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.Serializable;

/**
 * @author lee
 * @since  2021/6/18
 */
@RestController
@RequestMapping("/session")
@Tag(name = "会话测试")
public class SessionDemo {
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    @Operation(value = "登录",method = "GET")
    public String login(@RequestParam @Parameter(name = "name", description = "名称", required = true) String name,
                        @RequestParam @Parameter(name = "password", description = "密码", required = true) String password,
                        HttpSession session) {
        session.setAttribute(session.getId(), new User(name, password));
        return "Login success!😈";
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    @Operation(value = "登出",method = "GET")
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
