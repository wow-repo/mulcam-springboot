package com.test;

import org.springframework.stereotype.Service;

@Service("test")
public class TestBean {

	public TestBean() {
		System.out.println("===> TestBean 생성");
	}
}