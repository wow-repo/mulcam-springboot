package com.rubypaper;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import com.rubypaper.domain.Board;
import com.rubypaper.persistence.BoardRepository;

@SpringBootTest
class QueryMethodTest {
	@Autowired
	private BoardRepository boardRepository;
	
	@BeforeEach // @Test가 붙은 메소드가 수행되기 전에 무조건 먼저 실행된다.
	void initData() {
		for (int i=1; i<= 200; i++) {
			Board board = new Board();
            board.setTitle("테스트 제목 " + i);
            board.setWriter("테스터");
            board.setContent("테스트 내용 " + i);
			
			boardRepository.save(board);
		}
	}
	
//    @Test
    public void testQueryMethod() {
        List<Board> boardList = boardRepository.findByTitle("테스트 제목 10");
        System.out.println("[ 검색 결과 ]");
        for (Board board : boardList) {
            System.out.println("---> " + board.toString());
        }
    }
    
//	@Test
	public void testJpql() {
		List<Board> boardList = boardRepository.getBoardList1("제목 10");
		System.out.println("[ 검색 결과 ]");
		for (Board board : boardList) {
			System.out.println("---> " + board.toString());
		}
	}
	
//	@Test
	public void testJpql2() {
		List<Object[]> boardList = boardRepository.getBoardList2("제목 10");
		System.out.println("[ 검색 결과 ]");
		for (Object[] board : boardList) {
			System.out.println("---> " + Arrays.toString(board));
		}
	}
	
	@Test
	public void testNativeSql() {
		List<Object[]> boardList = boardRepository.getBoardList3("제목 10");
		System.out.println("[ 검색 결과 ]");
		for (Object[] board : boardList) {
			System.out.println("---> " + Arrays.toString(board));
		}
	}
    
//    @Test
    public void testContaningQueryMethod() {
//        List<Board> boardList = boardRepository.findByTitleContaining("테스트 제목 10");
//        List<Board> boardList = boardRepository.findByTitleContainingOrContentContaining("제목 10", "내용 20");
        List<Board> boardList = boardRepository.findByTitleContainingOrderBySeqDesc("제목 10");
        
        System.out.println("[ 검색 결과 ]");
        for (Board board : boardList) {
            System.out.println("---> " + board.toString());
        }
    }
    
//    @Test
    public void testPagingQueryMethod() {
    	// 페이징 처리를 위한 Pageable 설정
    	// 1페이지에 해당하는 5개의 데이터를 검색한다.
    	// 2페이지는 PageRequest.of(1, 5, Sort.Direction.DESC, "seq")
    	Pageable paging = PageRequest.of(0, 5, Sort.Direction.DESC, "seq");
        Page<Board> pageInfo = boardRepository.findByTitleContaining("제목 10", paging);
        
        System.out.println("검색된 데이터의 수 : " + pageInfo.getTotalElements());
        System.out.println("전체 페이지 수 : " + pageInfo.getTotalPages());
        System.out.println("한 페이지에 출력할 데이터의 수 : " + pageInfo.getSize());
        
        List<Board> boardList = pageInfo.getContent();
        System.out.println("[ 검색 결과 ]");
        for (Board board : boardList) {
            System.out.println("---> " + board.toString());
        }
        
        if (pageInfo.hasPrevious()) {
        	System.out.println("이전 페이지 : " + pageInfo.previousPageable());
        }
        System.out.println("현재 페이지 : " + pageInfo.getPageable().toString());
        
        if (pageInfo.hasNext()) {
        	System.out.println("다음 페이지 : " + pageInfo.nextPageable());
        }
    }
}
