package com.thesis.scheduling.modellevel.service;

import java.util.Collection;
import java.util.Objects;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.thesis.scheduling.businesslevel.exception.BaseException;
import com.thesis.scheduling.businesslevel.exception.MemberException;
import com.thesis.scheduling.modellevel.entity.Member;
import com.thesis.scheduling.modellevel.entity.Organization;
import com.thesis.scheduling.modellevel.entity.Title;
import com.thesis.scheduling.modellevel.model.M_Member_Register_Response;
import com.thesis.scheduling.modellevel.repository.MemberRepository;

@Service
public class MemberService {

	private final MemberRepository repository;

	public MemberService(MemberRepository repository) {
		this.repository = repository;
	}

	// GET
	public long countMember() {
		return repository.count();
	}

	public Collection<Member> findAll() {
		return repository.findAll();
	}

	public Collection<Member> findAllBySOrganizationId(Organization organizationId) {
		return repository.findAllByOrganizationId(organizationId);
	}


	public Optional<Member> findByUsername(String username) {
		return repository.findByUsername(username);
	}

	public Optional<Member> findByMemberId(int mId) {
		return repository.findByMemberId(mId);
	}
	
	public boolean matchPassword(String requestPassword, String realPassword) {
		return realPassword.equals(requestPassword);
	}

	
	// SET
	public Member create(Title title, String th_first_name, String th_last_name, String username, String password)
			throws BaseException {

		// validate
		if (Objects.isNull(title)) {
			throw MemberException.createTitleNull();
		}

		if (Objects.isNull(th_first_name)) {
			throw MemberException.createTHFirstNameNull();
		}

		if (Objects.isNull(th_last_name)) {
			throw MemberException.createTHLastNameNull();
		}

		if (Objects.isNull(username)) {
			throw MemberException.createUsernameNull();
		}

		if (Objects.isNull(password)) {
			throw MemberException.createPasswordNull();
		}

		// verify
		if (repository.existsByUsername(username)) {
			throw MemberException.createUsernameDuplicated();
		}

		// save
		Member entity = new Member();
		entity.setTitleId(title);
		entity.setThFirstName(th_first_name);
		entity.setThLastName(th_last_name);
		entity.setUsername(username);
		entity.setPassword(password);

		return repository.save(entity);
	}
	

	// DELETE

}
