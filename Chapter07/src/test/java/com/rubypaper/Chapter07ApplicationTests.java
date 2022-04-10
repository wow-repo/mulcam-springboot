package com.rubypaper;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import com.rubypaper.domain.Member;
import com.rubypaper.domain.Role;
import com.rubypaper.persistence.MemberRepository;

@SpringBootTest
class Chapter07ApplicationTests {

	@Autowired
	private MemberRepository memberRepository;

	@Autowired
	private PasswordEncoder encoder;

	@Test
	void contextLoads() {
		Member member = new Member();
		member.setUsername("sss");
		member.setPassword(encoder.encode("sss222"));
		member.setName("손님");
		member.setRole(Role.MEMBER);
		member.setEnabled(true);
		memberRepository.save(member);
	}
}