package com.example.quinBooksolrSearch;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.solr.repository.config.EnableSolrRepositories;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@SpringBootApplication
@EnableSolrRepositories(basePackages = "com.example.quinBooksolrSearch.solr_repository")
@EnableJpaRepositories(basePackages = "com.example.quinBooksolrSearch.repository")
@ComponentScan
@EnableSwagger2
public class QuinBookSolrSearchApplication {

	public static void main(String[] args) {

		SpringApplication.run(QuinBookSolrSearchApplication.class, args);
	}

}
