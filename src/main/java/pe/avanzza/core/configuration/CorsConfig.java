package pe.avanzza.core.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author Gloria R. Leyva Jerez
 * Configure globally the CORS security policy
 */
@Configuration
public class CorsConfig {

    /**
     * Specify which domains are authorized and which are not, and what they are authorized for.,
     *
     * @return WebMvcConfigurer configuration for project
     */
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**") /*	/ENTITY_API/**	*/
                        .allowedOrigins("*")          /*	ALL(*) or http://localhost:port	 (808 or other port) */
                        .allowedMethods("PUT", "DELETE", "GET", "POST", "PATCH" /*, other methods*/)
                        .allowCredentials(true)
                /*.maxAge(3600);*/             /* You can define maximum number of years*/
               ;
            }
        };
    }

    /*
     * You can easily change any properties, as well as only apply this CORS configuration to a specific path pattern:
     */
	/*	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/api/**")
				.allowedOrigins("http://domain2.com")
				.allowedMethods("PUT", "DELETE")
				.allowedHeaders("header1", "header2", "header3")
				.exposedHeaders("header1", "header2")
				.allowCredentials(false).maxAge(3600);
	}*/
}
