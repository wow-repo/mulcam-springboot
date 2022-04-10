package com.rubypaper.persistence;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.rubypaper.domain.Board;

@Repository
public interface BoardRepository extends JpaRepository<Board, Long>{
	//쿼리 메소드 선언
	List<Board> findByTitle(String title);
	
	List<Board> findByTitleContaining(String title);
	List<Board> findByTitleContainingOrContentContaining(String title, String content);
	List<Board> findByTitleContainingOrderBySeqDesc(String title);
	
	// 페이징 처리
	Page<Board> findByTitleContaining(String title, Pageable pageable);
	
	// @Query jpql사용하기
	@Query("SELECT b FROM Board b WHERE b.title LIKE %?1% ORDER BY b.seq DESC")
	List<Board> getBoardList1(String searchKeyword);
	
	// @Query jpql 변수명 사용하기
	@Query("SELECT b.title, b.content FROM Board b WHERE b.title LIKE %:keyword% ORDER BY b.seq DESC")
	List<Object[]> getBoardList2(@Param("keyword") String searchKeyword);
	
	// native query 사용하기
//	 @Query(value = "SELECT SEQ, TITLE, WRITER, CONTENT, REGDATE FROM BOARD WHERE TITLE LIKE '%'||:keyword||'%' ORDER BY SEQ DESC", nativeQuery = true)
//	 List<Object[]> getBoardList3(@Param("keyword") String searchKeyword);
}
