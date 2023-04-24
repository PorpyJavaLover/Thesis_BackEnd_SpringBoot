package com.thesis.scheduling.modellevel.repository;

import java.util.Collection;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.thesis.scheduling.modellevel.entity.Member;
import com.thesis.scheduling.modellevel.entity.Organization;

@Repository
public interface MemberRepository extends CrudRepository<Member, String>{

    @Override
    Collection<Member> findAll();

    Collection<Member> findAllByOrganizationId(Organization organizationId); 
	
    Optional<Member> findByMemberId(int memberId);
    
    Optional<Member> findByUsername(String username);
	
    boolean existsByUsername(String username);
}
