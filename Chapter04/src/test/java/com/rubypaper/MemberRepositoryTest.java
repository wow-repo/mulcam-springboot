package com.rubypaper;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.rubypaper.domain.Member;
import com.rubypaper.persistence.MemberRepository;

@SpringBootTest
public class MemberRepositoryTest {
	@Autowired
	private MemberRepository memberRepository;
	
	@Test
	public void insertMemberTest() {
		// EntityManager 획독이나 Transaction 관련 작업은 BoardRepository가 자동으로 처리해준다.
		Member member = new Member();
		member.setId("guest");
		member.setPassword("guest123");
		member.setName("손님");
		member.setRole("USER");
		memberRepository.save(member); // save() == persist()
	}
}
