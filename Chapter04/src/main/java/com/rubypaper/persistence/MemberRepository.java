package com.rubypaper.persistence;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.rubypaper.domain.Member;

@Repository
public interface MemberRepository extends CrudRepository<Member, String>{

}
