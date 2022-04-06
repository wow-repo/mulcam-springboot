package com.rubypaper.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Data;

@Data
@Entity // Board를 Entity로 선언
@SequenceGenerator(name = "BOARD_SEQ_GENERATOR", 
					sequenceName = "BOARD_SEQUENCE", 
					initialValue = 1, 
					allocationSize = 1)
public class Board {
	@Id // 식별자 변수(BOARD 테이블의 PK 컬럼과 매핑되는 변수)
	@GeneratedValue(strategy = GenerationType.SEQUENCE,
					generator = "BOARD_SEQ_GENERATOR") // seq 객체가 생성된 후, 자동으로 장가되는 유일한 숫자 값을 할당한다.
    private Long seq;
    private String title;
    private String writer;
    private String content;
    @Temporal(TemporalType.DATE)
    private Date regDate = new Date();
    private Long cnt = 0L;
}
