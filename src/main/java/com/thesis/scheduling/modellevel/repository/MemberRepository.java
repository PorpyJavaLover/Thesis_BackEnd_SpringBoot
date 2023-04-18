package com.thesis.scheduling.modellevel.repository;

import java.util.Collection;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.thesis.scheduling.modellevel.entity.Member;

@Repository
public interface MemberRepository extends CrudRepository<Member, String>{

    @Override
    Collection<Member> findAll();
	
    Optional<Member> findByMemberId(int gId);
    
    Optional<Member> findByUsername(String username);
	
    boolean existsByUsername(String username);
}
