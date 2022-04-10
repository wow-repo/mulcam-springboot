package com.rubypaper.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class Board {
	@Id @GeneratedValue
    private Long seq;
    private String title;
    
    @Column(updatable=false)
    private String writer;
    private String content;
    
    @Column(insertable = false, updatable = false, columnDefinition = "date default sysdate")
    private Date createDate;
    
    @Column(insertable = false, updatable = false, columnDefinition = "number(5) default 0")
    private Long cnt;
}
