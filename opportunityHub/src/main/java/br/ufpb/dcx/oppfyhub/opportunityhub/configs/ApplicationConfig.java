package br.ufpb.dcx.oppfyhub.opportunityhub.configs;

import br.ufpb.dcx.oppfyhub.opportunityhub.filter.FilterTokenJWT;
import org.modelmapper.ModelMapper;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfig {

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Bean
    public FilterRegistrationBean<FilterTokenJWT> filterJwt() {
        FilterRegistrationBean<FilterTokenJWT> filterRB = new FilterRegistrationBean<>();
        filterRB.setFilter(new FilterTokenJWT());
        filterRB.addUrlPatterns("/v1/api/jobs/*", "/auth/user");
        return filterRB;
    }
}