package com.rubypaper;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

// 1. @WebMvcTest는 @Controller나 @RestController 객체만 생성한다. 
// 2. 서블릿 컨테이너(MockMvc) 객체를 Mocking한다.
@WebMvcTest
class BoardControllerMockTest {
	@Autowired
	private MockMvc mockMvc; // MockMvc 객체가 바로 서블릿 컨테이너(== 서버)다

	@Test
	public void testHello() throws Exception {
		// 서블릿 컨테이너에 url 요청을 전송한다. (like http://localhost:8088/hello.do?name=Gurum)
		mockMvc.perform(get("/hello.do").param("name", "Gurum"))
		
		// 요청에 대한 응답 결과를 검증한다.
		.andExpect(MockMvcResultMatchers.status().isOk()) // 응답 상태코드가 200(OK)인지 확인
		.andExpect(MockMvcResultMatchers.content().string("Hello : Gurum")) //응답 결과 메시지가 Hello : Gurum 인지 확인
		.andDo(print()); // http 요청과 응답을 콘솔에 출력한다.
	}

}
