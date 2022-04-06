package com.rubypaper.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Service;

@Service
public class LoggingRunner implements ApplicationRunner {
	private Logger LOGGER = LoggerFactory.getLogger(LoggingRunner.class);

	@Override
	public void run(ApplicationArguments args) throws Exception {
		LOGGER.trace("TRACE 레벨 로그");
		LOGGER.debug("DEGUB 레벨 로그");
		LOGGER.info("INFO 레벨 로그");
		LOGGER.warn("WARN 레벨 로그");
		LOGGER.error("ERROR 레벨 로그");
	}
}