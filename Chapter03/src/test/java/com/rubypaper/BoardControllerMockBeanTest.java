package com.rubypaper;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.rubypaper.service.BoardService;


@SpringBootTest(webEnvironment = WebEnvironment.MOCK)
@AutoConfigureMockMvc
class BoardControllerMockBeanTest {
	@Autowired
	private MockMvc mockMvc;
	
	// 비즈니스 객체를 Mocking하면 실제 비즈니스 클래스(BoardServiceImpl)를 작성하지 않고도 테스트가 가능하다.
	@MockBean // BoardService 타입의 객체를 Mocking한 후 MockUp까지 처리한다.
	private BoardService boardService;

	@Test
	public void testHello() throws Exception {
		when(boardService.hello("Gurum")).thenReturn("Hello : Gurum");
		
		mockMvc.perform(get("/hello.do").param("name", "Gurum"))
		
		.andExpect(MockMvcResultMatchers.status().isOk())
		.andExpect(MockMvcResultMatchers.content().string("Hello : Gurum"));
	}

    @Test
    public void testGetBoard() throws Exception {
        Map<String, Object> boardResult = new HashMap<String, Object>();
        boardResult.put("SEQ", 1);
        boardResult.put("WRITER", "홍kill동");
        
        when(boardService.getBoard(1)).thenReturn(boardResult);
        
        mockMvc.perform(get("/getBoard.do").param("seq", "1"))        
        .andExpect(status().isOk()) 
        .andExpect(jsonPath("$.WRITER").isNotEmpty())
        .andExpect(jsonPath("$.WRITER").value("홍kill동")); 
    }
    
    @Test
    public void testGetBoardList() throws Exception {
    	// getBoardList() 메소드가 리턴할 결과데이터 세팅
    	List<Map<String, Object>> boardList = new ArrayList<Map<String, Object>>();
    	
    	for (int i = 1; i <= 5; i++) {
            Map<String, Object> boardResult = new HashMap<String, Object>();
            boardResult.put("SEQ", i);
            boardResult.put("WRITER", "홀kill동" + i);
            boardList.add(boardResult);
    	}
        
        when(boardService.getBoardList()).thenReturn(boardList);
        
        mockMvc.perform(get("/getBoardList.do"))        
        .andExpect(status().isOk()) 
        .andExpect(jsonPath("$", Matchers.hasSize(5)));
    }
}
