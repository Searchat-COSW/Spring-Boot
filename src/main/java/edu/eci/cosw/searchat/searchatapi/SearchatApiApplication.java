package edu.eci.cosw.searchat.searchatapi;

import edu.eci.cosw.searchat.searchatapi.config.JwtFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories ("edu.eci.cosw.searchat.searchatapi.persistence")
@EntityScan("edu.eci.cosw.searchat.searchatapi..model")
public class SearchatApiApplication {


	@Bean
	public FilterRegistrationBean jwtFilter()
	{
		final FilterRegistrationBean registrationBean = new FilterRegistrationBean();
		registrationBean.setFilter( new JwtFilter() );
		registrationBean.addUrlPatterns( "/api/*" );

		return registrationBean;
	}


	public static void main(String[] args) {
		SpringApplication.run(SearchatApiApplication.class, args);
	}
}
