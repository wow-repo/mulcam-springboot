package com.rubypaper.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.rubypaper.domain.Member;
import com.rubypaper.persistence.MemberRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService{
	@Autowired
	private MemberRepository memberRepository;
	
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Member member = memberRepository.findById(username).get();
        
        if(member == null) {
            throw new UsernameNotFoundException(username + "에 해당하는 회원 없음");
        } 
        
        return new User(member.getUsername(), member.getPassword(), 
        AuthorityUtils.createAuthorityList("ROLE_" + member.getRole().toString()));
    }
}
