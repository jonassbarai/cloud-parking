package projetos.jonas.cloudparking.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition
public class SwaggerConfig {
    @Bean
    public OpenAPI baseOpenAPI() {

        return new OpenAPI()
                .info(new Info()
                        .title("Parking API Doc")
                        .version("1.0.0")
                        .description("Documentation for Parking API "));
    }

    @Bean
    public GroupedOpenApi api() {
        String[] paths = {"/parking/**"};
        return GroupedOpenApi.builder()
                .group("api")
                .pathsToMatch(paths)
                .build();
    }
}

