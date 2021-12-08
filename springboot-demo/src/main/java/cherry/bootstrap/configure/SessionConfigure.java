package cherry.bootstrap.configure;

import org.springframework.context.annotation.Configuration;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @author lee
 * @date 8/19/21
 */
@EnableRedisHttpSession
@Configuration
public class SessionConfigure implements WebMvcConfigurer {
    /**
     * 添加登录校验拦截器
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new SecurityInterceptor())
                //排除拦截
                .excludePathPatterns("/session/login")
                .excludePathPatterns("/session/logout")
                .excludePathPatterns("/exclude/")
                .excludePathPatterns("/swagger-ui/index.html")
                //拦截路径
                .addPathPatterns("/**");
    }

    @Configuration
    public class SecurityInterceptor implements HandlerInterceptor {
        @Override
        public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {
            HttpSession session = request.getSession();
            if (session.getAttribute(session.getId()) != null){
                return true;
            }
            response.getWriter().write("please login first");
            return false;
        }
    }
}
