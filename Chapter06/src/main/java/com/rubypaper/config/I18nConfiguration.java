package com.rubypaper.config;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

@Configuration
public class I18nConfiguration implements WebMvcConfigurer {
	@Bean
	public MessageSource messageSource() {
		ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
		// message 패키지에 messageSource로 시작하는 메시지 파일들을 모두 로딩하도록 설정한다.
		messageSource.setBasename("message.messageSource");
		return messageSource;
	}
	
	@Bean
	public LocaleResolver localeResolver() {
		return new SessionLocaleResolver();
	}
	
    public LocaleChangeInterceptor localeChange() {
        LocaleChangeInterceptor localeChangeInterceptor = new LocaleChangeInterceptor();
        localeChangeInterceptor.setParamName("lang");
        return localeChangeInterceptor;
    }
    
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 인터셉터를 등록한다. 
        registry.addInterceptor(localeChange());
    }	
}
