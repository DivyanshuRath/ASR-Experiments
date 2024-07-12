package dropwizard.springboot.dropwizardToSpringboot.config;

import dropwizard.springboot.dropwizardToSpringboot.service.IdempotencyFilter;
import dropwizard.springboot.dropwizardToSpringboot.service.IdempotencyService;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterConfig {
    @Bean
    public FilterRegistrationBean<IdempotencyFilter> idempotencyFilter(IdempotencyService idempotencyService) {
        FilterRegistrationBean<IdempotencyFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new IdempotencyFilter(idempotencyService));
        registrationBean.addUrlPatterns("/*");  // Adjust the URL pattern as needed
        return registrationBean;
    }
}
