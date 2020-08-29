package pe.avanzza.core.configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Gloria R. Leyva Jerez
 */
@Configuration
public class OpenAPIConfig {

    public static final Contact DEFAULT_CONTACT = new Contact()
            .name("AVANZZA One single solution")
            .email("leyvajerezgr@gmail.com")
            .url("http://www.avanzza.io");

    public static final Info DEFAULT_API_INFO = new Info()
            .title("APIs REST de la Liga de Fútbol")
            .description("Documentación de las APIs REST de AVANZZA ")
            .version("1.0")
            .contact(DEFAULT_CONTACT)
            .termsOfService("http://swagger.io/terms/")
            .license(new License().name("Apache 2.0")
                    .url("http://springdoc.org"));

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(DEFAULT_API_INFO);
    }
}
