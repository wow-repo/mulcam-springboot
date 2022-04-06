package com.rubypaper.jdbc.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.rubypaper.jdbc.util.JDBCConnectionManager;

@Configuration
@EnableConfigurationProperties(JDBCConnectionMangaerProperties.class)
public class BoardAutoConfiguration {
	@Autowired
	private JDBCConnectionMangaerProperties properties;
	
	public BoardAutoConfiguration() {
		System.out.println("===> BoardAutoConfiguration 생성");
	}

	@Bean
	@ConditionalOnMissingBean // 만약 JDBCConnectionManager 타입의 객체가 메모리에 없다면..
	public JDBCConnectionManager connectionManager() {
		JDBCConnectionManager manager = new JDBCConnectionManager();
		manager.setDriverClass(properties.getDriverClass());
		manager.setUrl(properties.getUrl());
		manager.setUsername(properties.getUsername());
		manager.setPassword(properties.getPassword());
		return manager;
	}
}