package com.travel.travtronics;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;

import com.travel.travtronics.config.CORSFilter;

import it.ozimov.springboot.mail.configuration.EnableEmailTools;

//import it.ozimov.springboot.mail.configuration.EnableEmailTools;

/**
 * Hello world!
 *
 */
@SpringBootApplication
@EnableEmailTools
@EnableAsync
public class LoginApplication {
	public static void main(String[] args) {
		SpringApplication.run(LoginApplication.class, args);
	}

	@Bean
	public FilterRegistrationBean corsFilterRegistration() {
		FilterRegistrationBean registrationBean = new FilterRegistrationBean(new CORSFilter());
		registrationBean.setName("CORS Filter");
		registrationBean.addUrlPatterns("/*");
		registrationBean.setOrder(1);

		return registrationBean;
	}
}
