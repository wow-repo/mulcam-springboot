package com.rubypaper.jdbc.util;

import lombok.Data;

@Data
public class JDBCConnectionManager {

	public JDBCConnectionManager() {
		System.out.println("===> JDBCConnectionManager 생성");
	}

	private String driverClass;
	private String url;
	private String username;
	private String password;
}