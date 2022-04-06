package com.rubypaper;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.rubypaper.domain.Board;

@SpringBootTest
class Chapter04ApplicationTests {

//	@Test
	void jpaInsertTest() {
		// 1. EntityManagerFactory 생성
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("Chapter04");
		
		// 2. EntityManager 생성
		EntityManager em = emf.createEntityManager();
		
		// 3. Transaction 획득
		EntityTransaction tx = em.getTransaction();
		
		// INSERT 처리
		Board board = new Board();
		board.setTitle("JPA 테스트");
		board.setWriter("테스터");
		board.setContent("JPA 테스트....");
		
		tx.begin(); // transaction 시작
		em.persist(board); // H2 데이터베이스 BOARD 테이블에 INSERT가 전송된다.
		tx.commit(); // transaction 종료
		
        Board findBoard = em.find(Board.class, 1L);
        System.out.println("검색 결과 : " + findBoard.toString());
        
		// 자원 해제
		em.clear();
		emf.close();
	}

//	@Test
	void jpaSelectTest() {
		// 1. EntityManagerFactory 생성
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("Chapter04");
		
		// 2. EntityManager 생성
		EntityManager em = emf.createEntityManager();
		
		// SELECT 처리
		Board board = em.find(Board.class, 1L);
		
		System.out.println(" 상세 조회 결과---" + board.toString());
		
		// 자원 해제
		em.clear();
		emf.close();
	}
	
    @Test
    void jpaListTest() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("Chapter04");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        
        tx.begin(); // 트랜잭션 시작
        for (int i = 0; i < 10; i++) {
            Board board = new Board();
            board.setTitle("JPA 테스트");
            board.setWriter("테스터");
            board.setContent("JPA 테스트.....");
            em.persist(board);
        }
        tx.commit();// 트랜잭션 종료
        
        // 목록 조회 처리
        String jpql = "select b from Board b order by b.seq";
        
        List<Board> boardList = em.createQuery(jpql).getResultList();
        for (Board row : boardList) {
            System.out.println("---> " + row.toString());
        }
        
        em.close();
        emf.close();        
    }
	
//	@Test
	void jpaUpdateTest() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("Chapter04");
		
		EntityManager em = emf.createEntityManager();
		
		EntityTransaction tx = em.getTransaction();
		
		// UPDATE 처리
		tx.begin();
		
		// 상세 조회한 Board Entity를 수정한다.
		Board board = em.find(Board.class, 1L);
		board.setTitle("---제목 수정");
		board.setContent(">>>>>>내용 수정");
		
		tx.commit();
		
		// 자원 해제
		em.clear();
		emf.close();
	}
	
//	@Test
	void jpaDeleteTest() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("Chapter04");
		
		EntityManager em = emf.createEntityManager();
		
		EntityTransaction tx = em.getTransaction();
		
		// DELETE 처리
		tx.begin();
		
		// 상세 조회한 Board Entity를 삭제한다.
		Board board = em.find(Board.class, 1L);
		em.remove(board);
		
		tx.commit();
		
		// 자원 해제
		em.clear();
		emf.close();
	}
}
