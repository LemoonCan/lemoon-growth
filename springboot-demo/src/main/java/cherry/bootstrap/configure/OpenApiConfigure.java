package cherry.bootstrap.configure;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author lee
 * @since 7/15/21
 * 默认访问地址：/swagger-ui/index.html
 */
@Configuration
public class OpenApiConfigure {
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("API 文档")
                        .version("1.0.0")
                        .description("Spring Boot 3.x + OpenAPI"));
    }
}
