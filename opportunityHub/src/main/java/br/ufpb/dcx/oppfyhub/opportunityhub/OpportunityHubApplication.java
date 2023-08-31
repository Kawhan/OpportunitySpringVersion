package br.ufpb.dcx.oppfyhub.opportunityhub;

import br.ufpb.dcx.oppfyhub.opportunityhub.filter.FilterTokenJWT;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class OpportunityHubApplication {

	public static void main(String[] args) {
		SpringApplication.run(OpportunityHubApplication.class, args);
	}

}
