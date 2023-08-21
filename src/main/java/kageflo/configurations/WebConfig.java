package kageflo.configurations;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") // Set the mapping URL pattern to cover all endpoints
                .allowedOrigins("http://localhost:3000") // Set the allowed origin for CORS requests
                .allowedMethods("GET", "POST", "PUT", "DELETE") // Set the allowed HTTP methods for CORS requests
                .allowedHeaders("*") // Set the allowed headers for CORS requests (you can also specify specific headers)
                .exposedHeaders("header1", "header2") // Set the exposed headers for CORS responses (optional)
                .allowCredentials(true) // Allow sending credentials like cookies with CORS requests (optional)
                .maxAge(3600); // Set the maximum age of the CORS pre-flight request cache (optional)
    }
}
