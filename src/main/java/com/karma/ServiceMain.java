package com.karma;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@SpringBootApplication
public class ServiceMain {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(ServiceMain.class, args);
		System.out.println("Contains: Service -> " + context.containsBeanDefinition("karmaService"));
		System.out.println("Contains: Controller -> " + context.containsBeanDefinition("karmaController"));
		System.out.println("Contains: Repository -> " + context.containsBeanDefinition("karmaRepository"));
	}

	@Bean
	public Docket docket() {
		return new Docket(DocumentationType.SWAGGER_2).select()
				.apis(RequestHandlerSelectors.basePackage(getClass().getPackage().getName())).paths(PathSelectors.any())
				.build().apiInfo(generateApiInfo());
	}

	@SuppressWarnings("deprecation")
	private ApiInfo generateApiInfo() {
		return new ApiInfo("KarmaService", "Service for Karma APP", "Version 1.0", "urn:tos", "bloodbahamut@gmail.com",
				"Apache 2.0", "http://www.apache.org/licenses/LICENSE-2.0");
	}
}