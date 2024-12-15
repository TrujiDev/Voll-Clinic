package med.voll.api.infrastructure.springdoc;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringDocConfig {

  @Bean
  public OpenAPI customOpenAPI() {
      return new OpenAPI()
      .components(new Components()
      .addSecuritySchemes("bearer-jwt",
      new SecurityScheme().type(SecurityScheme.Type.HTTP).scheme("bearer").bearerFormat("JWT")))
          .info(new Info()
              .title("Voll.Med API")
              .description("API for Voll.Med")
              .version("1.0.0")
              .contact(new Contact()
                  .name("Voll.Med")
                  .email("backend@vollmed.com")
              )
              .license(new License()
                  .name("Apache 2.0")
                  .url("http://www.apache.org/licenses/LICENSE-2.0.html")
              )
          );
  }

}