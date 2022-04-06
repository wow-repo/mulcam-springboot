package com.rubypaper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;

import com.rubypaper.domain.BoardVO;

//@SpringBootTest는 @Service, @Repository, @Controller, @RestController 객체를 모두 생성한다. 
//@SpringBootTest는 서블릿 컨테이너를 모킹한다.(실제 서버가 구동되지 않는다.)
//실제 서버를 구동하고 싶으면 @SpringBootTest(webEnvironment = WebEnvironment.DEFINED_PORT)
//                   @SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@SpringBootTest(webEnvironment = WebEnvironment.DEFINED_PORT)
class BoardControllerTest {
	@Autowired
	private TestRestTemplate restTemplate; // TestRestTemplate은 브라우저를 대체한다.

	@Test
	public void testHello() throws Exception {
		// 브라우저를 통해 실제 서버에 요청을 전달한다.
		String result = restTemplate.getForObject("/hello.do?name=Gurum", String.class);
		
		// 응답 결과를검증한다.
		assertEquals("Hello : Gurum", result);
	}

	@Test
	public void testGetBoard() throws Exception {
		BoardVO board = restTemplate.getForObject("/getBoard.do", BoardVO.class);
		assertNotNull(board); // 리턴된 객체가 있는지 확인한다.
		assertEquals("테스터", board.getWriter()); // 리턴된 객체의 writer 변수 값이 "테스터"인지 확인한다.
	}
	
	@Test
	public void testGetBoardList() throws Exception {
		List<BoardVO> listVO = restTemplate.getForObject("/getBoardList.do", List.class);
		
		assertEquals(2, listVO.size());
	}
}
