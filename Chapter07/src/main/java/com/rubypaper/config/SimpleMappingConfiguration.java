package com.rubypaper.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class SimpleMappingConfiguration implements WebMvcConfigurer {
	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		// 단순 화면 이동과 관련한 특정 요청에 대해서 화면을 매핑한다.
		registry.addViewController("/").setViewName("index");
		registry.addViewController("/member").setViewName("member");
		registry.addViewController("/manager").setViewName("manager");
		registry.addViewController("/admin").setViewName("admin");
		
		registry.addViewController("/login").setViewName("login");
		registry.addViewController("/success").setViewName("success");
		registry.addViewController("/denied").setViewName("denied");
	}
}
