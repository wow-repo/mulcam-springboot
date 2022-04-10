package com.rubypaper;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.rubypaper.domain.Board;
import com.rubypaper.domain.Member;
import com.rubypaper.persistence.BoardRepository;
import com.rubypaper.persistence.MemberRepository;
@SpringBootTest
class RelationMappingTest {
    
    @Autowired
    private BoardRepository boardRepository;
    
    @Autowired
    private MemberRepository memberRepository;
    
//    @BeforeEach
    public void initData() {
        Member member1 = new Member();
        member1.setId("member1");
        member1.setPassword("member111");
        member1.setName("둘리");
        member1.setRole("USER");
        memberRepository.save(member1);
        
        Member member2 = new Member();
        member2.setId("member2");
        member2.setPassword("member222");
        member2.setName("도우너");
        member2.setRole("ADMIN");
        memberRepository.save(member2);
        
        for (int i = 1; i <= 3; i++) {
            Board board = new Board();
            board.setTitle("둘리가 등록한 게시글 제목 " + i);
            board.setContent("둘리가 등록한 게시글 내용 " + i);
            board.setRegDate(new Date());
            board.setCnt(0L);
            // Board 엔티티를  save 하기 전에 연관된 객체의 참조 정보를 설정한다.
            board.setMember(member1);
            boardRepository.save(board);
        }
        
        for (int i = 1; i <= 3; i++) {
            Board board = new Board();
            board.setTitle("도우너가 등록한 게시글 제목 " + i);
            board.setContent("도우너가 등록한 게시글 내용 " + i);
            board.setRegDate(new Date());
            board.setCnt(0L);
            // Board 엔티티를  save 하기 전에 연관된 객체의 참조 정보를 설정한다.
            board.setMember(member2);
            boardRepository.save(board);
        }
    }
    
//    @Test //단방향 테스트
    void testManyToOne() {
        // Board Entity만 검색한다.
    	Board board = boardRepository.findById(5L).get();
    	
    	System.out.println("[" + board.getSeq() + "번 게시글 상세 정보]");
    	System.out.println("제목 : " + board.getTitle());
    	System.out.println("내용 : " + board.getContent());
    	System.out.println("작성자 (Member) : " + board.getMember().getName());
    	System.out.println("권한 (Member) : " + board.getMember().getRole());
    }
    
//    @Test //양방향 테스트
    void testOneToMany() {
        // Member Entity만 검색한다.
    	Member member = memberRepository.findById("member1").get();
    	
    	System.out.println("[" + member.getName() + "님이 등록한 게시글 정보]");
    	List<Board> boardList = member.getBoardList();
    	for (Board board : boardList) {
    		System.out.println(board.toString());
    	}
    }
    
    @Test //연관 객체 삭제
    void testCascade() {
    	memberRepository.deleteById("member1");
    }
}
