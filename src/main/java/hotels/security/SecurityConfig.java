package hotels.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@EnableAsync
@Configuration
public class SecurityConfig extends WebMvcConfigurerAdapter {

  private final String allowedOriginsCommaSeparated;

  public SecurityConfig(
      @Value("${app.allowed.origins}") String allowedOriginsCommaSeparated
  ) {

    this.allowedOriginsCommaSeparated = allowedOriginsCommaSeparated;
  }

  @Bean
  public WebMvcConfigurer corsConfigurer() {
    return new WebMvcConfigurerAdapter() {
      @Override
      public void addCorsMappings(CorsRegistry registry) {
        registry
            .addMapping("/**")
            .allowedOrigins(
                allowedOriginsCommaSeparated.isEmpty()
                    ? new String[]{"*"}
                    : allowedOriginsCommaSeparated.split(",")
            )
            .allowedMethods(
                "GET", "POST", "PUT", "DELETE", "OPTIONS"
            );
      }
    };
  }
}
