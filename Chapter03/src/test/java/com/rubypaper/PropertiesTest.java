package com.rubypaper;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;

import com.rubypaper.persistence.BoardDAO;
import com.rubypaper.service.BoardServiceImpl;

@SpringBootTest(properties = {"author.name=Gurum", "author.age=25", "author.nation=KOREA"},
classes = { BoardServiceImpl.class, BoardDAO.class})
class PropertiesTest {

//	@Value("${author.name}")
//	private String name;
//	
//	@Value("${author.age}")
//	private int age;
	
	@Autowired
	private Environment env;
	
	@Test
	void testMethod() {
        System.out.println("이름 : " + env.getProperty("author.name"));
        System.out.println("나이 : " + env.getProperty("author.age"));
        System.out.println("국가 : " + env.getProperty("author.nation"));
		assertTrue(true);
	}

}
