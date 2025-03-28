package cherry.bootstrap.configure;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.context.annotation.Configuration;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.io.IOException;

/**
 * @author lee
 * @since 8/19/21
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
                .excludePathPatterns("/swagger-ui.html")
                .excludePathPatterns("/swagger-ui/**")
                .excludePathPatterns("/v3/api-docs")
                .excludePathPatterns("/v3/api-docs/**")
                //拦截路径
                .addPathPatterns("/**");
    }

    @Configuration
    public static class SecurityInterceptor implements HandlerInterceptor {
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
